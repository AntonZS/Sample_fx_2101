package sample;

import java.awt.*;

public class Lab_SB {
    /**
     * reference white in XYZ coordinates
     */
    public double[] D50 = {96.4212, 100.0, 82.5188};
    public double[] D55 = {95.6797, 100.0, 92.1481};
    public double[] D65 = {95.0429, 100.0, 108.8900};
    public double[] D75 = {94.9722, 100.0, 122.6394};
    public double[] whitePoint = D65;

    /**
     * reference white in xyY coordinates
     */
    public double[] chromaD50 = {0.3457, 0.3585, 100.0};
    public double[] chromaD55 = {0.3324, 0.3474, 100.0};
    public double[] chromaD65 = {0.3127, 0.3290, 100.0};
    public double[] chromaD75 = {0.2990, 0.3149, 100.0};
    public double[] chromaWhitePoint = chromaD65;

    /**
     * XYZ to sRGB conversion matrix
     */
    public double[][] Mi = {{3.2406, -1.5372, -0.4986},
            {-0.9689, 1.8758, 0.0415},
            {0.0557, -0.2040, 1.0570}};

    /**
     * default constructor, uses D65 for the white point
     */
    /*
    public Lab_SB() {
        whitePoint = D65;
        chromaWhitePoint = chromaD65;
    }
*/

    /**
     * constructor for setting a non-default white point
     *
     * @param white String specifying the white point to use
     */
    public Lab_SB(String white) {
        whitePoint = D65;
        chromaWhitePoint = chromaD65;
        if (white.equalsIgnoreCase("d50")) {
            whitePoint = D50;
            chromaWhitePoint = chromaD50;
        } else if (white.equalsIgnoreCase("d55")) {
            whitePoint = D55;
            chromaWhitePoint = chromaD55;
        } else if (white.equalsIgnoreCase("d65")) {
            whitePoint = D65;
            chromaWhitePoint = chromaD65;
        } else if (white.equalsIgnoreCase("d75")) {
            whitePoint = D75;
            chromaWhitePoint = chromaD75;
        }
    }


    public int[] LABtoRGB(double[] Lab) {
        return XYZtoRGB(LABtoXYZ(Lab));
    }

    public int[] LABtoHSB(double[] Lab) {
        int[] result;
        result = RGBtoHSB(LABtoRGB(Lab));

        return result;
    }

    public double[] LABtoXYZ(double L, double a, double b) {
        double[] result = new double[3];
        double y = (L + 16.0) / 116.0;
        double y3 = Math.pow(y, 3.0);
        double x = (a / 500.0) + y;
        double x3 = Math.pow(x, 3.0);
        double z = y - (b / 200.0);
        double z3 = Math.pow(z, 3.0);

        if (y3 > 0.008856) {
            y = y3;
        } else {
            y = (y - (16.0 / 116.0)) / 7.787;
        }
        if (x3 > 0.008856) {
            x = x3;
        } else {
            x = (x - (16.0 / 116.0)) / 7.787;
        }
        if (z3 > 0.008856) {
            z = z3;
        } else {
            z = (z - (16.0 / 116.0)) / 7.787;
        }

        result[0] = x * whitePoint[0];
        result[1] = y * whitePoint[1];
        result[2] = z * whitePoint[2];
        return result;
    }


    public double[] LABtoXYZ(double[] Lab) {
        return LABtoXYZ(Lab[0], Lab[1], Lab[2]);
    }


    public int[] XYZtoRGB(double[] XYZ) {
        return XYZtoRGB(XYZ[0], XYZ[1], XYZ[2]);
    }


    public int[] XYZtoRGB(double X, double Y, double Z) {
        int[] result = new int[3];

        double x = X / 100.0;
        double y = Y / 100.0;
        double z = Z / 100.0;

        // [r g b] = [X Y Z][Mi]
        double r = (x * Mi[0][0]) + (y * Mi[0][1]) + (z * Mi[0][2]);
        double g = (x * Mi[1][0]) + (y * Mi[1][1]) + (z * Mi[1][2]);
        double b = (x * Mi[2][0]) + (y * Mi[2][1]) + (z * Mi[2][2]);

        // assume sRGB
        if (r > 0.0031308) {
            r = ((1.055 * Math.pow(r, 1.0 / 2.4)) - 0.055);
        } else {
            r = (r * 12.92);
        }
        if (g > 0.0031308) {
            g = ((1.055 * Math.pow(g, 1.0 / 2.4)) - 0.055);
        } else {
            g = (g * 12.92);
        }
        if (b > 0.0031308) {
            b = ((1.055 * Math.pow(b, 1.0 / 2.4)) - 0.055);
        } else {
            b = (b * 12.92);
        }

        r = (r < 0) ? 0 : r;
        g = (g < 0) ? 0 : g;
        b = (b < 0) ? 0 : b;

        // convert 0..1 into 0..255
        result[0] = (int) Math.round(r * 255);
        result[1] = (int) Math.round(g * 255);
        result[2] = (int) Math.round(b * 255);
        return result;
    }

    /**
     * @param R Red in range 0..255
     * @param G Green in range 0..255
     * @param B Blue in range 0..255
     * @return HSB values: H is 0..360 degrees / 360 (0..1), S is 0..100, B is 0..100
     */
    public int[] RGBtoHSB(int R, int G, int B) {
        int[] result = new int[3];
        float[] hsb = new float[3];
        Color.RGBtoHSB(R, G, B, hsb);
        result[0] = Math.round(hsb[0] * 360);
        result[1] = Math.round(hsb[1] * 100);
        result[2] = Math.round(hsb[2] * 100);

        return result;
    }

    public int[] RGBtoHSB(int[] RGB) {
        return RGBtoHSB(RGB[0], RGB[1], RGB[2]);
    }

}
