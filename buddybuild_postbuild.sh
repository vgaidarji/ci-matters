#!/usr/bin/env bash

# recompile "debug" to avoid ParseException in auto-generated classes
# can we avoid project recompilation here?
./gradlew crashlyticsUploadDistributionDebug

echo "Send coverage information to Coveralls"
./gradlew jacocoTestReport coveralls
echo "Static code analysis"
./gradlew checkstyle pmd jdepend lintDebug buildDashboard
echo "Send build to Fabric.Beta"
