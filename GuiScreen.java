import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuiScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel pnlScreen = new JPanel();
	private JLabel lblcmd1, lblcmd2, lblcmd3,lblcmd4;
	
	private RandomMethod ran2 = new RandomMethod();
	
	public static GunAmmo ammo = new GunAmmo();

	public GuiScreen() {
		DisplayScreen();
		AddToPanel();
	}
	//display settings
	private void DisplayScreen() {
		setTitle("WaveSafe Interface");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,200);
		setLocation(550,250);
		
		setVisible(true);
	}
	//adds components to frame
	private void AddToPanel() {
		add(pnlScreen);
//		pnlScreen.setBackground(Color.gray);
		pnlScreen.setLayout(new GridLayout(4, 1));
		
		pnlScreen.add(lblcmd1 = new JLabel());
		pnlScreen.add(lblcmd2 = new JLabel());
		pnlScreen.add(lblcmd3 = new JLabel());
		pnlScreen.add(lblcmd4 = new JLabel());
		
		ran2.defaultComponentDesign(lblcmd1,lblcmd2);
		ran2.defaultComponentDesign(lblcmd3,lblcmd4);
	}

	// called in GuiControlPanel class in safetyMechanism
	public void lblDefaultSetting(int flag) {
		if (flag == 0) {
			ran2.defaultComponentDesign(lblcmd1,lblcmd2);
			ran2.defaultComponentDesign(lblcmd3,lblcmd4);
		}
	}
	//displays locked or unlocked
	public void displayDetail_A(boolean flag) {
		if (flag) {
			ran2.defaultComponentDesign(lblcmd3,lblcmd4);
			ran2.DefaultDesign(lblcmd2);
			lblcmd1.setText("Trigger Unlocked");
			ran2.textActive(lblcmd1);
		} 
		else {
			ran2.defaultComponentDesign(lblcmd3,lblcmd4);
			ran2.DefaultDesign(lblcmd1);
			lblcmd2.setText("Trigger locked");
			ran2.textWarning(lblcmd2);
		}
	}
	//display bullets details
	public void displayDetail_B() {
		ran2.defaultComponentDesign(lblcmd3,lblcmd4);
		lblcmd2.setText(ammo.getDefaultAmmo() + "/" + ammo.bulletCounter());
		if (GuiScreen.ammo.bulletCounter() != 0) {
			ran2.textActive(lblcmd2);
			ran2.textActive(lblcmd1);
			lblcmd3.setText("Loaded");
			ran2.textActive(lblcmd3);
		}
		else {
			ran2.defaultComponentDesign(lblcmd3,lblcmd4);
			lblcmd3.setText("Magazine empty");
			lblcmd4.setText("Please Reload");
			ran2.textWarning(lblcmd1);
			ran2.textWarning(lblcmd2);
			ran2.textWarning(lblcmd3);
			ran2.textWarning(lblcmd4);
		}
	}
	//pull slide and jam display
	public void displayDetail_C() {
		ran2.defaultComponentDesign(lblcmd3,lblcmd4);
		lblcmd3.setText("Gun Jam");
		lblcmd4.setText("Pull Slide");
		ran2.textWarning(lblcmd3);
		ran2.textWarning(lblcmd4);
	}







	
	
}
