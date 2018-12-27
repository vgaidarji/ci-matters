FROM vgaidarji/docker-android-shippable:latest

###################
# Android SDK
###################
RUN echo 'y' | android update sdk --no-ui --all --filter "android-15,android-21,android-22,android-25,build-tools-24.0.3"
RUN echo 'y' | android update sdk --no-ui --all --filter "extra-android-support,extra-google-m2repository,extra-android-m2repository,extra-google-google_play_services"
RUN echo 'y' | android update sdk -a -u -t "sys-img-armeabi-v7a-android-21"

RUN echo "\nd56f5187479451eabf01fb78af6dfcb131a6481e" > /opt/android-sdk/licenses/android-sdk-license

# Emulator won't start without these variables
ENV ANDROID_EMULATOR_FORCE_32BIT=true
ENV ANDROID_SDK_HOME=/root/.android/ 

COPY ui-tests-on-emulator.sh /usr/bin/ui-tests-on-emulator
COPY kill-running-emulators.sh /usr/bin/kill-running-emulators
COPY wait-for-emulator.sh /usr/bin/wait-for-emulator
COPY unlock-emulator-screen.sh /usr/bin/unlock-emulator-screen

COPY entrypoint.sh /

ENTRYPOINT ["/entrypoint.sh"]
