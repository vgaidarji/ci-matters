# Shippable

[![Run Status](https://api.shippable.com/projects/5832c72ab8b8e41000a5eb5c/badge?branch=master)](https://app.shippable.com/projects/5832c72ab8b8e41000a5eb5c) [![Coverage Badge](https://api.shippable.com/projects/5832c72ab8b8e41000a5eb5c/coverageBadge?branch=master)](https://app.shippable.com/projects/5832c72ab8b8e41000a5eb5c)

[Shippable](https://shippable.com) is an online CI service which provides packaging, continuous integration,
continuous delivery and continuous deployment services.

Shippable runs builds on Docker-based containers. It provides default containers with minimum required tools.
It's also possible to use [custom Docker images](http://docs.shippable.com/ci/languages/customImages/) 
to meet project requirements. Check [docs](http://docs.shippable.com) for more info.
 
Shippable is not mobile oriented CI service, but it's possible to build Android projects using this service.

#### How to build Android project on Shippable

1. [Import the project](http://docs.shippable.com/ci/runFirstBuild/) from source control.
2. Create [`shippable.yml`](https://github.com/vgaidarji/ci-matters/tree/master/app/shippable.yml) in root directory of a project. 
It's a configuration file which describes build steps. 
More info about configuration file can found [here](http://docs.shippable.com/ci/shippableyml/).

3. Create [secure environment variables](http://docs.shippable.com/ci/advancedOptions/environmentVariables/#secure-variables)

    <img src="/archive/shippable/screenshots/shippable_env_variables.png">
    
    *Variables should be separated with space.*
    
    This operation creates environment variables and stores them in a secure place. 
    Shippable provides secure string with encrypted variables, 
    which should be used in `shippable.yml` or `shippable.resources.yml` files. 
    
    <img src="/archive/shippable/screenshots/shippable_env_variables_encrypted.png">
     
    Copy encrypted string and place it into `shippable.yml`:
    
    <img src="/archive/shippable/screenshots/shippable_env_variables_in_yml.png">
    
4. Choose existing docker image to use or create your own and define it in `shippable.yml`:

    <img src="/archive/shippable/screenshots/shippable_docker_image.png">
    
    It's good idea to check what provides docker image by checking it's Dockerfile ([vgaidarji/docker-android-shippable](https://hub.docker.com/r/vgaidarji/docker-android-shippable/~/dockerfile/)).
    
5. Configure [build steps](http://docs.shippable.com/ci/shippableyml/) in `shippable.yml`:

    <img src="/archive/shippable/screenshots/shippable_ci_section.png">
    
    In this case we install missing Android dependencies, run Android emulator, `build/test/analyze` the project
    and publish code coverage results to Shippable. Shippable accepts code coverage reports only in Cobertura format.
    
6. Configure notifications:

    <img src="/archive/shippable/screenshots/shippable_notifications.png">
 
#### Reports

###### JUnit test results:

<img src="/archive/shippable/screenshots/shippable_junit_reports.png">
    
<img src="/archive/shippable/screenshots/shippable_code_coverage.png">

###### Build information:

<img src="/archive/shippable/screenshots/shippable_build_information.png">
