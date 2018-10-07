# Bitrise.io

[![Build Status](https://app.bitrise.io/app/002b43ae8a42b6b1/status.svg?token=xT4EDBQWGNcSWJveU6IEVA&branch=master)](https://app.bitrise.io/app/002b43ae8a42b6b1)

[Bitrise](https://bitrise.io) is one of the modern and very promising CI services. Read [how Bitrise works](http://devcenter.bitrise.io/v1.0/docs/how-bitrise-works) article first to have better understanding. 

Bitrise integrates with wide [list of services](https://www.bitrise.io/integrations). It operates with [workflows](http://devcenter.bitrise.io/docs/manage-your-bitrise-workflow) (analog of Jenkins job).

##### How to configure workflow for Android project on Bitrise.io:

1. Create new application at [Add new app](https://www.bitrise.io/apps/add)

    <img src="/screenshots/bitrise_add_new_app_1.png"> 
    
2. Find your repository in repositories list
    
    <img src="/screenshots/bitrise_add_new_app_2.png">
    
3. Follow the instructions (basic configuration with clone/build takes ~1 minute). 
Bitrise automatically registers webhook, which makes our lives even simplier.

    <img src="/screenshots/bitrise_add_new_app_3.png">

4. Configure build steps
    
    <img src="/screenshots/bitrise_build_steps.png">
     
    [.bitrise.yml](https://github.com/vgaidarji/ci-matters/tree/master/.bitrise.yml) contains full configuration without UI tests.
    
    [.bitrise-with-ui-tests.yml](https://github.com/vgaidarji/ci-matters/tree/master/.bitrise-with-ui-tests.yml) contains full configuration + UI tests.
    It uses ["Virtual Device Testing for Android step"](https://blog.bitrise.io/introducing-solid-and-snappy-virtual-device-testing-for-android).

    These configurations can be imported to bitrise.io. To achieve that create new workflow or edit existing, navigate to `Workflow Editor -> bitrise.yml` and paste file content there.

5. Add environment variables

    <img src="/screenshots/bitrise_env_variables.png">
    
6. If everything goes fine we get successful build

    <img src="/screenshots/bitrise_successful_build.png">
