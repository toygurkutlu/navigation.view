package com.kutlu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * Builds the specified named {@code NavigationView} using a varargs list of {@code NavItem} instances.
 * <p>
 * The provided {@code NavItem}s can contain either titles only or both titles and subtitles.
 * </p>
 * <ul>
 *   <li>Uses the {@code navName} to identify the {@code NavigationView} instances.</li>
 *   <li><b>If used with only titles:</b> The {@code NavigationView} appearance will be like a simple {@code ListView}.</li>
 *   <li><b>If used with titles and subtitles:</b> The {@code NavigationView} appearance will be like a {@code Tree}, and the subtitles will feature an expand/collapse mechanism.</li>
 * </ul>
 *
 * @see NavItem
 */
public class NavigationView extends JPanel {

    private final NavItem[] navItems;
    private NavItemView[] items;
    private final String navName;
    private int selectedTitle, selectedSub;
    private OnItemClickListener listener;
    private BodyAttributes attr;
    private Icon navCollapsedIcon;
    private Icon navExpandedIcon;
    private JPanel textPanel;
    private JLabel iconLabel;
    private boolean isNavCollapsed;
    private boolean navCanCollapse;

    /**
     * A listener interface for receiving title and subtitle click events.
     * <p>
     * This interface provides the following methods:
     * <ul>
     *     <li>{@code onTitleClick(int titleIndex)}: Invoked when a title is clicked.</li>
     *     <li>{@code onSubtitleClick(int titleIndex, int subtitleIndex)}: Invoked when a subtitle is clicked.</li>
     * </ul>
     * </p>
     *
     * @apiNote This interface is internally linked to {@code NavItemView.OnItemClickListener}.
     * When this listener is invoked, the corresponding event in {@code NavItemView.OnItemClickListener}
     * is automatically triggered.
     */
    public interface OnItemClickListener {

        /**
         * Invoked when a title is clicked.
         *
         * @param titleIndex The index of the clicked title.
         * @apiNote As this listener is internally linked to {@code NavItemView}, invoking this method
         * will automatically trigger the corresponding {@code NavItemView.onTitleClick} event.
         */
        void onTitleClick(int titleIndex);

        /**
         * Invoked when a subtitle is clicked.
         *
         * @param titleIndex    The index of the group title.
         * @param subtitleIndex The index of the clicked subtitle.
         * @apiNote As this listener is internally linked to {@code NavItemView}, invoking this method
         * will automatically trigger the corresponding {@code NavItemView.onSubtitleClick} event.
         */

        void onSubtitleClick(int titleIndex, int subtitleIndex);
    }

    /**
     * Sets the click listener for both titles and subtitles.
     * <p>
     * • Use {@code onTitleClick(int titleIndex)} to handle title clicks.<br>
     * • Use {@code onSubtitleClick(int titleIndex, int subtitleIndex)} to handle subtitle clicks.
     * </p>
     *
     * @param listener The click listener to set.
     * @apiNote This interface is internally linked to {@code NavItemView.OnItemClickListener}.
     * When this listener is invoked, the corresponding event in {@code NavItemView.OnItemClickListener}
     * is automatically triggered.
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Creates new {@code NavigationView} with {@code navName} which contains {@code navItems}.
     *
     * @param navName  The name of the {@code NavigationView}.
     * @param navItems Varargs of {@code NavItem} to display.
     * @apiNote Can contain either only one {@code NavItem} or an array of {@code NavItem}. <br>
     * {@code NavItem} can contain either titles or titles with subtitles.
     * @see NavItem
     */
    public NavigationView(String navName, NavItem... navItems) {
        super(new GridBagLayout());
        this.navItems = navItems;
        this.navName = navName;

        isNavCollapsed = NavStateManager.isNavCollapsed(navName);
        navCanCollapse = NavStateManager.navCanCollapse(navName);

        attr = Objects.requireNonNull(NavThemeManager.getSelectedTheme()).getBodyAttributes();
        selectedTitle = NavStateManager.getSelectedTitleIndex(navName);
        selectedSub = NavStateManager.getSelectedSubIndex(navName);

        init();
    }

    /**
     * Applies the new theme to the {@code NavigationView}.
     * @param theme The new theme.
     * */
    public void setTheme(NavTheme theme){
        attr = theme.getBodyAttributes();

        if(attr.isCollapseIconsColored()) {
            navExpandedIcon = NavHelper.recolorIcon(navExpandedIcon, attr.getCollapseIconsColor());
            navCollapsedIcon = NavHelper.recolorIcon(navCollapsedIcon, attr.getCollapseIconsColor());
        }

        removeAll();
        init();
        revalidate();
        repaint();
    }

    /**
     * Sets the icon for the collapsed state of the {@code NavigationView}.
     *
     * @param collapsedIcon The icon to set for the collapsed state.
     */
    public void setNavCollapsedIcon(Icon collapsedIcon) {
        this.navExpandedIcon = collapsedIcon;
        updateNavCollapseIcon();
        NavStateManager.setNavCollapsed(navName, true);
    }

    /**
     * Sets the icon for the expanded state of the {@code NavigationView}.
     *
     * @param expandedIcon The icon to set for the expanded state.
     */
    public void setNavExpandedIcon(Icon expandedIcon) {
        this.navCollapsedIcon = expandedIcon;
        updateNavCollapseIcon();
        NavStateManager.setNavCollapsed(navName, false);
    }

    private void updateNavCollapseIcon() {
        iconLabel.setIcon(textPanel.isVisible() ? navExpandedIcon : navCollapsedIcon);
    }

    private void init() {
        items = new NavItemView[navItems.length];

        GridBagConstraints gbc = new GridBagConstraints();

        Color collapseIconColor = attr.getCollapseIconsColor();
        if (navExpandedIcon == null) {
            navExpandedIcon = NavHelper.getDefaultExpandedIcon(collapseIconColor);
        }
        if (navCollapsedIcon == null) {
            navCollapsedIcon = NavHelper.getDefaultCollapsedIcon(collapseIconColor);
        }

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        iconLabel = new JLabel(navExpandedIcon);
        add(iconLabel, gbc);

        setBackground(attr.getBackground());
        gbc.fill = GridBagConstraints.BOTH;

        textPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcText = new GridBagConstraints();
        gbcText.weightx = 1;
        gbcText.anchor = GridBagConstraints.NORTHWEST;
        gbcText.fill = GridBagConstraints.BOTH;

        for (int i = 0; i < navItems.length; i++) {
            NavItemView itemView = new NavItemView(i, navName, navItems[i]);
            items[i] = itemView;

            gbcText.gridy = i;

            textPanel.add(itemView, gbcText);
            itemView.setOnItemClickListener(new NavItemView.OnItemClickListener() {
                @Override
                public void onTitleClick(int titleIndex) {
                    if (listener != null) {
                        listener.onTitleClick(titleIndex);
                        if (!itemView.subtitlesExist() && selectedTitle != titleIndex) {
                            itemView.setTitleSelected();
                            selectedTitle = titleIndex;
                        }
                    }
                }

                @Override
                public void onSubtitleClick(int titleIndex, int subtitleIndex) {
                    if (itemView.subtitlesExist() && listener != null) {
                        if (selectedTitle != titleIndex) {
                            NavItemView navItem = items[selectedTitle];
                            navItem.setTitleDeselected();
                            navItem.setSubDeselected(selectedSub);

                            itemView.setTitleSelected();
                            itemView.setSubSelected(subtitleIndex);

                            selectedTitle = titleIndex;
                            selectedSub = subtitleIndex;

                        } else {
                            if (selectedSub != subtitleIndex) {
                                itemView.setSubDeselected(selectedSub);
                                itemView.setSubSelected(subtitleIndex);

                                selectedSub = subtitleIndex;
                            }
                        }
                        listener.onSubtitleClick(titleIndex, subtitleIndex);
                    }
                }
            });
        }

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;

        add(textPanel, gbc);

        iconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textPanel.setVisible(!textPanel.isVisible());
                iconLabel.setIcon(textPanel.isVisible() ? navExpandedIcon : navCollapsedIcon);
                NavStateManager.setNavCollapsed(navName, !textPanel.isVisible());
                isNavCollapsed = !textPanel.isVisible();
            }
        });
        iconLabel.setIcon(!isNavCollapsed ? navExpandedIcon : navCollapsedIcon);

        textPanel.setVisible(!isNavCollapsed);

        if(!navCanCollapse && NavStateManager.isNavCollapsed(navName)){
            setNavCanCollapse(false);
        }
    }

    /**
     * Sets whether the collapse mechanism is enabled.
     *
     * @param canCollapse {@code true} to enable the collapse mechanism and display the {@code navIcon};
     *                    {@code false} to disable the collapse mechanism and remove the {@code navIcon}.
     */
    public void setNavCanCollapse(boolean canCollapse) {

        if (!textPanel.isVisible()) textPanel.setVisible(true);
        if(iconLabel != null) iconLabel.setVisible(canCollapse);

        NavStateManager.setNavCanCollapse(navName, canCollapse);
        if(navCanCollapse && !canCollapse){
            navCanCollapse = false;
            NavStateManager.setNavCollapsed(navName, false);
            iconLabel.setIcon(navExpandedIcon);
        }
    }
    /**
     * Sets whether the subtitles' collapse mechanism is enabled.
     *
     * @param canCollapse {@code true} to enable the subtitle collapse mechanism;
     *                    {@code false} to disable the subtitle collapse mechanism.
     */
    public void setSubsCanCollapse(boolean canCollapse){
        NavStateManager.setSubsCanCollapse(navName, canCollapse);
        for(NavItemView view : items){
            view.setSubsCanCollapse(canCollapse);
        }
    }
}