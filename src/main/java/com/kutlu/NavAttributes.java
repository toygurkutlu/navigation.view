package com.kutlu;

import java.awt.*;

/**
 * Provides attributes for customizing the style of the {@code NavigationView} body.<br>
 * <p>
 * • For the {@code NavigationView} body appearance, use {@code NavAttributes}.<br>
 * • For the {@code Subtitle} text appearance, use {@code NavSubtitleAttributes}.<br>
 * </p>
 *
 * @see NavTitleAttributes
 * @see NavSubtitleAttributes
 */
public class NavAttributes {
    private String background;
    private String collapseIconsColor;
    private boolean isCollapseIconsColored;
    int collapseIconsSize;

    /**
     * Gets the background color of the {@code NavigationView}.
     *
     * @return The background color.
     */
    public Color getBackground() {
        return Color.decode(background);
    }

    /**
     * Sets the  background color of the {@code NavigationView}.
     *
     * @param background The background color.
     */
    public void setBackground(Color background) {
        this.background = NavHelper.colorToHex(background);
    }

    /**
     * Gets the arrow icon color which located in the upper right corner.
     *
     * @return The arrow icon color.
     */
    public Color getCollapseIconsColor() {
        return NavHelper.hexToColor(collapseIconsColor);
    }

    /**
     * Sets the arrow icon color which located in the upper right corner.
     *
     * @param collapseIconsColor The arrow icon color.
     */
    public void setCollapseIconsColor(Color collapseIconsColor) {
        this.collapseIconsColor = NavHelper.colorToHex(collapseIconsColor);
    }

    /**
     * Gets whether the collapse icons (collapsed and expended) uses the collapseIconsColor.
     *
     * @return {@code true} if the collapse icons uses the collapseIconsColor;
     *         {@code false} if it uses its original color.
     */
    public boolean isCollapseIconsColored() {
        return isCollapseIconsColored;
    }

    /**
     * Sets whether the collapse icons (collapsed and expended) uses the collapseIconsColor.
     *
     * @param isCollapsedIconsColored  {@code true} if the collapsed icon uses the collapseIconsColor;
     *         {@code false} if it uses its original color.
     */
    public void setCollapseIconsColored(boolean isCollapsedIconsColored) {
        this.isCollapseIconsColored = isCollapsedIconsColored;
    }

    /**
     * Gets the size of the collapse icons.
     *
     * @return The size of the collapse icons in pixels.
     */
    public int getCollapseIconsSize() {
        return collapseIconsSize;
    }

    /**
     * Sets the size of the collapse icons.
     *
     * @param collapseIconsSize The size of the collapse icons in pixels.
     */
    public void setCollapseIconsSize(int collapseIconsSize) {
        this.collapseIconsSize = collapseIconsSize;
    }
}