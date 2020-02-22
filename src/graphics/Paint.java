package graphics;

//for saving
import javax.imageio.ImageIO;
//the screen
import javax.swing.*;
import javax.swing.event.*;
import graphics.Paint;
//window tools
import java.awt.*;
//to get mouse movement
import java.awt.event.*;
import java.io.*;


/**
 * The main class
 * @author Yonatan
 *
 */
public class Paint {
	
	/** The color for the brush */
	static Color color = Color.black;
	/** The size of the brush */
	static int size = 5;
	/**The default name */
	static String project = "New Painting";
	/** If to fill the shapes */
	static boolean fill = false;
	/** the current window */
	static JFrame window;
	/** keep only 1 shape at a time */
	static boolean shapes = true;
	/** keep only 1 text at a time */
	static boolean texts = true;
	/** Fill shape */
	static JButton fillOption;
	
	/*public void updateFillIcon() {
		fillOption
	}*/
	
	
	public static void main(String[] args) {
		
		//the frame for the program 
		JFrame painter = new JFrame();
		window = painter;
		
		
		//the base panel
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(800, 800, 200, 100);
		
		
		//the painting panel
		PaintPanel paintPanel = new PaintPanel();
		paintPanel.setBounds(250, 25, 730, 730);
		paintPanel.setBackground(Color.CYAN);
		
		
		//color panel
		JPanel Colors = new JPanel();
		Colors.setBounds(10, 350, 230, 200);
		Colors.setBorder(BorderFactory.createTitledBorder("Colors"));
		Colors.setLayout(null);
		
		//Blue color button
		JButton blueButton = new JButton();
		//when pressing set the color to blue
		ActionListener blueBrushColor = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	color = Color.blue;
		    }
		};
		blueButton.addActionListener(blueBrushColor);
		blueButton.setBounds(42, 40, 60, 30);
		blueButton.setBorderPainted(true);
		blueButton.setFocusable(false);
		blueButton.setBackground(Color.blue);
		Colors.add(blueButton);
		
		
		//Yellow color button
		JButton yellowButton = new JButton();
		//when pressing set the color to yellow
		ActionListener yellowBrushColor = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	color = Color.yellow;
		    }
		};
		yellowButton.addActionListener(yellowBrushColor);
		
		yellowButton.setBounds(120, 40, 60, 30);
		yellowButton.setBorderPainted(true);
		yellowButton.setFocusable(false);
		yellowButton.setBackground(Color.yellow);
		Colors.add(yellowButton);
		
		
		//Green color button
		JButton greenButton = new JButton();
		//when pressing set the color to green
		ActionListener greenBrushColor = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	color = Color.green;
		    }
		};
		greenButton.addActionListener(greenBrushColor);
		greenButton.setBounds(42, 90, 60, 30);
		greenButton.setBorderPainted(true);
		greenButton.setFocusable(false);
		greenButton.setBackground(Color.green);
		Colors.add(greenButton);
		
		
		//Red color button
		JButton redButton = new JButton();
		//when pressing set the color to red
		ActionListener redBrushColor = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	color = Color.red;
		    }
		};
		redButton.addActionListener(redBrushColor);
		redButton.setBounds(120, 90, 60, 30);
		redButton.setBorderPainted(true);
		redButton.setFocusable(false);
		redButton.setBackground(Color.red);
		Colors.add(redButton);
		
		
		//black color button
		JButton blackButton = new JButton();
		//when pressing set the color to black
		ActionListener blackBrushColor = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	color = Color.black;
		    }
		};
		blackButton.addActionListener(blackBrushColor);
		blackButton.setBounds(42, 140, 60, 30);
		blackButton.setBorderPainted(true);
		blackButton.setFocusable(false);
		blackButton.setBackground(Color.black);
		Colors.add(blackButton);
		
		
		//purple color button
		JButton purpleButton = new JButton();
		//when pressing set the color to purple
		ActionListener purpleBrushColor = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	color = Color.magenta;
		    }
		};
		purpleButton.addActionListener(purpleBrushColor);
		purpleButton.setBounds(120, 140, 60, 30);
		purpleButton.setBorderPainted(true);
		purpleButton.setFocusable(false);
		purpleButton.setBackground(Color.magenta);
		Colors.add(purpleButton);
		
		panel.add(Colors);
		
		//clear screen button
		JButton clear = null;
		try {
			clear = new JButton(new ImageIcon(ImageIO.read(new File("Textures/Clear.png"))));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		ActionListener clearScreen = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	paintPanel.clearAll();
		    }
		};
		clear.setBorderPainted(false);
		clear.setContentAreaFilled(false);
		clear.setFocusPainted(false);
		clear.setFocusable(false);
		clear.addActionListener(clearScreen);
		clear.setBounds(20, 670, 80, 80);
		panel.add(clear);
		
		
		// Undo
		JButton undo = null;
		try {
			undo = new JButton(new ImageIcon(ImageIO.read(new File("Textures/Undo.png"))));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		ActionListener Undo = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	paintPanel.undo();
		    }
		};
		undo.setBorderPainted(false);
		undo.setContentAreaFilled(false);
		undo.setFocusPainted(false);
		undo.setFocusable(false);
		undo.addActionListener(Undo);
		undo.setBounds(140, 672, 80, 80);
		panel.add(undo);
		
		
		/* Shapes */
		
		JPanel Shapes = new JPanel();
		
		
		
		JButton Line = null;
		try {
			Line = new JButton(new ImageIcon(ImageIO.read(new File("Textures/line.png"))));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		ActionListener line = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	if (shapes)
		    		paintPanel.Line();
		    }
		};
		Line.setBorderPainted(false);
		Line.setContentAreaFilled(false);
		Line.setFocusPainted(false);
		Line.setFocusable(false);
		Line.addActionListener(line);
		Shapes.add(Line);
		
		// Rectengle
		JButton Rect = null;
		try {
			Rect = new JButton(new ImageIcon(ImageIO.read(new File("Textures/rect.png"))));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		ActionListener drawRec = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	if (shapes)
		    		paintPanel.Ract();
		    }
		};
		Rect.setBorderPainted(false);
		Rect.setContentAreaFilled(false);
		Rect.setFocusPainted(false);
		Rect.setFocusable(false);
		Rect.addActionListener(drawRec);
		Shapes.add(Rect);
		
		
		
		// Triangle 90
		JButton tri90 = null;
		try {
			tri90 = new JButton(new ImageIcon(ImageIO.read(new File("Textures/tri90.png"))));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		ActionListener drawTri90 = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	if (shapes)
		    		paintPanel.triangleRi();
		    }
		};
		tri90.setBorderPainted(false);
		tri90.setContentAreaFilled(false);
		tri90.setFocusPainted(false);
		tri90.setFocusable(false);
		tri90.addActionListener(drawTri90);
		Shapes.add(tri90);
		
		// Isosceles Triangle
		JButton triangle = null;
		try {
			triangle = new JButton(new ImageIcon(ImageIO.read(new File("Textures/Triangle.png"))));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		ActionListener drawTriangle = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	if (shapes)
		    		paintPanel.triangleIs();
		    }
		};
		triangle.setBorderPainted(false);
		triangle.setContentAreaFilled(false);
		triangle.setFocusPainted(false);
		triangle.setFocusable(false);
		triangle.addActionListener(drawTriangle);
		Shapes.add(triangle);
		
		// Obtuse Triangle
		JButton triangleOb = null;
		try {
			triangleOb = new JButton(new ImageIcon(ImageIO.read(new File("Textures/obTri.png"))));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		ActionListener drawTriangleOb = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	if (shapes)
		    		paintPanel.triangleOb();
		    }
		};
		triangleOb.setBorderPainted(false);
		triangleOb.setContentAreaFilled(false);
		triangleOb.setFocusPainted(false);
		triangleOb.setFocusable(false);
		triangleOb.addActionListener(drawTriangleOb);
		Shapes.add(triangleOb);
		
		
		// Obtuse Triangle
		JButton Rhombus = null;
		try {
			Rhombus = new JButton(new ImageIcon(ImageIO.read(new File("Textures/Rhombus.png"))));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		ActionListener rhombus = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	if (shapes)
		    		paintPanel.rhombus();
		    }
		};
		Rhombus.setBorderPainted(false);
		Rhombus.setContentAreaFilled(false);
		Rhombus.setFocusPainted(false);
		Rhombus.setFocusable(false);
		Rhombus.addActionListener(rhombus);
		Shapes.add(Rhombus);
		
		
		// Ring Triangle
		JButton Ring = null;
		try {
			Ring = new JButton(new ImageIcon(ImageIO.read(new File("Textures/Round.png"))));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		ActionListener circle = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	if (shapes)
		    		paintPanel.Circle();
		    }
		};
		Ring.setBorderPainted(false);
		Ring.setContentAreaFilled(false);
		Ring.setFocusPainted(false);
		Ring.setFocusable(false);
		Ring.addActionListener(circle);
		Shapes.add(Ring);

		
		JScrollPane shapeScroll = new JScrollPane(Shapes);
		shapeScroll.setBounds(10, 30, 230, 110);
		panel.add(shapeScroll);
		
		
		
		
		
		/* Fill option */
		
		JToggleButton Fill = null;
		try {
			Fill = new JToggleButton(new ImageIcon(ImageIO.read(new File("Textures/FillF.png"))));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		ActionListener filling = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	if (fill) 
		    		fill = false;
		    	else 
		    		fill = true;
		    }
		};
		Fill.setBounds(140, 570, 80, 80);
		Fill.setBorderPainted(false);
		Fill.setContentAreaFilled(false);
		Fill.setFocusPainted(false);
		Fill.setFocusable(false);
		Fill.addActionListener(filling);
		try {
			Fill.setSelectedIcon(new ImageIcon(ImageIO.read(new File("Textures/FillT.png"))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		panel.add(Fill);
		
		
		
		/* Text */
		
		JButton Text = null;
		try {
			Text = new JButton(new ImageIcon(ImageIO.read(new File("Textures/Text.png"))));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		ActionListener write = new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	System.out.println("Clicked");
		    	paintPanel.write();
		    }
		};
		Text.setBounds(20, 570, 80, 80);
		Text.setBorderPainted(false);
		Text.setContentAreaFilled(false);
		Text.setFocusPainted(false);
		Text.addActionListener(write);
		panel.add(Text);
		
		
		/*brush sizes buttons and labels */
		
		//Panel
		JPanel brushSizePanel = new JPanel();
		brushSizePanel.setLayout(null);
		brushSizePanel.setBorder(BorderFactory.createTitledBorder("Brush Size"));
		brushSizePanel.setBounds(10, 150, 230, 200);
		
		//size 3
		JRadioButton S3ps = new JRadioButton("Size 3");
		S3ps.setBounds(90, 180, 20, 20);
		S3ps.setFocusable(false);
		panel.add(S3ps);
		
		JLabel S3tx = new JLabel("Size 3");
		S3tx.setBounds(40, 180, 80, 20);
		panel.add(S3tx);
		
		
		//size 4
		JRadioButton S4ps = new JRadioButton("Size 4");
		S4ps.setBounds(190, 180, 20, 20);
		S4ps.setFocusable(false);
		panel.add(S4ps);
		
		JLabel S4tx = new JLabel("Size 4");
		S4tx.setBounds(140, 180, 80, 20);
		panel.add(S4tx);
				
		
		//size 5
		JRadioButton S5ps = new JRadioButton("Size 5");
		S5ps.setBounds(90, 220, 20, 20);
		S5ps.setFocusable(false);
		panel.add(S5ps);
		
		JLabel S5tx = new JLabel("Size 5");
		S5tx.setBounds(40, 220, 80, 20);
		panel.add(S5tx);
		
		
		//size 6
		JRadioButton S6ps = new JRadioButton("Size 6");
		S6ps.setBounds(190, 220, 20, 20);
		S6ps.setFocusable(false);
		panel.add(S6ps);
		
		JLabel S6tx = new JLabel("Size 6");
		S6tx.setBounds(140, 220, 80, 20);
		panel.add(S6tx);
		
		
		//size 7
		JRadioButton S7ps = new JRadioButton("Size 7");
		S7ps.setBounds(90, 260, 20, 20);
		S7ps.setFocusable(false);
		panel.add(S7ps);
		
		JLabel S7tx = new JLabel("Size 7");
		S7tx.setBounds(40, 260, 80, 20);
		panel.add(S7tx);
		
		
		//size 7
		JRadioButton S8ps = new JRadioButton("Size 8");
		S8ps.setBounds(190, 260, 20, 20);
		S8ps.setFocusable(false);
		panel.add(S8ps);
		
		JLabel S8tx = new JLabel("Size 8");
		S8tx.setBounds(140, 260, 80, 20);
		panel.add(S8tx);
		
		
		//size 9
		JRadioButton S9ps = new JRadioButton("Size 9");
		S9ps.setBounds(90, 300, 20, 20);
		S9ps.setFocusable(false);
		panel.add(S9ps);
		
		JLabel S9tx = new JLabel("Size 9");
		S9tx.setBounds(40, 300, 80, 20);
		panel.add(S9tx);
		
		
		//size 10
		JRadioButton S10ps = new JRadioButton("Size 10");
		S10ps.setBounds(190, 300, 20, 20);
		S10ps.setFocusable(false);
		panel.add(S10ps);
		
		JLabel S10tx = new JLabel("Size 10");
		S10tx.setBounds(140, 300, 80, 20);
		panel.add(S10tx);
		
		
		//group the buttons together
		ButtonGroup brushSize = new ButtonGroup();
		brushSize.add(S3ps);
		brushSize.add(S4ps);
		brushSize.add(S5ps);
		brushSize.add(S6ps);
		brushSize.add(S7ps);
		brushSize.add(S8ps);
		brushSize.add(S9ps);
		brushSize.add(S10ps);
		
		//brush sizes listeners
		S3ps.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
	    		size = 3;
		    }
		});
		S4ps.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	size = 4;
		    }
		});
		S5ps.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	size = 5;
		    }
		});
		S6ps.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	size = 6;
		    }
		});
		S7ps.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	size = 7;
		    }
		});
		S8ps.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	size = 8;
		    }
		});
		S9ps.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	size = 9;
		    }
		});
		S10ps.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	size = 10;
		    }
		});
		
		panel.add(brushSizePanel);
		
		//menu bar at the top
		JMenuBar bar = new JMenuBar(); 
		bar.setBounds(0, 0, 1000, 20);
		bar.setBorderPainted(true);
		
		
		//new menu
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		//save file
		JMenuItem save = new JMenuItem("Save File");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Saving");
				paintPanel.saveImage();
			}
		});
		
		//Rename the file
		JMenuItem rename = new JMenuItem("Rename Project");
		rename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Opening new window");
				String name = JOptionPane.showInputDialog("Enter a new name");
				if (name != null && name.length() > 0) {
					project = name;
				}
				painter.setTitle("Painter V0.1 - " + project);
			}
		});
		
		//Open folder
		JMenuItem openFolder = new JMenuItem("Open Folder");
		openFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Opening");
				try {
					Desktop.getDesktop().open(new File(System.getProperty("user.dir")));
				} catch (IOException e) {
					System.out.println("Error: " + e);
				}
			}
		});
		
		//Colors to the edit sub menu
		JMenu brushColorMenu = new JMenu("Brush color");
		//Blue color
		JMenuItem blueBrushMenu = new JMenuItem("Blue");
		blueBrushMenu.addActionListener(blueBrushColor);
		//yellow color
		JMenuItem yellowBrushMenu = new JMenuItem("Yellow");
		yellowBrushMenu.addActionListener(yellowBrushColor);
		//green color
		JMenuItem greenBrushMenu = new JMenuItem("Green");
		greenBrushMenu.addActionListener(greenBrushColor);
		//red color
		JMenuItem redBrushMenu = new JMenuItem("Red");
		redBrushMenu.addActionListener(redBrushColor);
		//black color
		JMenuItem blackBrushMenu = new JMenuItem("Black");
		blackBrushMenu.addActionListener(blackBrushColor);
		//purple color
		JMenuItem purpleBrushMenu = new JMenuItem("Purple");
		purpleBrushMenu.addActionListener(purpleBrushColor);
		
		
		//brush size to the edit sub menu
		JMenuItem brushSizeMenu = new JMenuItem("Brush Size");
		brushSizeMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Open a window with a slider
				JFrame sizeSliderFrame = new JFrame();
				JPanel sizeSliderPlane = new JPanel();
				sizeSliderPlane.setLayout(null);
				
				//add a slider
				JSlider brushSizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 30, size);
				brushSizeSlider.setMajorTickSpacing(10);
				brushSizeSlider.setMinorTickSpacing(1);
				brushSizeSlider.setPaintTicks(true);
				brushSizeSlider.setPaintLabels(true);
				brushSizeSlider.setBounds(20, 40, 350, 80);
				
				brushSizeSlider.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						JSlider source = (JSlider)e.getSource();
						size = source.getValue();
					}
				});
				
				
				JButton closeSize = new JButton("Ok");
				closeSize.setBounds(150, 120, 80, 30);
				closeSize.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						sizeSliderFrame.dispose();
					}
				});
				
				sizeSliderPlane.add(closeSize);
				sizeSliderPlane.add(brushSizeSlider);
				
				sizeSliderFrame.add(sizeSliderPlane);
				sizeSliderFrame.setSize(400, 200);
				sizeSliderFrame.setTitle("Brush Size Slider - " + project);
				sizeSliderFrame.setLocationRelativeTo(null);
				sizeSliderFrame.setVisible(true);
			}
		});
		
		
		//clears screen
		JMenuItem clearScreenMenu = new JMenuItem("Clear");
		clearScreenMenu.addActionListener(clearScreen);		
		
		// Undo
		JMenuItem undoScreenMenu = new JMenuItem("Undo");
		undoScreenMenu.addActionListener(Undo);
	
		
		
		// Shapes menu
		JMenu addShapesMenu = new JMenu("Add Shape");
		
		/* The shapes */
		JMenuItem triangleMenu = new JMenuItem("Isosceles Triangle");
		triangleMenu.addActionListener(drawTriangle);
		
		JMenuItem rightTriangleMenu = new JMenuItem("Right Triangle");
		rightTriangleMenu.addActionListener(drawTri90);
		
		JMenuItem obtuseTriangleMenu = new JMenuItem("Obtuse Triangle");
		obtuseTriangleMenu.addActionListener(drawTriangleOb);
		
		JMenuItem rectMenu = new JMenuItem("Rectangle");
		rectMenu.addActionListener(drawRec);
		
		JMenuItem lineMenu = new JMenuItem("Line");
		lineMenu.addActionListener(line);
		
		JMenuItem diamondMenu = new JMenuItem("Diamond");
		diamondMenu.addActionListener(rhombus);
		
		JMenuItem roundMenu = new JMenuItem("Circle");
		roundMenu.addActionListener(circle);
		
		
		
		//add the save to the file menu
		fileMenu.add(save);
		fileMenu.add(rename);
		fileMenu.add(openFolder);
		//add to the brush color sub menu
		brushColorMenu.add(blueBrushMenu);
		brushColorMenu.add(yellowBrushMenu);
		brushColorMenu.add(greenBrushMenu);
		brushColorMenu.add(redBrushMenu);
		brushColorMenu.add(blackBrushMenu);
		brushColorMenu.add(purpleBrushMenu);
		//Add to the shapes menu
		addShapesMenu.add(triangleMenu);
		addShapesMenu.add(rightTriangleMenu);
		addShapesMenu.add(obtuseTriangleMenu);
		addShapesMenu.add(rectMenu);
		addShapesMenu.add(lineMenu);
		addShapesMenu.add(diamondMenu);
		addShapesMenu.add(roundMenu);
		//add the edit options to the edit menu
		editMenu.add(brushColorMenu);
		editMenu.add(brushSizeMenu);
		editMenu.addSeparator();
		editMenu.add(clearScreenMenu);
		editMenu.add(undoScreenMenu);
		editMenu.addSeparator();
		editMenu.add(addShapesMenu);
		//add the file menu to the bar
		bar.add(fileMenu);
		bar.add(editMenu);
		//add the bar to the panel
		panel.add(bar);
		
		//add the paint panel to the base panel
		panel.add(paintPanel);
		
		//add the panel to the frame
		painter.add(panel);
		

		//JFrame settings
		painter.setSize(1000, 800);
		painter.setTitle("Painter V1.0 - " + project);
		painter.setLocationRelativeTo(null);
		painter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		painter.setVisible(true);
		
	}	
}