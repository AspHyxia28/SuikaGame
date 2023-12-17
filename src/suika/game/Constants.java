package suika.game;

import java.awt.Color;

public class Constants {
    public static final int windowWidth = 570;
    public static final int windowHeight = 770;

    public static final int[] SIZE = {windowWidth, windowHeight};
    public static final int[] PAD = {24, 160};
    public static final int[] A = {PAD[0], PAD[1]};
    public static final int[] B = {PAD[0], windowHeight - PAD[0]};
    public static final int[] C = {windowWidth - PAD[0], windowHeight - PAD[0]};
    public static final int[] D = {windowWidth - PAD[0], PAD[1]};
    public static final Color BG_COLOR = new Color(250, 240, 148);
    public static final Color W_COLOR = new Color(250, 190, 58);
    public static final Color[] COLORS = {
        new Color(245, 0, 0),
        new Color(250, 100, 100),
        new Color(150, 20, 250),
        new Color(250, 210, 10),	
        new Color(250, 150, 0),
        new Color(245, 0, 0),
        new Color(250, 250, 100),
        new Color(255, 180, 180),
        new Color(255, 255, 0),
        new Color(100, 235, 10),
        new Color(0, 185, 0),
    };

    public static final int FPS = 240;
    public static final int[] RADII = {17, 25, 32, 38, 50, 63, 75, 87, 100, 115, 135};
    public static final int W_THICKNESS = 14;
    public static final double DENSITY = 0.001;
    public static final double ELASTICITY = 0.1;
    public static final int IMPULSE = 10000;
    public static final int GRAVITY = 9000;
    public static final double DAMPING = 0.8;
    public static final int NEXT_DELAY = FPS;
    public static final double BIAS = 0.00001;
    public static final int[] SCORE = {1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66};
}