package org.kutlu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Represents an item within the {@code NavigationView}.
 * <p>
 * • Can be used with or without subtitles.<br>
 * • If used with subtitles, subtitles will be grouped and will feature an expand/collapse mechanism.<br>
 * • If used without subtitles,  a simple {@code ListView} appearance (with or without icons) can be achieved.
 * </p>
 */
public class NavItem {
    private String title;
    private Icon titleIcon;
    private String[] subtitles;
    private Icon[] icons;

    /**
     * Creates a {@code NavItem} instance for the {@code NavigationView}.
     *
     * @param title      The title text.
     * @param titleIcon  The icon for the title.
     * @param subtitles  An array of {@code String} objects representing the subtitles.
     * @param icons      An array of {@code Icon} objects representing the subtitle icons.
     * @apiNote
     * • Omit subtitles if you want to achieve a simple {@code ListView} appearance (with or without icons).<br>
     * • Provide subtitles if you want to group them and achieve a tree-like appearance.<br>
     */
    public NavItem(@NotNull String title, @Nullable Icon titleIcon, @Nullable String[] subtitles,
                   @Nullable Icon[] icons) {
        this.title = title;
        this.titleIcon = titleIcon;
        this.subtitles = subtitles;
        this.icons = icons;
    }

    /**
     * Gets the title of {@code NavItem}.
     *
     * @return The title text of the {@code NavItem}.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of {@code NavItem}.
     *
     * @param title The title text for the {@code NavItem}.
     * @apiNote Set the title if you want to group your subtitles.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the title icon of {@code NavItem}.
     *
     * @return The icon of the title, or {@code null} if the title does not have an icon.
     */
    public Icon getTitleIcon() {
        return titleIcon;
    }

    /**
     * Sets the title icon for {@code NavItem}.
     *
     * @param titleIcon The icon of the title.
     */
    public void setTitleIcon(Icon titleIcon) {
        this.titleIcon = titleIcon;
    }

    /**
     * Gets the subtitles of {@code NavItem}.
     *
     * @return An array of {@code String} objects representing the subtitles or {@code null} if not provided.
     */
    public String[] getSubtitles() {
        return subtitles;
    }

    /**
     * Sets the subtitles for {@code NavItem}.
     *
     * @param subtitles An array of {@code String} objects representing the subtitles.
     */
    public void setSubtitles(String[] subtitles) {
        this.subtitles = subtitles;
    }

    /**
     * Gets the icons of Subtitles
     * @return An array of {@code Icon} objects representing the subtitle icons or {@code null} if not provided.
     * */
    public Icon[] getIcons() {
        return icons;
    }

    /**
     * Sets the icons for the Subtitles.
     *
     * @param icons An array of {@code Icon} objects representing the subtitle icons.
     */
    public void setIcons(Icon[] icons) {
        this.icons = icons;
    }
}