#!/bin/bash
echo "Building BeardAch"

#Get Initial version
VERSION=`git describe --tags`
TARGET_DIR="/home/james/bukkit/plugins/BeardAch-0.0.1-SNAPSHOT.jar"

#Add Uncommited  and DEV tags
# Update the index
    git update-index -q --ignore-submodules --refresh
    err=0

    # Disallow unstaged changes in the working tree
    if ! git diff-files --quiet --ignore-submodules --
    then

        git diff-files --name-status -r --ignore-submodules
        err=1
    fi

    # Disallow uncommitted changes in the index
    if ! git diff-index --cached --quiet HEAD --ignore-submodules --
    then

        git diff-index --cached --name-status -r --ignore-submodules HEAD --
        err=1
    fi

    if [ $err = 1 ]
    then
        VERSION=$VERSION"-DEV-"`date +%s`
        echo  "ALERT: Building Uncommited changes!"

    fi



#Update plugin.yml
cp src/main/resources/plugin.yml plugin.yml
sed 's/{VERSION}/'$VERSION'/' plugin.yml > src/main/resources/plugin.yml 

#Build it
mvn package

#reset template
cp plugin.yml src/main/resources/plugin.yml

echo "Installing to local bukkit server"
cp target/BeardAch-0.0.1-SNAPSHOT.jar $TARGET_DIR

echo "Done"
echo "Version: " $VERSION
echo "Source file size: "` du -hs src/`
