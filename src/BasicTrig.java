import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CircleIntersectionTest {
 
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // TODO make final
        int frameW = 768;
        int frameH = 768;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CircleIntersectionPanel());
        frame.setSize(frameW, frameH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class CircleIntersectionPanel extends JPanel implements MouseMotionListener {
    private Point mousePosition = null;

    CircleIntersectionPanel() {
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D)gr;
        
        // Set font
        int frameW = 768;
        int frameH = 768;
        int fontSize = frameH/32; 
        Font font = new Font("freeMono", Font.BOLD, fontSize);
        g.setFont(font);
       
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;
        double radius = 128;

        

        g.setStroke(new BasicStroke(6));
        g.setColor(Color.BLACK);;
        g.draw(new Ellipse2D.Double(
            centerX-radius, centerY-radius, 
            radius+radius, radius+radius));
        if (mousePosition == null)
        {
            return;
        }


        g.setColor(Color.BLUE);

        double x = (mousePosition.x - centerX) / radius;
        double y = computeY(x);

        double cx = centerX + radius * x;
        double cy = centerY - radius * y;
        g.fill(new Ellipse2D.Double(cx-8, cy-8, 16, 16));

        //new 
        g.setColor(Color.RED);
        g.draw(new Line2D.Double(
            mousePosition.x, centerY, mousePosition.x, cy));
        g.draw(new Line2D.Double(
            centerX, centerY, cx, cy)); 
        g.draw(new Line2D.Double(
            // centerX-radius, centerY, 
            // centerX+radius, centerY));
            centerX, centerY,
            mousePosition.x, centerY));

        
        g.setColor(Color.BLUE);
        g.fill(new Ellipse2D.Double(cx-8, cy-8, 24, 24));
        g.fill(new Ellipse2D.Double(centerX-8, centerY-8, 24, 24));
        g.fill(new Ellipse2D.Double(mousePosition.x-8, centerY-8, 16, 16));



        // 32,64,96,128,160,192,224,256,288,320,352,384,416,448,
        g.setColor(Color.BLACK);
        g.drawString("x pos: "+x, 16, 32);
        g.drawString("y pos: "+y, 16, 64);
        g.drawString("cx - : "+cx, 16, 96);
        g.drawString("cy - : "+cy, 16, 128);
        g.drawString("angle: "+Math.toDegrees(Math.acos(x)), 16, 160);

    }

    private static double computeY(double x)
    {
        return Math.sin(Math.acos(x));
    }


    @Override
    public void mouseMoved(MouseEvent e)
    {
        mousePosition = e.getPoint();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
    }

}
