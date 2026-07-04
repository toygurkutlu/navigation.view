package com.kutlu;

import java.util.prefs.Preferences;

/**
 * Manager class responsible for handling the click behaviors of the {@code NavigationView}.
 * <p>
 * This class coordinates the following behaviors:
 * <ul>
 *   <li>The collapse status of the {@code NavigationView},</li>
 *   <li>The title and subtitle preferences,</li>
 *   <li>The collapse status of the subtitle container.</li>
 * </ul>
 * </p>
 */
public class NavStateManager {
    private static final Preferences PREF = Preferences.userNodeForPackage(NavStateManager.class);
    private static final String NAV_COLLAPSE = ".nav.collapse";
    private static final String SELECTED_TITLE = ".selected.title";
    private static final String SELECTED_SUBTITLE = ".selected_subtitle";
    private static final String SUB_COLLAPSE = ".sub.collapse";

    /**
     * Gets {@code NavigationView}'s collapse status.
     *
     * @param name The name of the {@code NavigationView}
     * @return {@code true} if {@code NavigationView} is collapsed; {@code false} otherwise.
     */
    public static boolean isNavCollapsed(String name) {
        return PREF.getBoolean("nav." + name + NAV_COLLAPSE, false);
    }

    /**
     * Sets {@code NavigationView}'s collapse status.
     *
     * @param name      The name of the {@code NavigationView}
     * @param isVisible {@code true} if {@code NavigationView} is collapsed; {@code false} otherwise.
     */
    public static void setNavCollapsed(String name, boolean isVisible) {
        PREF.putBoolean("nav." + name + NAV_COLLAPSE, isVisible);
    }

    /**
     * Gets the selected title's index from the {@code NavigationView} with the specified name.
     *
     * @param navName The name of the {@code NavigationView}.
     * @return The index number of the selected title, or {@code -1} if no match is found for {@code navName}.
     */
    public static int getSelectedTitleIndex(String navName) {
        return PREF.getInt("nav." + navName + SELECTED_TITLE, 0);
    }

    /**
     * Sets the selected title's index of the {@code NavigationView} with the specified name.
     *
     * @param navName The name of the {@code NavigationView}.
     * @param index   The index number of the selected title.
     */
    public static void setSelectedTitleIndex(String navName, int index) {
        PREF.putInt("nav." + navName + SELECTED_TITLE, index);
    }

    /**
     * Gets the selected subtitle's index from the {@code NavigationView} with the specified name.
     *
     * @param navName The name of the {@code NavigationView}.
     * @return The index number of the selected title, or {@code 0} if no match is found for {@code navName}.
     */
    public static int getSelectedSubIndex(String navName) {
        return PREF.getInt("nav." + navName + SELECTED_SUBTITLE, 0);
    }

    /**
     * Sets the selected subtitle's index from the {@code NavigationView} with the specified name.
     *
     * @param navName  The name of the {@code NavigationView}.
     * @param subIndex The index number of the selected subtitle.
     */
    public static void setSelectedSubIndex(String navName, int subIndex) {
        PREF.putInt("nav." + navName + SELECTED_SUBTITLE, subIndex);
    }

    /**
     * Sets the collapse status of the specified {@code NavigationView} that matches the given {@code titleIndex}.
     *
     * @param navName    The name of the {@code NavigationView}.
     * @param titleIndex The index of the parent title.
     * @param collapsed  {@code true} if the subtitle container is collapsed; {@code false} otherwise.
     */
    public static void setSubCollapsed(String navName, int titleIndex, boolean collapsed) {
        String key = "nav." + navName + SUB_COLLAPSE + titleIndex;
        PREF.putBoolean(key, collapsed);
    }

    /**
     * Gets the collapse status of the specified {@code NavigationView} that matches the given {@code titleIndex}.
     *
     * @param navName    The name of the {@code NavigationView}.
     * @param titleIndex The index of the parent title.
     * @return {@code true} if the specified subtitle container is collapsed; {@code false} otherwise.
     * @apiNote The default value is {@code false}. Returns {@code false} even if no {@code NavigationView} exists with the given {@code navName}.
     */
    public static boolean isSubCollapsed(String navName, int titleIndex) {
        String key = "nav." + navName + SUB_COLLAPSE + titleIndex;
        return PREF.getBoolean(key, false);
    }
}