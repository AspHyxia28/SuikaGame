package suika.game;


import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.util.Map;

public class PreParticle {
    private int n;
    private double radius;
    private double x;

    public PreParticle(double x, int n) {
        this.n = n % 11;
        this.radius = Constants.RADII[this.n];
        this.x = x;
    }

    public void draw(Graphics2D g2d) {
        Color c1 = Constants.COLORS[n];
        Color c2 = new Color((int) (c1.getRed() * 0.8), (int) (c1.getGreen() * 0.8), (int) (c1.getBlue() * 0.8));

        int centerX = (int) x;
        int centerY = Constants.PAD[1] / 2;

        g2d.setColor(c2);
        g2d.fillOval(centerX - (int) radius, centerY - (int) radius, (int) (2 * radius), (int) (2 * radius));

        g2d.setColor(c1);
        g2d.fillOval(centerX - (int) (0.9 * radius), centerY - (int) (0.9 * radius), (int) (1.8 * radius), (int) (1.8 * radius));
    }

    public void setX(double x) {
        double lim = Constants.PAD[0] + radius + Constants.W_THICKNESS / 2;
        this.x = Math.max(Math.min(x, Constants.windowWidth - lim), lim);
    }

    public Particle release(World world, Map<Circle, Particle> mapper) {
        return new Particle(new Vector2(x, Constants.PAD[1] / 2), n, world, mapper);
    }
}