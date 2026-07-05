# NavigationView

Requires minimum **Java 17**.

`NavigationView` allows you to create either a simple `ListView` or a tree-like appearance equipped with an expand/collapse mechanism.

* **ListView:** Use `NavItem` objects that contain only a title (can optionally include a title icon).
* **Tree-like:** Use `NavItem` objects that contain both a title and subtitles (can optionally include icons for both).

`NavigationView` uses a `navName` property for uniquely identifying each instance by its name. It works directly with the `NavItem` data class.

### NavItem Structure
The `NavItem` class is a POJO that contains the following fields: `String title`, `Icon titleIcon`, `String[] subtitles`, and `Icon[] subtitleIcons`.

* `title`: The main text or group header.
* `titleIcon`: The icon for the main title (defaults to `null`).
* `subtitles`: The array of subtitle texts. Can be `null` (if `null`, a flat `ListView` appearance is achieved; otherwise, it renders as a `Tree-like` structure).
* `subtitleIcons`: The array of icons for the subtitles (can be `null`).

### Supported Features

#### 1. ListView Mode (Flat List)
* `NavigationView` overall collapse mechanism
* Standalone titles
* Optional title icons (configured by the user)

#### 2. Tree-like Mode (Grouped List)
* `NavigationView` overall collapse mechanism
* Group headers (Titles) with optional icons
* Hierarchical subtitles with optional subtitle icons
* Individual title expand/collapse container mechanism

### How to Use
[Click here](https://jitpack.io/#toygurkutlu/navigation.view/1.0.2) to check JitPack.

<details>
<summary><b>gradle</b></summary>

* ### Repositories
  Add the repository to your root `build.gradle` file:
  
  ```gradle
  dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
* ### Dependency
  Add the dependency to your module-level `build.gradle` file:

  ```gradle
  dependencies {
      implementation 'com.github.username:NavigationView:1.0.0'
  }
  ```
  
</details>

<details>
<summary><b>gradle.kts</b></summary>

* ### Repositories
Add it in your `build.gradle.kts` at the end of repositories:

  ```gradle
  dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url = uri("https://jitpack.io") }
		}
	}
  ```

* ### Dependency
  Add the dependency to your module-level `build.gradle.kts` file:

  ```gradle
  dependencies {
	        implementation("com.github.toygurkutlu:navigation.view:1.0.2")
	}
  ```
  
</details>

<details>
<summary><b>maven</b></summary>

* ### Repositories
Add to pom.xml

  ```gradle
  <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
  ```

* ### Dependency
  Add the dependency:

  ```gradle
  <dependency>
	    <groupId>com.github.toygurkutlu</groupId>
	    <artifactId>navigation.view</artifactId>
	    <version>1.0.2</version>
	</dependency>
  ```
  
</details>



<details>
<summary><b>sbt</b></summary>

* ### Repositories
Add it in your build.sbt at the end of resolvers:

  ```gradle
      resolvers += "jitpack" at "https://jitpack.io"
  ```

* ### Dependency
  Add the dependency:

  ```gradle
  	libraryDependencies += "com.github.toygurkutlu" % "navigation.view" % "1.0.2"	
  ```
  
</details>

<details>
<summary><b>leiningen</b></summary>

* ### Repositories
Add it in your project.clj at the end of repositories:

  ```gradle
    :repositories [["jitpack" "https://jitpack.io"]]
  ```

* ### Dependency
  Add the dependency:

  ```gradle
	  :dependencies [[com.github.toygurkutlu/navigation.view "1.0.2"]]	
  ```
  
</details>
