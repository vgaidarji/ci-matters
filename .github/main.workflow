workflow "Build, Test and Distribute" {
  on = "push"
  resolves = "Publish Code Coverage"
}

action "Build" {

}

action "Check" {

}

action "Run UI Tests" {

}

action "Distribute" {
  needs = ["Build", "Check", "Run UI Tests"]
}

action "Publish Code Coverage" {
  needs = ["Distribute"]
}
