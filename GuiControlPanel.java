import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GuiControlPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnl, pnlDistance, pnltrigger, pnlBtn;
	private JButton  btnTrigger, btnPullSlide, btnReload;
	private JTextField txtrfidListener, txtDistanceLock;
	@SuppressWarnings("unused")
	private JLabel lblrfidListener, lbldistanceLock;
	
	private boolean distanceFlag;
	
	private RandomMethod ran1 = new RandomMethod(); // this calls general methods
	
	private GuiScreen Commands = new GuiScreen(); // this is for sending true/false to  displayScreenIntructions method in GuiScreen
	
	public GuiControlPanel() {
		DisplayGUI();
		AddToPanel();
		DistanceListener();
	}
	//display settings 
	private void DisplayGUI() {
		setTitle("Control Panel");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,200);
		setLocation(550,600);
		setLayout(new GridLayout(1,	1));
//		setUndecorated(true);
		
	}
	//add components to frame
	private void AddToPanel() {
		Color pnlcolor = new Color(180,180,180);

		
		//whole button panel
		add(pnl = new JPanel());
		pnl.setLayout(new GridLayout(3, 1));
		pnl.setBackground(pnlcolor);
		
		//panel distance button
		pnl.add(pnlDistance = new JPanel());
		pnlDistance.setLayout(new GridLayout(1, 4));
		pnlDistance.add(lblrfidListener = new JLabel("RFID distance Listener"));
		pnlDistance.add(txtrfidListener = new JTextField());
		pnlDistance.add(lbldistanceLock = new JLabel("Distance Lock"));
		pnlDistance.add(txtDistanceLock = new JTextField());
		// panel trigger button
		pnl.add(pnltrigger = new JPanel());
		pnltrigger.setLayout(new GridLayout(1, 1));
		pnltrigger.add(btnTrigger = new JButton("TRIGGER"));
		btnTrigger.setEnabled(false);
		
		//panel lower button 
		pnl.add(pnlBtn = new JPanel());
		pnlBtn.add(btnReload = new JButton("RELOAD"));
		pnlBtn.add(btnPullSlide = new JButton("PULLSLIDE"));
		pnlBtn.setLayout(new GridLayout(1, 2));
		
	}
	// is the central control instructions and receives updated inputs from distanceHandler 
	public void safetyMechanism(String distance, String distanceLock) {
		if (measureDistanceRegex(distance, distanceLock) == false) { 
			Commands.lblDefaultSetting(textEmptyRegex()); //called from GuiScreen
		} else { // should returns false then will go to else 
			distanceFlag = measureDistance(distance, distanceLock); //this return true if distance < distanceLock
			Commands.displayDetail_A(distanceFlag); // called from GuiScreen
			//code for buttons 
			btnTrigger.setEnabled(distanceFlag);
			//sends boolean to ControlPanel
			controlPanel(distanceFlag);
			// true for all necessaries right
			
			//return true to controlPanel
			// Displays 
			//locked/unlocked
		}
	}
	//checks the regex of text fields and return true to safetyMechanism if right
	private boolean measureDistanceRegex(String distance, String distanceLock) {
		String regex = "^\\d{1,}";
		
		if (distance.matches(regex) && distanceLock.matches(regex))
			return true;
		else {
			return false;
		}
	}
	//converts into integers and returns true to safetyMechanism if distance is less or equal to distanceLock
	private boolean measureDistance(String distance, String distanceLock) {
		int intdistance, intdistanceLock;
		intdistance = Integer.parseInt(distance);
		intdistanceLock = Integer.parseInt(distanceLock); 
		
		if (intdistance <= intdistanceLock)
			return true;
		else
			return false; 

	}
	//checks if the text fields are empty and returns 0 for false to safetyMechanism
	public int textEmptyRegex() {
		String txtD = txtrfidListener.getText();
		String txtDL = txtDistanceLock.getText();
		
		if (txtD.matches("") || txtDL.matches("") ) {
			return 0;
		}
		else
			return 1;
	}
				/*
				 * Start of the button coding
				 */
	//is the central control instructions for button and receives boolean from safetyMechanism
	public void controlPanel(boolean flag) {
		if (flag) {
			Commands.displayDetail_B();
			if (ran1.randomJam() == GuiScreen.ammo.bulletCounter()) {
				btnTrigger.setEnabled(false);
				Commands.displayDetail_C();
			}
			else if (GuiScreen.ammo.bulletCounter() == 0) {
				btnTrigger.setEnabled(false);
			}
			
		}
		
	}
				/*
				 * start of the listeners methods
				 */
	//add listeners to components 

	private void DistanceListener () {
		txtrfidListener.getDocument().addDocumentListener(new distancehandler());
		txtDistanceLock.getDocument().addDocumentListener(new distancehandler());	
		
		btnTrigger.addActionListener(new buttonHandler());
		btnReload.addActionListener(new buttonHandler());
		btnPullSlide.addActionListener(new buttonHandler());
	}
	//text field listeners for feeding inputs to safetyMechanism
	private class distancehandler implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			safetyMechanism(txtrfidListener.getText(),txtDistanceLock.getText());
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			safetyMechanism(txtrfidListener.getText(),txtDistanceLock.getText());
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			safetyMechanism(txtrfidListener.getText(),txtDistanceLock.getText());
		}
	}
	//
	private class buttonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnTrigger) {
				GuiScreen.ammo.discharge();
				controlPanel(distanceFlag);
			}
			else if(e.getSource() == btnReload) {
				GuiScreen.ammo.reload();
				if (distanceFlag) {
					btnTrigger.setEnabled(true);
				}
				controlPanel(distanceFlag);
			}
			else if(e.getSource() == btnPullSlide) {
				if (distanceFlag) {
					GuiScreen.ammo.discharge();
					btnTrigger.setEnabled(true);
				}
				controlPanel(distanceFlag);
				
			}
			
		}
	}	
}
