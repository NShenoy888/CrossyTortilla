import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class CrossyTortillaPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 2100;
	public static final int HEIGHT = 1800;
	public static int x = 200;
	public static int y = 900;
	
	private Timer timer;
	private int moveX, moveY; // increment to move each time
	private JSlider slider;
	private JLabel sliderLabel;
	private JPanel sliderArea;
	
	BufferedImage img, candido, car;
	private Car actualCar = new Car();
	
	public CrossyTortillaPanel() {
		super();
		timer = new Timer(30, new ReboundListener());
		SliderListener listener = new SliderListener();
		moveX = 0;
		moveY = 30;
		
		//Slider stuff
		slider = new JSlider(JSlider.HORIZONTAL, 0, 200, 30);
		slider.setFocusable(false);
		slider.setMajorTickSpacing(40);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		slider.addChangeListener(listener);
		
		sliderLabel = new JLabel("Timer Delay");
		sliderLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		sliderArea = new JPanel();
		sliderArea.add(sliderLabel);
		sliderArea.add(slider);
		this.add(sliderArea, BorderLayout.SOUTH);

		init();
		
		try {                
	          candido = ImageIO.read(getClass().getResourceAsStream("/images/Candido.jpg"));
	       } catch (IOException ex) {
	            ex.printStackTrace();
	       }
		try {
			car = ImageIO.read(getClass().getResourceAsStream("/images/Car3.jpg"));
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		
		addKeyListener(new myKeyListener());
		setPreferredSize (new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		timer.start();
		
	}
	private class SliderListener implements ChangeListener {
		//------------------------------------------------
		//Called when the state of the slider has changed;
		//resets the delay on the timer.
		//------------------------------------------------
		private int value;
		
		public void stateChanged (ChangeEvent event) {
			value = slider.getValue();
			timer.setDelay(value);
		}
	}
	public class ReboundListener implements ActionListener {
		//----------------------------------------------------
		//actionPerformed is called by the timer -- it updates
		//the position of the bouncing ball
		//----------------------------------------------------
		private int slidePanelHt;
		
		public void actionPerformed(ActionEvent action) {
			slidePanelHt = sliderArea.getSize().height;
			actualCar.move(moveX,moveY);
			//change direction if ball hits a side
			int x = actualCar.getX();
			int y = actualCar.getY();
			if (x < 0 || x >= WIDTH)
				moveX = moveX * -1;
			if (y <= 300 || y >= HEIGHT-250)
				moveY = moveY * -1;
			repaint();
		}
	}
	public class myKeyListener extends KeyAdapter {
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT: {
				x+=100;
			}
				break;
			case KeyEvent.VK_LEFT: {
				x-=100;
			}
				break;
			case KeyEvent.VK_DOWN: {
				y+=100;
			}
				break;
			case KeyEvent.VK_UP: {
				y-=100;
			}
				break;
			case KeyEvent.VK_ESCAPE: {
				System.exit(0);
			}
			break;
		}
		repaint();
	}}
	
	private void init() {
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/images/TopangaCanyon.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g.drawImage(img, 0, 0, null);
		g.setColor(Color.GREEN);
		
		FontMetrics metrics = g.getFontMetrics();
		Rectangle2D strRect = metrics.getStringBounds("Crossy Tortilla!", g);
		
		int centerPanelX = 600;
		int centerPanelY = 200;
		
		int strX = centerPanelX;
		int strY = centerPanelY;
		Font myFont = new Font(Font.MONOSPACED, Font.BOLD, 100);
		g.setFont(myFont);
		g.drawString("Crossy Tortilla!", strX, strY);
		g.setColor(Color.RED);
		g2.setStroke(new BasicStroke(10));
		g2.drawLine(0, 300, 2100, 300);
		g.setColor(Color.BLACK);
		g.fillRect(900, 310, 450, 1500);
		g.setColor(Color.YELLOW);
		g.fillRect(1100, 310, 50, 250);
		g.fillRect(1100, 620, 50, 250);
		g.fillRect(1100, 930, 50, 250);
		g.fillRect(1100, 1240, 50, 250);
		g.fillRect(1100, 1550, 50, 250);
		g.setColor(Color.RED);
		actualCar.draw(g);
		g.drawImage(candido, x, y, null);
		}
}
