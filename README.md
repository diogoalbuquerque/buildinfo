# buildinfo ℹ️
Shows the usage of the SpringBoot buildInfo extension and show information from the git repository.

### 1 - Adding the extension

Add this part to your project's plugins.

```kotlin
springBoot {
    buildInfo()
}
```

#### 1.1 - Configuring the actuator

To display build information, you need to configure the actuator to display the info endpoint.

Then in the **application.yaml** file add the code below:
```yaml
management:
  endpoints:
    web:
      exposure:
        include:
          - info
```


After adding and accessing **/actuator/info**, you will see the result below:

```json
{
  "build": {
    "artifact": "buildinfo",
    "name": "buildinfo",
    "time": "2022-03-29T20:24:30.979Z",
    "version": "0.0.1-SNAPSHOT",
    "group": "br.com.da"
  }
}
```

### 2 - Adding the plugin

Add the git properties plugin in the **build.gradle.kts file**

```kotlin
id("com.gorylenko.gradle-git-properties") version "2.4.0"
```

After adding the plugin, and accessing **/actuator/info** again, you will be able to see the git properties in your endpoint.

```json
{
  "git": {
    "branch": "main",
    "commit": {
      "id": "c10a9d9",
      "time": "2022-03-29T20:20:59Z"
    }
  },
  "build": {
    "artifact": "buildinfo",
    "name": "buildinfo",
    "time": "2022-03-29T20:25:21.935Z",
    "version": "0.0.1-SNAPSHOT",
    "group": "br.com.da"
  }
}
```

#### 2.1 - Customizing the information

To display more information you must configure the endpoint.

Then go again in the **application.yaml** file and configure it to display all the information in your repository.

```yaml
management:
  endpoints:
    web:
      exposure:
        include:
          - info
  info:
    git:
      mode: full
```

After that you need to go to the build.gradle.kts file and define which properties you would like to show.

```kotlin
gitProperties {
    val keyProperties =
        listOf("git.branch", "git.commit.time", "git.commit.id.abbrev", "git.commit.user.name", "git.remote.origin.url")
    keys = keyProperties
}
```

At the end accessing the address **/actuator/info** you will see a result similar to this.

```json
{
  "git": {
    "remote": {
      "origin": {
        "url": "https://github.com/diogoalbuquerque/buildinfo.git"
      }
    },
    "commit": {
      "user": {
        "name": "diogo.albuquerque"
      },
      "id": {
        "abbrev": "7f282e9"
      },
      "time": "2022-03-29T20:28:02Z"
    },
    "branch": "main"
  },
  "build": {
    "artifact": "buildinfo",
    "name": "buildinfo",
    "time": "2022-03-29T21:20:02.821Z",
    "version": "0.0.1-SNAPSHOT",
    "group": "br.com.da"
  }
}
```

### 3 - More information

[gradle-git-properties](https://github.com/n0mer/gradle-git-properties)