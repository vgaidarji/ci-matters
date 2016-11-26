# TeamCity

##### How to install TeamCity

[Download](https://www.jetbrains.com/teamcity/) TeamCity archive and follow [installation instructions](https://confluence.jetbrains.com/display/TCD10/Installing+and+Configuring+the+TeamCity+Server).

Basic installation:

- extract TeamCity from archive
- edit `TeamCityFolder/buildAgent/conf/buildAgent.properties` if needed (server url, etc.)
- execute `TeamCityFolder/bin/runAll.sh start` to start TeamCity server
- open TeamCity server page (address can be found in `TeamCityFolder/buildAgent/conf/buildAgent.properties`) and finish configuration.

#### How to create TeamCity job with `clone/build/test/analyse/notify` actions:

1. TeamCity initiates first project configuration on configuration step. Also, is possible to create project in `Administration -> Projects -> Create project`

    <img src="/screenshots/team_city_create_project.png">
    
    <img src="/screenshots/team_city_configure_github_project.png">

2. On successful project import, project will have Github connection set up 
and ready to automatically start builds when change is pushed to remote repository:

    <img src="/screenshots/team_city_github_connection.png">
    
3. Configure build steps (TeamCity will try to automatically detect basic build steps (like `clean assemble` gradle tasks) on project import):
 
    <img src="/screenshots/team_city_build_tasks.png">
 
4. Configure `Build` build step: 

    <img src="/screenshots/team_city_build_step_build.png">
    
5. Configure `Unit tests` build step:

    <img src="/screenshots/team_city_build_step_unit_tests.png">

6. Configure `Static Code Analysis` build step:

    <img src="/screenshots/team_city_build_step_static_analysis.png">
    
7. Configure `UI tests` build step:

    <img src="/screenshots/team_city_build_step_ui_tests.png">

    Shell script content can be found in [`ui_tests_on_emulator.sh`](https://github.com/vgaidarji/ci-matters/tree/master/ui_tests_on_emulator.sh) file.

8. Configure `Upload to Fabric` build step:
 
    <img src="/screenshots/team_city_build_step_upload_to_fabric.png">

9. Configure email notifications in settings `Administration -> ServerAdministration -> Email Notifier` (SMTP server information is required)

    <img src="/screenshots/team_city_email_notifier.png">

    and email notification rules in `Administration -> Groups -> All Users -> Notification Rules`:

    <img src="/screenshots/team_city_email_notification_rules.png">

10. Configure environment variables used in build steps. Create Environment Variables using "Add new parameter" button:
    
    <img src="/screenshots/team_city_environment_variables.png">

11. Configure build artifacts

    <img src="/screenshots/team_city_build_artifacts.png">

    which will be published after the build and can be found in "Artifacts" tab:
    
    <img src="/screenshots/team_city_build_artifacts_tab.png">

12. Configure JaCoCo code coverage report in [`jacoco.gradle`](https://github.com/vgaidarji/ci-matters/tree/master/app/config/jacoco.gradle) file: 

    ```groovy
    if (project.hasProperty("teamcity")) {
        println '##teamcity[jacocoReport dataPath=\'app/build/jacoco/testDebugUnitTest.exec\' includes=\'com.vgaidarji.cimatters.*\' excludes=\'com.vgaidarji.cimatters.test.* **/*R*.* **/*Injector*.* **/*Activity*.* .*R .*CiMattersApplication .*BuildConfig .*Activity .*Test \']'
    }
    ```

    Coverage information will be available in `Overview` tab or in `Code Coverage` tab:
    
    <img src="/screenshots/team_city_coverage_information.png">
    
