# ci-matters
Integration (comparison) of different continuous integration services on Android project.

### CI's integration

* [x] [GitHub Actions](./GITHUB_ACTIONS.md) ![Build Status](https://github.com/github/docs/actions/workflows/main.yml/badge.svg?branch=master)
* [x] [Bitrise](./BITRISE.md) [![Build Status](https://app.bitrise.io/app/002b43ae8a42b6b1/status.svg?token=xT4EDBQWGNcSWJveU6IEVA&branch=master)](https://app.bitrise.io/app/002b43ae8a42b6b1)
* [x] [Circle](./CIRCLE.md) [![CircleCI](https://circleci.com/gh/vgaidarji/ci-matters.svg?style=svg)](https://circleci.com/gh/vgaidarji/ci-matters)
* [x] [Jenkins](./JENKINS.md)
* [x] [TeamCity](./TEAM_CITY.md)

### TODO

* [ ] Codemagic.io
* [ ] Gitlab CI
* [ ] Drone.io

---

### Codecov.io

[![codecov](https://codecov.io/gh/vgaidarji/ci-matters/graph/badge.svg?token=ubhWNTji7m)](https://codecov.io/gh/vgaidarji/ci-matters)

`Codecov.io` provides test coverage information. `CODECOV_TOKEN` environment variable should be exported on the build machine.

### Comparison

#### Comparison table

This table should help people make a decision which CI to choose for the project.

| CI            | :dancers:,:construction_worker:,:mag_right::bug:,:vertical_traffic_light:,:mailbox_with_mail: | :iphone::eyes: | :rocket: | :page_facing_up: | :chart_with_upwards_trend: | :bust_in_silhouette::raised_hands:/:cloud: | :radio:/:computer: | :dollar: |
| ------------- |:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| Jenkins       |:star:|:star:|:star:|:star:|:star:|:bust_in_silhouette::raised_hands:|:radio:/:computer:|:free:|
| TeamCity      |:star:|:star:|:star:|:star:|:star:|:bust_in_silhouette::raised_hands:/:cloud:|:computer:|:moneybag::moneybag::moneybag:|
| Bitrise       |:star:|:star:|:star:|:star:|:x:|:cloud:|:computer:|:moneybag::moneybag:|
| Circle CI     |:star:|:star:|:star:|:star:|:x:|:cloud:|:computer:|:moneybag::moneybag:|
| GitHub Actions|:star:|:star:|:star:|:star:|:x:|:cloud:|:computer:|:free:|
| Gitlab CI     |.|.|.|.|.|.|.|:moneybag:|
| Nevecode.io   |.|.|.|.|.|.|.|:moneybag::moneybag:|
| Codemagic.io  |.|.|.|.|.|.|.|:moneybag:|

|. |. |.|. |. |. |. |. |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|:dancers: clone<br>:construction_worker: build <br>:mag_right::bug: test <br>:vertical_traffic_light: analyse<br>:mailbox_with_mail: notify|:iphone::eyes: UI tests |:rocket: deploy|:page_facing_up: configuration file |:chart_with_upwards_trend: visual reports|:bust_in_silhouette::raised_hands:/:cloud: self-hosted/cloud |:radio:/:computer: CI user interface (old/new)|:dollar: price|

### Pricing

All listed CI's provide free plan with some restrictions like "open-source projects only" or "only 1 build node",
but it's enough to configure the build process and check if CI suits or not.

##### Free plans

Choosing a free plan for a project is more suitable for a freelance project.
Usually, some services provide limits on such plans for understandable reasons.

##### Paid plans

CI's have different approaches and different paid plans.
Check every CI pricing information for more details and choose the best option for you.

:warning: *This information can get outdated very quickly as services evolve.* :warning:

What might matter is the starting price for paid plan:

* [Jenkins](https://www.cloudbees.com/products/pricing) - starts with **X$.** (paid version (Cloudbees), no public information)
* [Travis CI](https://travis-ci.com/plans) - starts with **69$/month.** (1 concurrent build, &infin; projects, &infin; build time)
* [Bitrise](https://www.bitrise.io/pricing) - starts with **50$/month.** (1 concurrent build, &infin; projects, 45 min. max build time)
* [TeamCity](https://www.jetbrains.com/teamcity/buy/#license-type=new-license) - starts with **299$.** (4 concurrent builds, 30 project configurations, &infin; build time)
* [Gitlab CI](https://about.gitlab.com/products/) - starts with **15$/month.** (2 concurrent builds, &infin; projects, &infin; build time)
* [Circle CI](https://circleci.com/pricing/) - starts with **39$/month.** (2 concurrent builds, &infin; projects, 500 minutes build time per month)
* [Nevercode.io](https://nevercode.io/pricing/) - starts with **5$/month.** (1 concurrent build, &infin; projects, 90 min. max build time)
* [Drone.io](https://drone.io/pricing) - starts with **25$/month.** (1 concurrent build, 5 private projects, &infin; build time)

#### Presentation

I gave a talk back in 2016 and this repository was a technical part for it. I am describing there configured CI's
and providing my personal opinion about which CI to choose depending on your needs (in :ru: language ).

It doesn't contain information about ALL existing CI services, but should be a good start.

[![CI comparison presentation](http://img.youtube.com/vi/81G_C1J5hQ4/0.jpg)](http://www.youtube.com/watch?v=81G_C1J5hQ4)

In few words:
- [Jenkins](https://jenkins.io/)/[TeamCity](https://www.jetbrains.com/teamcity/) for complex workflow
- [Circle CI](https://circleci.com/) for open-source projects
- [Bitrise.io](https://bitrise.io/) for any workflow

---

### Firebase App Distribution project configuration

In order to distribute Android builds to Firebase, Google Service Account ([link](https://firebase.google.com/docs/app-distribution/android/distribute-gradle#authenticate)) credentials should be created.
Service Account then needs to be saved in secure place and exported in `FIREBASE_APP_DISTRIBUTION_SERVICE_ACCOUNT_JSON={service-account.json}` environment variable on CI. 
Use secret variables on CI/repo level for this.

Distribute the build using following command:

    ./gradlew appDistributionUpload{Debug|Release}

### Archive

Following CIs are no longer integrated (either because they have been discontinued or due to pricing policy change such as Travis).
* [x] [Travis CI](archive/travis/TRAVIS.md)
* [x] [BuddyBuild](archive/buddybuild/BUDDY_BUILD.md)
* [x] [Shippable](archive/shippable/SHIPPABLE.md) 
