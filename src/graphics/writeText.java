package graphics;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;



/**
 * Write text on screen
 * @author Yonatan
 */
public class writeText extends JLabel {

	private static final long serialVersionUID = 1L;
	private KeyLis listener;
	String text;
	int y = 100, x = 100;
	PaintPanel Panel;
	MouseListener ML;
	boolean on = true;
	
	/**
	 * Write Text on screen
	 * @param panel the panel
	 */
	public writeText(PaintPanel panel) {
		text = "_";
		Panel = panel;
		panel.setLayout(null);
		this.setBounds(x, y, 500, 200);
		updateTx();
		listener = new KeyLis();
		
		
		// add key listener
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		panel.addKeyListener(listener);
		
		ML = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				x = arg0.getX();
				y = arg0.getY();
				updateLoc();
				
			}

			/* unused */
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		};
		
		// add mouse listener
		Panel.addMouseListener(ML);
		
		// Remove the drawing one
		Panel.removeMouseMotionListener(panel.Drawing);
		
		// Font Size and Color
		setFont(new Font("Serif", Font.BOLD, Paint.size * 5));
		this.setForeground(Paint.color);
		
		// Change mouse Cursor
		cursor();
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void cursor() {
		Paint.window.setCursor(Cursor.TEXT_CURSOR);
	}
	
	/**
	 * Restore the mouse listeners
	 */
	@SuppressWarnings("deprecation")
	public void done() {
		on = false;
		Panel.removeMouseListener(ML);
		Panel.addMouseMotionListener(Panel.Drawing);
		Panel.removeKeyListener(listener);
		Paint.texts = true;
		Panel.undoOption = 2;
		Paint.window.setCursor(Cursor.DEFAULT_CURSOR);
	}
	
	/**
	 * Update the location
	 */
	public void updateLoc() {
		this.setBounds(x, y - 100, 500, 200);
	}
	
	/**
	 * Update the text
	 */
	public void updateTx() {
		this.setText(text);
	}
	
	/**
	 * Listen to the keyboard
	 * @author Yonatan
	 */
	public class KeyLis extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if (key == KeyEvent.VK_BACK_SPACE) {
				try {
					text = text.substring(0, text.length() - 2);
					text = text + "_";
				} catch (Exception er) {}
			} else if (key == KeyEvent.VK_ENTER) {
				// Enter
				text = text.substring(0, text.length() - 1);
				Panel.removeKeyListener(this);
				done();
			} else if (key == KeyEvent.VK_SPACE) {
				// Space
				text = text.substring(0, text.length() - 1);
				text = text + " ";
				text = text + "_";
			} else if (key >= 48 && key <= 57 || key >= 65 && key <= 90 || key >= 97 && key <= 122){
				// Numbers, Letters and Chars
				text = text.substring(0, text.length() - 1);
				text = text + e.getKeyChar();
				text = text + "_";
			}
			
			updateTx();
			
		}
	}
	
}
