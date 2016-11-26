#!/usr/bin/env bash

# get rid of running emulators
$ANDROID_HOME/platform-tools/adb devices | grep emulator | cut -f1 | while read line; do $ANDROID_HOME/platform-tools/adb -s $line emu kill; done

# start android emulator
START=$SECONDS > /dev/null
echo no | $ANDROID_HOME/tools/android create avd --force -n teamcity -t android-21 --abi default/armeabi-v7a
$ANDROID_HOME/tools/emulator -engine classic -avd teamcity -no-window -no-boot-anim -noaudio -verbose &

# Wait for Android to finish booting
# (would be great to set max waiting time to avoid infinite waiting in error case)
# alternatively use wait_for_emulator.sh
WAIT_CMD="$ANDROID_HOME/platform-tools/adb wait-for-device shell getprop init.svc.bootanim"
until $WAIT_CMD | grep -m 1 stopped; do
  echo "Waiting..." > /dev/null
  sleep 1
done

# Unlock the Lock Screen
$ANDROID_HOME/platform-tools/adb shell input keyevent 82 &
$ANDROID_HOME/platform-tools/adb shell input keyevent 4 &

duration=$(( SECONDS - START )) > /dev/null
echo "Android Emulator started after $duration seconds."

# emulator isn't ready yet, wait 1 min more
# prevents APK installation error
sleep 60

# manually install APK's and run instrumentation tests
./gradlew assembleDebug -PfabricApiKey=$FABRIC_API_KEY -PfabricApiSecret=$FABRIC_API_SECRET
./gradlew assembleDebugAndroidTest -PfabricApiKey=$FABRIC_API_KEY -PfabricApiSecret=$FABRIC_API_SECRET
$ANDROID_HOME/platform-tools/adb install app/build/outputs/apk/app-debug.apk
$ANDROID_HOME/platform-tools/adb install app/build/outputs/apk/app-debug-androidTest.apk
$ANDROID_HOME/platform-tools/adb shell am instrument -w -r -e debug false -e class com.vgaidarji.cimatters.LoginActivityTest com.vgaidarji.cimatters.test/android.support.test.runner.AndroidJUnitRunner

# alternative way to run tests
#./gradlew connectedAndroidTest -PfabricApiKey=$FABRIC_API_KEY -PfabricApiSecret=$FABRIC_API_SECRET

# kill all emulators
$ANDROID_HOME/platform-tools/adb devices | grep emulator | cut -f1 | while read line; do $ANDROID_HOME/platform-tools/adb -s $line emu kill; done
