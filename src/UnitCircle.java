import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.text.NumberFormat;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class UnitCircle {

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
        int frameW = 1024;
        int frameH = 768;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new UnitCirclePanel());
        frame.setSize(frameW, frameH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class UnitCirclePanel extends JPanel implements MouseMotionListener {
    private Point mousePosition = null;

    UnitCirclePanel() {
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
        int fontSizeSM = 20;
        int fontSizeMD = 26;
        int fontSizeLG = 36;
        Font font = new Font("InputMonoCompressed", Font.BOLD, fontSizeSM);
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

	// Y
	for (int i=0;i<=512;i+=64) {
            g.draw(new Line2D.Double((centerX-radius)+i, (centerY-radius),
				     (centerX-radius)+i, (centerY+radius)));
        }

	// X
	for (int j=0;j<=512;j+=64) {
            g.draw(new Line2D.Double((centerX-radius), (centerY-radius)+j,
                                     (centerX+radius), (centerY-radius)+j));
        }

	// Mid	// X
        g.setColor(fgColor);

	g.draw(new Line2D.Double((centerX-(radius+64)),  (centerY),
				 (centerX+(radius+64)), (centerY)));

	// (-1, 0)
	g.draw(new Line2D.Double((centerX-(radius+64)),  (centerY),
				 (centerX-(radius+56)), (centerY+8)));
	g.draw(new Line2D.Double((centerX-(radius+64)),  (centerY),
				 (centerX-(radius+56)), (centerY-8)));

	// (1, 0)
	g.draw(new Line2D.Double((centerX+(radius+64)),  (centerY),
				 (centerX+(radius+56)), (centerY+8)));
	g.draw(new Line2D.Double((centerX+(radius+64)),  (centerY),
				 (centerX+(radius+56)), (centerY-8)));

	// y
	g.draw(new Line2D.Double((centerX), (centerY-(radius+64)),
				 (centerX), (centerY+(radius+64))));

	// (0, -1)
	g.draw(new Line2D.Double((centerX), (centerY-(radius+64)),
				 (centerX+8), (centerY-(radius+56))));
	g.draw(new Line2D.Double((centerX), (centerY-(radius+64)),
				 (centerX-8), (centerY-(radius+56))));

	// (0, 1)
	g.draw(new Line2D.Double((centerX), (centerY+(radius+64)),
				 (centerX+8), (centerY+(radius+56))));
	g.draw(new Line2D.Double((centerX), (centerY+(radius+64)),
				 (centerX-8), (centerY+(radius+56))));

	// main points
	g.fill(new Ellipse2D.Double((centerX+radius)-10, centerY-10, 20, 20));
	g.drawString("(1,0)", (int)(centerX+radius)+10, (int)centerY-20);

	g.fill(new Ellipse2D.Double((centerX-radius)-10, centerY-10, 20, 20));
	g.drawString("(-1,0)", (int)(centerX-radius)-72, (int)centerY-20);

	g.fill(new Ellipse2D.Double(centerX-10, (centerY+radius)-10, 20, 20));
	g.drawString("(0,-1)", (int)centerX+10, (int)(centerY+radius)+30);

	g.fill(new Ellipse2D.Double(centerX-10, (centerY-radius)-10, 20, 20));
	g.drawString("(0,1)", (int)centerX+10, (int)(centerY-radius)-30);
	
        // Draw circle
	g.setColor(puColor);
        g.draw(new Ellipse2D.Double(centerX-(radius/2), centerY-(radius/2),
				    radius, radius));
	
        g.setStroke(new BasicStroke(4));

	g.setColor(fgColor);
        g.draw(new Ellipse2D.Double(
            centerX-radius, centerY-radius,
            radius+radius, radius+radius));
        g.setColor(fgColor);

        if (mousePosition == null) {return;}

	// Get mouse input variables
        double x = (mousePosition.x - centerX) / radius;
	System.out.println("\n(mPos.x("+mousePosition.x+")-centerX("+centerX+"))= "+(mousePosition.x - centerX));
	System.out.println("x=(mPos.x("+mousePosition.x+")-centerX("+centerX+"))/radius("+radius+")");
        double y = computeY(x);
        double cx = centerX + radius * x;
        double cy = centerY - radius * y;
	
        // Draw triangle 
        g.setColor(reColor);
        //g.draw(new Line2D.Double(mousePosition.x, centerY, mousePosition.x, cy));

 


	// Draw Sum and Difference 
        //g.setColor(puColor);
        //g.draw(new Line2D.Double(mousePosition.x, centerY, mousePosition.x, cy));
        //g.draw(new Line2D.Double(centerX, centerY, cx, cy)); 
	// g.draw(new Line2D.Double(centerX, centerY, mousePosition.x, centerY));

	// MAin tri points...
	Point pA = new Point((int)cx, (int)cy);
	Point pB = new Point((int)mousePosition.x, (int)centerY);
	Point pC = new Point((int)centerX, (int)centerY);

        // NumberFormat fmtr = new DecimalFormat("#0.00");     
        // System.out.println();
	
	// A point
	g.setColor(reColor);
	g.draw(new Line2D.Double(pA.x, pA.y, pB.x, pB.y));

	// B point--Yellow 
	g.setColor(reColor);
	g.draw(new Line2D.Double(pB.x, pB.y, pC.x, pC.y));
 
	g.setColor(yeColor);
	g.fill(new Ellipse2D.Double(pB.x-10, pB.y-10, 20, 20));
	g.drawString("B [x=cos(θ)="+String.format( "%.2f",x)+
                       ", 0]", (int)pB.x+10, (int)pB.y+30);

	// C point--Green
	g.setColor(reColor);
	g.draw(new Line2D.Double(pC.x, pC.y, pA.x, pA.y));

	g.setColor(grColor);
	g.fill(new Ellipse2D.Double(pC.x-10, pC.y-10, 20, 20));
	g.drawString("C [0, 0]", (int)pC.x+10, (int)pC.y+30);

	// point A
	g.setColor(blColor);
        g.fill(new Ellipse2D.Double(pA.x-10, pA.y-10, 20, 20));
	g.drawString("A [x=cos(θ)="+String.format( "%.2f",x)+
		      ", y=sin(θ) "+String.format( "%.2f",y)+"]", (int)pA.x+10, (int)pA.y-30);



       
	
	// Adj, C and B midpoint
	g.setColor(orColor);
	g.fill(new Ellipse2D.Double((((pB.x-5)+(pC.x-5))/2),
				    (((pB.y-5)+(pC.y-5))/2), 10, 10));
	
	g.drawString("Adj", (int)(((pB.x-30)+(pC.x-0))/2),
		                 (int)(((pB.y-10)+(pC.y-10))/2));

	
	// Hyp, C and A midpoint
	g.fill(new Ellipse2D.Double((((cx-5)+(centerX-5))/2),
				    (((cy-5)+(centerY-5))/2), 10, 10));
	
	g.drawString("Hyp", (int)(((cx-30)+(centerX))/2),
		                 (int)(((cy-30)+(centerY))/2));

	// Opp, B and A midpoint
	g.fill(new Ellipse2D.Double((((pB.x-5)+(pA.x-5))/2),
				    (((pB.y-5)+(pA.y-5))/2), 10, 10));
	
	g.drawString("Opp", (int)(((pB.x)+(pA.x+20))/2),
		                 (int)(((pB.y+15)+(pA.y))/2));




	
	// Screen Text // BLACK COLOR
        Font fontText = new Font("InputMonoCompressed", Font.BOLD, 24);
        g.setFont(fontText);

	//        g.setColor(fgColor);
        // g.drawString("x pos: "+x, 16, 32);
        // g.drawString("y pos: "+y, 16, 64);
        g.setColor(puColor);
	g.drawString("Angle θ: "+String.format( "%.2f",Math.toDegrees(Math.acos(x))),
		     (int)(centerX-radius), (int)(centerY-(radius+10)));	
     }
    
    private static double computeY(double x) {
	System.out.println("x in computeY="+x);
	double x2 = Math.sin(Math.acos(x)); 
	System.out.println("Math.sin(Math.acos(x))="+x2);
	double x3 = Math.acos(x);
	System.out.println("Math.acos(x))="+x3);
	// g.drawString("A: "+Math.toDegrees(Math.acos(x)), 76, 96);	
        return Math.sin(Math.acos(x));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition = e.getPoint();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
