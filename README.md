jig-maven-plugin
============================================================

[![](https://maven-badges.herokuapp.com/maven-central/com.github.irof/jig-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.irof/jig-maven-plugin/)

[JIG](https://github.com/dddjava/jig)

# usage

```pom.xml
<project>
    ...
    <build>
        ...
        <plugins>
            ...
            <plugin>
                <groupId>com.github.irof</groupId>
                <artifactId>jig-maven-plugin</artifactId>
                <version>0.5</version>
                <configuration>
                    <documentTypes>
                        <documentType>PackageRelationDiagram</documentType>
                    </documentTypes>
                    <domainPattern>.*</domainPattern>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

```
mvn jig:jig
```

or

```
mvn com.github.irof:jig-maven-plugin:jig
```

# 設定

|対象|configurationタグ名|プロパティ名|
|----|----|----|
|出力対象JIGドキュメント| `documentTypes` | `jig.document.types` |
|ドメインのパターン| `domainPattern` | `jig.pattern.domain` |

ともに任意。指定なしの場合はJIGのデフォルトに従う。
