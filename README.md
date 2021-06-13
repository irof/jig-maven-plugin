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
                <version>0.3</version>
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
$ mvn jig:jig
```

