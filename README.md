jig-maven-plugin
============================================================

[![](https://maven-badges.herokuapp.com/maven-central/com.github.irof/jig-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.irof/jig-maven-plugin/)

[JIG](https://github.com/dddjava/jig)

# usage

## 簡易

```
mvn com.github.irof:jig-maven-plugin:jig
```

`pom.xml` には何も書かなくていいです。

## 通常

プラグインのバージョンとか設定を `/project/build/plugins` に追加

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
                <version>2021.11.4</version>
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

# 設定

|対象|configurationタグ名|プロパティ名|
|----|----|----|
|出力対象JIGドキュメント| `documentTypes` | `jig.document.types` |
|ドメインのパターン| `domainPattern` | `jig.pattern.domain` |

ともに任意。指定なしの場合はJIGのデフォルトに従う。
