package com.kutlu;


import java.awt.*;

/**
 * Provides attributes for customizing the style of the {@code Subtitles}.
 * <p>
 * • For the {@code NavigationView} body appearance, use {@code NavAttributes}.<br>
 * • For the {@code Title} text appearance, use {@code NavTitleAttributes}.
 * </p>
 *
 * @see NavAttributes
 * @see NavTitleAttributes
 */
public class NavSubtitleAttributes extends NavTextAttributes {

    private String foreground;
    private String background;
    private String selectedBackground;
    private String selectedForeground;
    private String hoverBackground;
    private String hoverForeground;
    private TextPosition textPosition;
    private int iconTextGap;
    private int gapTop;
    private int gapLeft;
    private int gapBottom;
    private int gapRight;
    private String fontFamily;
    private int fontStyle;
    private int fontSize;

    /**
     * Gets the foreground color.
     *
     * @return The text color (foreground).
     */
    @Override
    public Color getForeground() {
        return NavHelper.hexToColor(foreground);
    }

    /**
     * Sets the foreground color.
     *
     * @param foreground The text color (foreground).
     */
    public void setForeground(Color foreground) {
        this.foreground = NavHelper.colorToHex(foreground);
    }

    /**
     * Gets the background color.
     *
     * @return The text's background color.
     */
    @Override
    public Color getBackground() {
        return NavHelper.hexToColor(background);
    }

    /**
     * Sets the background color.
     *
     * @param background The text's background color.
     */
    public void setBackground(Color background) {
        this.background = NavHelper.colorToHex(background);
    }

    /**
     * Gets the background color of selected item.
     *
     * @return The selected item's background color.
     * @apiNote This background color is used to indicate which item is selected when a user clicks it.
     */
    @Override
    public Color getSelectedBackground() {
        return NavHelper.hexToColor(selectedBackground);
    }

    /**
     * Sets the background color of selected item.
     *
     * @param selectedBackground The selected item's background color.
     * @apiNote • This background color is used to indicate which item is selected when a user clicks it.
     */
    public void setSelectedBackground(Color selectedBackground) {
        this.selectedBackground = NavHelper.colorToHex(selectedBackground);
    }

    /**
     * Gets the foreground color of selected item.
     *
     * @return The selected item's foreground color.
     * @apiNote This foreground color is used to indicate which item is selected when a user clicks it.
     */
    @Override
    public Color getSelectedForeground() {
        return NavHelper.hexToColor(selectedForeground);
    }

    /**
     * Sets the foreground color of selected item.
     *
     * @param selectedForeground The selected item's foreground color.
     * @apiNote • This foreground color is used to indicate which item is selected when a user clicks it.
     */
    public void setSelectedForeground(Color selectedForeground) {
        this.selectedForeground = NavHelper.colorToHex(selectedForeground);
    }

    /**
     * Gets the hover background color.
     *
     * @return The hover background color.
     * @apiNote The {@code hoverBackground} color is used to highlight the title
     * by changing its background when the mouse hovers over its area.
     */
    @Override
    public Color getHoverBackground() {
        return NavHelper.hexToColor(hoverBackground);
    }

    /**
     * Sets the hover background color.
     *
     * @param hoverBackground The hover background color.
     * @apiNote The {@code hoverBackground} color is used to highlight the title
     * by changing its background when the mouse hovers over its area.
     */
    public void setHoverBackground(Color hoverBackground) {
        this.hoverBackground = NavHelper.colorToHex(hoverBackground);
    }

    /**
     * Gets the hover foreground color.
     *
     * @return The hover foreground color.
     * @apiNote The {@code hoverForeground} color is used to highlight the title
     * by changing its foreground when the mouse hovers over its area.
     */
    @Override
    public Color getHoverForeground() {
        return NavHelper.hexToColor(hoverForeground);
    }

    /**
     * Sets the hover foreground color.
     *
     * @param hoverForeground The hover foreground color.
     * @apiNote The {@code hoverForeground} color is used to highlight the title
     * by changing its foreground when the mouse hovers over its area.
     */
    public void setHoverForeground(Color hoverForeground) {
        this.hoverForeground = NavHelper.colorToHex(hoverForeground);
    }

    /**
     * Gets the position of the text.
     *
     * @return Text's position (either {@code TextPosition.LEFT} or {@code TextPosition.RIGHT}).
     * @apiNote {@code TextPositions} instances can be compared using the {@code ==} operator.
     * @see TextPosition
     */
    @Override
    public TextPosition getTextPosition() {
        return textPosition;
    }

    /**
     * Gets the position of the text.
     *
     * @param textPosition Text's position. Can be one of followings:<br>
     *                     • {@code TextPosition.LEFT} ({@code TEXT} then {@code ICON}),<br>
     *                     • {@code TextPosition.RIGHT} ({@code ICON} then {@code TEXT}).
     * @apiNote {@code TextPosition} instances can be compared using the {@code ==} operator.
     * @see TextPosition
     */
    public void setTextPosition(TextPosition textPosition) {
        this.textPosition = textPosition;
    }

    /**
     * Gets the space between the text and the icon.
     *
     * @return Gap size in pixels.
     */
    @Override
    public int getIconTextGap() {
        return iconTextGap;
    }

    /**
     * Sets the space between the text and the icon.
     *
     * @param iconTextGap Gap size in pixels.
     */
    public void setIconTextGap(int iconTextGap) {
        this.iconTextGap = iconTextGap;
    }

    /**
     * Gets the space between the text and the item above it.
     *
     * @return The gap size in pixels.
     * @apiNote • {@code gapTop} represents the space between consecutive subtitles.<br>
     * • For the first subtitle of a group, it represents the space between the subtitle and its parent title.
     */
    @Override
    public int getGapTop() {
        return gapTop;
    }

    /**
     * Sets the space between the text and the item above it.
     *
     * @param gapTop The gap size in pixels.
     * @apiNote • {@code gapTop} sets the space between consecutive subtitles.<br>
     * • For the first subtitle of a group, it sets the space between the subtitle and its parent title.
     */
    public void setGapTop(int gapTop) {
        this.gapTop = gapTop;
    }

    /**
     * Gets the left indentation space for the text relative to the NavigationView.
     *
     * @return The gap size in pixels.
     */
    @Override
    public int getGapLeft() {
        return gapLeft;
    }

    /**
     * Sets the left indentation space for the text relative to the NavigationView.
     *
     * @param gapLeft The gap size in pixels.
     */
    public void setGapLeft(int gapLeft) {
        this.gapLeft = gapLeft;
    }

    /**
     * Gets the space between the text and the item below it.
     *
     * @return The gap size in pixels.
     * @apiNote • {@code gapBottom} represents the space between consecutive subtitles.<br>
     * • For the last subtitle of a group, it represents the space between that subtitle and the next title (except for the very last item in the entire navigation structure).
     */
    @Override
    public int getGapBottom() {
        return gapBottom;
    }

    /**
     * Sets the space between the text and the item below it.
     *
     * @param gapBottom The gap size in pixels.
     * @apiNote • {@code gapBottom} sets the space between consecutive subtitles.<br>
     * • For the last subtitle of a group, it sets the space between that subtitle and the next title (except for the very last item in the entire navigation structure).
     */
    public void setGapBottom(int gapBottom) {
        this.gapBottom = gapBottom;
    }

    /**
     * Gets the right indentation space for the text relative to the NavigationView.
     *
     * @return The gap size in pixels.
     */
    @Override
    public int getGapRight() {
        return gapRight;
    }

    /**
     * Sets the right indentation space for the text relative to the NavigationView.
     *
     * @param gapRight The gap size in pixels.
     */
    public void setGapRight(int gapRight) {
        this.gapRight = gapRight;
    }

    /**
     * Gets the font of the text.
     *
     * @return The font of the text.
     */
    @Override
    public Font getFont() {
        return new Font(fontFamily, fontStyle, fontSize);
    }

    /**
     * Sets the font of the text.
     *
     * @param font The font of the text.
     */
    public void setFont(Font font) {
        setFontFamily(font.getFamily());
        setFontStyle(font.getStyle());
        setFontSize(font.getSize());
    }

    /**
     * Gets the font family of the text.
     *
     * @return The font family of the text.
     */
    @Override
    public String getFontFamily() {
        return fontFamily;
    }

    /**
     * Sets the font family of the text.
     *
     * @param fontFamily The font family of the text.
     */
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    /**
     * Gets the font style of the text.
     *
     * @return The font style of the text.
     */
    @Override
    public int getFontStyle() {
        return fontStyle;
    }

    /**
     * Sets the font style of the text.
     *
     * @param fontStyle The font style of the text.
     */
    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    /**
     * Gets the font style of the text.
     *
     * @return The font style of the text.
     */
    @Override
    public int getFontSize() {
        return fontStyle;
    }

    /**
     * Sets the font size of the text.
     *
     * @param fontSize The font size of the text.
     */
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}