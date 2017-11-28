import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BasicTrig {
 
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
        frame.getContentPane().add(new BasicTrigPanel());
        frame.setSize(frameW, frameH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class BasicTrigPanel extends JPanel implements MouseMotionListener {
    private Point mousePosition = null;

    BasicTrigPanel() {
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D)gr;
	g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

	// Set colors
	Color bgColor = Color.decode("#282A36");
	Color fgColor = Color.decode("#D8D8D8");
	Color mdColor = Color.decode("#555555");
	Color reColor = Color.decode("#F02828");
	Color orColor = Color.decode("#FFA000");
	Color yeColor = Color.decode("#F4D03F");
	Color grColor = Color.decode("#27AE60");
	Color blColor = Color.decode("#3498DB");
	Color puColor = Color.decode("#9B59B6");
	
        // Set font
        int frameW = 768;
        int frameH = 768;
        int fontSize = frameH/16; 
        Font font = new Font("InputMonoCompressed", Font.BOLD, fontSize);
        g.setFont(font);

	// Basic variables
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;
        double radius = 256;

	// Set BG
        g.setColor(bgColor);;
	g.fillRect(0, 0, 2048, 2048);

	// Grid
	g.setColor(mdColor);
	g.setStroke(new BasicStroke(2));
        for (int i=0;i<=512;i+=64) {
	    g.draw(new Line2D.Double((centerX-radius)+i, 0,
				     (centerX-radius)+i, 2048));
	}
	for (int j=0;j<=512;j+=64) {
	    g.draw(new Line2D.Double(0, (centerY-radius)+j,
				     2048, (centerY-radius)+j));
	}
	
	// Draw circle
	g.setStroke(new BasicStroke(6));
        g.setColor(fgColor);
        g.draw(new Ellipse2D.Double(
            centerX-radius, centerY-radius, 
            radius+radius, radius+radius));
        if (mousePosition == null) {return;}

	// Get mouse input variables
        double x = (mousePosition.x - centerX) / radius;
        double y = computeY(x);
        double cx = centerX + radius * x;
        double cy = centerY - radius * y;
	
        // Draw triangle 
        g.setColor(reColor);
        g.draw(new Line2D.Double(mousePosition.x, centerY, mousePosition.x, cy));
        g.draw(new Line2D.Double(centerX, centerY, cx, cy)); 
        g.draw(new Line2D.Double(centerX, centerY, mousePosition.x, centerY));

	// Blue points // BLUE COLOR
        g.setColor(blColor);
        g.fill(new Ellipse2D.Double(cx-16, cy-16, 32, 32));
	g.drawString("A", (int)cx+16, (int)cy-16);
	g.setColor(yeColor);
	g.fill(new Ellipse2D.Double(mousePosition.x-16, centerY-16, 32, 32));
	g.drawString("B", mousePosition.x+16, (int)centerY-16);
	g.setColor(grColor);
	g.fill(new Ellipse2D.Double(centerX-16, centerY-16, 32, 32));
	g.drawString("C", (int)centerX+16, (int)centerY-16);

	// Screen Text // BLACK COLOR
        g.setColor(fgColor);
        // g.drawString("x pos: "+x, 16, 32);
        // g.drawString("y pos: "+y, 16, 64);
        g.drawString("Angle θ: "+Math.toDegrees(Math.acos(x)), mousePosition.x, 64);

	
	
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
