package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import Model.Application;
import View.GUIChoice;
import View.GUIView;
import View.RegRenterView;

public class RegRenterController {
	RegRenterView regRenter;
	Application app;

	RegRenterController(GUIView r, Application a) {
		regRenter = (RegRenterView) r;
		app = a;

		regRenter.addListListener(new ListListener());
		regRenter.addSearchListener(new SearchListener());
		regRenter.addEmailListener(new EmailListener());
		regRenter.addUnsubscribeListener(new UnsubscribeListener());
		regRenter.addExistListener(new ExistListener());
		regRenter.addNewListener(new NewListener());
		regRenter.addLogoutListener(new LogoutListener());
		regRenter.addRentListener(new RentListener());

	}

	class RentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Rent");
			int result = regRenter.showIDBox();
			if (result == 1 && regRenter.IDBoxIsEmpty() == false) {
				String id = regRenter.getIDBox();
				boolean check = app.checkID(id);
				if (check == false)
					regRenter.showMessage("Invalid ID or ID does not exist");
				else if (check == true) {
					boolean rented = app.rent(id);
					if (rented == true)
						regRenter.showMessage("Rent Successful!");
					else
						regRenter.showMessage("Rent Unuccessful, already rented!!");

				}
			} else if (result == 1 && regRenter.IDBoxIsEmpty() == true) {
				regRenter.showMessage("One or more fields missing");
			}

		}
	}

	class LogoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			regRenter.setVisible(false);
			regRenter.displayChoice();
		}
	}

	class ExistListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Existing RegRenter");
			int result = regRenter.displayExistingUser();

			if (result == 1 && regRenter.existIsEmpty() == false) {
				String username = regRenter.getExistUsername();
				String password = regRenter.getExistPassword();

				boolean check = app.checkRegRenterList(username, password);
				if (check == true) {
					regRenter.closeLogin();
					regRenter.display();
				} else {
					regRenter.showMessage("Incorrect username or password.");
				}
			} else if (result == 1 && regRenter.existIsEmpty() == true)
				regRenter.showMessage("Missing one or more fields");

		}

	}

	class NewListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("New RegRenter");
			int result = regRenter.displayNewUser();

			if (result == 1 && regRenter.newIsEmpty() == false) {

				String username = regRenter.getNewUsername();
				String password = regRenter.getNewPassword();
			boolean check = app.addNewRegRenter(username, password);
				if(check == false) {
					regRenter.showMessage("Username already exists, please try another username");
					
				}
				else {
					regRenter.closeLogin();
				regRenter.display();
				}
			} else if (result == 1 && regRenter.newIsEmpty() == true) {
				regRenter.showMessage("One or more fields missing");
			}

		}
	}

	class ListListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Reg List all");
			String text = app.getAllListings();
			String[] split = text.split("\\*");
			regRenter.clearDisplay();
			for (int i = 0; i < split.length; i++) {
				regRenter.writeText(split[i]);
				regRenter.writeText("\n");
			}
		}
	}

	class SearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Reg Search");
			int search = regRenter.createSearchPanel();
			String info = regRenter.getSearchInfo();
			if (search == 1) {
				String searchedInfo = app.searchProperty(info);

				String[] split = searchedInfo.split("\\*");
				regRenter.clearDisplay();
				for (int i = 0; i < split.length; i++) {
					regRenter.writeText(split[i]);
					regRenter.writeText("\n");
				}
			}
		}

	}

	class EmailListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Email");
			int result = regRenter.showIDBox();
			if (result == 1 && regRenter.IDBoxIsEmpty() == false) {
				String id = regRenter.getIDBox();
				boolean check = app.checkID(id);
				if (check == false)
					regRenter.showMessage("Invalid ID or ID does not exist");
				else if (check == true) {
					int mess = regRenter.showMessageBox();
					if (mess == 1 && regRenter.messageIsEmpty() == false) {
						String message = regRenter.getMessage();
						app.sendMessage(Integer.parseInt(id), message);
						regRenter.showMessage("Message Successfully sent!");
					}
				}
			} else if (result == 1 && regRenter.IDBoxIsEmpty() == true) {
				regRenter.showMessage("One or more fields missing");
			}
		}
	}

	class UnsubscribeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Unsubscribe");
			app.unsubscribe();
			regRenter.setVisible(false);
			regRenter.displayChoice();
			
		}
	}
}
