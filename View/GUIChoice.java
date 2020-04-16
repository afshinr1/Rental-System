package View;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUIChoice extends JFrame {

	private static final long serialVersionUID = 1L;
private JLabel title = new JLabel("Please select which category you fall under\n");
private JButton landLord = new JButton("LandLord");
private JButton manager = new JButton("Manager");
private JButton renter = new JButton("Normal Renter");
private JButton registerRenter = new JButton("Registered Renter");
private JPanel panel;

public GUIChoice(){
	panel = new JPanel();
	this.setSize(300,150);
	panel.add(title);
	panel.add(landLord);
	panel.add(manager);
	panel.add(renter);
	panel.add(registerRenter);
	this.add(panel);
}

public void addLandLordListener(ActionListener landLordListener) {
	landLord.addActionListener(landLordListener);
}

public void addManagerListener(ActionListener managerListener) {
	manager.addActionListener(managerListener);
}
public void addRenterListener(ActionListener renterListener) {
	renter.addActionListener(renterListener);
}
public void addRegRenterListener(ActionListener regRenterListener) {
	registerRenter.addActionListener(regRenterListener);
}
}
