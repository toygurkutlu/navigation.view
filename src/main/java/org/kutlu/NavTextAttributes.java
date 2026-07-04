package org.kutlu;

import java.awt.*;

/**
 * Common text attributes shared by both titles and subtitles.
 */
public abstract class NavTextAttributes {
    public abstract Color getForeground();
    public abstract Color getBackground();
    public abstract Color getSelectedBackground();
    public abstract Color getSelectedForeground();
    public abstract Color getHoverBackground();
    public abstract Color getHoverForeground();
    public abstract TextPosition getTextPosition();
    public abstract  int getIconTextGap();
    public abstract  int getGapTop();
    public abstract  int getGapLeft();
    public abstract  int getGapBottom();
    public abstract  int getGapRight();
    public abstract Font getFont();
    public abstract String getFontFamily();
    public abstract int getFontStyle();
    public abstract int getFontSize();
}
