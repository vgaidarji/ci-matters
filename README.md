# ci-matters
Integration (comparison) of different continuous integration services on Android project.

### CI's integration

* [x] [Jenkins](https://github.com/vgaidarji/ci-matters/blob/master/JENKINS.md)
* [x] [Travis CI](https://github.com/vgaidarji/ci-matters/blob/master/TRAVIS.md) [![Build Status](https://travis-ci.org/vgaidarji/ci-matters.svg?branch=master)](https://travis-ci.org/vgaidarji/ci-matters)
* [x] [Bitrise](https://github.com/vgaidarji/ci-matters/blob/master/BITRISE.md) [![Build Status](https://www.bitrise.io/app/002b43ae8a42b6b1.svg?token=xT4EDBQWGNcSWJveU6IEVA&branch=master)](https://www.bitrise.io/app/002b43ae8a42b6b1)

### TODO

* [ ] TeamCity
* [ ] Buddybuild
* [ ] GreenHouse
* [ ] Gitlab CI
* [ ] Circle CI

---

### Checkstyle

Project uses custom Checkstyle [rules](https://github.com/vgaidarji/ci-matters/blob/master/app/config/checkstyle/checkstyle-yopeso.xml).

---

### Fabric/Crashlytics project configuration

In order to upload APK to Crashlytics project should have following configuration:
`${projectDir}/fabric.properties` file with `apiSecret` and `io.fabric.ApiKey` in AndroidManifest.xml([1](https://github.com/vgaidarji/ci-matters/blob/master/app/src/main/AndroidManifest.xml#L17), 
[2](https://github.com/vgaidarji/ci-matters/blob/master/app/build.gradle#L59)) file.
**Both keys should not be uploaded to the repository for security reasons!**

Pass both parameters to your build from command line:

    ./gradlew -PfabricApiKey="YOUR_API_KEY" -PfabricApiSecret="YOUR_API_SECRET" crashlyticsUploadDistributionDebug
 
------

### Coveralls

[![Coverage Status](https://coveralls.io/repos/github/vgaidarji/ci-matters/badge.svg)](https://coveralls.io/github/vgaidarji/ci-matters)

`Coveralls` provides test coverage information. `COVERALLS_REPO_TOKEN` environment variable should be exported on the build machine.

