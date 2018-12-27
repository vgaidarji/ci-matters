workflow "Build, Test and Distribute" {
  on = "push"
  resolves = "Publish Code Coverage"
}

action "Build" {
  uses = "./.github/android-github-actions"
  args = "./gradlew assembleDebug"
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
}

action "Check" {
  uses = "./.github/android-github-actions"
  args = "./gradlew testDebug jacocoTestReport coveralls checkstyle pmd jdepend lintDebug buildDashboard -PpreDexEnable=false -PversionCode=1 -PfabricApiKey=$FABRIC_API_KEY -PfabricApiSecret=$FABRIC_API_SECRET"
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
}

action "Run UI Tests" {
  uses = "./.github/android-github-actions"
}

action "Distribute" {
  needs = ["Build", "Check", "Run UI Tests"]
  uses = "./.github/android-github-actions"
}

action "Publish Code Coverage" {
  needs = ["Distribute"]
  uses = "./.github/android-github-actions"
  secrets = ["COVERALLS_REPO_TOKEN"]
}
