package com.kutlu;

import java.awt.*;

/**
 * Provides attributes for customizing the style of the text items.<br>
 * <p>• For the {@code NavigationView} body appearance, use {@code NavAttributes}.</p>
 *
 * @see BodyAttributes
 */
public class TextAttributes {

    private String foreground;
    private String background;
    private String selectedForeground;
    private String selectedBackground;
    private String hoverForeground;
    private String hoverBackground;
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
     * Empty constructor of {@code NavTextAttributes}.<br>
     * Can be used with {@code setter} methods for customizing your text style.
     */
    public TextAttributes() {
    }

    /**
     * Constructs a deep copy of the specified {@code NavTextAttributes}.
     *
     * @param textAttributes The text attributes to copy.
     */
    public TextAttributes(TextAttributes textAttributes) {
        this.foreground = NavHelper.colorToHex(textAttributes.getForeground());
        this.background = NavHelper.colorToHex(textAttributes.getBackground());
        this.selectedForeground = NavHelper.colorToHex(textAttributes.getSelectedForeground());
        this.selectedBackground = NavHelper.colorToHex(textAttributes.getSelectedBackground());
        this.hoverForeground = NavHelper.colorToHex(textAttributes.getHoverForeground());
        this.hoverBackground = NavHelper.colorToHex(textAttributes.getHoverBackground());
        this.textPosition = textAttributes.getTextPosition();
        this.iconTextGap = textAttributes.getIconTextGap();
        this.gapTop = textAttributes.getGapTop();
        this.gapLeft = textAttributes.getGapLeft();
        this.gapBottom = textAttributes.getGapBottom();
        this.gapRight = textAttributes.getGapRight();
        this.fontFamily = textAttributes.getFontFamily();
        this.fontStyle = textAttributes.getFontStyle();
        this.fontSize = textAttributes.getFontSize();
    }

    /**
     * Gets the foreground color.
     *
     * @return The text color (foreground).
     */
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
     * Gets the foreground color of selected item.
     *
     * @return The selected item's foreground color.
     * @apiNote This foreground color is used to indicate which item is selected when a user clicks it.
     */
    public Color getSelectedForeground() {
        return NavHelper.hexToColor(selectedForeground);
    }

    /**
     * Sets the foreground color of selected item.
     *
     * @param selectedForeground The selected item's foreground color.
     * @apiNote • This foreground color is used to indicate which item is selected when a user clicks it.<br>
     * • Note that if subtitles are present, the Title's foreground color will only change if a subtitle is selected.
     */
    public void setSelectedForeground(Color selectedForeground) {
        this.selectedForeground = NavHelper.colorToHex(selectedForeground);
    }

    /**
     * Gets the background color of selected item.
     *
     * @return The selected item's background color.
     * @apiNote This background color is used to indicate which item is selected when a user clicks it.
     */
    public Color getSelectedBackground() {
        return NavHelper.hexToColor(selectedBackground);
    }

    /**
     * Sets the background color of selected item.
     *
     * @param selectedBackground The selected item's background color.
     * @apiNote • This background color is used to indicate which item is selected when a user clicks it.<br>
     * • Note that if subtitles present, the Title's background color will only change if a subtitle is selected.
     */
    public void setSelectedBackground(Color selectedBackground) {
        this.selectedBackground = NavHelper.colorToHex(selectedBackground);
    }

    /**
     * Gets the hover foreground color.
     *
     * @return The hover foreground color.
     * @apiNote The {@code hoverForeground} color is used to highlight the text
     * by changing its foreground when the mouse hovers over its area.
     */
    public Color getHoverForeground() {
        return NavHelper.hexToColor(hoverForeground);
    }

    /**
     * Sets the hover foreground color.
     *
     * @param hoverForeground The hover foreground color.
     * @apiNote The {@code hoverForeground} color is used to highlight the text
     * by changing its foreground when the mouse hovers over its area.
     */
    public void setHoverForeground(Color hoverForeground) {
        this.hoverForeground = NavHelper.colorToHex(hoverForeground);
    }

    /**
     * Gets the hover background color.
     *
     * @return The hover background color.
     * @apiNote The {@code hoverBackground} color is used to highlight the text
     * by changing its background when the mouse hovers over its area.
     */
    public Color getHoverBackground() {
        return NavHelper.hexToColor(hoverBackground);
    }

    /**
     * Sets the hover background color.
     *
     * @param hoverBackground The hover background color.
     * @apiNote The {@code hoverBackground} color is used to highlight the text
     * by changing its background when the mouse hovers over its area.
     */
    public void setHoverBackground(Color hoverBackground) {
        this.hoverBackground = NavHelper.colorToHex(hoverBackground);
    }

    /**
     * Gets the position of the text.
     *
     * @return Text's position (either {@code TextPosition.LEFT} or {@code TextPosition.RIGHT}).
     * @apiNote {@code TextPositions} instances can be compared using the {@code ==} operator.
     * @see TextPosition
     */
    public TextPosition getTextPosition() {
        return textPosition;
    }

    /**
     * Sets the position of the Text.
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
     * Gets the space between the item text and the element above it.
     *
     * @return The gap size in pixels.
     * @apiNote <p>
     * <b>If used with titles:</b> {@code gapTop} represents the space between the current title and either the previous title's last subtitle (if present) or the previous title itself.<br>
     * • Note that for the first title, it represents the space between the title and the top of the {@code NavigationView}.<br><br>
     * <b>If used with subtitles:</b> {@code gapTop} represents the space between consecutive subtitles.<br>
     * • Note that for the first subtitle of a group, it represents the space between the subtitle and its parent title.
     * </p>
     */
    public int getGapTop() {
        return gapTop;
    }

    /**
     * Gets the space between the item text and the element above it.
     *
     * @param gapTop The gap size in pixels.
     * @apiNote <p>
     * <b>If used with titles:</b> {@code gapTop} represents the space between the current title and either the previous title's last subtitle (if present) or the previous title itself.<br>
     * • Note that for the first title, it represents the space between the title and the top of the {@code NavigationView}.<br><br>
     * <b>If used with subtitles:</b> {@code gapTop} represents the space between consecutive subtitles.<br>
     * • Note that for the first subtitle of a group, it represents the space between the subtitle and its parent title.
     * </p>
     */
    public void setGapTop(int gapTop) {
        this.gapTop = gapTop;
    }

    /**
     * Gets the left indentation space for the text relative to the {@code NavigationView}.
     *
     * @return The gap size in pixels.
     */
    public int getGapLeft() {
        return gapLeft;
    }

    /**
     * Sets the left indentation space for the text relative to the {@code NavigationView}.
     *
     * @param gapLeft The gap size in pixels.
     */
    public void setGapLeft(int gapLeft) {
        this.gapLeft = gapLeft;
    }

    /**
     * Gets the space between the item text and the element below it.
     *
     * @return The gap size in pixels.
     * @apiNote <p>
     *     <b>If used with titles:</b> {@code gapBottom} represents the space between the current title and either its first subtitle (if present) or the next title itself.<br>
     *     • Note that for the last title and if subtitles do not exist, it represents the space between the title and the bottom of the {@code `NavigationView`}.<br><br>
     *     <b>If used with subtitles:</b> {@code gapBottom} represents the space between consecutive subtitles.<br>
     *     • For the last subtitle of a group, it represents the space between that subtitle and the next title.<br>
     *     • Note that for the very last subtitle in the entire structure, it represents the space between that subtitle and the bottom of the {@code `NavigationView`}.
     * </p>
     */
    public int getGapBottom() {
        return gapBottom;
    }

    /**
     * Sets the space between the item text and the element below it.
     *
     * @param gapBottom  The gap size in pixels.
     * @apiNote <p>
     *     <b>If used with titles:</b> {@code gapBottom} represents the space between the current title and either its first subtitle (if present) or the next title itself.<br>
     *     • Note that for the last title and if subtitles do not exist, it represents the space between the title and the bottom of the {@code `NavigationView`}.<br><br>
     *     <b>If used with subtitles:</b> {@code gapBottom} represents the space between consecutive subtitles.<br>
     *     • For the last subtitle of a group, it represents the space between that subtitle and the next title.<br>
     *     • Note that for the very last subtitle in the entire structure, it represents the space between that subtitle and the bottom of the {@code `NavigationView`}.
     * </p>
     */
    public void setGapBottom(int gapBottom) {
        this.gapBottom = gapBottom;
    }

    /**
     * Gets the right indentation space for the text relative to the {@code NavigationView}.
     *
     * @return The gap size in pixels.
     */
    public int getGapRight() {
        return gapRight;
    }

    /**
     * Sets the right indentation space for the text relative to the {@code NavigationView}.
     *
     * @param gapRight The gap size in pixels.
     */
    public void setGapRight(int gapRight) {
        this.gapRight = gapRight;
    }

    /**
     * Gets the font of the text.
     *
     * @return  font The font of the text.
     */
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
     * Gets the font size of the text.
     *
     * @return The font style of the text.
     */
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