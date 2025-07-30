jig-maven-plugin
============================================================

[![](https://maven-badges.herokuapp.com/maven-central/com.github.irof/jig-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.irof/jig-maven-plugin/)

[JIG](https://github.com/dddjava/jig)

# usage

## 簡易

```
mvn org.dddjava.jig:jig-maven-plugin:jig
```

`pom.xml` には何も書かなくていいです。

「とりあえずパッケージ関連図を出したい」だとこんな感じです。

```
mvn clean compile org.dddjava.jig:jig-maven-plugin:jig -D"jig.pattern.domain=.*" -D"jig.document.types=PackageRelationDiagram"
```

## 通常

```pom.xml
<project>
    ...
    <build>
        ...
        <plugins>
            ...
            <plugin>
                <groupId>org.dddjava.jig</groupId>
                <artifactId>jig-maven-plugin</artifactId>
                <version>${jig-maven-plugin.version}</version>
                <configuration>
                    <domainPattern>.*</domainPattern>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

実行

```
mvn jig:jig
```

## マルチモジュールプロジェクト対応

`2023.2.1.1` 以降、マルチモジュールプロジェクトに対応しています。
使用例は https://github.com/dddjava/jig-sample/tree/main/sample-maven-multi を参照してください。

# 設定

|対象|configurationタグ名|プロパティ名|
|----|----|----|
|出力対象JIGドキュメント| `documentTypes` | `jig.document.types` |
|出力除外JIGドキュメント| `excludeDocumentTypes` |  |
|ドメインのパターン| `domainPattern` | `jig.pattern.domain` |

任意。指定なしの場合はJIGのデフォルトに従う。

