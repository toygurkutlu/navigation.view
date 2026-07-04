package org.kutlu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents the appearance of each {@code NavItem} within the {@code NavigationView}.
 * <p>
 * Uses the same {@code navName} as the {@code NavigationView} to bind them together.
 * </p>
 *
 * @apiNote {@code NavItemView} components use the selected theme ({@code NavThemes}).
 * @see NavItem
 */
public class NavItemView extends JPanel {
    private final NavItem navItem;
    private final String navName;
    private JPanel headerPanel;
    private JPanel subPanel;
    private JLabel titleLabel;
    private final NavStyle style;
    private final NavTitleAttributes titleStyle;
    private final NavSubtitleAttributes subStyle;
    private OnItemClickListener listener;
    private final int titleIndex;
    private int subWidth = 0;
    private int headerWidth = 0;

    /**
     * A listener interface for receiving title and subtitle click events.
     * <p>
     * This interface provides the following methods:
     * <ul>
     *     <li>{@code onTitleClick(int titleIndex)}: Invoked when a title is clicked.</li>
     *     <li>{@code onSubtitleClick(int titleIndex, int subtitleIndex)}: Invoked when a subtitle is clicked.</li>
     * </ul>
     * </p>
     */
    public interface OnItemClickListener {
        /**
         * Invoked when a title is clicked.
         *
         * @param titleIndex The index of the clicked title.
         */
        void onTitleClick(int titleIndex);

        /**
         * Invoked when a subtitle is clicked.
         *
         * @param titleIndex    The index of the group title.
         * @param subtitleIndex The index of the clicked subtitle.
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
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Creates a {@code NavItemView} instance with the specified title index, navigation name, and data object.
     *
     * @param titleIndex The index of the title.
     * @param navName    The name of the {@code NavigationView}.
     * @param navItem    The {@code NavItem} data object to render.
     * @apiNote The {@code navItem} object can contain either titles only or both titles and subtitles (optionally with icons).
     * @see NavItem
     */
    public NavItemView(int titleIndex, String navName, NavItem navItem) {
        super(new GridBagLayout());
        this.navItem = navItem;
        this.navName = navName;
        this.titleIndex = titleIndex;

        style = NavStyleManager.getSelectedTheme();
        titleStyle = style.getTitleAttributes();
        subStyle = style.getSubtitleAttributes();
        init();
    }

    /**
     * Sets the selected appearance for the title.
     */
    public void setTitleSelected() {
        headerPanel.setBackground(titleStyle.getSelectedBackground());
        titleLabel.setForeground(titleStyle.getSelectedForeground());

        NavStateManager.setSelectedTitleIndex(navName, titleIndex);
    }

    /**
     * Sets the deselected appearance for the title.
     */
    public void setTitleDeselected() {
        headerPanel.setBackground(titleStyle.getBackground());
        titleLabel.setForeground(titleStyle.getForeground());
    }

    /**
     * Sets the selected appearance for the specified subtitle.
     *
     * @param subIndex The index of the subtitle to select.
     * @apiNote Also invokes state management to update the selected subtitle index
     * via the {@code NavStateManager.setSelectedSubIndex} method.
     * @see NavStateManager
     */
    public void setSubSelected(int subIndex) {
        JPanel itemPanel = (JPanel) subPanel.getComponent(subIndex);
        String subName = navName + "." + titleIndex + ".sub." + subIndex;
        for (Component c : itemPanel.getComponents()) {
            if (c instanceof JLabel && c.getName().equals(subName)) {
                c.setForeground(subStyle.getSelectedForeground());
                itemPanel.setBackground(subStyle.getSelectedBackground());
                NavStateManager.setSelectedSubIndex(navName, subIndex);
            }
        }
    }

    /**
     * Sets the deselected appearance for the specified subtitle.
     *
     * @param subIndex The index of the subtitle to deselect.
     */
    public void setSubDeselected(int subIndex) {
        JPanel itemPanel = (JPanel) subPanel.getComponent(subIndex);
        String subName = navName + "." + titleIndex + ".sub." + subIndex;
        for (Component c : itemPanel.getComponents()) {
            if (c instanceof JLabel && c.getName().equals(subName)) {
                c.setForeground(subStyle.getForeground());
                itemPanel.setBackground(subStyle.getBackground());
            }
        }
    }

    /**
     * Gets the maximum width between the {@code headerPanel} and the {@code subPanel}.
     *
     * @return The maximum width value in pixels.
     */
    public int getMaxWidth() {
        return Math.max(headerWidth, subWidth);
    }

    /**
     * Gets the availability of the subtitles.
     *
     * @return {@code true} if the subtitles are present; {@code false} otherwise.
     */
    public boolean subtitlesExist() {
        return navItem.getSubtitles() != null && navItem.getSubtitles().length > 0;
    }

    private void init() {


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;

        createHeaderPanel();
        add(headerPanel, gbc);

        if (subtitlesExist()) {
            gbc.gridy = 1;
            createSubPanel();
            add(subPanel, gbc);
        }

        setBackground(style.getNavAttributes().getBackground());
    }

    private void createHeaderPanel() {
        headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(currentTitleIsSelected() ? titleStyle.getSelectedBackground()
                                          : titleStyle.getBackground());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(titleStyle.getGapTop(), titleStyle.getGapLeft(), titleStyle.getGapBottom(),
                                titleStyle.getGapRight());

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel iconLabel;
        titleLabel = new JLabel(navItem.getTitle());
        titleLabel.setFont(titleStyle.getFont());
        titleLabel.setForeground(currentTitleIsSelected() ? titleStyle.getSelectedForeground()
                                         : titleStyle.getForeground());

        if (navItem.getTitleIcon() != null) {
            iconLabel = new JLabel(navItem.getTitleIcon());

            gbc.insets.right = titleStyle.getIconTextGap();

            if (titleStyle.getTextPosition() == TextPosition.LEFT) {
                headerPanel.add(titleLabel, gbc);
                gbc.gridx++;
                gbc.insets.right = titleStyle.getGapRight();
                gbc.weightx = 1;
                headerPanel.add(iconLabel, gbc);
            } else {
                headerPanel.add(iconLabel, gbc);
                gbc.gridx++;
                gbc.insets.right = titleStyle.getGapRight();
                gbc.weightx = 1;
                headerPanel.add(titleLabel, gbc);
            }
        } else {
            gbc.weightx = 1;
            headerPanel.add(titleLabel, gbc);
        }

        headerWidth = headerPanel.getMinimumSize().width;
        headerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (subPanel != null) {
                    toggleSubPanel(subPanel.isVisible());
                } else {
                    if (listener != null) listener.onTitleClick(titleIndex);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                headerPanel.setBackground(titleStyle.getHoverBackground());
                titleLabel.setForeground(titleStyle.getHoverForeground());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                headerPanel.setBackground(currentTitleIsSelected() ? titleStyle.getSelectedBackground()
                                                  : titleStyle.getBackground());
                titleLabel.setForeground(currentTitleIsSelected() ? titleStyle.getSelectedForeground()
                                                 : titleStyle.getForeground());
            }
        });
        headerPanel.setMinimumSize(new Dimension(getMaxWidth(), headerPanel.getMinimumSize().height));
    }

    private void createSubPanel() {
        subPanel = new JPanel(new GridBagLayout());
        subPanel.setBackground(subStyle.getBackground());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        String[] subtitles = navItem.getSubtitles();
        Icon[] icons = navItem.getIcons();

        for (int i = 0; i < subtitles.length; i++) {
            JPanel itemPanel = new JPanel(new GridBagLayout());
            itemPanel.setBackground(currentSubIsSelected(i) ? subStyle.getSelectedBackground() : subStyle.getBackground());

            GridBagConstraints gbcItem = new GridBagConstraints();
            gbcItem.insets = new Insets(subStyle.getGapTop(), subStyle.getGapLeft(), subStyle.getGapBottom(),
                                        subStyle.getGapRight());
            gbcItem.gridx = 0;
            gbcItem.gridy = 0;
            gbcItem.fill = GridBagConstraints.HORIZONTAL;
            gbcItem.anchor = GridBagConstraints.WEST;

            String subName = navName + "." + titleIndex + ".sub." + i;

            JLabel subIconLabel;
            JLabel subLabel = new JLabel(subtitles[i]);
            subLabel.setFont(subStyle.getFont());
            subLabel.setForeground(currentSubIsSelected(i) ? subStyle.getSelectedForeground() : subStyle.getForeground());

            subLabel.setName(subName);

            if (icons != null && icons[i] != null) {
                gbcItem.insets.right = subStyle.getIconTextGap();
                subIconLabel = new JLabel(icons[i]);

                if (subStyle.getTextPosition() == TextPosition.LEFT) {
                    gbcItem.weightx = 0;
                    itemPanel.add(subLabel, gbcItem);
                    gbcItem.gridx++;
                    gbcItem.insets.left = subStyle.getGapBottom();
                    gbcItem.weightx = 1;
                    itemPanel.add(subIconLabel, gbcItem);
                } else {
                    gbcItem.weightx = 0;
                    itemPanel.add(subIconLabel, gbcItem);
                    gbcItem.gridx++;
                    gbcItem.insets.left = subStyle.getGapBottom();
                    gbcItem.weightx = 1;
                    itemPanel.add(subLabel, gbcItem);
                }
            } else {
                gbcItem.weightx = 1;
                itemPanel.add(subLabel, gbcItem);
            }

            int finalI = i;
            itemPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (listener != null) listener.onSubtitleClick(titleIndex, finalI);


                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    itemPanel.setBackground(subStyle.getHoverBackground());
                    subLabel.setForeground(subStyle.getHoverForeground());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    itemPanel.setBackground(currentSubIsSelected(finalI) ? subStyle.getSelectedBackground()
                                                    : subStyle.getBackground());
                    subLabel.setForeground(currentSubIsSelected(finalI) ? subStyle.getSelectedForeground()
                                                   : subStyle.getForeground());
                }
            });
            gbc.gridy = i;
            subPanel.add(itemPanel, gbc);
        }

        subPanel.setBackground(subStyle.getBackground());
        subWidth = subPanel.getMinimumSize().width;

        subPanel.setMinimumSize(new Dimension(getMaxWidth(), subPanel.getMinimumSize().height));
        subPanel.setVisible(NavStateManager.isSubCollapsed(navName, titleIndex));
    }

    private void toggleSubPanel(boolean collapsed) {
        subPanel.setVisible(!collapsed);

        NavStateManager.setSubCollapsed(navName, titleIndex, !collapsed);
    }

    private boolean currentTitleIsSelected() {
        int selectedTitle = NavStateManager.getSelectedTitleIndex(navName);
        return selectedTitle == titleIndex;
    }

    private boolean currentSubIsSelected(int subIndex) {
        int selectedSub = NavStateManager.getSelectedSubIndex(navName);
        return currentTitleIsSelected() && selectedSub == subIndex;
    }
}