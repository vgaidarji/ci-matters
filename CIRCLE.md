# Circle CI

[![CircleCI](https://circleci.com/gh/vgaidarji/ci-matters.svg?style=svg)](https://circleci.com/gh/vgaidarji/ci-matters)

#### Configuration

Circle CI provides [hello world](https://circleci.com/docs/2.0/hello-world/) tutorial.
It should be enough to understand how Circle CI works and what are the basic steps.
Circle CI operates with [workflows](https://circleci.com/docs/2.0/workflows/) but those aren't used at this project.

##### Configuration details

1. Add project under `Projects -> Add Project`.

    <img src="/screenshots/circle_add_new_project.png">

2. Select appropriate stack. In case of this project it's Linux OS, Ubuntu 14.04.

    <img src="/screenshots/circle_setup_project_os.png">

3. Select your project language and hit "Start building".
This should generate default `.circleci/config.yml` and trigger first project build.

    <img src="/screenshots/circle_select_language.png">

4. Describe your build steps in `.circleci/config.yml`.
Refer to this project's [.circleci/config.yml](./.circleci/config.yml) for configuration details.

5. Configure environment variables in `Settings -> Environment Variables`

    <img src="/screenshots/circle_env_variables.png">

6. Build the project and observe results

    <img src="/screenshots/circle_build_results_1.png">
    <img src="/screenshots/circle_build_results_2.png">