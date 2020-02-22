package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * new class called PaintPanel that extends the JPanel class and adds new things to it, graphics and updates for the painter program
 * @author Yonatan
 *
 */
class PaintPanel extends JPanel {
	
	/**
	 * Serial Version (?)
	 */
	private static final long serialVersionUID = 1L;
	
	//gets point on screen
	ArrayList<writeText> Texts = new ArrayList<>();
	public BufferedImage background;
	public BufferedImage lastBackground;
	public int undoOption = 1;
	MouseMotionListener Drawing;
	
	
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
	 * Paint panel
	 */
	public PaintPanel() {
		/* Listen to mouse motion */
		Drawing = new MouseMotionAdapter() {
			/**
			 * Draw in the dragged points
			 */
			@Override
			public void mouseDragged(MouseEvent event) {
				draw(event.getPoint());
				repaint();
			}
		};
		addMouseMotionListener(Drawing);
		
		/* Listen for mouse press and release */
		
		MouseListener mouseLis = new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent e) {
				// Makes it so you can do undo to 1 brush movement
				System.out.println("Changing background");
				lastBackground = deepCopy(background);
				undoOption = 1;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
		};
		addMouseListener(mouseLis);
	}
	
	/**
	 * Write text
	 */
	public void write() {
		if (Paint.texts) {
			Paint.texts = false;
			writeText text = new writeText(this);
			add(text);
			Texts.add(text);
		}
	}
	
	/**
	 * Undo 1 thing
	 */
	public void undo() {
		try {
			if (undoOption == 1) {
				background = lastBackground;
				if (background == null) {
		            updateBuffer();
		        }
				Graphics2D g2d = background.createGraphics();
				g2d.drawImage(lastBackground, 0, 0, null);
				g2d.dispose();
				// Restore the text
				updateText();
				repaint();
			} else if (undoOption == 2) {
				remove(Texts.get(Texts.size() - 1));
				Texts.remove(Texts.size() - 1);
				undoOption = 1;
				repaint();
			}
		}
		catch (Exception err) {
			System.out.println(err);
		}
		
	}
	
	
	/**
	 * Draw a rectangle
	 */
	public void Ract() {
		// Update background
		if (background == null) {
            updateBuffer();
        }
		// Add new panel for the shape
		this.setLayout(null);
		add(new DrawShape(this, 4));
	}
	
	/**
	 * Draw a 90 Triangle
	 */
	public void triangleRi() {
		// Update background
		if (background == null) {
            updateBuffer();
        }
		// Add new panel for the shape
		this.setLayout(null);
		add(new DrawShape(this, 3));
	}
	
	/**
	 * Draw Isosceles triangle
	 */
	public void triangleIs() {
		// Update background
		if (background == null) {
            updateBuffer();
        }
		// Add new panel for the shape
		this.setLayout(null);
		add(new DrawShape(this, 2));
	}
	
	/**
	 * Draw Obtuse Triangle
	 */
	public void triangleOb() {
		// Update background
		if (background == null) {
            updateBuffer();
        }
		// Add new panel for the shape
		this.setLayout(null);
		add(new DrawShape(this, 5));
	}
	
	/**
	 * Draw Rhombus
	 */
	public void rhombus() {
		// Update background
		if (background == null) {
            updateBuffer();
        }
		// Add new panel for the shape
		this.setLayout(null);
		add(new DrawShape(this, 6));
	}
	
	/**
	 * Draw line
	 */
	public void Line() {
		// Update background
		if (background == null) {
            updateBuffer();
        }
		// Add new panel for the shape
		this.setLayout(null);
		add(new DrawShape(this, 1));
	}
	
	/**
	 * DrawCircle
	 */
	public void Circle() {
		// Update background
		if (background == null) {
            updateBuffer();
        }
		// Add new panel for the shape
			this.setLayout(null);
			add(new DrawShape(this, 7));
	}
	
	/**
	 * updating the background
	 */
	protected void updateBuffer() {

        if (getWidth() > 0 && getHeight() > 0) {
            BufferedImage newBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            //new graphics
            Graphics2D g2d = newBuffer.createGraphics();
            
            //set color to cyan
            g2d.setColor(Color.cyan);
            //fiils
            g2d.fillRect(0, 0, getWidth(), getHeight());
            if (background != null) {
                g2d.drawImage(background, 0, 0, this);
            }
            g2d.dispose();
            background = newBuffer;
        }

    }
	
	/**
	 * Clears the panel
	 */
	public void clearAll() {
		background = null;
		updateBuffer();
		for (writeText text : Texts) {
			this.remove(text);
		}
		for (int i = Texts.size() - 1; i >= 0; i--) {
			Texts.remove(i);
		}
		repaint();
		System.out.println("Cleared");
	}
	
	/**
	 * removes and re-adds the text
	 */
	public void updateText() {
		for (writeText text : Texts) {
			this.remove(text);
		}
		for (writeText text : Texts) {
			this.add(text);
		}
	}
	
	/**
	 * Saves the panel to image in project folder
	 */
	public void saveImage() {
        try {
        	System.out.println("Writing!");
        	System.out.println(Paint.project + "");
        	ImageIO.write(background, "png", new File(Paint.project + ".png"));
        }
        catch (Exception err) {
        	System.out.println("Error saving");
        }
	}
	
	
	/**
	 * Draws a point on the panel
	 * @param p the point to draw in
	 */
	public void draw(Point p) {
		Graphics2D g2d = background.createGraphics();
		if (background == null) {
            updateBuffer();
        }
		
		/*get the color */
		g2d.setColor(Paint.color);
		
		//paint in the location with the brush size
		g2d.fillOval(p.x - 5, p.y - 5, Paint.size, Paint.size);
        g2d.dispose();
	}
	
	
	@Override
	/**
	 * Paint Component for graphics
	 */
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		if (background == null) {
            updateBuffer();
        }
		g2d.drawImage(background, 0, 0, this);
        g2d.dispose();
	}
}

