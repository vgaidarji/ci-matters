#!/bin/bash

PATH_TO_SDK=/Users/Shared/Jenkins/Home/tools/android-sdk

/usr/bin/expect -c '
set timeout -1   ;
spawn '${PATH_TO_SDK}'/tools/android update sdk --no-ui --all --filter '"${SDK_LIST}"' ;
  expect {
    "Do you accept the license" { exp_send "y\r" ; exp_continue }
    eof
}
'
