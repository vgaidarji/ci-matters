# Jenkins

#### How to install Jenkins

[Download](https://jenkins.io/download/) Jenkins and follow the installing [instructions](https://jenkins.io/doc/book/getting-started/installing/). It shouldn't be tricky.   

#### Must have plugins for Android project

* [Git plugin](https://wiki.jenkins-ci.org/display/JENKINS/Git+Plugin) (clone the project)
* [Gradle Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Gradle+Plugin) (build/test/etc. Android project)
* [Mailer Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Mailer) (notify the team)
* [Android emulator plugin](https://wiki.jenkins-ci.org/display/JENKINS/Android+Emulator+Plugin) (run Android emulator)
* [Static Analysis Collector Plug-in](https://wiki.jenkins-ci.org/display/JENKINS/Static+Code+Analysis+Plug-ins) (combined report of Checkstyle/DRY/FindBugs/PMD reports) 
* [JaCoCo Plugin](https://wiki.jenkins-ci.org/display/JENKINS/JaCoCo+Plugin) (collect code coverage)
* [Checkstyle Plug-in](https://wiki.jenkins-ci.org/display/JENKINS/Checkstyle+Plugin) (Checkstyle errors/warnings)
* [Android Lint plugin](https://wiki.jenkins-ci.org/display/JENKINS/Android+Lint+Plugin) (Lint errors/warnings)
* [JUnit Plugin](https://wiki.jenkins-ci.org/display/JENKINS/JUnit+Plugin) (unit tests report)
* [HockeyApp Plugin](https://wiki.jenkins-ci.org/display/JENKINS/HockeyApp+Plugin) or [Fabric Beta Publisher](https://wiki.jenkins-ci.org/display/JENKINS/Fabric+Beta+Publisher+Plugin) (distribute APK's)

*Optional*

* [Next build number](https://wiki.jenkins-ci.org/display/JENKINS/Next+Build+Number+Plugin) (set next build number used as versionCode)
* [Clone Workspace SCM Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Clone+Workspace+SCM+Plugin) (clone workspace and use in downstream job)
* [JobConfigHistory Plugin](https://wiki.jenkins-ci.org/display/JENKINS/JobConfigHistory+Plugin) (saves copies of all job and system configurations) 
* [Embeddable Build Status Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Embeddable+Build+Status+Plugin) (build status badge)

##### Environment variables 

In order to build the project we need to have environment variables set in Jenkins (`Manage Jenkins -> Configure system -> Global properties -> Environment variables`):
 
 <img src="/screenshots/jenkins_env_variables.png">

 
#### How to create Jenkins job with `clone/build/test/analyse/notify` actions:

1. Create job with Freestyle type:

    <img src="/screenshots/freestyle_job_type.png">

2. Configure source code management section:

    <img src="/screenshots/freestyle_source_code_management.png">
    
3. Configure webhooks/polling:
 
    In order to build the project automatically when changes are pushed to the remote repository webhook should be configured. 
    [How to create a webhook](https://wiki.jenkins-ci.org/display/JENKINS/GitHub+Plugin#GitHubPlugin-AutomaticMode%28Jenkinsmanageshooksforjobsbyitself%29).
    
    If 2fa is enabled on github, then it will not be possible to configure webhook as mentioned in previous link.
    Polling might be used as an alternative in this case.   
 
    <img src="/screenshots/freestyle_poll_scm.png">

4. Configure Android emulator for UI tests. It will be started automatically during the build:

    Startup delay isn't mandatory. 
    In order to start Android emulator during the build ensure that all required Android targets and ABI's installed on Jenkins node.
    
    `android list targets` command shows "Available Android targets".
    
    <img src="/screenshots/freestyle_emulator_1.png">
    
    <img src="/screenshots/freestyle_emulator_2.png">
 
5. Configure how the project will be built using Gradle build scripts

    <img src="/screenshots/freestyle_gradle_build.png">
    
    In "Tasks" section  we need to specify a list of Gradle tasks to be invoked.
    
    For example: `clean connectedAndroidTest assembleDebug testDebug jacocoTestReport checkstyle pmd jdepend lintDebug buildDashboard  -PversionCode=${BUILD_NUMBER}  -PfabricApiKey="YOUR_KEY" -PfabricApiSecret="YOUR_SECRET"`
    
6. Configure reports 
    
    <img src="/screenshots/freestyle_lint.png">
    
    <img src="/screenshots/freestyle_junit.png">
    
    <img src="/screenshots/freestyle_jacoco.png">
    
    Make sure JaCoCo exclusions match [JaCoCo project configuration](https://github.com/vgaidarji/ci-matters/tree/master/app/config/jacoco.gradle).
    
7. Configure APK distribution to Fabric Beta

    <img src="/screenshots/freestyle_fabric.png">
 
8. Configure email notifications

    <img src="/screenshots/freestyle_email.png">
 
**Bonus**:
Jenkins job described above can be easily created using [jenkins/config.xml](https://github.com/vgaidarji/ci-matters/tree/master/jenkins/config.xml) file.

1. Check [Jenkins HOME directory](https://wiki.jenkins-ci.org/display/JENKINS/Administering+Jenkins) structure to have better understanding where Jenkins keeps jobs.
2. Create subdirectory in `JENKINS_HOME/jobs` and copy `config.xml` file there.
3. Restart jenkins (`https://YOUR_JENKINS_SERVER_URL/restart`) to apply the changes.
4. Configure the job with missing secret keys (Fabric API key & secret, etc.)
 
## Jenkins Pipeline

Not all plugins are compatible with [Pipeline](https://jenkins.io/doc/book/pipeline/overview/) plugin. Check [COMPATIBILITY](https://github.com/jenkinsci/pipeline-plugin/blob/master/COMPATIBILITY.md) document for complete information.

[jenkins/Jenkinsfile](https://github.com/vgaidarji/ci-matters/tree/master/jenkins/Jenkinsfile) contains Pipeline job configuration.
 It contains `clone/build/test/analyse/notify` steps as a regular job.
 
 **Secret keys must be exported on the Jenkins node or set directly in Jenkinsfile or passed as build parameters** like
 
    env.FABRIC_API_KEY = "${FABRIC_API_KEY}"
    env.FABRIC_API_SECRET = "${FABRIC_API_SECRET}"

How to create Pipeline job:

1. Create job with Pipeline type

    <img src="/screenshots/pipeline_job_type.png">

2. Configure build parameters

    <img src="/screenshots/pipeline_build_params.png">

3. Add Pipeline script or use from Jenkinsfile
    
    <img src="/screenshots/pipeline_jenkins_file.png">
    
4. Build the project by pushing it to remote repository or manually

    <img src="/screenshots/pipeline_build_status.png">

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
   
