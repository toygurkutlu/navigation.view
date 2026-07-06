package com.kutlu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Helper class that contains type converters.
 */
public class NavHelper {

    /**
     * Changes the color of the specified icon.
     *
     * @param icon  The icon whose color will be changed.
     * @param color The new color to apply to the icon.
     * @return The new {@code Icon} instance with the updated color.
     */
    public static Icon recolorIcon(Icon icon, Color color) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();

        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        icon.paintIcon(null, g2, 0, 0);
        g2.dispose();

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int rgba = img.getRGB(x, y);
                int alpha = (rgba >> 24) & 0xff;

                if (alpha > 0) {
                    img.setRGB(x, y,
                               (alpha << 24) |
                                       (color.getRed() << 16) |
                                       (color.getGreen() << 8) |
                                       color.getBlue()
                    );
                }
            }
        }

        return new ImageIcon(img);
    }

    /**
     * Converts a {@code Color} object to its hexadecimal string representation.
     *
     * @param color The color to convert.
     * @return The hex code string of the color (e.g., "#FFFFFF").
     */
    public static String colorToHex(Color color) {
        return "#" + Integer.toHexString(color.getRGB()).substring(2);
    }

    /**
     * Converts a hexadecimal string representation back to a {@code Color} object.
     *
     * @param colorHex The hex code string of the color (e.g., "#FFFFFF").
     * @return The corresponding {@code Color} object.
     */
    public static Color hexToColor(String colorHex) {
        return Color.decode(colorHex);
    }
}