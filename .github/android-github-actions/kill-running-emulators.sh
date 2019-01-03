#!/usr/bin/env bash

set -eu

# get rid of running emulators
$ANDROID_HOME/platform-tools/adb devices | grep emulator | cut -f1 | while read line; do $ANDROID_HOME/platform-tools/adb -s $line emu kill; done
