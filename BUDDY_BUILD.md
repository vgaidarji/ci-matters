# BuddyBuild

[BuddyBuild](https://www.buddybuild.com) is an online CI service.

#### How to configure Android project on BuddyBuild

Official documentation contains [basic steps](http://docs.buddybuild.com/docs/selecting-an-app) 
which are sufficient to configure the project.
  
##### Configuration details

1. Configure environment variables in `AppSettings -> Build Settings -> Environment Variables` 

    <img src="/screenshots/buddy_build_env_variables.png">

2. Configure email notifications and notifications rules
 
    <img src="/screenshots/buddy_build_email_notifications.png">
    
3. Configure UI tests

    <img src="/screenshots/buddy_build_ui_tests_real_devices.png">

    <img src="/screenshots/buddy_build_ui_tests_emulator.png">
    
    **Unfortunately there's no possibility to customize Android emulator settings.**
    
    **Be ready to wait enough time before UI tests start on emulator/real device.
    Also, UI tests fail from time to time and connection with device hangs forever.**

4. Install BuddyBuild SDK
 
    <img src="/screenshots/buddy_build_install_sdk.png">

    BuddyBuild can automatically configure itself in a project, 
    but in this case project might not compile if custom code style checks configured. 

#### Extension points

BuddyBuild doesn't provide full control over build process. 
Only `After repository has been cloned`, `Before a build` and `After a build` points can be customized 
via `buddybuild_postclone.sh`, `buddybuild_prebuild.sh` and `buddybuild_postbuild.sh` respectively. 

"Coveralls report", "static code analysis" and "deploy to Fabric.Beta" is done in 
[`buddybuild_postbuild.sh`](https://github.com/vgaidarji/ci-matters/tree/master/app/buddybuild_postbuild.sh).  

#### Reports

###### BuddyBuild shows JUnit reports automatically (not very informative):

<img src="/screenshots/buddy_build_junit_tests.png">

For some reason `Code coverage` is 0%

<img src="/screenshots/buddy_build_junit_tests_coverage.png">

and `Builds` tab shows wrong coverage 91.1% coverage instead of 100%:

<img src="/screenshots/buddy_build_builds_view.png">

###### BuddyBuild shows UI reports automatically (not very informative):

<img src="/screenshots/buddy_build_ui_tests.png">

