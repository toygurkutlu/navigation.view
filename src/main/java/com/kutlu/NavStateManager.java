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

    private static final Preferences PREF = Preferences.userRoot().node("NavStateManager");
    private static final String NAV_COLLAPSE = ".collapse";
    private static final String CAN_COLLAPSE = ".can.collapse";
    private static final String SELECTED_TITLE = ".selected.title";
    private static final String SELECTED_SUBTITLE = ".selected.subtitle";
    private static final String SUB_COLLAPSE = ".sub.collapse";
    private static final String SUBS_CAN_COLLAPSE = ".subs.can.collapse";

    /**
     * Gets whether the collapse mechanism is enabled for the specified {@code NavigationView}.
     *
     * @param navName The name of the {@code NavigationView}.
     * @return {@code true} if the collapse mechanism is enabled; {@code false} otherwise.
     */
    public static boolean navCanCollapse(String navName) {
        return PREF.getBoolean("nav." + navName + CAN_COLLAPSE, true);
    }

    /**
     * Sets whether the collapse mechanism is enabled.
     *
     * @param navName     The name of the {@code NavigationView}.
     * @param canCollapse {@code true} to enable the collapse mechanism and display the {@code navIcon};
     *                    {@code false} to disable the collapse mechanism and remove the {@code navIcon}.
     */
    public static void setNavCanCollapse(String navName, boolean canCollapse) {
        PREF.putBoolean("nav." + navName + CAN_COLLAPSE, canCollapse);
    }

    /**
     * Gets {@code NavigationView}'s collapse status.
     *
     * @param navName The name of the {@code NavigationView}
     * @return {@code true} if {@code NavigationView} is collapsed; {@code false} otherwise.
     */
    public static boolean isNavCollapsed(String navName) {
        return PREF.getBoolean("nav." + navName + NAV_COLLAPSE, false);
    }

    /**
     * Sets {@code NavigationView}'s collapse status.
     *
     * @param navName   The name of the {@code NavigationView}
     * @param isVisible {@code true} if {@code NavigationView} is collapsed; {@code false} otherwise.
     */
    public static void setNavCollapsed(String navName, boolean isVisible) {
        PREF.putBoolean("nav." + navName + NAV_COLLAPSE, isVisible);
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
     * Sets whether the subtitles' collapse mechanism is enabled.
     *
     * @param navName     The name of the {@code NavigationView}.
     * @param canCollapse {@code true} to enable the subtitle collapse mechanism;
     *                    {@code false} to disable the subtitle collapse mechanism.
     */
    public static void setSubsCanCollapse(String navName, boolean canCollapse) {
        PREF.putBoolean("nav." + navName + SUBS_CAN_COLLAPSE, canCollapse);
    }

    /**
     * Gets whether the subtitle collapse mechanism is enabled for the specified {@code NavigationView}.
     *
     * @param navName The name of the {@code NavigationView}.
     * @return {@code true} if the subtitle collapse mechanism is enabled; {@code false} otherwise.
     */
    public static boolean subsCanCollapse(String navName) {
        return PREF.getBoolean("nav." + navName + SUBS_CAN_COLLAPSE, true);
    }
}