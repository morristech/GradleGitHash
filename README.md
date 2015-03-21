[![Build Status](https://travis-ci.org/Maddoc42/GradleGitHash.svg?branch=master)](https://travis-ci.org/Maddoc42/GradleGitHash)
[ ![Download](https://api.bintray.com/packages/maddoc42/maven/githash/images/download.svg) ](https://bintray.com/maddoc42/maven/githash/_latestVersion)

# GradleGitHash
A Gradle plugin for creating Java source files that contain the current Git commit hash.

## How to use
Add the following to your ```build.gradle```

```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'de.bitdroid.githash:plugin:<GitHash version>'
    }
}

apply plugin: 'de.bitdroid.githash'

gitHash {
    packageName = "<my.fancy.package>"
}
```

This will add a ```gitHash``` task to Gradle which depends on ```compileJava``` and listens to changes in the ```<projectDir>/.git/HEAD``` file and the ```<projectDir>/.git/refs/``` directrory.

The task will generate a file called ```GitConstants.java``` under ```target/generated-sources/githash/<my.fancy.pacakge>``` which looks like

```Java
package de.bitdroid.githash.example;

public final class GitConstants {

  public static final String COMMIT_HASH = "70c5a1f5c6fc";
  public static final String VERSION = "0.1.2";

  private GitConstants() { }
}
```

where `COMMIT_HASH` is the current git commit hash and `VERSION` and project version set in the gradle build file (if any).

See also the [```example```](https://github.com/Maddoc42/GradleGitHash/tree/master/example) directory.


## Configuration

The default settings for the plugin are as follows:

```groovy
gitHash {
    packageName = "my.package"
    outputDir = file('target/generated-sources/githash')
    version = project.version

    def gitFolder = "${project.rootProject.projectDir}/.git"
    gitHeadFile = file(gitFolder + '/HEAD')
    gitRefsDir = file(gitFolder + '/refs/')
}
```


## License
Copyright 2015 Philipp Eichhorn 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
