RELEASE
------------------------------------------------------------

手作業で行います。

前提: Central Publisher Portalのトークンは生成済みで`settings.xml`に設定済み。

```sh
./mvnw clean deploy
```

- GPGのパスが求められるので入力する。
- 検証済みになるとSUCCESSで終了するが、この時点では公開されていない。

- https://central.sonatype.com/publishing/deployments を開く。
- `Publish` ボタンを押す。
- しばらく待てば完了。