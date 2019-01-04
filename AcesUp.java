
import javax.swing.*;

/**
 *   Program to play a game of Aces Up
 */

/**
 * @author Joowon Kim
 *
 */
public class AcesUp {
	
	// DO NOT CHANGE, REMOVE OR ADD ANY CODE GIVEN IN THIS CLASS

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Aces Up");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameController controller = new GameController();
		frame.getContentPane().add(new GamePanel(controller));

		frame.pack();
		frame.setSize(1000,500);
		frame.setVisible(true);
		
	}

}
