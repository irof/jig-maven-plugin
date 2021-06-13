package com.github.irof.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.dddjava.jig.application.service.JigSourceReadService;
import org.dddjava.jig.domain.model.documents.documentformat.JigDocument;
import org.dddjava.jig.domain.model.sources.file.SourcePaths;
import org.dddjava.jig.domain.model.sources.file.binary.BinarySourcePaths;
import org.dddjava.jig.domain.model.sources.file.text.CodeSourcePaths;
import org.dddjava.jig.domain.model.sources.jigreader.SourceCodeAliasReader;
import org.dddjava.jig.infrastructure.configuration.Configuration;
import org.dddjava.jig.infrastructure.configuration.JigProperties;
import org.dddjava.jig.infrastructure.javaparser.JavaparserAliasReader;

import java.io.File;
import java.util.Collections;

@Mojo(name = "jig")
public class JigMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.outputDirectory}", readonly = true, required = true)
    private File targetClassesDirectory;

    @Parameter(defaultValue = "${project.build.directory}", readonly = true, required = true)
    private File targetDirectory;

    @Parameter(defaultValue = "${project.build.sourceDirectory}", readonly = true, required = true)
    private File sourceDirectory;

    @Parameter(defaultValue = ".+\\.domain\\..+", readonly = true, required = true)
    private String domainPattern;

    public void execute() {
        JigProperties properties = new JigProperties(
                JigDocument.canonical(),
                domainPattern,
                targetDirectory.toPath().resolve("jig")
        );
        Configuration configuration = new Configuration(properties, new SourceCodeAliasReader(new JavaparserAliasReader()));

        JigSourceReadService sourceReadService = configuration.implementationService();
        sourceReadService.readSourceFromPaths(new SourcePaths(
                new BinarySourcePaths(Collections.singleton(targetClassesDirectory.toPath())),
                new CodeSourcePaths(Collections.singleton(sourceDirectory.toPath()))
        ));

        configuration.documentHandlers().handleJigDocuments(JigDocument.canonical(), configuration.outputDirectory());
    }
}