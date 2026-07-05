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
	
Do not use subtitles if you want `ListView` apperance:

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
Use subtitles if you want `Tree-like` apperance:

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

Create your own `NavigationView` and use your data (`NavItem[] items`).
<br>

```create
NavigationView nav = new NavigationView("myNavigation", items);
```
</blockquote>
</details>

<details>
<summary><b>4. Add ClickListener to your NavigationView</b></summary>

<blockquote><br>

For handling title and subtitle click events, add OnItemClickListener to your `NavigationView`.
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

## Managers

<details>
<summary><b>1. NavStateManager</b></summary>
<blockquote><br>
<code>NavStateManager</code> is responsible for managing and persisting click behaviors and selection states across all navigation items.<br><br>

* **Works in real time:** Selection states are updated and synchronized instantly upon each click.
* **Automated storage:** States are securely stored in the user node of Java Preferences (`NavStateManager.class`).
* **Zero config:** No user intervention or manual saving is required.

<details>
<summary><b>setter methods</b></summary>

* `setNavCanCollapse(String navName, boolean canCollapse)`<br>
Sets whether the collapse mechanism is enabled.<br>

* `setNavCollapsed(String navName, boolean isVisible)`<br>
Sets `NavigationView`'s collapse status.<br>

* `setSelectedTitleIndex(String navName, int index)`<br>
Sets the selected title's index of the `NavigationView` with the specified name.

* `setSelectedSubIndex(String navName, int subIndex)`<br>
Sets the selected subtitle's index from the `NavigationView` with the specified name.

* `setSubCollapsed(String navName, int titleIndex, boolean collapsed)`<br>
Sets the collapse status of the specified `NavigationView` that matches the given `titleIndex`.
  
</details>

<details>
<summary><b>getter methods</b></summary>

* `navCanCollapse(String navName)`<br>
Gets whether the collapse mechanism is enabled for the specified `NavigationView`.<br>

* `isNavCollapsed(String navName)`<br>
Gets `NavigationView`'s collapse status.<br>

* `getSelectedTitleIndex(String navName)`<br>
Gets the selected title's index from the `NavigationView` with the specified name.<br>

* `getSelectedSubIndex(String navName)`<br>
Gets the selected subtitle's index from the `NavigationView` with the specified name.<br>

* `isSubCollapsed(String navName, int titleIndex)`<br>
Gets the collapse status of the specified `NavigationView` that matches the given `titleIndex`.
  
</details>
</details>




<details>
<summary><b>2. NavStyleManager</b></summary>
<blockquote><br>
<code>NavStyleManager</code> is responsible for managing, applying, and persisting color attributes.<br><br>

* **Works in real time:** Selection states are updated and synchronized instantly upon each click.
* **Automated storage:** States are securely stored in the user node of Java Preferences (`NavStateManager.class`).
* **Zero config:** No user intervention or manual saving is required.

<details>
<summary><b>setter methods</b></summary>

* `setSelectedTheme(int themeId)`<br>
Sets the theme that matches the specified `themeId` as selected.

* `setSelectedTheme(String themeName)`<br>
Sets the theme that matches the specified `themeName` as selected..<br>

</details>

<details>
<summary><b>getter methods</b></summary>

* `getDefaultCollapsedIcon()`<br>
Gets the default collapsed icon.<br>

* `getDefaultExpandedIcon()`<br>
Gets the default expanded icon.<br>

* `getSelectedThemeId()`<br>
Gets the ID of the selected theme.<br>

* `getSelectedTheme()`<br>
Gets the theme that matches the `SELECTED_THEME_ID`.<br>

* `getSelectedThemeName(int themeId)`<br>
Gets the name of the selected theme.<br>

* `getThemeById(int themeId)`<br>
Gets the theme that matches the specified `themeId`.<br>

* `getUserTheme(String name)`<br>
Gets the user theme that matches the specified name.<br>

* `getUserThemes()`<br>
Gets all user-defined themes.<br>

* `getUserThemeList()`<br>
Gets the list of names of the user-defined themes.<br>

* `getUserThemeId(String themeName)`<br>
Gets the id of the user theme.<br>

* `getUserThemeIndex(String themeName)`<br>
Gets the index of the theme name from `UserThemeList`.<br>

* `getThemeId(String themeName)`<br>
Gets the id of the theme.<br>

* `getAllThemeList()`<br>
Gets all system and user defined theme names.<br>

* `getSystemThemes()`<br>
Gets all pre-defined system themes.<br>

* `getSystemTheme(int themeId)`<br>
Gets the pre-defined system theme.<br>

* `getDefaultTheme()`<br>
Gets the pre-defined default Dark Theme.<br>

</details>

<details>
<summary><b>other methods</b></summary>

* `createUserTheme(NavStyle theme, String name)`<br>
Creates a new user theme and persists its data to Java Preferences..<br>

* `removeUserTheme(String themeName)`<br>
Removes the theme with the specified name from the user theme list and deletes its persisted data from Java Preferences.<br>

* `renameUserTheme(String oldName, String newName)`<br>
Renames the specified theme with a new name and automatically updates its corresponding entry in Java Preferences.<br>

* `duplicateUserTheme(String themeName, String newThemeName)`<br>
Duplicates the specified user theme and creates a new one with a new name.<br>

* `renameUserTheme(String oldName, String newName)`<br>
Renames the specified theme with a new name and automatically updates its corresponding entry in Java Preferences.

</details>

</blockquote>
</details>

