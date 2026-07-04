package com.kutlu;

import java.awt.*;

/**
 * Defines the available themes for the {@code NavigationView}.
 * <p>
 * • Provides pre-defined Dark, Dark Orange, Light and Ivory themes.<br>
 * • Allows users to create customized themes using {@code NavStyle} and store them via {@code NavStyleManager}.
 * </p>
 *
 * @see NavStyle
 * @see NavStyleManager
 */
public class NavThemes {
    public static final int DARK_INDEX = 0;
    public static String DARK ="Dark";
    public static final int DARK_ORANGE_INDEX = 1;
    public static String DARK_ORANGE ="Dark Orange";
    public static final int LIGHT_INDEX = 2;
    public static String LIGHT ="Light";
    public static final int IVORY_INDEX = 3;
    public static String IVORY ="Ivory";

    /**
     * Gets the pre-defined Dark Theme.
     *
     * @return The pre-defined Dark Theme instance.
     */
    public static NavStyle Dark() {
        NavStyle style = new NavStyle();
        style.setNavAttributes(getDarkNavAttributes());
        style.setTitleAttributes(getDarkTitleAttributes());
        style.setSubtitleAttributes(getDarkSubtitleAttributes());

        return style;
    }
    private static NavAttributes getDarkNavAttributes(){
        NavAttributes attr = new NavAttributes();

        Color collapseColor = new Color(175, 175, 195);

        int iconSize = 24;

        attr.setBackground(new Color(41, 41, 41));
        attr.setCollapseIconsColor(collapseColor);
        attr.setCollapseIconsColored(false);
        attr.setCollapseIconsSize(iconSize);

        return attr;
    }
    private static NavTitleAttributes getDarkTitleAttributes() {
        NavTitleAttributes attr = new NavTitleAttributes();

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
    private static NavSubtitleAttributes getDarkSubtitleAttributes() {
        NavSubtitleAttributes attr = new NavSubtitleAttributes();

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
     * Gets the pre-defined Light Theme.
     *
     * @return The pre-defined Light Theme instance.
     */
    public static NavStyle Light() {
        NavStyle style = new NavStyle();

        style.setNavAttributes(getLightNavAttributes());
        style.setTitleAttributes(getLightTitleAttributes());
        style.setSubtitleAttributes(getLightSubtitleAttributes());

        return style;
    }
    private static NavAttributes getLightNavAttributes(){

        NavAttributes attr = new NavAttributes();

        Color collapseColor = new Color(250, 250, 250);
        int iconSize = 24;

        attr.setBackground(new Color(90, 100, 150));
        attr.setCollapseIconsColor(collapseColor);
        attr.setCollapseIconsColored(false);
        attr.setCollapseIconsSize(iconSize);

        return attr;
    }
    private static NavTitleAttributes getLightTitleAttributes() {
        NavTitleAttributes attr = new NavTitleAttributes();

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
    private static NavSubtitleAttributes getLightSubtitleAttributes() {
        NavSubtitleAttributes attr = new NavSubtitleAttributes();

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
     * Gets the pre-defined Dark Orange Theme.
     *
     * @return The pre-defined Dark Orange Theme instance.
     */
    public static NavStyle DarkOrange() {
        NavStyle style = new NavStyle();
        style.setNavAttributes(getDarkOrangeNavAttributes());
        style.setTitleAttributes(getDarkOrangeTitleAttributes());
        style.setSubtitleAttributes(getDarkOrangeSubtitleAttributes());

        return style;
    }
    private static NavAttributes getDarkOrangeNavAttributes(){
        NavAttributes attr = new NavAttributes();

        Color collapseColor = new Color(155, 75, 45);

        int iconSize = 24;

        attr.setBackground(new Color(25, 25, 25));
        attr.setCollapseIconsColor(collapseColor);
        attr.setCollapseIconsColored(false);
        attr.setCollapseIconsSize(iconSize);

        return attr;
    }
    private static NavTitleAttributes getDarkOrangeTitleAttributes() {
        NavTitleAttributes attr = new NavTitleAttributes();

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
    private static NavSubtitleAttributes getDarkOrangeSubtitleAttributes() {
        NavSubtitleAttributes attr = new NavSubtitleAttributes();

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
     * Gets the pre-defined Ivory Theme.
     *
     * @return The pre-defined Ivory Theme instance.
     */
    public static NavStyle Ivory() {
        NavStyle style = new NavStyle();
        style.setNavAttributes(getIvoryNavAttributes());
        style.setTitleAttributes(getIvoryTitleAttributes());
        style.setSubtitleAttributes(getIvorySubtitleAttributes());

        return style;
    }
    private static NavAttributes getIvoryNavAttributes(){
        NavAttributes attr = new NavAttributes();

        Color collapseColor = new Color(200, 200, 175);

        int iconSize = 24;

        attr.setBackground(new Color(45, 50, 50));
        attr.setCollapseIconsColor(collapseColor);
        attr.setCollapseIconsColored(false);
        attr.setCollapseIconsSize(iconSize);

        return attr;
    }
    private static NavTitleAttributes getIvoryTitleAttributes() {
        NavTitleAttributes attr = new NavTitleAttributes();

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
    private static NavSubtitleAttributes getIvorySubtitleAttributes() {
        NavSubtitleAttributes attr = new NavSubtitleAttributes();

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