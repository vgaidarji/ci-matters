# GitHub Actions

#### Configuration

GitHub provides [basic documentation](https://developer.github.com/actions) on how actions/workflows work and how to create them.

##### Configuration details

1. [Create new workflow using visual editor](https://help.github.com/articles/creating-a-workflow-with-github-actions/#creating-a-workflow-using-the-visual-editor) or manually in file `.github/main.workflow`.

2. Define build steps (aka Actions) of the workflow and dependencies between them (see [Creating a new GitHub Action](https://developer.github.com/actions/creating-github-actions/creating-a-new-action/)).

3. For this Android project, special actions were created:
    - [vgaidarji/android-github-actions/build](https://github.com/vgaidarji/android-github-actions/tree/master/build)
    - [vgaidarji/android-github-actions/emulator](https://github.com/vgaidarji/android-github-actions/tree/master/emulator)

    See these projects' `README` and `Dockerfile` files for configuration details.

    Use `vgaidarji/android-github-actions/build@v1.0.0` for regular build steps and provide commands via action `args` parameter.
    For example: `args = "./gradlew assembleDebug -PpreDexEnable=false"`. This will be passed to `entrypoint.sh` of the appropriate GitHub Action and executed in root folder of the workspace.

    Use `vgaidarji/android-github-actions/emulator@v1.0.0` for running UI tests on Android Emulator.
    By default, this action will execute [ui-test-on-emulator](https://github.com/vgaidarji/android-github-actions/blob/0381c333953e22b4b95d4ef843effefb35c67fdf/emulator/Dockerfile#L18) script (see [script sources](https://github.com/vgaidarji/docker-android/blob/570d8f3aacd6af72b817254d08c99cd5bae57636/docker-android-emulator/ui-tests-on-emulator.sh)).

    To modify the way Android UI tests are executed, you need to override `/usr/bin/run-ui-tests` script and provide custom way of executing UI tests. Alternatively, use another GitHub Action or Docker image.
    By default, UI tests will be executed via `./gradlew connectedAndroidTest` command.

    See [.github/man.workflow](./.github/main.workflow) with final configuration.

4. Add `COVERALLS_REPO_TOKEN`, `FABRIC_API_KEY`, `FABRIC_API_TOKEN` secrets via Actions UI editor

    <img src="/screenshots/github_actions_new_secret_variable.png">

    Once secrets are created via editor, they will be automatically used in Action from which you initiated the secrets creation. For any other Action which needs secrets or environment variables we need to either specify them

    via visual editor

    <img src="/screenshots/github_actions_secret_variables_in_action.png">

    or directly in workflow file as follows
    ```
    secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
    ```

5. Project will be built on every `push` action (both from branches and PR's)

    <img src="/screenshots/github_actions_successful_build.png">
