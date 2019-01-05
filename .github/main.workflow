workflow "Build, Test and Distribute" {
  on = "push"
  resolves = "Publish Code Coverage"
}

action "Build" {
  uses = "docker://vgaidarji/docker-android-build:1.0.0"
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
  args = "./gradlew assembleDebug -PpreDexEnable=false"
}

action "Check" {
  needs = ["Build"]
  uses = "docker://vgaidarji/docker-android-build:1.0.0"
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
  args = "./gradlew testDebug jacocoTestReport checkstyle pmd jdepend lintDebug buildDashboard -PpreDexEnable=false"
}

action "Run UI Tests" {
  needs = ["Build"]
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
  uses = "docker://vgaidarji/docker-android-emulator:1.0.0"
}

action "Distribute" {
  needs = ["Check", "Run UI Tests"]
  uses = "docker://vgaidarji/docker-android-build:1.0.0"
  secrets = ["FABRIC_API_KEY", "FABRIC_API_SECRET"]
  args = "./gradlew crashlyticsUploadDistributionDebug -PpreDexEnable=false"
}

action "Publish Code Coverage" {
  needs = ["Distribute"]
  uses = "docker://vgaidarji/docker-android-build:1.0.0"
  secrets = ["COVERALLS_REPO_TOKEN"]
  args = "./gradlew coveralls -PpreDexEnable=false"
}
