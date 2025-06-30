package com.github.irof.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.dddjava.jig.JigExecutor;
import org.dddjava.jig.domain.model.documents.documentformat.JigDiagramFormat;
import org.dddjava.jig.domain.model.documents.documentformat.JigDocument;
import org.dddjava.jig.domain.model.sources.SourceBasePath;
import org.dddjava.jig.domain.model.sources.SourceBasePaths;
import org.dddjava.jig.infrastructure.configuration.Configuration;
import org.dddjava.jig.infrastructure.configuration.JigProperties;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mojo(name = "jig")
public class JigMojo extends AbstractMojo {

    /**
     * SuperPOMで <code>${project.build.directory}</code> は <code>${project.basedir}/target</code> になる。
     */
    @Parameter(defaultValue = "${project.build.directory}/jig", readonly = true, required = true)
    private File targetDirectory;

    /**
     * マルチモジュールの場合の対象モジュール。
     * シングルモジュールプロジェクトでは空のままにする。
     * マルチモジュールプロジェクトの場合、デフォルトですべてのモジュールを対象にするが、限定したい場合はここで指定する。
     */
    @Parameter(defaultValue = "${project.modules}")
    private String[] modules;

    /**
     * SuperPOMで <code>${project.build.outputDirectory}</code> は <code>${project.build.directory}/classes</code> になる。
     * 基本的に設定不要だが、マルチモジュールプロジェクトなどでoutputDirectoryが異なる場合や出力ディレクトリが複数ある場合に設定する。
     */
    @Parameter(defaultValue = "${project.build.outputDirectory}", required = true)
    private File[] classesDirectories;

    /**
     * SuperPOMで <code>${project.build.sourceDirectory}</code> は <code>${project.basedir}/src/main/java</code>
     * 基本的に設定不要だが、マルチモジュールプロジェクトなどでsourceDirectoryが異なる場合やソースディレクトリが複数ある場合に設定する。
     */
    @Parameter(defaultValue = "${project.build.sourceDirectory}", required = true)
    private File[] sourceDirectories;

    /**
     * マルチモジュールプロジェクトのパスを解釈するために取得しておく
     */
    @Parameter(defaultValue = "${project.basedir}", readonly = true)
    private File baseDir;

    /**
     * 初期値から変更されているかを確認するために取得しておく
     */
    @Parameter(defaultValue = "${project.build.outputDirectory}", readonly = true)
    private File defaultClassesDirectory;

    /**
     * 初期値から変更されているかを確認するために取得しておく
     */
    @Parameter(defaultValue = "${project.build.sourceDirectory}", readonly = true)
    private File defaultSourcesDirectory;

    @Parameter(property = "jig.document.types")
    private String[] documentTypes;

    @Parameter(property = "jig.pattern.domain")
    private String domainPattern;

    @Parameter(defaultValue = "true")
    private boolean transitiveReduction;

    public void execute() {
        JigExecutor.execute(configuration(), sourcePaths());
    }

    private Configuration configuration() {
        JigProperties properties = new JigProperties(
                documentTypes(),
                domainPattern,
                targetDirectory.toPath(),
                JigDiagramFormat.SVG,
                transitiveReduction
        );
        return new Configuration(properties);
    }

    private List<JigDocument> documentTypes() {
        if (documentTypes == null || documentTypes.length == 0) {
            return JigDocument.canonical();
        }
        return Arrays.stream(documentTypes)
                .map(JigDocument::valueOf)
                .collect(Collectors.toList());
    }

    private SourceBasePaths sourcePaths() {
        // シングルプロジェクト
        if (modules.length == 0) {
            SourceBasePath binarySourcePaths = new SourceBasePath(
                    Arrays.stream(classesDirectories).map(File::toPath).collect(Collectors.toList()));
            SourceBasePath codeSourcePaths = new SourceBasePath(Arrays.stream(sourceDirectories)
                    .map(File::toPath).collect(Collectors.toList()));
            return new SourceBasePaths(binarySourcePaths, codeSourcePaths);
        }

        // modulesがある場合はマルチモジュールプロジェクトとして処理する
        return new SourceBasePaths(
                new SourceBasePath(getPaths(classesDirectories, defaultClassesDirectory)),
                new SourceBasePath(getPaths(sourceDirectories, defaultSourcesDirectory)));
    }

    private List<Path> getPaths(File[] specifiedDirectories, File defaultDirectory) {
        Path basePath = baseDir.toPath();
        if (specifiedDirectories.length == 1 && specifiedDirectories[0].equals(defaultDirectory)) {
            // ディレクトリ指定なし
            Path classesDirectory = specifiedDirectories[0].toPath();
            Path relativeClassesDirectory = basePath.relativize(classesDirectory);
            return Arrays.stream(modules)
                    .map(module -> basePath.resolve(module).resolve(relativeClassesDirectory))
                    .collect(Collectors.toList());
        }
        // ディレクトリを指定している場合はそのまま適用する
        return Arrays.stream(specifiedDirectories)
                .map(File::toPath)
                .collect(Collectors.toList());
    }
}