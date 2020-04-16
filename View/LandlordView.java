package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class LandlordView extends GUIView {

	private static final long serialVersionUID = 1L;
	private JLabel lTitle = new JLabel("LandLord Interface");
	private JTextArea text = new JTextArea();
	private JScrollPane textScroll = new JScrollPane(text);

	private JButton register = new JButton("Register Property");
	private JButton changeState = new JButton("Change State of Property");
	private JButton pay = new JButton("Make Payment");
	private JButton list = new JButton("List my Properties");
	private JButton logout = new JButton("Logout");
	private JButton getEmails = new JButton("Get Emails");

	private JPanel mainPanel;

	private JPanel displayPanel;
	private JLabel loginTitle = new JLabel("Please enter your first and last name followed by your email:");
	private JTextField firstName = new JTextField(10);
	private JTextField lastName = new JTextField(10);
	private JTextField email = new JTextField(10);
	private JButton landlordInfo = new JButton("OK");
	private JTextField idbox = new JTextField(10);

	private JTextField houseType = new JTextField(8);
	private JTextField numBedrooms = new JTextField(8);
	private JTextField numBathrooms = new JTextField(8);
	private JTextField furnished = new JTextField(8);
	private JTextField quadrant = new JTextField(8);
	private JTextField propID = new JTextField(8);
	private JTextField propAdd = new JTextField(8);

	private JTextField id2 = new JTextField(8);
	private JTextField state = new JTextField(8);

	
	public LandlordView() {
		displayInfoBox();
	}

	private void displayInfoBox() {
		displayPanel = new JPanel();
		this.setSize(400, 150);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		displayPanel.add(loginTitle);
		displayPanel.add(firstName);
		displayPanel.add(lastName);
		displayPanel.add(email);
		displayPanel.add(landlordInfo);

		this.add(displayPanel);

	}

	@Override
	public void display() {
		mainPanel = new JPanel();
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.add(lTitle);
		text.setEditable(false);
		textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textScroll.setPreferredSize(new Dimension(450, 365));

		mainPanel.add(textScroll);
		mainPanel.add(register);
		mainPanel.add(changeState);
		mainPanel.add(pay);
		mainPanel.add(logout);
		mainPanel.add(list);
		mainPanel.add(getEmails);

		this.add(mainPanel);
	}

	public void addRegisterListener(ActionListener addreg) {
		register.addActionListener(addreg);
	}

	public void addStateListener(ActionListener state) {
		changeState.addActionListener(state);
	}

	public void addPayistener(ActionListener fee) {
		pay.addActionListener(fee);
	}

	public void addLogoutListener(ActionListener log) {
		logout.addActionListener(log);
	}
	
	public void addEmailListener(ActionListener emailListener) {
		getEmails.addActionListener(emailListener);
	}

	public void addInfoListener(ActionListener e) {
		landlordInfo.addActionListener(e);
	}

	public void addListListener(ActionListener listListener) {
		list.addActionListener(listListener);
	}

	public boolean infoBoxIsEmpty() {
		if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isEmpty())
			return true;
		return false;
	}

	public String getFname() {
		return firstName.getText();
	}

	public String getLname() {
		return lastName.getText();
	}

	public String getEmail() {
		return email.getText();
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void closeInfoBox() {
		displayPanel.setVisible(false);
	}

	public int propertyInfo() {

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(4, 3));
		infoPanel.add(new JLabel("Property ID: "));
		infoPanel.add(propID);
		infoPanel.add(new JLabel("Property Address"));
		infoPanel.add(propAdd);
		infoPanel.add(new JLabel("House Type:"));
		infoPanel.add(houseType);
		infoPanel.add(new JLabel("Number of BedRooms\n"));
		infoPanel.add(numBedrooms);
		infoPanel.add(new JLabel("Number of Bathrooms"));
		infoPanel.add(numBathrooms);
		infoPanel.add(new JLabel("Furnished(Y/N): "));
		infoPanel.add(furnished);
		infoPanel.add(new JLabel("City Quadrant"));
		infoPanel.add(quadrant);
		int result = JOptionPane.showConfirmDialog(null, infoPanel, "Property Information",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			return 1;
		}
		if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		return 0;

	}

	public boolean infoIsEmpty() {
		if (getID().isEmpty() || getAdd().isEmpty() || getHouse().isEmpty() || getBath().isEmpty() || getBed().isEmpty()
				|| getFurn().isEmpty() || getQuad().isEmpty())
			return true;
		return false;
	}

	public String getID() {
		return propID.getText();
	}

	public String getAdd() {
		return propAdd.getText();
	}

	public String getHouse() {
		return houseType.getText();
	}

	public String getBath() {
		return numBathrooms.getText();
	}

	public String getBed() {
		return numBedrooms.getText();
	}

	public String getFurn() {
		return furnished.getText();
	}

	public String getQuad() {
		return quadrant.getText();
	}

	public void writeText(String s) {
		text.append(s);
	}

	public void clearDisplay() {
		text.setText(null);
	}

	public int showIDDialogBox() {
		JPanel idPanel = new JPanel();
		idPanel.add(new JLabel("Please Enter ID number of Property you wish to pay for: "));
		idPanel.add(idbox);
		int result = JOptionPane.showConfirmDialog(null, idPanel, "ID Box", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			return 1;
		}
		if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		return 0;
	}

	public boolean idBoxIsEmpty() {
		if (idbox.getText().isEmpty())
			return true;
		return false;
	}

	public String getIdBox() {
		return idbox.getText();
	}

	public int showState() {
		JPanel statePanel = new JPanel();
		statePanel.add(new JLabel("Property ID: "));
		statePanel.add(id2);
		statePanel.add(new JLabel("New State(0 = Active, "
				+ "1 = Rented, "
				+ "2 = Cancelled, "
				+ "3 = Suspended"));
		statePanel.add(state);
		
		int result = JOptionPane.showConfirmDialog(null, statePanel, "New State", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			return 1;
		}
		
		if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		
		return 0;	
	}
	
	public boolean stateIsEmpty() {
		if(id2.getText().isEmpty() || state.getText().isEmpty())
			return true;
		return false;
	}

	public String getStateID() {
		return id2.getText();
	}

	public String getNewState() {
		return state.getText();
	}



}
