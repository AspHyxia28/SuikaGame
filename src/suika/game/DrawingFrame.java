package suika.game;

import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DrawingFrame extends JFrame implements KeyListener {
    private World world;
    private List<Particle> particles;
    private PreParticle nextParticle;
    private boolean gameOver;
    private Map<Circle, Particle> mapper;

    public DrawingFrame() {
        setTitle("Suika");
        world = new World();
        world.setGravity(new Vector2(0, Constants.GRAVITY));
        world.getSettings().setBaumgarte(Constants.BIAS);            
       
        particles = new ArrayList<>();
        mapper = new HashMap<>();

        nextParticle = new PreParticle(Constants.windowWidth / 2, new Random().nextInt(5));

        gameOver = false;

        // Add this as a KeyListener
        this.addKeyListener(this);
        this.setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        
        Wall left = new Wall(new Vector2(Constants.A[0], Constants.A[1]), new Vector2(Constants.B[0], Constants.B[1]), world);
        Wall bottom = new Wall(new Vector2(Constants.B[0], Constants.B[1]), new Vector2(Constants.C[0], Constants.C[1]), world);
        Wall right = new Wall(new Vector2(Constants.C[0], Constants.C[1]), new Vector2(Constants.D[0], Constants.D[1]), world);

        if (!gameOver) {
            left.draw(g2d);
            bottom.draw(g2d);
            right.draw(g2d);
            
            for (Particle particle : particles) {
                particle.draw(g2d);
            }

            nextParticle.draw(g2d);
        }
    }

    // KeyListener methods
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            // Move the nextParticle to the left
            nextParticle.setX(nextParticle.getX() - 10);
        } else if (key == KeyEvent.VK_D) {
            // Move the nextParticle to the right
            nextParticle.setX(nextParticle.getX() + 10);
        } else if (key == KeyEvent.VK_ENTER) {
            // Release the nextParticle
            particles.add(nextParticle.release(world, mapper));
            nextParticle = new PreParticle(Constants.windowWidth / 2, new Random().nextInt(5));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key release events if necessary
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key typed events if necessary
    }
}
