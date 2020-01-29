import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class CrossyTortillaGame {
	public static void main(String[] args) {
		JFrame window = new JFrame("Crossy Tortilla!");
		window.setContentPane(new CrossyTortillaPanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
