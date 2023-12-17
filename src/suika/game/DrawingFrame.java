package suika.game;	

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Vector2;

@SuppressWarnings("serial")
public class DrawingFrame extends JFrame implements MouseListener, MouseMotionListener, ActionListener {
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

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.setFocusable(true);
        
        Timer timer = new Timer((1000 / Constants.FPS), this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    private void update() {
        int mouseX = MouseInfo.getPointerInfo().getLocation().x - this.getLocationOnScreen().x;
        nextParticle.setX(mouseX);
        System.out.println("Mouse X: " + mouseX);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            particles.add(nextParticle.release(world, mapper));
            nextParticle = new PreParticle(Constants.windowWidth / 2, new Random().nextInt(5));
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        nextParticle.setX(e.getX());
    }

    // Implement other methods of MouseListener and MouseMotionListener if needed

    @Override
    public void mousePressed(MouseEvent e) {
        // Handle mouse press events
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Handle mouse release events
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Handle mouse enter events
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Handle mouse exit events
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Handle mouse drag events
    }
}
