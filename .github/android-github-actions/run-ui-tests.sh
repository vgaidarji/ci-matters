#!/usr/bin/env bash

set -eu

# manually install APK's and run UI tests
./gradlew assembleDebug -PfabricApiKey=$FABRIC_API_KEY -PfabricApiSecret=$FABRIC_API_SECRET
./gradlew assembleDebugAndroidTest -PfabricApiKey=$FABRIC_API_KEY -PfabricApiSecret=$FABRIC_API_SECRET
$ANDROID_HOME/platform-tools/adb install app/build/outputs/apk/app-debug.apk
$ANDROID_HOME/platform-tools/adb install app/build/outputs/apk/app-debug-androidTest.apk
$ANDROID_HOME/platform-tools/adb shell am instrument -w -r -e debug false -e class com.vgaidarji.cimatters.LoginActivityTest com.vgaidarji.cimatters.test/android.support.test.runner.AndroidJUnitRunner
