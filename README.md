# ci-matters
Integration (comparison) of different continuous integration services on Android project.

### CI's integration

* [x] [Jenkins](./JENKINS.md)
* [x] [Travis CI](./TRAVIS.md) [![Build Status](https://travis-ci.org/vgaidarji/ci-matters.svg?branch=master)](https://travis-ci.org/vgaidarji/ci-matters)
* [x] [Bitrise](./BITRISE.md) [![Build Status](https://app.bitrise.io/app/002b43ae8a42b6b1/status.svg?token=xT4EDBQWGNcSWJveU6IEVA&branch=master)](https://app.bitrise.io/app/002b43ae8a42b6b1)
* [x] [TeamCity](./TEAM_CITY.md)
* [x] [BuddyBuild](./BUDDY_BUILD.md) [![BuddyBuild](https://dashboard.buddybuild.com/api/statusImage?appID=58398ac5beb35b010082e315&branch=master&build=latest)](https://dashboard.buddybuild.com/apps/58398ac5beb35b010082e315/build/latest)
* [x] [Shippable](./SHIPPABLE.md) [![Run Status](https://api.shippable.com/projects/5832c72ab8b8e41000a5eb5c/badge?branch=master)](https://app.shippable.com/projects/5832c72ab8b8e41000a5eb5c) [![Coverage Badge](https://api.shippable.com/projects/5832c72ab8b8e41000a5eb5c/coverageBadge?branch=master)](https://app.shippable.com/projects/5832c72ab8b8e41000a5eb5c)
* [x] [Circle](./CIRCLE.md) [![CircleCI](https://circleci.com/gh/vgaidarji/ci-matters.svg?style=svg)](https://circleci.com/gh/vgaidarji/ci-matters)
* [x] [GitHub Actions](./GITHUB_ACTIONS.md)

### TODO

* [ ] Nevercode.io
* [ ] Gitlab CI
* [ ] Drone.io

---

### Comparison

#### Comparison table

This table should help people make a decision which CI to choose for the project.

| CI            | :dancers:,:construction_worker:,:mag_right::bug:,:vertical_traffic_light:,:mailbox_with_mail: | :iphone::eyes: | :rocket: | :page_facing_up: | :chart_with_upwards_trend: | :bust_in_silhouette::raised_hands:/:cloud: | :radio:/:computer: | :dollar: |
| ------------- |:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| Jenkins       |:star:|:star:|:star:|:star:|:star:|:bust_in_silhouette::raised_hands:|:radio:/:computer:|:free:|
| TeamCity      |:star:|:star:|:star:|:star:|:star:|:bust_in_silhouette::raised_hands:/:cloud:|:computer:|:moneybag::moneybag::moneybag:|
| Travis CI     |:star:|:star:|:star:|:star:|:x:|:cloud:|:computer:|:moneybag::moneybag:|
| Bitrise       |:star:|:star:|:star:|:star:|:x:|:cloud:|:computer:|:moneybag::moneybag:|
| Shippable     |:star:|:star:|:star:|:star:|:x:|:cloud:|:radio:|:moneybag:|
| Circle CI     |:star:|:star:|:star:|:star:|:x:|:cloud:|:computer:|:moneybag::moneybag:|
| Buddybuild    |:star:|:star:|:x:|:x:|:x:|:cloud:|:computer:|:moneybag::moneybag:|
| GitHub Actions|.|.|.|.|.|.|.|.|
| Gitlab CI     |.|.|.|.|.|.|.|:moneybag:|
| Nevecode.io   |.|.|.|.|.|.|.|:moneybag::moneybag:|
| Drone.io      |.|.|.|.|.|.|.|:moneybag:|

1. :dancers: - clone 2. :construction_worker: - build 3. :mag_right::bug: - test 4. :vertical_traffic_light: - analyse 5. :mailbox_with_mail: - notify
6. :iphone::eyes: - UI tests 7. :rocket: - deploy 8. :page_facing_up: - configuration file 9. :chart_with_upwards_trend: - visual reports
10. :bust_in_silhouette::raised_hands:/:cloud: - self-hosted/cloud 11. :radio:/:computer: - CI user interface (old/new) 12. :dollar: - price


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
* [Shippable](https://app.shippable.com/pricing.html) - starts with **25$/month.** (2 concurrent builds, &infin; projects, &infin; build time)
* [Gitlab CI](https://about.gitlab.com/products/) - starts with **15$/month.** (2 concurrent builds, &infin; projects, &infin; build time)
* [Circle CI](https://circleci.com/pricing/) - starts with **39$/month.** (2 concurrent builds, &infin; projects, 500 minutes build time per month)
* [Nevercode.io](https://nevercode.io/pricing/) - starts with **5$/month.** (1 concurrent build, &infin; projects, 90 min. max build time)
* [Drone.io](https://drone.io/pricing) - starts with **25$/month.** (1 concurrent build, 5 private projects, &infin; build time)
* [Buddybuild](https://www.buddybuild.com/pricing/) - starts with **79$/month.** (1 concurrent build, &infin; projects, &infin; build time)

#### Presentation

I gave a talk back in 2016 and this repository was a technical part for it. I am describing there configured CI's
and providing my personal opinion about which CI to choose depending on your needs (in :ru: language ).

It doesn't contain information about ALL existing CI services, but should be a good start.

[![CI comparison presentation](http://img.youtube.com/vi/81G_C1J5hQ4/0.jpg)](http://www.youtube.com/watch?v=81G_C1J5hQ4)

In few words:
- [Jenkins](https://jenkins.io/)/[TeamCity](https://www.jetbrains.com/teamcity/) for complex workflow
- [Travis CI](https://travis-ci.org/)/[Circle CI](https://circleci.com/) for open-source projects
- [Bitrise.io](https://bitrise.io/) for any workflow

---

### Checkstyle

Project uses custom Checkstyle [rules](https://github.com/vgaidarji/ci-matters/blob/master/app/config/checkstyle/checkstyle.xml).

---

### Fabric/Crashlytics project configuration

In order to upload APK to Crashlytics project should have following configuration:
`${projectDir}/fabric.properties` file with `apiSecret` and `io.fabric.ApiKey` in AndroidManifest.xml([1](https://github.com/vgaidarji/ci-matters/blob/master/app/src/main/AndroidManifest.xml#L17),
[2](https://github.com/vgaidarji/ci-matters/blob/master/app/build.gradle#L59)) file.
**Both keys should not be uploaded to the repository for security reasons!**

Pass both parameters to your build from command line:

    ./gradlew -PfabricApiKey="YOUR_API_KEY" -PfabricApiSecret="YOUR_API_SECRET" crashlyticsUploadDistributionDebug

or export these keys as environment variables on a build machine
and they will be automatically read from there during the build (no need to pass keys as parameters in this case).

------

### Coveralls

[![Coverage Status](https://coveralls.io/repos/github/vgaidarji/ci-matters/badge.svg)](https://coveralls.io/github/vgaidarji/ci-matters)

`Coveralls` provides test coverage information. `COVERALLS_REPO_TOKEN` environment variable should be exported on the build machine.
