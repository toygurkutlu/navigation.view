package com.kutlu;

/**
 * Provides attributes for customizing the style of the {@code NavigationView}.
 * <p>
 * • Use {@code NavAttributes} to customize the {@code NavigationView} body style.<br>
 * • Use {@code NavTitleAttributes} to customize the style of the titles.<br>
 * • Use {@code NavSubtitleAttributes} to customize the style of the subtitles.
 * </p>
 *
 * @see NavAttributes
 * @see NavTitleAttributes
 * @see NavSubtitleAttributes
 */
public class NavStyle {
    private NavAttributes navAttributes;
    private NavTitleAttributes titleAttributes;
    private NavSubtitleAttributes subtitleAttributes;

    /**
     * Empty constructor of {@code NavStyle}.<br>
     * Can be used with {@code setter} methods for customizing your style.
     */
    public NavStyle() {
    }

    /**
     * Full constructor for creating {@code NavStyle}.<br>
     * Initializes all attributes of the navigation style.<br>
     * All parameters are required and must not be null.<br>
     *
     * @param navAttributes      The NavigationView's style attributes.
     * @param titleAttributes    The title's style attributes.
     * @param subtitleAttributes The subtitle's style attributes.
     */
    public NavStyle(NavAttributes navAttributes, NavTitleAttributes titleAttributes,
                    NavSubtitleAttributes subtitleAttributes) {
        this.navAttributes = navAttributes;
        this.titleAttributes = titleAttributes;
        this.subtitleAttributes = subtitleAttributes;
    }

    /**
     * Gets the attributes to customize {@code NavigationView}.
     *
     * @return The {@code NavigationView} style attributes.
     * @see NavAttributes
     */
    public NavAttributes getNavAttributes() {
        return navAttributes;
    }

    /**
     * Sets the attributes for customizing the {@code NavigationView}.
     *
     * @param navAttributes The {@code NavigationView} style attributes.
     * @see NavAttributes
     */
    public void setNavAttributes(NavAttributes navAttributes) {
        this.navAttributes = navAttributes;
    }

    /**
     * Gets the title attributes.
     *
     * @return The title's {@code NavTitleAttributes} object which contains all Title attributes.
     * @see NavTitleAttributes
     */
    public NavTitleAttributes getTitleAttributes() {
        return titleAttributes;
    }

    /**
     * Sets the title attributes to customize your own style.
     *
     * @param titleAttributes The title's {@code NavTitleAttributes} object.
     * @see NavTitleAttributes
     */
    public void setTitleAttributes(NavTitleAttributes titleAttributes) {
        this.titleAttributes = titleAttributes;
    }

    /**
     * Gets the subtitle attributes.
     *
     * @return The subtitle's {@code NavSubtitleAttributes} object which contains all Subtitle attributes.
     * @see NavSubtitleAttributes
     */
    public NavSubtitleAttributes getSubtitleAttributes() {
        return subtitleAttributes;
    }

    /**
     * Sets the subtitle attributes to customize your own style.
     *
     * @param subtitleAttributes The subtitle's {@code NavSubtitleAttributes} object.
     * @see NavSubtitleAttributes
     */
    public void setSubtitleAttributes(NavSubtitleAttributes subtitleAttributes) {
        this.subtitleAttributes = subtitleAttributes;
    }
}