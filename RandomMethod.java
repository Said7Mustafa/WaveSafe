import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.JLabel;

public class RandomMethod {
	
	//creates random jam called in control panel
	public int randomJam() {
		Random ran = new Random();
		int x = ran.nextInt(16)+2;
		return x;
	}
	/**
	 * 
	 * for gui screen
	 */
	
	//label designs
	public void DefaultDesign(JLabel lbl) {
		
		Color color = new Color(72,72,72);
		
		lbl.setOpaque(true);
		lbl.setHorizontalAlignment(JLabel.CENTER);
		lbl.setVerticalAlignment(JLabel.CENTER);
		lbl.setBackground(color);
		lbl.setForeground(Color.black);
		lbl.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 19));
		lbl.setText("");
	}
	
	public void defaultComponentDesign(JLabel lbli , JLabel lblj) {
		DefaultDesign(lbli);
		DefaultDesign(lblj);
	}
	public void textActive(JLabel lbl) {
		lbl.setForeground(Color.GREEN);
	}
	public void textWarning(JLabel lbl) {
		lbl.setForeground(Color.RED);
	}

}
