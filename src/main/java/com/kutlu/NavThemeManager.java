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
public class NavThemeManager {

    private static final Preferences PREF = Preferences.userRoot().node("NavStyleManager");
    private static final Gson GSON = new Gson();
    private static final String SELECTED_THEME = "nav.theme.selected";
    private static final String USER_THEMES = "nav.themes.";
    private static final String LAST_THEME_ID = "nav.theme.id";

    //Selected Theme Methods
    /**
     * Gets the id of the selected theme.
     *
     * @return The id of the selected theme.
     * @see #getSelectedTheme()
     * @see #getSelectedThemeName()
     */
    public static int getSelectedThemeId() {
        return PREF.getInt(SELECTED_THEME, NavSystemThemes.DARK_THEME_ID);
    }

    /**
     * Gets the name of the selected theme.
     *
     * @return The name of the selected theme.
     * @see #getSelectedTheme()
     * @see #getSelectedThemeId()
     */
    public static String getSelectedThemeName() {
        int themeId = getSelectedThemeId();

        if (themeId < NavSystemThemes.TOTAL_DEFAULT_THEMES) return getSystemThemeName(themeId);

        NavTheme theme = getUserTheme(themeId);
        return theme != null ? theme.getThemeName() : null;
    }

    /**
     * Gets the theme that matches the {@code SELECTED_THEME_ID}.
     *
     * @return The theme corresponding to the {@code SELECTED_THEME_ID}, or {@code null} if no match is found.
     * @see #getSelectedThemeId()
     * @see #getSelectedThemeName()
     * @see #getTheme(int themeId)
     * @see #getTheme(String themeName)
     * @see #getDefaultTheme()
     */
    public static NavTheme getSelectedTheme() {
        int themeId = getSelectedThemeId();

        if (themeId < NavSystemThemes.TOTAL_DEFAULT_THEMES) return getSystemTheme(themeId);

        int userIndex = themeId - NavSystemThemes.TOTAL_DEFAULT_THEMES;
        List<NavTheme> userThemes = getUserThemes();

        if (userIndex >= userThemes.size()) return null;

        return userThemes.get(userIndex);
    }

    /**
     * Sets the theme that matches the specified {@code themeId} as selected.
     *
     * @param themeId The id of the theme.
     * @apiNote This method only updates the persisted theme selection state. To reflect the changes visually, you must refresh or redraw the {@code NavigationView} programmatically.
     * @see #setSelectedTheme(String themeName)
     * @see #setSelectedTheme(int themeId, NavigationView navView)
     * @see #setSelectedTheme(String themeName, NavigationView navView)
     */
    public static void setSelectedTheme(int themeId) {
        PREF.putInt(SELECTED_THEME, themeId);
    }

    /**
     * Sets the theme that matches the specified {@code themeName} as selected.
     *
     * @param themeName The name of the theme.
     * @apiNote This method only updates the persisted theme selection state. To reflect the changes visually, you must refresh or redraw the {@code NavigationView} programmatically.
     * @see #setSelectedTheme(int themeId)
     * @see #setSelectedTheme(int themeId, NavigationView navView)
     * @see #setSelectedTheme(String themeName, NavigationView navView)
     */
    public static void setSelectedTheme(String themeName) {
        int themeId = getThemeId(themeName);
        PREF.putInt(SELECTED_THEME, themeId);
    }

    /**
     * Sets the theme that matches the specified {@code themeId} as selected and applies it.
     *
     * @param themeId The ID of the theme.
     * @param navView The {@code NavigationView} to which the new theme will be applied.
     * @see #setSelectedTheme(int themeId)
     * @see #setSelectedTheme(String themeName)
     * @see #setSelectedTheme(String themeName, NavigationView navView)
     *
     */
    public static void setSelectedTheme(int themeId, NavigationView navView) {
        NavTheme theme = getTheme(themeId);
        if (theme != null && themeId > -1) {
            PREF.putInt(SELECTED_THEME, themeId);

            navView.setTheme(theme);
        }
    }

    /**
     * Sets the theme that matches the specified {@code themeId} as selected and applies it.
     *
     * @param themeName The name of the theme.
     * @param navView The {@code NavigationView} to which the new theme will be applied.
     * @see #setSelectedTheme(int themeId)
     * @see #setSelectedTheme(String themeName)
     * @see #setSelectedTheme(int themeId, NavigationView navView)
     * */
    public static void setSelectedTheme(String themeName, NavigationView navView) {
        NavTheme theme = getTheme(themeName);
        if (theme != null && (isSystemTheme(themeName) || isUserTheme(themeName))) {
            int themeId = getThemeId(themeName);
            PREF.putInt(SELECTED_THEME, themeId);

            navView.setTheme(theme);
        }
    }

    //Theme (pre-defined & user-defined themes) Methods
    /**
     * Gets all user-defined themes.
     *
     * @return An array of {@code NavStyle} objects representing the user-defined themes,
     * or {@code null} if no user-defined themes exist.
     * @see #getSelectedTheme()
     * @see #getTheme(int themeId)
     * @see #getTheme(String themeName)
     * @see #getThemeId(String themeName)
     * @see #getThemeName(int themeId)
     */
    public static List<NavTheme> getThemes() {
        List<NavTheme> allThemes = new ArrayList<>(getSystemThemes());

        NavUserThemes userThemes = GSON.fromJson(PREF.get(USER_THEMES, ""), NavUserThemes.class);
        if (userThemes != null) allThemes.addAll(userThemes.getThemes());

        return allThemes;
    }

    /**
     * Gets the theme that matches the specified {@code themeId}.
     *
     * @param themeId The themeId of the theme.
     * @return The {@code NavStyle} object representing the  theme, or {@code null} if no match is found.
     * @see #getTheme(String themeName)
     * @see #getThemeId(String themeName)
     * @see #getThemeName(int themeId)
     * @see #getDefaultTheme()
     */
    public static NavTheme getTheme(int themeId) {
        List<NavTheme> allThemes = getThemes();
        for (NavTheme theme : allThemes) {
            if (theme.getThemeId() == themeId) {
                return theme;
            }
        }
        return null;
    }

    /**
     * Gets the theme that matches the specified {@code themeName}.
     *
     * @param themeName The name of the theme.
     * @return The {@code NavStyle} object representing the theme, or {@code null} if no match is found.
     * @see #getThemeIndex(int themeId)
     * @see #getThemeId(String themeName)
     * @see #getThemeName(int themeId)
     * @see #getDefaultTheme()
     */
    public static NavTheme getTheme(String themeName) {
        List<NavTheme> allThemes = getThemes();

        for (NavTheme theme : allThemes) {
            if (theme.getThemeName().equals(themeName)) {
                return theme;
            }
        }
        return null;
    }

    /**
     * Gets the pre-defined default Dark theme.
     *
     * @return The pre-defined Dark theme.
     */
    private static NavTheme getDefaultTheme() {
        return NavSystemThemes.Dark();
    }

    /**
     * Gets the id of the theme that matches the specified {@code themeName}
     *
     * @param themeName The name of the theme.
     * @return The id of the matching theme, or {@code -1} if no match is found.
     * @see #getThemeName(int themeId)
     */
    public static int getThemeId(String themeName) {
        if (isSystemTheme(themeName)) return getSystemTheme(themeName).getThemeId();
        if (isUserTheme(themeName)) return Objects.requireNonNull(getUserTheme(themeName)).getThemeId();
        return -1;
    }

    /**
     * Gets the name of the theme that matches the specified {@code themeId}
     *
     * @param themeId The id of the theme.
     * @return The name of the matching theme, or {@code null} if no match is found.
     * @see #getThemeId(String themeName)
     */
    public static String getThemeName(int themeId) {
        if (isSystemTheme(themeId)) return getSystemTheme(themeId).getThemeName();
        if (isUserTheme(themeId)) return Objects.requireNonNull(getUserTheme(themeId)).getThemeName();
        return null;
    }

    /**
     * Gets the position of a theme within both the system and user theme lists.
     *
     * @param themeId The id of the theme to find.
     * @return The index of the matching theme, or {@code -1} if no match is found.
     * @apiNote Note that {@code index == ID} holds true only for system themes;
     * for user-defined themes, the index and {@code ID} values will differ.
     * @see #getThemeIndex(String themeName)
     */
    public static int getThemeIndex(int themeId) {
        if (themeId < NavSystemThemes.TOTAL_DEFAULT_THEMES) return themeId;
        if (isUserTheme(themeId)) return getUserThemeIndex(themeId);
        return -1;
    }

    /**
     * Gets the position of a theme within both the system and user theme lists.
     *
     * @param themeName The name of the theme to find.
     * @return The index of the matching theme, or {@code -1} if no match is found.
     * @apiNote Note that {@code index == ID} holds true only for system themes;
     * for user-defined themes, the index and {@code ID} values will differ.
     * @see #getThemeIndex(int themeId)
     */
    public static int getThemeIndex(String themeName) {
        if (isSystemTheme(themeName)) return getSystemThemeId(themeName);
        if (isUserTheme(themeName)) return getUserThemeIndex(themeName);
        return -1;
    }

    /**
     * Checks whether the system theme list contains the specified {@code themeName}.
     *
     * @param themeId The name of the theme to check.
     * @return {@code true} if the pre-defined system theme list contains the given name; {@code false} otherwise.
     * @see #isSystemTheme(String themeName)
     */
    public static boolean isSystemTheme(int themeId) {
        return themeId >= 0 && themeId < NavSystemThemes.TOTAL_DEFAULT_THEMES;
    }

    /**
     * Checks whether the system theme list contains the specified {@code themeName}.
     *
     * @param themeName The name of the theme to check.
     * @return {@code true} if the pre-defined system theme list contains the given name; {@code false} otherwise.
     * @see #isSystemTheme(int themeId)
     */
    public static boolean isSystemTheme(String themeName) {
        return getSystemThemeList().contains(themeName);
    }

    /**
     * Returns whether a theme with the specified {@code themeId} exists.
     *
     * @param themeID The id of the theme to check.
     * @return {@code true} if a theme with the specified {@code themeId} exists; {@code false} otherwise.
     * @see #isUserTheme(String themeName)
     */
    public static boolean isUserTheme(int themeID) {
        return getUserThemeIds().contains(themeID);
    }

    /**
     * Returns whether a theme with the specified {@code themeName} exists.
     *
     * @param themeName The name of the theme to check.
     * @return {@code true} if a theme with the specified {@code themeName} exists; {@code false} otherwise.
     * @see #isUserTheme(int themeId)
     */
    public static boolean isUserTheme(String themeName) {
        return getUserThemeNames().contains(themeName);
    }

    //Theme Operations

    /**
     * Creates a new user-defined theme.
     *
     * @param theme The {@code NavStyle} object representing the new theme.
     * @return The ID of the new user theme if the theme creation is successful; {@code -1} otherwise.
     */
    public static int createUserTheme(NavTheme theme) {
        try {
            int id = getLastId() + 1;

            if (!isUserTheme(theme.getThemeName())) {
                theme.setThemeId(id);

                List<NavTheme> userList = getUserThemes();
                userList.add(theme);

                PREF.put(USER_THEMES, GSON.toJson(new NavUserThemes(userList), NavUserThemes.class));
                PREF.putInt(LAST_THEME_ID, id);
                PREF.putInt(SELECTED_THEME, id);

                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Updates the specified theme with a new {@code NavStyle}.
     *
     * @param themeId  The id of the theme to update.
     * @param newTheme The new {@code NavStyle} object to apply to the theme.
     * @return {@code true} if the theme was successfully updated; {@code false} otherwise.
     * @see #updateUserTheme(String themeName, NavTheme newTheme)
     */
    public static boolean updateUserTheme(int themeId, NavTheme newTheme) {
        if (newTheme == null) return false;

        if (!isUserTheme(themeId)) return false;

        try {
            int themeIndex = getUserThemeIndex(themeId);
            List<NavTheme> userThemes = getUserThemes();
            userThemes.set(themeIndex, newTheme);

            PREF.put(USER_THEMES, GSON.toJson(new NavUserThemes(userThemes), NavUserThemes.class));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates the specified theme with a new {@code NavStyle}.
     *
     * @param themeName The name of the theme to update.
     * @param newTheme  The new {@code NavStyle} object to apply to the theme.
     * @return {@code true} if the theme was successfully updated; {@code false} otherwise.
     * @see #updateUserTheme(int themeId, NavTheme newTheme)
     */
    public static boolean updateUserTheme(String themeName, NavTheme newTheme) {
        if (newTheme == null) return false;

        if (!isUserTheme(themeName)) return false;

        try {
            int themeIndex = getUserThemeIndex(themeName);
            List<NavTheme> userThemes = getUserThemes();
            userThemes.set(themeIndex, newTheme);

            PREF.put(USER_THEMES, GSON.toJson(new NavUserThemes(userThemes), NavUserThemes.class));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Removes the user-defined theme with the specific {@code themeId} from the user theme list.
     *
     * @param themeId The id of the user-defined theme whose mapping is to be removed from the preference node.
     * @return {@code true} if the theme was successfully removed; {@code false} otherwise.
     * @see #deleteUserTheme(String themeName)
     */
    public static boolean deleteUserTheme(int themeId) {
        List<NavTheme> themes = getUserThemes();
        if (themes.isEmpty()) return false;

        try {
            if (isUserTheme(themeId)) {
                int selectedId = getSelectedThemeId();
                themes.remove(getUserThemeIndex(themeId));

                PREF.put(USER_THEMES, GSON.toJson(new NavUserThemes(themes), NavUserThemes.class));

                if (selectedId == themeId) {
                    PREF.putInt(SELECTED_THEME, NavSystemThemes.DARK_THEME_ID);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Removes the user-defined theme with the specific {@code themeName} from the user theme list.
     *
     * @param themeName The name of the user-defined theme whose mapping is to be removed from the preference node.
     * @return {@code true} if the theme was successfully removed; {@code false} otherwise.
     * @see #deleteUserTheme(int themeId)
     */
    public static boolean deleteUserTheme(String themeName) {
        List<NavTheme> themes = getUserThemes();
        if (themes.isEmpty()) return false;

        try {
            if (isUserTheme(themeName)) {
                String selectedName = getSelectedThemeName();
                themes.remove(getUserThemeIndex(themeName));

                PREF.put(USER_THEMES, GSON.toJson(new NavUserThemes(themes), NavUserThemes.class));

                if (themeName.equals(selectedName)) {
                    PREF.putInt(SELECTED_THEME, NavSystemThemes.DARK_THEME_ID);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Renames the specified user-defined theme with a new name.
     *
     * @param themeId The id of the user-defined theme.
     * @param newName The new name of the user-defined them theme.
     * @return {@code true} if the theme name was successfully changed; {@code false} otherwise.
     * @see #renameUserTheme(String oldName, String newName)
     */
    public static boolean renameUserTheme(int themeId, String newName) {
        List<NavTheme> userThemes = getUserThemes();
        if (userThemes == null || userThemes.isEmpty()) return false;

        String oldName = getUserThemeName(themeId);
        if (!isUserTheme(oldName)) return false;

        if (isUserTheme(newName)) return false;

        if (Objects.requireNonNull(oldName).equals(newName)) return false;

        try {
            int index = getUserThemeIndex(themeId);
            if (index == -1) return false;

            userThemes.get(index).setThemeName(newName);

            PREF.put(USER_THEMES, GSON.toJson(new NavUserThemes(userThemes), NavUserThemes.class));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Renames the specified user-defined theme with a new name.
     *
     * @param oldName The old name of the user-defined theme.
     * @param newName The new name of the user-defined them theme.
     * @return {@code true} if the theme name was successfully changed; {@code false} otherwise.
     * @see #renameUserTheme(int themeId, String newName)
     */
    public static boolean renameUserTheme(String oldName, String newName) {
        List<NavTheme> userThemes = getUserThemes();
        if (userThemes == null || userThemes.isEmpty()) return false;

        if (!isUserTheme(oldName)) return false;

        if (isUserTheme(newName)) return false;

        if (oldName.equals(newName)) return false;

        try {
            int index = getUserThemeIndex(oldName);
            if (index == -1) return false;

            userThemes.get(index).setThemeName(newName);

            PREF.put(USER_THEMES, GSON.toJson(new NavUserThemes(userThemes), NavUserThemes.class));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Duplicates the specified user theme and creates a new one with a new name.
     *
     * @param themeId      The id of the theme to clone.
     * @param newThemeName The new name for the cloned theme.
     * @return The newly duplicated {@code NavStyle} object if the operation is successful; {@code null} otherwise.
     * @see #duplicateTheme(String themeName, String newThemeName)
     */
    public static NavTheme duplicateTheme(int themeId, String newThemeName) {
        NavTheme original = getTheme(themeId);

        int lastId = getLastId() + 1;
        if (original == null) return null;

        if (isSystemTheme(newThemeName) || isUserTheme(newThemeName)) return null;

        try {
            NavTheme newTheme = new NavTheme(original);
            newTheme.setThemeId(lastId);
            newTheme.setThemeName(newThemeName);

            return createUserTheme(newTheme) != -1 ? newTheme : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Duplicates the specified user theme and creates a new one with a new name.
     *
     * @param themeName    The name of the theme to clone.
     * @param newThemeName The new name for the cloned theme.
     * @return The newly duplicated {@code NavStyle} object if the operation is successful; {@code null} otherwise.
     * @see #duplicateTheme(int themeId, String newThemeName)
     */
    public static NavTheme duplicateTheme(String themeName, String newThemeName) {
        NavTheme original = getTheme(themeName);
        int lastId = getLastId() + 1;
        if (original == null) return null;

        if (isSystemTheme(newThemeName) || isUserTheme(newThemeName)) return null;

        try {
            NavTheme newTheme = new NavTheme(original);
            newTheme.setThemeId(lastId);
            newTheme.setThemeName(newThemeName);

            return createUserTheme(newTheme) != -1 ? newTheme : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //System Themes Methods
    /**
     * Gets the pre-defined system theme.
     *
     * @param themeId The ID of the system theme. Can be one of the following:
     *                <ul>
     *                    <li>{@code 0: NavThemes.DARK_THEME}</li>
     *                    <li>{@code 1: NavThemes.DARK_ORANGE_THEME}</li>
     *                    <li>{@code 2: NavThemes.LIGHT_THEME}</li>
     *                    <li>{@code 3: NavThemes.IVORY_THEME}</li>
     *                </ul>
     * @return The pre-defined system theme that matches the specified {@code themeId}, or {@code null} if no match is found.
     */
    private static NavTheme getSystemTheme(int themeId) {
        return switch (themeId) {
            case NavSystemThemes.DARK_THEME_ID -> NavSystemThemes.Dark();
            case NavSystemThemes.DARK_ORANGE_THEME_ID -> NavSystemThemes.DarkOrange();
            case NavSystemThemes.LIGHT_THEME_ID -> NavSystemThemes.Light();
            case NavSystemThemes.IVORY_THEME_ID -> NavSystemThemes.Ivory();
            default -> null;
        };
    }

    /**
     * Gets the pre-defined system theme.
     *
     * @param themeName The name of the system theme. Can be one of the following:
     *                  <ul>
     *                      <li>{@code 0: NavThemes.DARK}</li>
     *                      <li>{@code 1: NavThemes.DARK_ORANGE}</li>
     *                      <li>{@code 2: NavThemes.LIGHT}</li>
     *                      <li>{@code 3: NavThemes.IVORY}</li>
     *                  </ul>
     * @return The pre-defined system theme that matches the specified {@code themeId}, or {@code null} if no match is found.
     */
    private static NavTheme getSystemTheme(String themeName) {
        return switch (themeName) {
            case NavSystemThemes.DARK_THEME_NAME -> NavSystemThemes.Dark();
            case NavSystemThemes.DARK_ORANGE_THEME_NAME -> NavSystemThemes.DarkOrange();
            case NavSystemThemes.LIGHT_THEME_NAME -> NavSystemThemes.Light();
            case NavSystemThemes.IVORY_THEME_NAME -> NavSystemThemes.Ivory();
            default -> null;
        };
    }

    /**
     * Gets the system theme name that matches with the specific {@code themeId}.
     *
     * @param themeId The id of the system theme.
     * @return The name of the pre-defined system theme; {@code null} if no match is found.
     */
    private static String getSystemThemeName(int themeId) {
        return switch (themeId) {
            case NavSystemThemes.DARK_THEME_ID -> NavSystemThemes.DARK_THEME_NAME;
            case NavSystemThemes.DARK_ORANGE_THEME_ID -> NavSystemThemes.DARK_ORANGE_THEME_NAME;
            case NavSystemThemes.LIGHT_THEME_ID -> NavSystemThemes.LIGHT_THEME_NAME;
            case NavSystemThemes.IVORY_THEME_ID -> NavSystemThemes.IVORY_THEME_NAME;
            default -> null;
        };
    }

    /**
     * Gets the system theme id that matches with the specific {@code themeName}.
     *
     * @param themeName The name of the system theme.
     * @return The id of the pre-defined system theme; {@code -1} if no match is found.
     */
    private static int getSystemThemeId(String themeName) {
        return switch (themeName) {
            case NavSystemThemes.DARK_THEME_NAME -> NavSystemThemes.DARK_THEME_ID;
            case NavSystemThemes.DARK_ORANGE_THEME_NAME -> NavSystemThemes.DARK_ORANGE_THEME_ID;
            case NavSystemThemes.LIGHT_THEME_NAME -> NavSystemThemes.LIGHT_THEME_ID;
            case NavSystemThemes.IVORY_THEME_NAME -> NavSystemThemes.IVORY_THEME_ID;
            default -> -1;
        };
    }

    /**
     * Gets the list of system theme names.
     *
     * @return A list containing the names of all pre-defined system themes.
     */
    private static List<String> getSystemThemeList() {
        List<String> list = new ArrayList<>();
        list.add(NavSystemThemes.DARK_THEME_NAME);
        list.add(NavSystemThemes.DARK_ORANGE_THEME_NAME);
        list.add(NavSystemThemes.LIGHT_THEME_NAME);
        list.add(NavSystemThemes.IVORY_THEME_NAME);

        return list;
    }

    /**
     * Gets pre-defined system themes. The theme list contains the following:
     * <ul>
     *     <li>{@code 0: NavThemes.Dark()}</li>
     *     <li>{@code 1: NavThemes.DarkOrange()}</li>
     *     <li>{@code 2: NavThemes.Light()}</li>
     *     <li>{@code 3: NavThemes.Ivory()}</li>
     * </ul>
     */
    private static List<NavTheme> getSystemThemes() {
        List<NavTheme> list = new ArrayList<>();
        list.add(NavSystemThemes.Dark());
        list.add(NavSystemThemes.DarkOrange());
        list.add(NavSystemThemes.Light());
        list.add(NavSystemThemes.Ivory());

        return list;
    }

    //User Themes Methods
    /**
     * Gets all user-defined themes.
     *
     * @return An array of {@code NavStyle} objects representing the user-defined themes,
     * or {@code null} if no user-defined themes exist.
     */
    private static List<NavTheme> getUserThemes() {
        NavUserThemes userThemes = GSON.fromJson(PREF.get(USER_THEMES, ""), NavUserThemes.class);
        return userThemes != null ? userThemes.getThemes() : new ArrayList<>();
    }

    /**
     * Gets the user-defined theme that matches the specified {@code themeName}.
     *
     * @param themeName The name of the theme.
     * @return The {@code NavStyle} object representing the user-defined theme, or {@code null} if no match is found.
     */
    private static NavTheme getUserTheme(String themeName) {
        for (NavTheme theme : getUserThemes()) {
            if (theme.getThemeName().equals(themeName)) {
                return theme;
            }
        }
        return null;
    }

    /**
     * Gets the user-defined theme that matches the specified {@code themeId}.
     *
     * @param themeId The themeId of the theme.
     * @return The {@code NavStyle} object representing the user-defined theme, or {@code null} if no match is found.
     */
    private static NavTheme getUserTheme(int themeId) {
        for (NavTheme theme : getUserThemes()) {
            if (theme.getThemeId() == themeId) {
                return theme;
            }
        }
        return null;
    }

    /**
     * Gets the list of names of the user-defined themes.
     *
     * @return A list containing the names of the user themes.
     */
    private static List<String> getUserThemeNames() {
        List<String> names = new ArrayList<>();
        for (NavTheme theme : getUserThemes()) {
            names.add(theme.getThemeName());
        }
        return names;
    }

    /**
     * Gets the name of the user-defined theme that matches the specified {@code themeId}.
     *
     * @param themeId The id of the theme.
     * @return {@code themeName}
     */
    private static String getUserThemeName(int themeId) {
        for (NavTheme theme : getUserThemes()) {
            if (theme.getThemeId() == themeId) {
                return theme.getThemeName();
            }
        }
        return null;
    }

    /**
     * Gets the list of IDs of the user-defined themes.
     *
     * @return A list containing the IDs of the user themes.
     */
    private static List<Integer> getUserThemeIds() {
        List<Integer> ids = new ArrayList<>();

        for (NavTheme theme : getUserThemes()) {
            ids.add(theme.getThemeId());
        }

        return ids;
    }

    /**
     * Gets the position of a user-defined theme within the user theme list.
     *
     * @param themeName The name of the theme.
     * @return The index of the matching theme, or {@code -1} if the {@code themeName} is invalid or not found.
     */
    private static int getUserThemeIndex(String themeName) {
        int index = getUserThemeNames().indexOf(themeName);
        return index >= 0 ? index : -1;
    }

    /**
     * Gets the position of a user-defined theme within the user theme list.
     *
     * @param themeId The ID of the theme.
     * @return The index of the matching theme, or {@code -1} if the {@code themeId} is invalid or not found.
     */
    private static int getUserThemeIndex(int themeId) {
        int index = getUserThemeIds().indexOf(themeId);
        return index >= 0 ? index : -1;
    }

    private static int getLastId() {
        return PREF.getInt(LAST_THEME_ID, NavSystemThemes.TOTAL_DEFAULT_THEMES - 1);
    }
}