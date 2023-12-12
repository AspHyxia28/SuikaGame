package suika.game;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;

import java.awt.*;

public class Wall {
    private static final int THICKNESS = Constants.W_THICKNESS;
    private Body body;
    private org.dyn4j.geometry.Segment shape;

    public Wall(Vector2 a, Vector2 b, World world) {
        this.body = new Body();
        this.shape = new org.dyn4j.geometry.Segment(a, b);
        this.body.addFixture(shape);
        this.body.setMass(org.dyn4j.geometry.MassType.INFINITE); // Wall is static, set mass to infinite
        world.addBody(body);
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Constants.W_COLOR);
        g2d.setStroke(new BasicStroke(THICKNESS));
        g2d.drawLine((int) shape.getPoint1().x, (int) shape.getPoint1().y, (int) shape.getPoint2().x, (int) shape.getPoint2().y);
    }
}
