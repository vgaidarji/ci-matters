# ci-matters
Integration (comparison) of different continuous integration services on Android project.

### CI's integration

* [ ] Jenkins
* [ ] Travis CI
* [ ] Bitrise

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

    `./gradlew -PfabricApiKey="YOUR_API_KEY" -PfabricApiSecret="YOUR_API_SECRET" crashlyticsUploadDistributionDebug`
 
------

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
* HockeyApp Plugin or Fabric Beta Publisher (distribute APK's)

*Optional*

* Next build number (set next build number used as versionCode)
* Clone Workspace SCM Plug-in (clone workspace and use in downstream job)
 
How to create Jenkins job with clone/build/test/analyse/notify actions:

1. Create job with Freestyle type:

    <img src="/screenshots/freestyle_job_type.png" width="926" height="83">

2. Configure source code management section:

    <img src="/screenshots/freestyle_source_code_management.png" width="939" height="544">
    
3. Configure webhooks/polling:
 
    In order to build the project automatically when changes are pushed to the remote repository webhook should be configured. 
    [How to create a webhook](https://wiki.jenkins-ci.org/display/JENKINS/GitHub+Plugin#GitHubPlugin-AutomaticMode%28Jenkinsmanageshooksforjobsbyitself%29).
    
    If 2fa is enabled on github, then it will not be possible to configure webhook as mentioned in previous link.
    Polling might be used as an alternative in this case.   
 
    <img src="/screenshots/freestyle_poll_scm.png" width="921" height="212">

4. Configure Android emulator for UI tests. It will be started automatically during the build:

    Startup delay isn't mandatory. 
    In order to start Android emulator during the build ensure that all required Android targets and ABI's installed on Jenkins node.
    
    `android list targets` command shows "Available Android targets".
    
    <img src="/screenshots/freestyle_emulator_1.png" width="503" height="572">
    
    <img src="/screenshots/freestyle_emulator_2.png" width="553" height="368">
 
5. Configure how the project will be built using Gradle build scripts

    <img src="/screenshots/freestyle_gradle_build.png" width="921" height="582">
    
    In "Tasks" section  we need to specify a list of Gradle tasks to be invoked.
    
    For example: `clean connectedAndroidTest assembleDebug testDebug jacocoTestReport checkstyle pmd jdepend lintDebug buildDashboard  -PversionCode=${BUILD_NUMBER}  -PfabricApiKey="YOUR_KEY" -PfabricApiSecret="YOUR_SECRET"`
    
6. Configure reports 
    
    <img src="/screenshots/freestyle_lint.png" width="907" height="396">
    
    <img src="/screenshots/freestyle_junit.png" width="904" height="270">
    
    <img src="/screenshots/freestyle_jacoco.png" width="905" height="362">
    
    Make sure JaCoCo exclusions match [JaCoCo project configuration](https://github.com/vgaidarji/ci-matters/tree/master/app/config/jacoco.gradle).
    
7. Configure APK distribution to Fabric Beta

    <img src="/screenshots/freestyle_fabric.png" width="905" height="514">
 
8. Configure email notifications

    <img src="/screenshots/freestyle_email.png" width="907" height="199">
 
## Jenkins Pipeline

Not all plugins are compatible with [Pipeline](https://jenkins.io/doc/book/pipeline/overview/) plugin. Check [COMPATIBILITY](https://github.com/jenkinsci/pipeline-plugin/blob/master/COMPATIBILITY.md) document for complete information.

[jenkins/Jenkinsfile](https://github.com/vgaidarji/ci-matters/tree/master/jenkins/Jenkinsfile) contains Pipeline job configuration.
 It contains `clone/build/test/analyse/notify` steps as a regular job.
 
 **Secret keys must be exported on the Jenkins node or set directly in Jenkinsfile or passed as build parameters** like
 
    env.FABRIC_API_KEY = "${FABRIC_API_KEY}"
    env.FABRIC_API_SECRET = "${FABRIC_API_SECRET}"

How to create Pipeline job:

1. Create job with Pipeline type

    <img src="/screenshots/pipeline_job_type.png" width="925" height="83">

2. Configure build parameters

    <img src="/screenshots/pipeline_build_params.png" width="946" height="710">

3. Add Pipeline script or use from Jenkinsfile
    
    <img src="/screenshots/pipeline_jenkins_file.png" width="948" height="704">
    
4. Build the project by pushing it to remote repository or manually

    <img src="/screenshots/pipeline_build_status.png" width="1061" height="242">

**Note**: UI tests stage is disabled at the moment. 
It works on local PC, but doesn't work in Jenkins for some reason.
Same emulator configuration works fine in regular, non-pipeline Jenkins job described in previous section.

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
