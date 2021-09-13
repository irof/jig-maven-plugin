リリース
==================================================

## バージョン指定

- `jig.version` でJIGのバージョンを指定。
- `jig-maven-pugin.version` でリリースバージョンを指定。未指定の場合は `jig.version` + `-SNAPSHOT` になる。

## リリース

`maven-gpg-plugin` での署名が必要。
`gpg: signing failed: Inappropriate ioctl for device` が出る場合、`GPG_TTY`を設定する。

```zsh
export GPG_TTY=$(tty)
mvn clean deploy
```
