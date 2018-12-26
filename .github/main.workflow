workflow "Build, Test and Distribute" {
  on = "push"
  resolves = "Publish Code Coverage"
}

action "Build" {
  uses = "./.github/android-github-actions"
  args = "./gradlew assembleDebug"
}

action "Check" {
  uses = "./.github/android-github-actions"
  args = "./gradlew testDebug jacocoTestReport coveralls checkstyle pmd jdepend lintDebug buildDashboard -PpreDexEnable=false -PversionCode=1"
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
}
