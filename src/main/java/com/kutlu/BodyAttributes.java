package com.kutlu;

import java.awt.*;

/**
 * Provides attributes for customizing the style of the {@code NavigationView} body.<br>
 * <p>
 * • For the text appearance, use {@code TextAttributes}.<br>
 * </p>
 *
 * @see TextAttributes
 */
public class BodyAttributes {

    private String background;
    private String collapseIconsColor;
    private boolean isCollapseIconsColored;

    /**
     * Empty constructor of {@code BodyAttributes}.<br>
     * Can be used with {@code setter} methods for customizing your style.
     */
    public BodyAttributes() {
    }

    /**
     * Constructs a deep copy of the specified {@code BodyAttributes}.
     *
     * @param bodyAttributes The body attributes to copy.
     */
    public BodyAttributes(BodyAttributes bodyAttributes) {
        this.background = NavHelper.colorToHex(bodyAttributes.getBackground());
        this.collapseIconsColor = NavHelper.colorToHex(bodyAttributes.getCollapseIconsColor());
        this.isCollapseIconsColored = bodyAttributes.isCollapseIconsColored();
    }

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
}