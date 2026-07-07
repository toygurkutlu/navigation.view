package com.kutlu;

import java.awt.*;

/**
 * Defines the available themes for the {@code NavigationView}.
 * <p>
 * • Provides pre-defined Dark, Dark Orange, Light and Ivory themes.<br>
 * • Allows users to create customized themes using {@code NavStyle} and store them via {@code NavStyleManager}.
 * </p>
 *
 * @see NavTheme
 * @see NavThemeManager
 */
public class NavSystemThemes {

    public static final int TOTAL_DEFAULT_THEMES = 4;

    public static final int DARK_THEME_ID = 0;
    public static final int DARK_ORANGE_THEME_ID = 1;
    public static final int LIGHT_THEME_ID = 2;
    public static final int IVORY_THEME_ID = 3;
    public static final String DARK_THEME_NAME ="System.Dark";
    public static final String DARK_ORANGE_THEME_NAME ="System.Dark Orange";
    public static final String LIGHT_THEME_NAME ="System.Light";
    public static final String IVORY_THEME_NAME ="System.Ivory";

    /**
     * Gets the pre-defined Dark Theme.
     *
     * @return The pre-defined Dark Theme instance.
     */
    public static NavTheme Dark() {
        NavTheme style = new NavTheme();
        style.setThemeName(DARK_THEME_NAME);
        style.setThemeId(DARK_THEME_ID);
        style.setBodyAttributes(getDarkNavAttributes());
        style.setTitleAttributes(getDarkTitleAttributes());
        style.setSubtitleAttributes(getDarkSubtitleAttributes());

        return style;
    }
    private static BodyAttributes getDarkNavAttributes(){
        BodyAttributes attr = new BodyAttributes();

        Color collapseColor = new Color(175, 175, 195);

        attr.setBackground(new Color(41, 41, 41));
        attr.setCollapseIconsColor(collapseColor);
        attr.setCollapseIconsColored(true);

        return attr;
    }
    private static TextAttributes getDarkTitleAttributes() {
        TextAttributes attr = new TextAttributes();

        Color colorPrimary = new Color(175, 175, 195);

        attr.setForeground(colorPrimary);
        attr.setBackground(new Color(41, 41, 41));
        attr.setSelectedForeground(colorPrimary);
        attr.setSelectedBackground(new Color(5, 5, 5));
        attr.setHoverBackground(colorPrimary);
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
    private static TextAttributes getDarkSubtitleAttributes() {
        TextAttributes attr = new TextAttributes();

        attr.setForeground(new Color(175, 175, 175));
        attr.setBackground(new Color(41, 41, 41));
        attr.setSelectedForeground(new Color(175, 175, 195));
        attr.setSelectedBackground(new Color(25, 25, 25));
        attr.setHoverBackground(new Color(175, 175, 195));
        attr.setHoverForeground(new Color(41, 41, 41));
        attr.setTextPosition(TextPosition.RIGHT);
        attr.setIconTextGap(10);
        attr.setGapTop(2);
        attr.setGapLeft(35);
        attr.setGapBottom(2);
        attr.setGapRight(20);
        attr.setFont(new Font("Sky Sans Medium Small Caps", Font.BOLD, 14));

        return attr;
    }

    /**
     * Gets the pre-defined Dark Orange Theme.
     *
     * @return The pre-defined Dark Orange Theme instance.
     */
    public static NavTheme DarkOrange() {
        NavTheme style = new NavTheme();

        style.setThemeName(DARK_ORANGE_THEME_NAME);
        style.setThemeId(DARK_ORANGE_THEME_ID);
        style.setBodyAttributes(getDarkOrangeNavAttributes());
        style.setTitleAttributes(getDarkOrangeTitleAttributes());
        style.setSubtitleAttributes(getDarkOrangeSubtitleAttributes());

        return style;
    }
    private static BodyAttributes getDarkOrangeNavAttributes(){
        BodyAttributes attr = new BodyAttributes();

        Color collapseColor = new Color(155, 75, 45);

        attr.setBackground(new Color(25, 25, 25));
        attr.setCollapseIconsColor(collapseColor);
        attr.setCollapseIconsColored(true);

        return attr;
    }
    private static TextAttributes getDarkOrangeTitleAttributes() {
        TextAttributes attr = new TextAttributes();

        Color colorPrimary = Color.BLACK;
        Color colorSecondary = new Color(155, 75, 45);

        attr.setForeground(new Color(200, 200, 175));
        attr.setBackground(new Color(25, 25, 25));
        attr.setSelectedForeground(colorPrimary);
        attr.setSelectedBackground(colorSecondary);
        attr.setHoverBackground(colorPrimary);
        attr.setHoverForeground(colorSecondary);
        attr.setTextPosition(TextPosition.RIGHT);
        attr.setIconTextGap(5);
        attr.setGapTop(5);
        attr.setGapLeft(5);
        attr.setGapBottom(5);
        attr.setGapRight(5);
        attr.setFont(new Font("Sky Sans Medium Small Caps", Font.BOLD, 16));

        return attr;
    }
    private static TextAttributes getDarkOrangeSubtitleAttributes() {
        TextAttributes attr = new TextAttributes();

        Color colorPrimary = new Color(155, 75, 45);
        Color colorSecondary = Color.BLACK;

        attr.setForeground(new Color(175, 175, 175));
        attr.setBackground(new Color(25, 25, 25));
        attr.setSelectedForeground(colorPrimary);
        attr.setSelectedBackground(colorSecondary);
        attr.setHoverForeground(colorSecondary);
        attr.setHoverBackground(colorPrimary);
        attr.setTextPosition(TextPosition.RIGHT);
        attr.setIconTextGap(10);
        attr.setGapTop(2);
        attr.setGapLeft(35);
        attr.setGapBottom(2);
        attr.setGapRight(20);
        attr.setFont(new Font("Sky Sans Medium Small Caps", Font.BOLD, 14));

        return attr;
    }

    /**
     * Gets the pre-defined Light Theme.
     *
     * @return The pre-defined Light Theme instance.
     */
    public static NavTheme Light() {
        NavTheme style = new NavTheme();

        style.setThemeName(LIGHT_THEME_NAME);
        style.setThemeId(LIGHT_THEME_ID);
        style.setBodyAttributes(getLightNavAttributes());
        style.setTitleAttributes(getLightTitleAttributes());
        style.setSubtitleAttributes(getLightSubtitleAttributes());

        return style;
    }
    private static BodyAttributes getLightNavAttributes(){

        BodyAttributes attr = new BodyAttributes();

        Color collapseColor = new Color(250, 250, 250);

        attr.setBackground(new Color(90, 100, 150));
        attr.setCollapseIconsColor(collapseColor);
        attr.setCollapseIconsColored(true);

        return attr;
    }
    private static TextAttributes getLightTitleAttributes() {
        TextAttributes attr = new TextAttributes();

        Color c = new Color(250,250,250);
        attr.setForeground(c);
        attr.setBackground(new Color(90, 100, 150));
        attr.setSelectedForeground(new Color(250, 250, 250));
        attr.setSelectedBackground(new Color(60, 70, 120));
        attr.setHoverBackground(c);
        attr.setHoverForeground(new Color(60, 70, 120));
        attr.setTextPosition(TextPosition.RIGHT);
        attr.setIconTextGap(5);
        attr.setGapTop(5);
        attr.setGapLeft(5);
        attr.setGapBottom(5);
        attr.setGapRight(5);
        attr.setFont(new Font("Sky Sans Medium Small Caps", Font.BOLD, 16));

        return attr;
    }
    private static TextAttributes getLightSubtitleAttributes() {
        TextAttributes attr = new TextAttributes();

        Color c = new Color(250, 250, 250);
        attr.setForeground(c);
        attr.setBackground(new Color(90, 100, 150));
        attr.setSelectedForeground(c);
        attr.setSelectedBackground(new Color(100, 140, 100));
        attr.setHoverBackground(c);
        attr.setHoverForeground(new Color(100, 140, 100));
        attr.setTextPosition(TextPosition.RIGHT);
        attr.setIconTextGap(10);
        attr.setGapTop(2);
        attr.setGapLeft(35);
        attr.setGapBottom(2);
        attr.setGapRight(20);
        attr.setFont(new Font("Sky Sans Medium Small Caps", Font.BOLD, 14));

        return attr;
    }

    /**
     * Gets the pre-defined Ivory Theme.
     *
     * @return The pre-defined Ivory Theme instance.
     */
    public static NavTheme Ivory() {
        NavTheme style = new NavTheme();

        style.setThemeName(IVORY_THEME_NAME);
        style.setThemeId(IVORY_THEME_ID);
        style.setBodyAttributes(getIvoryNavAttributes());
        style.setTitleAttributes(getIvoryTitleAttributes());
        style.setSubtitleAttributes(getIvorySubtitleAttributes());

        return style;
    }
    private static BodyAttributes getIvoryNavAttributes(){
        BodyAttributes attr = new BodyAttributes();

        Color collapseColor = new Color(200, 200, 175);

        attr.setBackground(new Color(45, 50, 50));
        attr.setCollapseIconsColor(collapseColor);
        attr.setCollapseIconsColored(true);

        return attr;
    }
    private static TextAttributes getIvoryTitleAttributes() {
        TextAttributes attr = new TextAttributes();

        Color colorPrimary = new Color(45, 50, 50);

        attr.setForeground(new Color(200, 200, 175));
        attr.setBackground(new Color(45, 50, 50));
        attr.setSelectedForeground(colorPrimary);
        attr.setSelectedBackground(new Color(180, 175, 130));
        attr.setHoverBackground(new Color(70, 110, 100));
        attr.setHoverForeground(new Color(45, 50, 50));
        attr.setTextPosition(TextPosition.RIGHT);
        attr.setIconTextGap(5);
        attr.setGapTop(5);
        attr.setGapLeft(5);
        attr.setGapBottom(5);
        attr.setGapRight(5);
        attr.setFont(new Font("Sky Sans Medium Small Caps", Font.BOLD, 16));

        return attr;
    }
    private static TextAttributes getIvorySubtitleAttributes() {
        TextAttributes attr = new TextAttributes();

        attr.setForeground(new Color(175, 175, 175));
        attr.setBackground(new Color(45, 50, 50));
        attr.setSelectedForeground(new Color(180, 175, 130));
        attr.setSelectedBackground(new Color(25, 40, 40));
        attr.setHoverBackground(new Color(70, 110, 100));
        attr.setHoverForeground(new Color(45, 50, 50));
        attr.setTextPosition(TextPosition.RIGHT);
        attr.setIconTextGap(10);
        attr.setGapTop(2);
        attr.setGapLeft(35);
        attr.setGapBottom(2);
        attr.setGapRight(20);
        attr.setFont(new Font("Sky Sans Medium Small Caps", Font.BOLD, 14));

        return attr;
    }
}