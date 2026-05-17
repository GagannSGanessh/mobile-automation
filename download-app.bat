@echo off
mkdir apps
curl -L -o apps/wdio-demo.apk https://github.com/webdriverio/native-demo-app/releases/download/v2.2.0/android.wdio.native.app.v2.2.0.apk
echo APK downloaded successfully!