package com.kutlu;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.prefs.Preferences;

/**
 * Style Manager for NavigationView<br>
 * Either use pre-defined dark (or light) theme or create your own theme with available attributes.
 */
public class NavStyleManager {
    private static final Preferences PREF =
            Preferences.userNodeForPackage(NavStyleManager.class);
    private static final String SELECTED_THEME_ID = "nav.theme";
    private static final String USER_THEME_LIST = "nav.theme.user.list";
    private static final String USER_THEME = "nav.theme.user.";
    private static final int TOTAL_DEFAULT_THEMES = 4;
    private static final Gson GSON = new Gson();

    /**
     * Gets the default collapsed icon.
     *
     * @return The default collapsed icon, or {@code null} if the icon does not exist.
     */
    public static Icon getDefaultCollapsedIcon() {
        NavStyle theme = getSelectedTheme();
        Color c = theme.getNavAttributes().getCollapseIconsColor();

        return NavHelper.recolorIcon(new ImageIcon(Objects.requireNonNull(NavStyleManager.class.getResource("/nav/icons/right_arrow.png"))), c);
    }

    /**
     * Gets the default expanded icon.
     *
     * @return The default expanded icon, or {@code null} if the icon does not exist.
     */
    public static Icon getDefaultExpandedIcon() {
        NavStyle theme = getSelectedTheme();
        Color c = theme.getNavAttributes().getCollapseIconsColor();

        return NavHelper.recolorIcon(new ImageIcon(Objects.requireNonNull(NavStyleManager.class.getResource("/nav/icons/left_arrow.png"))), c);
    }

    /**
     * Sets the theme that matches the specified {@code themeId} as selected.
     *
     * @param themeId The ID of the theme.
     */
    public static void setSelectedTheme(int themeId) {
        PREF.putInt(SELECTED_THEME_ID, themeId);
    }

    /**
     * Sets the theme that matches the specified {@code themeName} as selected.
     *
     * @param themeName The name of the theme.
     */
    public static void setSelectedTheme(String themeName) {
        int themeId = getThemeId(themeName);
        PREF.putInt(SELECTED_THEME_ID, themeId);
    }

    /**
     * Gets the ID of the selected theme.
     *
     * @return The ID of the selected theme.
     * @apiNote The default value is {@code NavThemes.DARK}.
     */
    public static int getSelectedThemeId() {
        return PREF.getInt(SELECTED_THEME_ID, NavThemes.DARK_INDEX);
    }

    /**
     * Removes the theme with the specific name from the user theme list.
     *
     * @param themeName The name whose mapping is to be removed from the preference node.
     * @return {@code true} if the theme was successfully removed; {@code false} otherwise.
     * @throws NullPointerException     If {@code themeName} is null.
     * @throws IllegalStateException    If this node (or an ancestor) has been removed with the {@code removeNode()} method.
     * @throws IllegalArgumentException If {@code themeName} contains the null control character (code point U+0000).
     */
    public static boolean removeUserTheme(String themeName) {
        try {
            int selectedId = getSelectedThemeId();
            int removedId = getUserThemeId(themeName);

            String list = PREF.get(USER_THEME_LIST, "");
            if (list.isEmpty()) return false;

            List<String> names = new ArrayList<>(Arrays.asList(getUserThemeList()));
            if (!names.remove(themeName)) return false;

            String newList = String.join(",", names);
            PREF.put(USER_THEME_LIST, newList);

            PREF.remove(USER_THEME + themeName);

            if (names.isEmpty() || selectedId == removedId) {
                PREF.putInt(SELECTED_THEME_ID, NavThemes.DARK_INDEX);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Renames the specified theme with a new name.
     *
     * @param oldName The old name of the theme.
     * @param newName The new name of the theme.
     * @return {@code true} if the theme name was successfully changed; {@code false} otherwise.
     */
    public static boolean renameUserTheme(String oldName, String newName) {
        try {
            NavStyle theme = getUserTheme(oldName);

            if (!oldName.equals(newName) && theme != null) {
                PREF.remove(USER_THEME + oldName);

                String list = replaceName(oldName, newName);

                PREF.put(USER_THEME_LIST, list);
                PREF.put(USER_THEME + newName, GSON.toJson(theme, NavStyle.class));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Duplicates the specified user theme and creates a new one with a new name.
     *
     * @param themeName    The name of the theme to clone.
     * @param newThemeName The new name for the cloned theme.
     */
    public static void duplicateUserTheme(String themeName, String newThemeName) {
        NavStyle theme = getUserTheme(themeName);
        if (theme == null) return;

        String[] arr = getUserThemeList();
        List<String> names = new ArrayList<>(Arrays.asList(arr));

        if (names.contains(newThemeName)) return;

        names.add(newThemeName);

        PREF.put(USER_THEME_LIST, String.join(",", names));
        PREF.put(USER_THEME + newThemeName, GSON.toJson(theme, NavStyle.class));

        int id = getUserThemeId(newThemeName);
        if (id != -1) PREF.putInt(SELECTED_THEME_ID, id);
    }

    /**
     * Changes the theme name from {@code oldName} to {@code newName} from the {@code UserThemeList}.
     *
     * @param oldName The current name of the theme.
     * @param newName The new name of the theme.
     * @return The {@code String} representation of the {@code UserThemeList}.
     */
    private static String replaceName(String oldName, String newName) {
        String[] arr = getUserThemeList();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(oldName)) {
                arr[i] = newName;
            }
        }
        return String.join(",", arr);
    }

    /**
     * Gets the theme that matches the {@code SELECTED_THEME_ID}.
     *
     * @return The theme corresponding to the {@code SELECTED_THEME_ID}, or {@code null} if no match is found.
     */
    public static NavStyle getSelectedTheme() {
        return getThemeById(getSelectedThemeId());
    }

    /**
     * Gets the name of the selected theme.
     *
     * @param themeId The ID of the selected theme.
     * @return The theme name that matches the specified {@code themeId}, or {@code null} if no match is found.
     */
    public static String getSelectedThemeName(int themeId) {
        if (themeId < TOTAL_DEFAULT_THEMES) {
            return getSystemThemeList()[themeId];
        }

        int userIndex = getThemeIndex(themeId);
        String[] list = getUserThemeList();

        if (userIndex < 0 || userIndex >= list.length) {
            return null;
        }

        return list[userIndex];
    }

    /**
     * Gets the theme index from theme id.
     *
     * @param themeId The id of the theme.
     * @return The user theme index that matches the {@code themeId}.
     * @apiNote Theme index will be same value with index if one {@code SystemThemes} indexes are used.
     */
    public static int getThemeIndex(int themeId) {
        return themeId > 3 ? themeId - TOTAL_DEFAULT_THEMES : themeId;
    }

    /**
     * Gets the theme that matches the specified {@code themeId}.
     *
     * @param themeId The ID of the theme.
     * @return The user theme that matches the {@code themeId}, or {@code null} if no match is found.
     */
    public static NavStyle getThemeById(int themeId) {
        if (themeId < TOTAL_DEFAULT_THEMES) {
            return getSystemTheme(themeId);
        }

        if (getThemeIndex(themeId) >= getUserThemeList().length) return null;

        return getUserTheme(getUserThemeList()[getThemeIndex(themeId)]);
    }

    /**
     * Returns whether a theme with the specified name exists.
     *
     * @param name The name of the theme to check.
     * @return {@code true} if a theme with the specified name exists; {@code false} otherwise.
     */
    public static boolean userListContains(String name) {
        return Arrays.asList(getUserThemeList()).contains(name);
    }

    /**
     * Sets the theme created by the user.
     *
     * @param theme The {@code NavStyle} object representing the theme.
     * @param name  The name of the theme.
     */
    public static void createUserTheme(NavStyle theme, String name) {
        String list = PREF.get(USER_THEME_LIST, "");
        if (!userListContains(name)) {
            list = list.isEmpty() ? name : list + "," + name;

            PREF.put(USER_THEME_LIST, list);
            PREF.put(USER_THEME + name, GSON.toJson(theme, NavStyle.class));

            int themeId = TOTAL_DEFAULT_THEMES + list.split(",").length - 1;
            PREF.putInt(SELECTED_THEME_ID, themeId);
        }
    }

    /**
     * Gets the user theme that matches the specified name.
     *
     * @param name The name of the theme.
     * @return The {@code NavStyle} object representing the user-defined theme, or {@code null} if no match is found.
     */
    public static NavStyle getUserTheme(String name) {
        String json = PREF.get(USER_THEME + name, null);
        return json != null ? GSON.fromJson(json, NavStyle.class) : null;
    }

    /**
     * Gets all user-defined themes.
     *
     * @return An array of {@code NavStyle} objects representing the user-defined themes.
     */
    public static NavStyle[] getUserThemes() {
        List<NavStyle> themes = new ArrayList<>();

        for (String name : getUserThemeList()) {
            if (!name.isEmpty()) {
                themes.add(getUserTheme(name));
            }
        }
        return themes.toArray(new NavStyle[0]);
    }

    /**
     * Gets the id of the theme.
     *
     * @param themeName The name of the theme.
     * @return {@code themeId}
     */
    public static int getUserThemeId(String themeName) {
        String[] arr = getUserThemeList();
        int index = Arrays.asList(arr).indexOf(themeName);
        return index == -1 ? -1 : TOTAL_DEFAULT_THEMES + index;
    }

    /**
     * Gets the index of the theme name from {@code UserThemeList}.
     *
     * @param themeName The name of the theme.
     * @return {@code themeIndex}
     */
    public static int getUserThemeIndex(String themeName) {
        String[] arr = getUserThemeList();
        return Arrays.asList(arr).indexOf(themeName);
    }

    /**
     * Gets the id of the theme.
     *
     * @param themeName The name of the theme.
     * @return The id of theme that matches with {@code themeName}.
     */
    public static int getThemeId(String themeName) {
        return Arrays.asList(getAllThemeList()).indexOf(themeName);
    }

    /**
     * Gets all system and user defined theme names.
     *
     * @return Array of all available theme names.
     */
    public static String[] getAllThemeList() {
        int userThemes = getUserThemeList().length;
        String[] themes = new String[TOTAL_DEFAULT_THEMES + userThemes];

        for (int i = 0; i < themes.length; i++) {
            if (i < 4) {
                themes[i] = getSystemThemeList()[i];
            } else {
                themes[i] = getUserThemeList()[i - TOTAL_DEFAULT_THEMES];
            }
        }
        return themes;
    }

    /**
     * Gets the list of names of the user-defined themes.
     *
     * @return A list containing the names of the user themes.
     */
    public static String[] getUserThemeList() {
        String list = PREF.get(USER_THEME_LIST, "");
        if (list.isEmpty()) return new String[0];
        return list.split(",");
    }

    /**
     * Gets pre-defined system themes. The array contains the following:
     * <ul>
     *     <li>{@code 0: NavThemes.DARK}</li>
     *     <li>{@code 1: NavThemes.DARK_ORANGE}</li>
     *     <li>{@code 2: NavThemes.LIGHT}</li>
     *     <li>{@code 3: NavThemes.IVORY}</li>
     * </ul>
     */
    public static NavStyle[] getSystemThemes() {
        return new NavStyle[]{
                NavThemes.Dark(),
                NavThemes.DarkOrange(),
                NavThemes.Light(),
                NavThemes.Ivory()};
    }

    /**
     * An array of {@code String} objects containing the system theme names.
     */
    public static String[] getSystemThemeList() {
        return new String[]{
                NavThemes.DARK,
                NavThemes.DARK_ORANGE,
                NavThemes.LIGHT,
                NavThemes.IVORY
        };
    }

    /**
     * Gets the pre-defined system theme.
     *
     * @param themeId The ID of the system theme. Can be one of the following:
     *                <ul>
     *                    <li>{@code 0: NavThemes.DARK}</li>
     *                    <li>{@code 1: NavThemes.DARK_ORANGE}</li>
     *                    <li>{@code 2: NavThemes.LIGHT}</li>
     *                    <li>{@code 3: NavThemes.IVORY}</li>
     *                </ul>
     * @return The pre-defined system theme.
     */
    public static NavStyle getSystemTheme(int themeId) {
        return getSystemThemes()[themeId];
    }

    /**
     * Gets the pre-defined default Dark Theme.
     *
     * @return The default theme.
     */
    public static NavStyle getDefaultTheme() {
        return NavThemes.Dark();
    }
}