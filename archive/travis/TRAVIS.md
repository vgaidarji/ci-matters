# Travis CI

[![Build Status](https://travis-ci.org/vgaidarji/ci-matters.svg?branch=master)](https://travis-ci.org/vgaidarji/ci-matters)

#### Configuration

Travis CI provides [Building and Android project](https://docs.travis-ci.com/user/languages/android) article which describes the basics.
All what's needed to build the project on Travis CI is [.travis.yml](https://github.com/vgaidarji/ci-matters/tree/master/.travis.yml) file.
Syntax of `.travis.yml` is very self-explanatory. 

Travis CI understands when the change is pushed to github and starts the build automatically.

##### Environment variables 

In order to build the project we need to have environment variables set in project settings:
 
 <img src="/archive/travis/screenshots/travis_env_variables.png">
