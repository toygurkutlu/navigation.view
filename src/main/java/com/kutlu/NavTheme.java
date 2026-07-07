package com.kutlu;

/**
 * Provides attributes for customizing the style of the {@code NavigationView}.
 * <p>
 * • Use {@code NavAttributes} to customize the {@code NavigationView} body style.<br>
 * • Use {@code NavTextAttributes} to customize the style of the titles and subtitles.
 * </p>
 *
 * @see BodyAttributes
 * @see TextAttributes
 */
public class NavTheme {

    private String themeName;
    private int themeId;
    private BodyAttributes bodyAttributes;
    private TextAttributes titleAttributes;
    private TextAttributes subtitleAttributes;

    /**
     * Empty constructor of {@code NavStyle}.<br>
     * Can be used with {@code setter} methods for customizing your style.
     */
    public NavTheme() {
    }

    /**
     * Constructs a deep copy of the specified {@code NavStyle}.
     *
     * @param theme The theme to copy.
     * @apiNote Note that this constructor duplicates the theme with the exact values,
     * including its {@code themeName} and {@code themeId}
     */
    public NavTheme(NavTheme theme) {
        this.themeName = theme.getThemeName();
        this.themeId = theme.getThemeId();

        this.bodyAttributes = new BodyAttributes(theme.getBodyAttributes());
        this.titleAttributes = new TextAttributes(theme.getTitleAttributes());
        this.subtitleAttributes = new TextAttributes(theme.getSubtitleAttributes());
    }

    /**
     * Full constructor for creating {@code NavStyle}.<br>
     * Initializes all attributes of the navigation style.<br>
     * All parameters are required and must not be null.<br>
     *
     * @param bodyAttributes      The NavigationView's style attributes.
     * @param titleAttributes    The title's style attributes.
     * @param subtitleAttributes The subtitle's style attributes.
     */
    public NavTheme(String themeName, int themeId, BodyAttributes bodyAttributes, TextAttributes titleAttributes,
                    TextAttributes subtitleAttributes) {
        this.themeName = themeName;
        this.themeId = themeId;
        this.bodyAttributes = bodyAttributes;
        this.titleAttributes = titleAttributes;
        this.subtitleAttributes = subtitleAttributes;
    }

    /**
     * Gets the name of the theme specified by the user.
     *
     * @return The name of the theme.
     */
    public String getThemeName() {
        return themeName;
    }

    /**
     * Sets the name of the theme specified by the user.
     *
     * @param themeName The name of the theme.
     */
    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    /**
     * Gets the id of the theme specified by the user.
     *
     * @return The id of the theme.
     */
    public int getThemeId() {
        return themeId;
    }

    /**
     * Sets the id of the theme specified by the user.
     *
     * @param themeId The id of the theme.
     */
    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    /**
     * Gets the attributes to customize {@code NavigationView}.
     *
     * @return The {@code NavigationView} style attributes.
     * @see BodyAttributes
     */
    public BodyAttributes getBodyAttributes() {
        return bodyAttributes;
    }

    /**
     * Sets the attributes for customizing the {@code NavigationView}.
     *
     * @param bodyAttributes The {@code NavigationView} style attributes.
     * @see BodyAttributes
     */
    public void setBodyAttributes(BodyAttributes bodyAttributes) {
        this.bodyAttributes = bodyAttributes;
    }

    /**
     * Gets the title attributes.
     *
     * @return The title's {@code NavTextAttributes} object which contains all Title attributes.
     * @see TextAttributes
     */
    public TextAttributes getTitleAttributes() {
        return titleAttributes;
    }

    /**
     * Sets the title attributes to customize your own style.
     *
     * @param titleAttributes The title's {@code NavTextAttributes} object.
     * @see TextAttributes
     */
    public void setTitleAttributes(TextAttributes titleAttributes) {
        this.titleAttributes = titleAttributes;
    }

    /**
     * Gets the subtitle attributes.
     *
     * @return The subtitle's {@code NavTextAttributes} object which contains all Subtitle attributes.
     * @see TextAttributes
     */
    public TextAttributes getSubtitleAttributes() {
        return subtitleAttributes;
    }

    /**
     * Sets the subtitle attributes to customize your own style.
     *
     * @param subtitleAttributes The subtitle's {@code NavTextAttributes} object.
     * @see TextAttributes
     */
    public void setSubtitleAttributes(TextAttributes subtitleAttributes) {
        this.subtitleAttributes = subtitleAttributes;
    }
}