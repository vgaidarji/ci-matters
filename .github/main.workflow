workflow "Build, Test and Distribute" {
  on = "push"
  resolves = "Publish Code Coverage"
}

action "Build" {
  uses = "vgaidarji/android-github-actions/build@v1.0.0"
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
  args = "./gradlew assembleDebug -PpreDexEnable=false"
}

action "Check" {
  needs = ["Build"]
  uses = "vgaidarji/android-github-actions/build@v1.0.0"
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
  args = "./gradlew testDebug jacocoTestReport checkstyle pmd jdepend lintDebug buildDashboard -PpreDexEnable=false"
}

action "Run UI Tests" {
  needs = ["Build"]
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
  uses = "vgaidarji/android-github-actions/emulator@v1.0.0"
}

action "Distribute" {
  needs = ["Check", "Run UI Tests"]
  uses = "vgaidarji/android-github-actions/build@v1.0.0"
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
  args = "./gradlew crashlyticsUploadDistributionDebug -PpreDexEnable=false"
}

action "Publish Code Coverage" {
  needs = ["Distribute"]
  uses = "vgaidarji/android-github-actions/build@v1.0.0"
  secrets = ["COVERALLS_REPO_TOKEN"]
  args = "./gradlew coveralls -PpreDexEnable=false"
}
