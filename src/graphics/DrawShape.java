package graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import javax.swing.JPanel;

/**
 * Draw shapes on the panel
 * @author Yonatan
 *
 */
public class DrawShape extends JPanel {
	

	private static final long serialVersionUID = 1L;
	
	
	int x, y, x2, y2, num;
	public PaintPanel Panel;
	public BufferedImage currentBackground;
	public BufferedImage tempBackground;
	
	/**
	 * Copy a buffered image
	 * @param bi the image to copy
	 * @return new buffered image
	 */
	static BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	
	/**
	 * Draw a shape
	 * @param panel the paint panel
	 * @param n 5 for Obtuse triangle | 4 for Rectangle | 3 for 90Triangle | 2 for isosceles Triangle | 1 for Line 
	 */
    @SuppressWarnings("deprecation")
	public DrawShape(PaintPanel panel, int n) {
    	//Add the text labels
    	for (writeText text : panel.Texts) {
    		add(text);
    	}
    	
    	panel.add(this);
    	this.setBounds(0, 0, 730, 730);
    	DrawSh();
    	Panel = panel;
    	currentBackground = deepCopy(Panel.background);
    	Panel.lastBackground = deepCopy(Panel.background);
    	num = n;
    	
    	// Disable new shapes
    	Paint.shapes = false;
    	
    	// Change cursor
    	Paint.window.setCursor(Cursor.HAND_CURSOR);
    }

    public void DrawSh() {
        x = y = x2 = y2 = 0; // 
        MyMouseListener listener = new MyMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    /**
     * Set Start point
     * @param x the x location
     * @param y the y location
     */
    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set end point
     * @param x the x location
     * @param y the y location
     */
    public void setEndPoint(int x, int y) {
        x2 = (x);
        y2 = (y);
    }

    /**
     * Draw the shape
     * @param x starter X
     * @param y starter Y
     * @param x2 End X
     * @param y2 End Y
     */
    public void drawPerfectRect(int x, int y, int x2, int y2) {
    	
    	//Return of no end
    	if (x2 > 0 || y2 > 0) {
			
			// Import and save the old background
			if (tempBackground != null) {
				Panel.background = tempBackground;
			}
			tempBackground = deepCopy(Panel.background);
			
			Graphics2D g2d = currentBackground.createGraphics();
			
			// Stroke thickness 
			g2d.setStroke(new java.awt.BasicStroke(Paint.size));
			
			/** Get colors */
			g2d.setColor(Paint.color);
			// Return to the old background
			g2d.drawImage(Panel.background, 0, 0, null);
		
			
		    // Draw the polygon
			if (num == 3 || num == 4) {
				// Rect or 90 triangle
				if (Paint.fill) 
					g2d.fillPolygon(new int[] {x, x2, x2, x}, new int[] {y, y, y2, y2}, num);
				else 
					g2d.drawPolygon(new int[] {x, x2, x2, x}, new int[] {y, y, y2, y2}, num);
			} else if (num == 2) {
				// isosceles triangle
				int x3 = x - (x2-x);
				if (Paint.fill) 
					g2d.fillPolygon(new int[] {x, x3, x2}, new int[] {y, y2, y2}, 3);
				else
					g2d.drawPolygon(new int[] {x, x3, x2}, new int[] {y, y2, y2}, 3);
			} else if (num == 5) {
				// Obtuse
				int x3 = x - (x2-x); 
				if (Paint.fill) 
					g2d.fillPolygon(new int[] {x, x3, x2}, new int[] {y, y, y2}, 3);
				else 
					g2d.drawPolygon(new int[] {x, x3, x2}, new int[] {y, y, y2}, 3);
			} else if (num == 6) {
				// For Diamond
				int x3 = x - (x2 - x);
				int y3 = y2 + (y2 - y);
				if (Paint.fill)
					g2d.fillPolygon(new int[] {x, x2, x, x3}, new int[] {y, y2, y3, y2}, 4);
				else 
					g2d.drawPolygon(new int[] {x, x2, x, x3}, new int[] {y, y2, y3, y2}, 4);
			} else if (num == 1) {
				// For line
				if (Paint.fill)
					g2d.fillPolygon(new int[] {x, x2}, new int[] {y, y2}, 2);
				else 
					g2d.drawPolygon(new int[] {x, x2}, new int[] {y, y2}, 2);
			} else if (num == 7) {
				if (Paint.fill)
					g2d.fillOval(x, y,  Math.max(x, x2) - Math.min(x, x2), Math.max(y, y2) - Math.min(y, y2));
				else
					g2d.drawOval(x, y,  Math.max(x, x2) - Math.min(x, x2), Math.max(y, y2) - Math.min(y, y2));
			}
		    repaint();
		    
		    Panel.background = currentBackground;
    	}
    }

    /**
     * Mouse listener
     * @author Yonatan
     */
    class MyMouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            setStartPoint(e.getX(), e.getY());
        }

        public void mouseDragged(MouseEvent e) {
            setEndPoint(e.getX(), e.getY());
            repaint();
        }

        public void mouseReleased(MouseEvent e) {
            setEndPoint(e.getX(), e.getY());
            repaint();
            // Remove on release
            Remove();
            
        }
    }

    /**
     * Remove the shape component from the panel 
     */
    @SuppressWarnings("deprecation")
	public void Remove() {
    	Panel.background = currentBackground;
        Panel.remove(this);
        for (writeText text : Panel.Texts) {
			this.remove(text);
		}
        Paint.shapes = true;
        Panel.undoOption = 1;
        Panel.updateText();
        Paint.window.setCursor(Cursor.DEFAULT_CURSOR);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Panel.background, 0, 0, null);
        g.setColor(Color.RED);
        drawPerfectRect(x, y, x2, y2);
    }
}

