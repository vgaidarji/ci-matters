workflow "Build, Test and Distribute" {
  on = "push"
  resolves = "Run UI Tests"
}

action "Build" {
  uses = "./.github/android-github-actions"
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
  args = "./gradlew assembleDebug"
}

action "Check" {
  needs = ["Build"]
  uses = "./.github/android-github-actions"
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
  args = "./gradlew testDebug jacocoTestReport checkstyle pmd jdepend lintDebug buildDashboard -PpreDexEnable=false"
}

action "Run UI Tests" {
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
  uses = "./.github/android-emulator-github-actions"
  args = "ui-tests-on-emulator"
}

action "Distribute" {
  needs = ["Check", "Run UI Tests"]
  uses = "./.github/android-github-actions"
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
  args = "./gradlew crashlyticsUploadDistributionDebug -PpreDexEnable=false"
}

action "Publish Code Coverage" {
  needs = ["Distribute"]
  uses = "./.github/android-github-actions"
  secrets = ["COVERALLS_REPO_TOKEN"]
  args = "./gradlew coveralls -PpreDexEnable=false"
}