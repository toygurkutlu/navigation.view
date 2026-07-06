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

[Click here](https://jitpack.io/#toygurkutlu/navigation.view/1.0.2) to check JitPack.
<blockquote>
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

## Customizing NavigationView Appearance

* The color preferences of the `NavigationView` can be changed by using themes.
* You can either use pre-defined system themes or create your own custom themes.
* `NavStyle` is an object that represents theme attributes.
* `NavStyleManager` is responsible for creating, storing, and handling `NavigationView` themes.

<details>
<summary><b>1. Pre-defined system themes</b></summary>
	<blockquote>
	There are 4 different pre-defined system themes:<br>
		
<ul>
<li>
	
`NavThemes.Dark()`<br></li>
<li>

`NavThemes.DarkOrange()`<br></li>
<li>
	
`NavThemes.Light()`<br></li>
<li>
	
`NavThemes.Ivory()`</li></ul>

</blockquote>
</details>

<details>
<summary><b>2. Create a user theme</b></summary><br>

<blockquote>
	
You can create a new theme with creating your own `NavStyle`. `NavStyle` object contains following 3 objects:<br>

<details>
<summary><b>NavAttributes</b></summary><br>

Body attributes of `NavigationView`.

<blockquote>
<details>
<summary><b>Fields</b></summary>
	
* `String background`
 Represents the hexadecimal code representation of the background color for the `NavigationView`. 

* `isCollapseIconsColored`
`true` if the navigation icons (collapsed and expanded) use the `collapseIconsColor`; `false` if they use their own original colors.

* `String collapseIconsColor`
Represents the hexadecimal representation of the colors for the collapse and expanded icons.

</details>

<details>
<summary><b>setter methods</b></summary>
	
* `setBackground(Color background)`
 Takes the `background` object as a parameter and coverts it to a hexadecimal code.

* `setCollapseIconsColored(boolean isCollapsedIconsColored)`
`true` if the navigation icons (collapsed and expanded) use the `collapseIconsColor`; `false` if they use their own original colors.

* `setCollapseIconsColor(Color collapseIconsColor)`
Takes the `collapseIconsColor` object as a parameter and converts it to a hexadecimal code.

</details>

<details>
<summary><b>getter methods</b></summary>
	
* `getBackground()`
Returns the background color of the `NavigationView`.

* `isCollapseIconsColored()`
Returns `true` if the navigation icons (collapsed and expanded) use the `collapseIconsColor`; returns `false` if they use their own original colors.

* `getCollapseIconsColor()`
Returns the collapse icons color of the `NavigationView`.

</details>

<details>
<summary><b>Create NavAttributes</b></summary><br>
	
```create
private NavAttributes createNavAttributes() {
        NavAttributes attr = new NavAttributes();

        attr.setBackground(new Color(100, 100, 100));
        attr.setCollapseIconsColored(true);
        attr.setCollapseIconsColor(new Color(200, 175, 200));

        return attr;
}
```
</blockquote>
</details>


<details>
<summary><b>NavTitleAttributes</b></summary><br>

Title attributes of `NavigationView`.

<blockquote>
<details>
<summary><b>Fields</b></summary>

* `String foreground`<br>
 Represents the hexadecimal representation of the foreground color for the title.

* `String background`<br>
Represents the hexadecimal representation of the background color for the title.

* `String selectedForeground`<br>
Represents the hexadecimal representation of the title's selected item foreground color. This foreground color is used to indicate which title is selected when a user clicks it. 

* `String selectedBackground`<br>
Represents the hexadecimal representation of the title's selected item background color. This background color is used to indicate which title is selected when a user clicks it. Note that if subtitles present, the Title's background color will only change if a subtitle is selected.

* `String hoverForeground`<br>
Represents the hexadecimal representation of the title's hover foreground color. This color is used to highlight the title by changing its foreground when the mouse hovers over its area.

* `String hoverBackground`<br>
Represents the hexadecimal representation of the title's hover background color. This color is used to highlight the title by changing its background when the mouse hovers over its area.

* `TextPosition textPosition`<br>
The text position of the title. Can be either `TextPosition.LEFT` (title followed by the icon) or `TextPosition.RIGHT` (icon followed by the title).

* `int iconTextGap`<br>
The space between the title and the icon.

* `int gapTop`<br>
The space between the title and the item above it.

* `int gapLeft`<br>
Left indentation space for the title relative to the `NavigationView`.

* `int gapBottom`<br>
The space between the title and the item below it.

* `int gapRight`<br>
The right indentation space for the title relative to the `NavigationView`.

* `String fontFamily`<br>
 The font family of the title.

* `int fontStyle`<br>
The font style of the title.

* `int fontSize`<br>
The font size of the title.

</details>

<details>
<summary><b>setter methods</b></summary>
	
* `setForeground(Color foreground)`<br>
 Takes the `foreground` object as a parameter and coverts it to a hexadecimal code.

* `setBackground(Color background)`<br>
Takes the `background` object as a parameter and coverts it to a hexadecimal code.

* `setSelectedForeground(Color selectedBackground)`<br>
Takes the `selectedForeground` object as a parameter and converts it to a hexadecimal code.

* `setSelectedBackground(Color selectedForeground)`<br>
 Takes the `selectedBackground` object as a parameter and coverts it to a hexadecimal code.

* `setHoverForeground(Color hoverForeground)`<br>
Takes the `hoverForeground` object as a parameter and coverts it to a hexadecimal code.

* `setHoverBackground(Color hoverBackground)`<br>
Takes the `hoverBackground` object as a parameter and coverts it to a hexadecimal code.

* `setTextPosition(TextPosition textPosition)`<br>
 Sets the the position of the title.

* `setIconTextGap(int iconTextGap)`<br>
Sets the space (in pixels) between title and icon.

* `setGapTop(int gapTop)`<br>
Sets the space (in pixels) between the title and the previous item (the top of the `NavigationView`, the previous title, or the last subtitle of the previous group).

* `setGapLeft(int gapLeft)`<br>
Sets the left indentation space (in pixels) for the title relative to the `NavigationView`.

* `setGapBottom(int gapBottom)`<br>
Sets the space (in pixels) between the title and the next item (the next title, the first subtitle of the current group, or the bottom of the `NavigationView`).

* `setGapRight(int gapRight)`<br>
Sets the right indentation space (in pixels) for the title relative to the `NavigationView`.

* `setFont(Font font)`<br>
Takes the `Font` object as a parameter and sets the following fields: `fontFamily`, `fontStyle`, and `fontSize`.

* `setFontFamily(String fontFamily)`<br>
Sets the `fontFamily` independently of the font configuration method described above (`setFont(Font font)`).

* `setFontStyle(int fontStyle)`<br>
Sets the `fontStyle` independently of the font configuration method described above (`setFont(Font font)`).

* `setFontSize(int fontSize)`<br>
Sets the `fontSize` independently of the font configuration method described above (`setFont(Font font)`).

</details>

<details>
<summary><b>getter methods</b></summary>
	
* `getForeground()`<br>
 Returns the foreground color of the title.

* `getBackground()`<br>
Returns the background color of the title.

* `getSelectedForeground()`<br>
Returns the foreground color of the selected title.

* `getSelectedBackground()`<br>
Returns the background color of the selected title.

* `getHoverForeground()`<br>
Returns the hover foreground color of the title.

* `getHoverBackground()`<br>
Returns the hover background color of the title.

* `getTextPosition()`<br>
Returns the `TextPosition` of the title according to its icon.

* `getIconTextGap()`<br>
Returns the space (in pixels) between title and icon.

* `getGapTop()`<br>
Returns the space (in pixels) between the title and the previous item (the top of the `NavigationView`, the previous title, or the last subtitle of the previous group).

* `getGapLeft()`<br>
Returns the left indentation space (in pixels) for the title relative to the `NavigationView`.

* `getGapBottom()`<br>
Returns the space (in pixels) between the title text and the next item (the next title, the first subtitle of the current group, or the bottom of the `NavigationView`).

* `getGapRight()`<br>
Returns the right indentation space (in pixels) for the title relative to the `NavigationView`.

* `getFont()`<br>
Returns the title's `Font`.

* `getFontFamily()`<br>
Returns the font family of the title's `Font`.

* `getFontStyle()`<br>
Returns the font style of the title's `Font`.

* `getFontSize()`<br>
Returns the font size of the title's `Font`.

</details>

<details>
<summary><b>Create NavTitleAttributes</b></summary><br>
	
```create
private NavTitleAttributes createTitleAttributes() {
        NavTitleAttributes attr = new NavTitleAttributes();

        attr.setForeground(new Color(150, 225, 150));
        attr.setBackground(new Color(41, 41, 41));
        attr.setSelectedForeground(new Color(190, 200, 190));
        attr.setSelectedBackground(new Color(5, 5, 5));
        attr.setHoverBackground(new Color(85, 50, 85));
        attr.setHoverForeground(new Color(41, 41, 41));
        attr.setTextPosition(TextPosition.RIGHT);
        attr.setIconTextGap(5);
        attr.setGapTop(5);
        attr.setGapLeft(5);
        attr.setGapBottom(5);
        attr.setGapRight(5);
        attr.setFont(new Font("Sky Sans Medium Small Caps", Font.BOLD, 16));

        return attr;
}
```
</blockquote>
</details>

<details>
<summary><b>NavSubtitleAttributes</b></summary><br>

Subtitle attributes of `NavigationView`.

<blockquote>
<details>
<summary><b>Fields</b></summary>

* `String foreground`<br>
 Represents the hexadecimal representation of the foreground color for the subtitle.

* `String background`<br>
Represents the hexadecimal representation of the background color for the subtitle.

* `String selectedForeground`<br>
Represents the hexadecimal representation of the subtitle's selected item foreground color. This foreground color is used to indicate which subtitle is selected when a user clicks it. 

* `String selectedBackground`<br>
Represents the hexadecimal representation of the subtitle's selected item background color. This background color is used to indicate which subtitle is selected when a user clicks it.

* `String hoverForeground`<br>
Represents the hexadecimal representation of the subtitle's hover foreground color. This color is used to highlight the subtitle by changing its foreground when the mouse hovers over its area.

* `String hoverBackground`<br>
Represents the hexadecimal representation of the subtitle's hover background color. This color is used to highlight the subtitle by changing its background when the mouse hovers over its area.

* `TextPosition textPosition`<br>
The text position of the subtitles. Can be either `TextPosition.LEFT` (subtitle followed by the icon) or `TextPosition.RIGHT` (icon followed by the subtitle).

* `int iconTextGap`<br>
The space between the subtitle and the icon.

* `int gapTop`<br>
The space between the subtitle and the item above it.

* `int gapLeft`<br>
Left indentation space for the subtitle relative to the `NavigationView`.

* `int gapBottom`<br>
The space between the subtitle and the item below it.

* `int gapRight`<br>
The right indentation space for the subtitle relative to the `NavigationView`.

* `String fontFamily`<br>
 The font family of the subtitle.

* `int fontStyle`<br>
The font style of the subtitle.

* `int fontSize`<br>
The font size of the subtitle.

</details>

<details>
<summary><b>setter methods</b></summary>
	
* `setForeground(Color foreground)`<br>
 Takes the `foreground` object as a parameter and coverts it to a hexadecimal code.

* `setBackground(Color background)`<br>
Takes the `background` object as a parameter and coverts it to a hexadecimal code.

* `setSelectedForeground(Color selectedBackground)`<br>
Takes the `selectedForeground` object as a parameter and converts it to a hexadecimal code.

* `setSelectedBackground(Color selectedForeground)`<br>
 Takes the `selectedBackground` object as a parameter and coverts it to a hexadecimal code.

* `setHoverForeground(Color hoverForeground)`<br>
Takes the `hoverForeground` object as a parameter and coverts it to a hexadecimal code.

* `setHoverBackground(Color hoverBackground)`<br>
Takes the `hoverBackground` object as a parameter and coverts it to a hexadecimal code.

* `setTextPosition(TextPosition textPosition)`<br>
 Sets the the position of the subtitle.

* `setIconTextGap(int iconTextGap)`<br>
Sets the space (in pixels) between subtitle and icon.

* `setGapTop(int gapTop)`<br>
Sets the space (in pixels) between the subtitle and the previous item (the current title of the same group, or previous subtitle of the same group).

* `setGapLeft(int gapLeft)`<br>
Sets the left indentation space (in pixels) for the subtitle relative to the `NavigationView`.

* `setGapBottom(int gapBottom)`<br>
Sets the space (in pixels) between the subtitle and the next item (the next subtitle of the same group, the next title of the next group, or the bottom of the `NavigationView`).

* `setGapRight(int gapRight)`<br>
Sets the right indentation space (in pixels) for the subtitle relative to the `NavigationView`.

* `setFont(Font font)`<br>
Takes the `Font` object as a parameter and sets the following fields: `fontFamily`, `fontStyle`, and `fontSize`.

* `setFontFamily(String fontFamily)`<br>
Sets the `fontFamily` independently of the font configuration method described above (`setFont(Font font)`).

* `setFontStyle(int fontStyle)`<br>
Sets the `fontStyle` independently of the font configuration method described above (`setFont(Font font)`).

* `setFontSize(int fontSize)`<br>
Sets the `fontSize` independently of the font configuration method described above (`setFont(Font font)`).

</details>

<details>
<summary><b>getter methods</b></summary>
	
* `getForeground()`<br>
 Returns the foreground color of the subtitle.

* `getBackground()`<br>
Returns the background color of the subtitle.

* `getSelectedForeground()`<br>
Returns the foreground color of the selected subtitle.

* `getSelectedBackground()`<br>
Returns the background color of the selected subtitle.

* `getHoverForeground()`<br>
Returns the hover foreground color of the subtitle.

* `getHoverBackground()`<br>
Returns the hover background color of the subtitle.

* `getTextPosition()`<br>
Returns the `TextPosition` of the subtitle according to its icon.

* `getIconTextGap()`<br>
Returns the space (in pixels) between subtitle and icon.

* `getGapTop()`<br>
Returns the space (in pixels) between the subtitle and the previous item (the title of the same group or the previous subtitle of the same group).

* `getGapLeft()`<br>
Returns the left indentation space (in pixels) for the subtitle relative to the `NavigationView`.

* `getGapBottom()`<br>
Returns the space (in pixels) between the subtitle and the next item (the next subtitle of the same group, the next title of the next group, or the bottom of the `NavigationView`).

* `getGapRight()`<br>
Returns the right indentation space (in pixels) for the subtitle relative to the `NavigationView`.

* `getFont()`<br>
Returns the subtitle's `Font`.

* `getFontFamily()`<br>
Returns the font family of the subtitle's `Font`.

* `getFontStyle()`<br>
Returns the font style of the subtitle's `Font`.

* `getFontSize()`<br>
Returns the font size of the subtitle's `Font`.

</details>

<details>
<summary><b>Create NavSubtitleAttributes</b></summary><br>
	
```create
private NavSubtitleAttributes createSubAttributes() {
        NavSubtitleAttributes attr = new NavSubtitleAttributes();

        attr.setForeground(new Color(150, 225, 150));
        attr.setBackground(new Color(41, 41, 41));
        attr.setSelectedForeground(new Color(190, 200, 190));
        attr.setSelectedBackground(new Color(15, 75, 15));
        attr.setHoverBackground(new Color(250, 250, 250));
        attr.setHoverForeground(new Color(75, 75, 55));
        attr.setTextPosition(TextPosition.RIGHT);
        attr.setIconTextGap(5);
        attr.setGapTop(5);
        attr.setGapLeft(10);
        attr.setGapBottom(5);
        attr.setGapRight(10);
        attr.setFont(new Font("Sky Sans Medium Small Caps", Font.BOLD, 14));

        return attr;
}
```
</blockquote>
</details>


<details>
<summary><b>NavStyle</b></summary><br>

The theme of the `NavigationView`.

<blockquote>
<details>
<summary><b>Fields</b></summary>

* `NavAttributes navAttributes`<br>
Body attributes of the `NavigationView`.

* `NavTitleAttributes titleAttributes`<br>
Title attributes of the `NavigationView`.

* `NavSubtitleAttributes subtitleAttributes`<br>
  Subtitle attributes of the `NavigationView`.

</details>

<details>
<summary><b>setter methods</b></summary>
	
* `setNavAttributes(NavAttributes navAttributes)`<br>
Sets the body attributes for `NavigationView`.

* `setTitleAttributes(NavTitleAttributes titleAttributes)`<br>
Sets the title attributes for `NavigationView`.

* `setSubtitleAttributes(NavSubtitleAttributes subtitleAttributes)`<br>
Sets the subtitle attributes for `NavigationView`.
</details>

<details>
<summary><b>getter methods</b></summary>
	
* `getNavAttributes()`<br>
Returns the body attributes of the `NavigationView`.

* `getTitleAttributes()`<br>
Returns the title attributes of the `NavigationView`.

* `getSubtitleAttributes()`<br>
Returns the subtitle attributes of the `NavigationView`.

</details>

<details>
<summary><b>Create NavStyle</b></summary><br>

* Prepare your style attributes:
```create
NavAttributes bodyAttr = createNavAttributes();
NavTitleAttributes titleAttr = createTitleAttributes();
NavSubtitleAttributes subAttr = createSubAttributes();
```
* Either use the no-argument constructor to create a `NavStyle` instance and invoke its setter methods to configure individual attributes:
```create
NavStyle myStyle = new NavStyle();
myStyle.setNavAttributes(bodyAttr);
myStyle.setTitleAttributes(titleAttr);
myStyle.setSubtitleAttributes(subAttr);
```
* Or use the all-arguments constructor to create a `NavStyle` instance and initialize all attributes at once:
```create
NavAttributes bodyAttr = createNavAttributes();
NavTitleAttributes titleAttr = createTitleAttributes();
NavSubtitleAttributes subAttr = createSubAttributes();

NavStyle myStyle = new NavStyle(bodyAttr, titleAttr, subAttr);
```
</blockquote>

</details>

<details>
<summary><b>NavStyleManager</b></summary><br>

A helper class for responsible of handling `NavigationView` themes.

<blockquote>

Create a user theme via using `NavStyleManager`'s `createUserTheme(NavStyle style, String name)` method.
```create
NavStyleManager.createUserTheme(myStyle, "my_theme");
```

> **Note:** The `createUserTheme()` method creates a user theme with the specified name and sets it as the selected theme at the same time.

</blockquote>
</details>

</blockquote>
</details>

<details>
<summary><b>3. Set a theme</b></summary><br>

<blockquote>

You can use either the theme ID or the theme name to apply a theme (supported for both system and user-defined themes).	<br>
	
* Use theme id to set a theme:
	
```create
NavStyleManager.setSelectedTheme(NavThemes.LIGHT_THEME);
NavStyleManager.setSelectedTheme(4);
NavStyleManager.setSelectedTheme(NavStyleManager.getUserThemeId("my_theme"));
```

* Use theme name to set a theme:
	
```create
NavStyleManager.setSelectedTheme(NavThemes.LIGHT);
NavStyleManager.setSelectedTheme("my_theme");
```
The selected system theme's ID is stored in Java Preferences and will be automatically loaded on startup.

> **Note:** Pre-defined system theme IDs are 0-indexed (ranging from 0 to 3). User-defined theme IDs start from 4 and increment sequentially.

</details>
</blockquote>
</details>

## Theme Operations
<details>
<summary><b>1. Create a User Theme</b></summary>
<br>
<blockquote>
	
Creates a theme with the given attributes and associates it with the specified name.<br>

```create
NavStyleManager.createUserTheme(style, "my_theme");
```
> **Note:** This method also sets the created theme as the selected theme.

</blockquote>
</details>

<details>
<summary><b>2. Remove a User Theme</b></summary>
<br>
<blockquote>
	
Removes and deletes the theme with the specified name from Java Preferences.<br>

```create
NavStyleManager.removeUserTheme("my_theme");
```
> **Note:** This method also sets the default dark system theme as the selected theme.

</blockquote>
</details>

<details>
<summary><b>3. Rename a User Theme</b></summary>
<br>
<blockquote>
	
Renames an existing user theme and updates the user theme list in Java Preferences.<br>

```create
NavStyleManager.renameUserTheme("my_theme", "myDarkTheme");
```
</blockquote>
</details>

<details>
<summary><b>4. Duplicate a Theme</b></summary>
<br>
<blockquote>
	
Duplicates the theme with a new name, allowing to modify its attributes without creating a new theme from scratch.<br>

```create
NavStyle style = NavStyleManager.duplicateTheme("myDarkTheme", "duplicated_theme");
```
</blockquote>
</details>

<details>
<summary><b>5. Update a User Theme</b></summary>
<br>
<blockquote>
	
Updates specified user theme with the new `NavStyle`.<br>

```create
NavStyleManager.updateUserTheme("duplicated_theme", newStyle);
```
</blockquote>
</details>

</blockquote>
</details>

## Managers

<details>
<summary><b>1. NavStateManager</b></summary>
<br>
<blockquote>
	
`NavStateManager` is responsible for managing and persisting click behaviors and selection states across all navigation items.<br>

* **Works in real time:** Selection states are updated and synchronized instantly upon each click.
* **Automated storage:** States are securely stored in the user node of Java Preferences (`NavStateManager.class`).
* **Zero config:** No user intervention or manual saving is required.


<details>
<summary><b>setter methods</b></summary>
<blockquote>
	
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
  </blockquote>
</details>

<details>
<summary><b>getter methods</b></summary>
<blockquote>
	
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
  </blockquote>
</details>
</blockquote>
</details>

<details>
<summary><b>2. NavStyleManager</b></summary>
<br>
<blockquote>
		
`NavStyleManager` is responsible for managing, applying, and persisting color attributes.<br><br>

<details>
<summary><b>setter methods</b></summary>
<blockquote>
	
* `setSelectedTheme(int themeId)`<br>
Sets the theme that matches the specified `themeId` as selected.

* `setSelectedTheme(String themeName)`<br>
Sets the theme that matches the specified `themeName` as selected..<br>
</blockquote>
</details>

<details>
<summary><b>getter methods</b></summary>
<blockquote>

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

* `getTheme(String name)`<br>
Gets the theme that matches the specified name.<br>

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
</blockquote>

</details>

<details>
<summary><b>other methods</b></summary>
<blockquote>

* `createUserTheme(NavStyle theme, String name)`<br>
Creates a new user theme and persists its data to Java Preferences..<br>

* `removeUserTheme(String themeName)`<br>
Removes the theme with the specified name from the user theme list and deletes its persisted data from Java Preferences.<br>

* `renameUserTheme(String oldName, String newName)`<br>
Renames the specified theme with a new name and automatically updates its corresponding entry in Java Preferences.<br>

* `duplicateUserTheme(String themeName, String newThemeName)`<br>
Duplicates the specified user theme and creates a new one with a new name.
<blockquote>
</details>

</blockquote>
</details>
