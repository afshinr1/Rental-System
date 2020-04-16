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

public class RegRenterView extends GUIView {

	private static final long serialVersionUID = 1L;

	private GUIChoice choice;
	
	private JPanel mainPanel;
	private JLabel rrTitle = new JLabel("Registered Renter Interface");
	private JTextArea text = new JTextArea();
	private JScrollPane textScroll = new JScrollPane(text);

	private JButton regSearch = new JButton("Search");
	private JButton email = new JButton("Email Landlord");
	private JButton list = new JButton("List All Properties");
	private JButton unsubscribe = new JButton("Unsubscribe");
	private JButton logout = new JButton("Logout");
	private JButton rent = new JButton("Rent");

	private JPanel loginPanel;
	private JLabel loginTitle = new JLabel("Please login with one of the following");
	private JButton newUser = new JButton("New User");
	private JButton existingUser = new JButton("Existing user");

	private JPanel newPanel;
	private JLabel newTitle = new JLabel("Please enter your desired username and password.");
	private JTextField newUsername = new JTextField(10);
	private JTextField newPassword = new JTextField(10);
	
	private JTextField messageDialogBox = new JTextField(20);


	private JPanel existPanel;
	private JLabel existTitle = new JLabel("Please enter your username and passord.");
	private JTextField existUsername = new JTextField(10);
	private JTextField existPassword = new JTextField(10);
	
	private JPanel searchPanel;
	private JTextField houseType = new JTextField(8);
	private JTextField numBedrooms = new JTextField(8);
	private JTextField numBathrooms = new JTextField(8);
	private JTextField furnished = new JTextField(8);
	private JTextField quadrant = new JTextField(8);
	
	private JTextField houseID = new JTextField(10);

	public RegRenterView(GUIChoice gc) {
		displayLogin();
		choice = gc;
	}

	@Override
	public void display() {
		mainPanel = new JPanel();
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.add(rrTitle);
		text.setEditable(false);
		textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textScroll.setPreferredSize(new Dimension(450, 365));

		mainPanel.add(textScroll);
		mainPanel.add(regSearch);
		mainPanel.add(email);
		mainPanel.add(list);
		mainPanel.add(unsubscribe);
		mainPanel.add(logout);
		mainPanel.add(rent);
		this.add(mainPanel);
	}

	public void displayLogin() {
		loginPanel = new JPanel();
		this.setSize(300, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loginPanel.add(loginTitle);
		loginPanel.add(newUser);
		loginPanel.add(existingUser);

		this.add(loginPanel);

	}

	public int displayNewUser() {
		newPanel = new JPanel();

		newPanel.add(newTitle);
		newPanel.add(newUsername);
		newPanel.add(newPassword);

		int result = JOptionPane.showConfirmDialog(null, newPanel, "", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			return 1;
		}
		if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		return 0;
	}

	public boolean newIsEmpty() {
		if (getNewUsername().isEmpty() || getNewPassword().isEmpty()) {
			return true;
		}
		return false;
	}

	public String getNewUsername() {
		return newUsername.getText();
	}

	public int displayExistingUser() {
		existPanel = new JPanel();

		existPanel.add(existTitle);
		existPanel.add(existUsername);
		existPanel.add(existPassword);

		int result = JOptionPane.showConfirmDialog(null, existPanel, "", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			return 1;
		}
		if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		return 0;
	}

	public void addLogoutListener(ActionListener e) {
		logout.addActionListener(e);
	}
	
	public void addSearchListener(ActionListener e) {
		regSearch.addActionListener(e);
	}

	public void addEmailListener(ActionListener e) {
		email.addActionListener(e);
	}

	public void addListListener(ActionListener e) {
		list.addActionListener(e);
	}

	public void addUnsubscribeListener(ActionListener e) {
		unsubscribe.addActionListener(e);
	}

	public void addNewListener(ActionListener e) {
		newUser.addActionListener(e);
	}

	public void addExistListener(ActionListener e) {
		existingUser.addActionListener(e);
	}
	
	public void addRentListener(ActionListener e) {
		rent.addActionListener(e);
	}

	public String getNewPassword() {
		return newPassword.getText();
		
		
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void closeLogin() {
		loginPanel.setVisible(false);
	}

	public boolean existIsEmpty() {

		if (getExistUsername().isEmpty() || getExistPassword().isEmpty()) {
			return true;
		}
		return false;
	}

	public String getExistUsername() {
		String s = existUsername.getText();
		return s;
	}

	public String getExistPassword() {
		String s = existPassword.getText();
		return s;
	}

	public void writeText(String string) {
		text.append(string);
	}

	public void clearDisplay() {
		text.setText(null);
	}

	public int createSearchPanel() {
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(3, 2));
		searchPanel.add(new JLabel("House Type:"));
		searchPanel.add(houseType);
		searchPanel.add(new JLabel("Number of BedRooms\n"));
		searchPanel.add(numBedrooms);
		searchPanel.add(new JLabel("Number of Bathrooms"));
		searchPanel.add(numBathrooms);
		searchPanel.add(new JLabel("Furnished(Y/N): "));
		searchPanel.add(furnished);
		searchPanel.add(new JLabel("City Quadrant"));
		searchPanel.add(quadrant);
		int result = JOptionPane.showConfirmDialog(null, searchPanel, "Search Property", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			return 1;
		}
		if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		return 0;	
	}

	public String getSearchInfo() {
		String info = "";
		info += houseType.getText() + " ";
		info += numBedrooms.getText() + " ";
		info += numBathrooms.getText() + " ";
		info += furnished.getText() + " ";
		info += quadrant.getText() + " ";
		
		return info;
	}

	public int showIDBox() {
		JPanel idBox = new JPanel();
		idBox.add(new JLabel("Please enter house ID: "));
		idBox.add(houseID);
		int result = JOptionPane.showConfirmDialog(null, idBox, "Send Email", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			return 1;
		}
		if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		return 0;
	}

	public boolean IDBoxIsEmpty() {
		if (houseID.getText().isEmpty())
			return true;

		return false;
	}

	public String getIDBox() {
		return houseID.getText();
	}
	
	public void displayChoice() {
		choice.setVisible(true);
	}
	
	public int showMessageBox() {
		JPanel messagePanel = new JPanel();
		messagePanel.add(new JLabel("Please enter your message: "));
		messagePanel.add(messageDialogBox);
		int result = JOptionPane.showConfirmDialog(null, messagePanel, "Message Dialog Box",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			return 1;
		}
		if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		return 0;
	}
	
	public boolean messageIsEmpty() {
		if (messageDialogBox.getText().isEmpty())
			return true;
		return false;
	}

	public String getMessage() {
		return messageDialogBox.getText();
	}
}
