package suika.game;

import java.util.Map;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Circle;


public class CollisionHandler {
    public static Particle handleCollision(Particle p1, Particle p2, World world, Map<Circle, Particle> mapper) {
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
