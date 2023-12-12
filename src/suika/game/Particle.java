package suika.game;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.util.Map;

public class Particle {
    private int n;
    private double radius;
    private Body body;
    private Circle shape;
    private boolean alive;
    private boolean has_collided;

    public Particle(Vector2 pos, int n, World world, Map<Circle, Particle> mapper) {
        this.n = n % 11;
        this.radius = Constants.RADII[this.n];
        this.body = new Body();
        BodyFixture fixture = new BodyFixture(new Circle(radius));
        fixture.setDensity(Constants.DENSITY);
        fixture.setRestitution(Constants.ELASTICITY);
        fixture.setFriction(0.2);        
        this.body.addFixture(fixture);
        this.body.setMass(MassType.NORMAL);
        this.body.translate(pos);
        this.body.setLinearDamping(Constants.DAMPING);
        this.body.setAngularDamping(Constants.DAMPING);
        
        this.shape = (Circle) fixture.getShape();
        this.alive = true;
        this.has_collided = false;

        mapper.put(this.shape, this);
        world.addBody(this.body);
    }


    public void draw(Graphics2D g2d) {
        if (alive) {
            Color c1 = Constants.COLORS[n];
            Color c2 = new Color((int) (c1.getRed() * 0.8), (int) (c1.getGreen() * 0.8), (int) (c1.getBlue() * 0.8));
            
            int x = (int) body.getTransform().getTranslationX();
            int y = (int) body.getTransform().getTranslationY();

            g2d.setColor(c2);
            g2d.fillOval(x - (int) radius, y - (int) radius, (int) (2 * radius), (int) (2 * radius));

            g2d.setColor(c1);
            g2d.fillOval(x - (int) (0.9 * radius), y - (int) (0.9 * radius), (int) (1.8 * radius), (int) (1.8 * radius));
        }
    }

    public void kill(World world) {
        world.removeBody(body);
        alive = false;
    }

    public Vector2 getPos() {
        return body.getTransform().getTranslation();
    }

    public boolean isAlive() {
        return alive;
    }


	public double getRadius() {
		return this.radius;
	}


	public Body getBody() {
		return this.body;
	}
	
	public int getN() {
		return n;
	}


	public boolean setHasCollided(boolean condition) {
		return this.has_collided;
	}

}

