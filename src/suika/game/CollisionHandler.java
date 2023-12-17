package suika.game;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.contact.ContactConstraint;
import org.dyn4j.dynamics.contact.ContactPoint;
import org.dyn4j.geometry.Vector2;

public class CollisionHandler {
    public void handleCollision(Particle p1, Particle p2, World world, Map<Circle, Particle> mapper) {
        if (p1.getN() == p2.getN()) {
            double distance = p1.getPos().distance(p2.getPos());
            if (distance < 2 * p1.getRadius()) {
                p1.kill(world);
                p2.kill(world);
                Particle pn = new Particle(p1.getPos().add(p2.getPos()).multiply(0.5), p1.getN() + 1, world, mapper);
                return pn;
            }
        }
        return null;
    }
}
