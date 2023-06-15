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

「とりあえずパッケージ関連図を出したい」だとこんな感じです。

```
mvn clean compile com.github.irof:jig-maven-plugin:jig -D"jig.pattern.domain=.*" -D"jig.document.types=PackageRelationDiagram"
```

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
                <version>2023.6.3</version>
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
|ドメインのパターン| `domainPattern` | `jig.pattern.domain` |
|TBD|||

ともに任意。指定なしの場合はJIGのデフォルトに従う。

