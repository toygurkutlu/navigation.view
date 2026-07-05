# NavigationView

Requires minimum **Java 17**.

`NavigationView` allows you to create either a simple `ListView` or a tree-like appearance equipped with an expand/collapse mechanism.

* **ListView:** Use `NavItem` objects that contain only a title (can optionally include a title icon).
* **Tree-like:** Use `NavItem` objects that contain both a title and subtitles (can optionally include icons for both).

`NavigationView` uses a `navName` property for uniquely identifying each instance by its name. It works directly with the `NavItem` data class.

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

## How to Use

<details>
<summary><b>1. Add to your project</b></summary>

<blockquote>

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
      implementation 'com.github.toygurkutlu:navigation.view:1.0.2'
  }
  ```
  
</details>

<details>
<summary><b>gradle.kts</b></summary>

* ### Repositories
  Add it in your `build.gradle.kts` at the end of repositories:

  ```kotlin
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

  ```kotlin
  dependencies {
      implementation("com.github.toygurkutlu:navigation.view:1.0.2")
  }
  ```
  
</details>

<details>
<summary><b>maven</b></summary>

* ### Repositories
  Add to pom.xml

  ```xml
  <repositories>
      <repository>
          <id>jitpack.io</id>
          <url>https://jitpack.io</url>
      </repository>
  </repositories>
  ```

* ### Dependency
  Add the dependency:

  ```xml
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

  ```scala
  resolvers += "jitpack" at "https://jitpack.io"
  ```

* ### Dependency
  Add the dependency:

  ```scala
  libraryDependencies += "com.github.toygurkutlu" % "navigation.view" % "1.0.2"	
  ```
  
</details>

<details>
<summary><b>leiningen</b></summary>

* ### Repositories
  Add it in your project.clj at the end of repositories:

  ```clojure
  :repositories [["jitpack" "https://jitpack.io"]]
  ```

* ### Dependency
  Add the dependency:

  ```clojure
  :dependencies [[com.github.toygurkutlu/navigation.view "1.0.2"]]	
  ```
  
</details>

</blockquote>
</details>

<details>
<summary><b>2. Prepare your data</b></summary>

<blockquote>
<br>
	
Do not use subtitles if you want <code>ListView</code> apperance:

```create
String[] titles = getTitles();
Icon[] icons = getTitleIcons();

NavItem[] items = new NavItem[titles.length];

for(int i = 0; i < titles.length; i++){
     String title = titles[i];
     Icon icon = icons[i];

     items[i] = new NavItem(title, icon, null, null);
}
```

	
Use subtitles if you want <code>Tree-like</code> apperance:

```create
String[] titles = getTitles();
Icon[] icons = getTitleIcons();

String[][] subtitles = getSubtitles();
Icon[][] subtitleIcons = getSubtitleIcons();

NavItem[] items = new NavItem[titles.length];

for(int i = 0; i < titles.length; i++){
     String title = titles[i];
     Icon icon = icons[i];

     String[] subs = subtitles[i];
     Icon[] subIcons = subtitleIcons[i];

     items[i] = new NavItem(title, icon, subs, subIcons);
}
```

</blockquote>
</details>

<details>
<summary><b>3. Create NavigationView</b></summary>



<blockquote><br>

Create your own <code>NavigationView</code> and use your data (<code>NavItem[] items</code>).
<br>

```create
NavigationView nav = new NavigationView("myNavigation", items);
```
</blockquote>
</details>

<details>
<summary><b>4. Add ClickListener to your NavigationView</b></summary>



<blockquote><br>

For handling title and subtitle click events, add OnItemClickListener to your <code>NavigationView</code>.
<br>

```create
nav.setOnItemClickListener(new NavigationView.OnItemClickListener() {
            @Override
            public void onTitleClick(int titleIndex) {
                //Handle title click events here.
            }

            @Override
            public void onSubtitleClick(int titleIndex, int subtitleIndex) {
                //Handle subtitle click events here.
            }
});
```
</blockquote>
</details>

<details>
<summary><b>5. Add the NavigationView to its container.</b></summary>



<blockquote><br>

```create
container.add(nav);
```
</blockquote>
</details>
