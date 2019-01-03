#!/usr/bin/env bash

set -eu

# Unlock the Lock Screen
$ANDROID_HOME/platform-tools/adb shell input keyevent 82 &
$ANDROID_HOME/platform-tools/adb shell input keyevent 4 &
