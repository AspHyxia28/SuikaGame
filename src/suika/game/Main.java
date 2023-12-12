package suika.game;

import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class Main {
    private static final int WIDTH = Constants.windowWidth;
    private static final int HEIGHT = Constants.windowHeight;
    private static final int GRAVITY = Constants.GRAVITY;
    private static final double BIAS = Constants.BIAS;

    public static void main(String[] args) {
        DrawingFrame frame = new DrawingFrame();
        frame.setDefaultCloseOperation(DrawingFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    
    @SuppressWarnings("serial")
    private static class DrawingFrame extends JFrame {
        private World world;
        private List<Particle> particles;
        private PreParticle nextParticle;
        private boolean gameOver;

        public DrawingFrame() {
            setTitle("Suika");
            world = new World();
            world.setGravity(new Vector2(0, GRAVITY));
            world.getSettings().setBaumgarte(BIAS);            
           
            particles = new ArrayList<>();

            nextParticle = new PreParticle(WIDTH / 2, new Random().nextInt(5));

            gameOver = false;
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
    }
}
