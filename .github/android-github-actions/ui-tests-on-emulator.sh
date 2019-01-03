#!/usr/bin/env bash

set -eu

# start android emulator
START=`date +%s` > /dev/null

echo no | $ANDROID_HOME/tools/android create avd --force -n test -t android-21 --abi default/armeabi-v7a
$ANDROID_HOME/tools/android list avd
$ANDROID_HOME/tools/emulator64-arm -avd test -no-window -no-boot-anim -no-audio -verbose &
wait-for-emulator
unlock-emulator-screen

DURATION=$(( `date +%s` - START )) > /dev/null
echo "Android Emulator started after $DURATION seconds."

# emulator isn't ready yet, wait 1 min more
# prevents APK installation error
sleep 60

# manually install APK's and run UI tests
./gradlew assembleDebug -PfabricApiKey=$FABRIC_API_KEY -PfabricApiSecret=$FABRIC_API_SECRET
./gradlew assembleDebugAndroidTest -PfabricApiKey=$FABRIC_API_KEY -PfabricApiSecret=$FABRIC_API_SECRET
$ANDROID_HOME/platform-tools/adb install app/build/outputs/apk/app-debug.apk
$ANDROID_HOME/platform-tools/adb install app/build/outputs/apk/app-debug-androidTest.apk
$ANDROID_HOME/platform-tools/adb shell am instrument -w -r -e debug false -e class com.vgaidarji.cimatters.LoginActivityTest com.vgaidarji.cimatters.test/android.support.test.runner.AndroidJUnitRunner

kill-running-emulators
