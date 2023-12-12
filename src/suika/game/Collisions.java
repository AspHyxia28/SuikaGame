package suika.game;

import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Vector2;

import java.util.List;
import java.util.Map;

public class Collisions {
    private static final double IMPULSE = Constants.IMPULSE;

    public static Particle resolveCollision(Particle p1, Particle p2, World world, List<Particle> particles, Map<Circle, Particle> mapper) {
        if (p1.getN() == p2.getN()) {
            double distance = p1.getPos().distance(p2.getPos());
            if (distance < 2 * p1.getRadius()) {
                p1.kill(world);
                p2.kill(world);
                Particle pn = new Particle(p1.getPos().add(p2.getPos()).multiply(0.5), p1.getN() + 1, world, mapper);
                for (Particle p : particles) {
                    if (p.isAlive()) {
                        Vector2 vector = p.getPos().subtract(pn.getPos());
                        double dist = vector.getMagnitude();
                        if (dist < pn.getRadius() + p.getRadius()) {
                            Vector2 impulse = vector.multiply(IMPULSE / (dist * dist));
                            p.getBody().applyImpulse(impulse);
                        }
                    }
                }
                return pn;
            }
        }
        return null;
    }
}
