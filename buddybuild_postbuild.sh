#!/usr/bin/env bash

echo "Send coverage information to Coveralls"
./gradlew jacocoTestReport coveralls
echo "Static code analysis"
./gradlew lintDebug buildDashboard
echo "Send build to Fabric.Beta (disabled, doesn't work)"
# doesn't work, no signed APK
# ./gradlew crashlyticsUploadDistributionDebug
