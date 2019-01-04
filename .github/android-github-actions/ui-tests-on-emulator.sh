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

run-ui-tests

kill-running-emulators
