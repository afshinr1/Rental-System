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

public class RenterView extends GUIView {

	private JPanel mainPanel;
	private JLabel rTitle = new JLabel("Renter Interface");
	private JTextArea text = new JTextArea();
	private JScrollPane textScroll = new JScrollPane(text);

	private static final long serialVersionUID = 1L;
	private JButton search = new JButton("Search");
	private JButton email = new JButton("Email Landlord");
	private JButton list = new JButton("List All Properties");
	private JButton rent = new JButton("Rent");

	private JPanel searchPanel;
	private JTextField houseType = new JTextField(8);
	private JTextField numBedrooms = new JTextField(8);
	private JTextField numBathrooms = new JTextField(8);
	private JTextField furnished = new JTextField(8);
	private JTextField quadrant = new JTextField(8);

	private JTextField houseID = new JTextField(10);
	private JTextField messageDialogBox = new JTextField(20);

	public RenterView() {
		display();
	}

	@Override
	public void display() {
		mainPanel = new JPanel();
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.add(rTitle);
		text.setEditable(false);
		textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textScroll.setPreferredSize(new Dimension(450, 365));

		mainPanel.add(textScroll);
		mainPanel.add(search);
		mainPanel.add(email);
		mainPanel.add(list);
		mainPanel.add(rent);

		this.add(mainPanel);
	}

	public void addSearchListener(ActionListener e) {
		search.addActionListener(e);
	}

	public void addEmailListener(ActionListener e) {
		email.addActionListener(e);
	}

	public void addListListener(ActionListener e) {
		list.addActionListener(e);
	}

	public void addRentListener(ActionListener e) {
		rent.addActionListener(e);
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
		if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		return 0;
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
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

	public void writeText(String s) {
		text.append(s);
	}

	public void clearDisplay() {
		text.setText(null);
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
