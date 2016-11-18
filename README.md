# ci-matters
Integration (comparison) of different continuous integration services on Android project.

# CI's integration

- [ ] Jenkins
- [ ] Travis CI
- [ ] Bitrise

# TODO

- [ ] TeamCity
- [ ] Buddybuild
- [ ] GreenHouse
- [ ] Gitlab CI
- [ ] Circle CI

---

# Jenkins

**Must have plugins for Android project:**

* Git plugin (clone the project)
* Gradle Plugin (build/test/etc. Android project)
* Mailer Plugin (notify the team)
* Android emulator plugin (run Android emulator)
* Static Analysis Collector Plug-in (combined report of Checkstyle/DRY/FindBugs/PMD reports) 
* JaCoCo Plugin (collect code coverage)
* Checkstyle Plug-in (Checkstyle errors/warnings)
* Android Lint plugin (Lint errors/warnings)
* JUnit Plugin (unit tests report)

*Optional*

* Next build number (set next build number used as versionCode)
* Clone Workspace SCM Plug-in (clone workspace and use in downstream job)
* HockeyApp Plugin or Fabric Beta Publisher (distribute APK's)
 
In order to build the project automatically when change are pushed to the remote repository webhook should be configured. 
[How to create a webhook](https://wiki.jenkins-ci.org/display/JENKINS/GitHub+Plugin#GitHubPlugin-AutomaticMode%28Jenkinsmanageshooksforjobsbyitself%29).
If 2fa is enabled on github, then it will not be possible to configure webhook as mentioned in previous link.
Polling might be used as an alternative in this case.  
`Build Triggers -> Poll SCM -> * * * * *`. `* * * * *` - poll changes from repository every minute.
 
## Jenkins Pipeline

Not all plugins are compatible with Pipeline plugin. 
Check [COMPATIBILITY](https://github.com/jenkinsci/pipeline-plugin/blob/master/COMPATIBILITY.md) document for complete information.

[jenkins/Jenkinsfile]((https://github.com/vgaidarji/ci-matters/tree/master/jenkins/Jenkinsfile)) contains Pipeline job configuration.
 It contains clone/build/test/analyse/send steps as a regular job.
 **Secret keys must be exported on the Jenkins node or set directly in Jenkinsfile** like
 
    env.FABRIC_API_KEY = ""
    env.FABRIC_API_SECRET = ""
    
 
### Install/Update Android SDK

Different Android project might use different Android SDK versions and build tools.
These should be installed on the build machine. 
Having 2 jobs in Jenkins which help with Android dependencies installation should be enough:

1. [install_specific_android_sdk.sh](https://github.com/vgaidarji/ci-matters/tree/master/jenkins/install_specific_android_sdk.sh) - This will install specific Android SDK on the build machine. 
   Create parametrized job with content of this shell script and `SDK_LIST` String parameter (comma-separated Android SDK versions).

2. [update_android_sdk.sh](https://github.com/vgaidarji/ci-matters/tree/master/jenkins/update_android_sdk.sh) - This will update/install `all` Android SDK versions.
   Create parametrized job with content of this shell script and schedule periodic builds of this job. 
   Would be nice to execute this job every night to make sure that build machine has latest Android SDK versions installed.
   To achieve that we need to enable periodic midnight builds - `Build Triggers -> Build periodically -> @midnight`.

---

### Checkstyle

Project uses custom Checkstyle [rules](https://github.com/vgaidarji/ci-matters/blob/master/app/config/checkstyle/checkstyle-yopeso.xml).

---

### Fabric/Crashlytics configuration

In order to upload APK to Crashlytics project should have following configuration:
`${projectDir}/fabric.properties` file with `apiSecret` and `io.fabric.ApiKey` in AndroidManifest.xml([1](https://github.com/vgaidarji/ci-matters/blob/master/app/src/main/AndroidManifest.xml#L17), 
[2](https://github.com/vgaidarji/ci-matters/blob/master/app/build.gradle#L59)) file.
**Both keys should not be uploaded to the repository for security reasons!**

Pass both parameters to your build from command line: `./gradlew -PfabricApiKey="YOUR_API_KEY" -PfabricApiSecret="YOUR_API_SECRET" crashlyticsUploadDistributionDebug` 

