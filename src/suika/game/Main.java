package suika.game;

import javax.swing.*;
import java.util.HashMap;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
        frame.gameLoop();
    }

    public static class DrawingPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Your drawing code here...
        }
    }
    
    @SuppressWarnings("serial")
    private static class DrawingFrame extends JFrame {
        private World world;
        private List<Particle> particles;
        private PreParticle nextParticle;
        private boolean gameOver;
        public Map<Circle, Particle> mapper;

        public DrawingFrame() {
            setTitle("Suika");
            world = new World();
            world.setGravity(new Vector2(0, GRAVITY));
            world.getSettings().setBaumgarte(BIAS);

            particles = new ArrayList<>();
            mapper = new HashMap<>();
            nextParticle = new PreParticle(WIDTH / 2, new Random().nextInt(5));

            gameOver = false;
            
          Main.DrawingPanel panel = new Main.DrawingPanel();
          this.add(panel);
            // Add a MouseListener to the frame
            addMouseListener(new CustomMouseListener());
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
        
        public void gameLoop() {
        	while(true) {
        		world.update(1.0/60.0);
        		repaint();
        		try {
        			Thread.sleep(1000/60);
        		}
        		catch(InterruptedException e) {
        			e.printStackTrace();
        		}
        	}
        }

        // Custom MouseListener to handle left-click events
        private class CustomMouseListener implements MouseListener {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int mouseX = e.getX();
                    int mouseY = 0;

                    // Generate a random integer for n
                    int n = new Random().nextInt(11);  // Adjust the argument to Random().nextInt() as needed

                    Particle newParticle = new Particle(new Vector2(mouseX, mouseY), n, world, mapper);

                    particles.add(newParticle);
                    repaint();  // Refresh the display
                }
            }

            // Other MouseListener methods...
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        }
    }
}
