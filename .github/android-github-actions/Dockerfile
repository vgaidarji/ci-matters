FROM phusion/baseimage:0.11

ENV JAVA_HOME=/usr/lib/jvm/java-8-oracle \
    ANDROID_HOME=/opt/android-sdk \
    PATH="$PATH:/usr/lib/jvm/java-8-oracle/bin:/opt/android-sdk/tools:/opt/android-sdk/platform-tools"

RUN apt-get update -qq
RUN apt-get install -y --no-install-recommends wget lib32stdc++6 libqt5widgets5 lib32z1 unzip
RUN apt-get install -y awscli
RUN apt-get install -y expect

###################
# JDK8
###################
RUN \
    echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
    add-apt-repository -y ppa:webupd8team/java && \
    apt-get update && \
    apt-get install -y oracle-java8-installer && \
    rm -rf /var/lib/apt/lists/* && \
    rm -rf /var/cache/oracle-jdk8-installer
RUN java -version

##################
# Android licenses
##################
RUN mkdir /opt/android-sdk/
RUN mkdir /opt/android-sdk/licenses/
RUN echo "8933bad161af4178b1185d1a37fbf41ea5269c55" > /opt/android-sdk/licenses/android-sdk-license
RUN echo "\nd56f5187479451eabf01fb78af6dfcb131a6481e" > /opt/android-sdk/licenses/android-sdk-license
RUN echo "84831b9409646a918e30573bab4c9c91346d8abd" > /opt/android-sdk/licenses/android-sdk-preview-license
RUN echo "d975f751698a77b662f1254ddbeed3901e976f5a" > /opt/android-sdk/licenses/intel-android-extra-license

###################
# Android SDK
###################
RUN wget -O android-sdk.zip https://dl.google.com/android/repository/tools_r25.2.3-linux.zip
RUN unzip -a android-sdk.zip
RUN rm android-sdk.zip
RUN mv /tools /opt/android-sdk/tools
RUN echo $ANDROID_HOME
RUN echo $PATH
RUN ls -al /opt
RUN ls -al /opt/android-sdk
RUN ls -al /opt/android-sdk/tools
RUN echo 'y' | android update sdk --no-ui --all --filter "platform-tools,build-tools-25.0.2,build-tools-24.0.3"
RUN echo 'y' | android update sdk --no-ui --all --filter "extra-google-support,extra-android-support,extra-google-m2repository,extra-android-m2repository,extra-google-google_play_services"
RUN echo 'y' | android update sdk --no-ui --all --filter "android-15,android-21,android-22,android-25"
RUN echo 'y' | android update sdk -a -u -t "sys-img-armeabi-v7a-android-21"
RUN rm -rf /opt/android-sdk/add-ons

##################
# Speeding up android builds
# Gradle will pick these properties when running
##################
RUN mkdir ~/.gradle
RUN echo "org.gradle.daemon=true" >> ~/.gradle/gradle.properties
RUN echo "org.gradle.jvmargs=-Xmx4096m -XX:MaxPermSize=512m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8" >> ~/.gradle/gradle.properties
RUN echo "org.gradle.parallel=true" >> ~/.gradle/gradle.properties
RUN echo "org.gradle.configureondemand=true" >> ~/.gradle/gradle.properties
RUN echo "android.builder.sdkDownload=true" >> ~/.gradle/gradle.properties
RUN rm -rf /var/lib/apt/lists/*

##################
# Set mandatory environment variables
##################
ENV ANDROID_EMULATOR_FORCE_32BIT=true
ENV ANDROID_SDK_HOME=/root/.android/ 

###################
# GitHub Action description
###################
LABEL "com.github.actions.name"="Android"
LABEL "com.github.actions.description"="Build Android projects and run UI tests"
LABEL "com.github.actions.icon"="smartphone"
LABEL "com.github.actions.color"="green"

LABEL "repository"="http://github.com/actions"
LABEL "homepage"="http://github.com/actions"
LABEL "maintainer"="Veaceslav Gaidarji <veaceslav.gaidarji@gmail.com>"

COPY ui-tests-on-emulator.sh /usr/bin/ui-tests-on-emulator
COPY run-ui-tests.sh /usr/bin/run-ui-tests
COPY kill-running-emulators.sh /usr/bin/kill-running-emulators
COPY wait-for-emulator.sh /usr/bin/wait-for-emulator
COPY unlock-emulator-screen.sh /usr/bin/unlock-emulator-screen
COPY entrypoint.sh /
ENTRYPOINT ["/entrypoint.sh"]
