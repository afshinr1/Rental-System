package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Application;
import View.GUIView;
import View.RenterView;

public class RenterController {
	RenterView renterView;
	Application app;

	public RenterController(GUIView renter, Application model) {
		renterView = (RenterView) renter;
		app = model;

		renterView.addListListener(new ListListener());
		renterView.addSearchListener(new SearchListener());
		renterView.addEmailListener(new EmailListener());
		renterView.addRentListener(new RentListener());

	}

	class RentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Rent");
			int result = renterView.showIDBox();
			if (result == 1 && renterView.IDBoxIsEmpty() == false) {
				String id = renterView.getIDBox();
				boolean check = app.checkID(id);
				if (check == false)
					renterView.showMessage("Invalid ID or ID does not exist");
				else if (check == true) {
					boolean rented = app.rent(id);
					if (rented == true)
						renterView.showMessage("Rent Successful!");
					else
						renterView.showMessage("Rent Unuccessful, already rented!!");

				}
			} else if (result == 1 && renterView.IDBoxIsEmpty() == true) {
				renterView.showMessage("One or more fields missing");
			}

		}
	}

	class ListListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("List all");
			String text = app.getAllListings();
			String[] split = text.split("\\*");
			renterView.clearDisplay();
			for (int i = 0; i < split.length; i++) {
				renterView.writeText(split[i]);
				renterView.writeText("\n");
			}
		}
	}

	class SearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Search");
			int search = renterView.createSearchPanel();
			String info = renterView.getSearchInfo();
			if (search == 1) {
				String searchedInfo = app.searchProperty(info);

				String[] split = searchedInfo.split("\\*");
				renterView.clearDisplay();
				for (int i = 0; i < split.length; i++) {
					renterView.writeText(split[i]);
					renterView.writeText("\n");
				}
			}
		}

	}

	class EmailListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Email");
			int result = renterView.showIDBox();
			if (result == 1 && renterView.IDBoxIsEmpty() == false) {
				String id = renterView.getIDBox();
				boolean check = app.checkID(id);
				if (check == false)
					renterView.showMessage("Invalid ID or ID does not exist");
				else if (check == true) {
					int mess = renterView.showMessageBox();
					if (mess == 1 && renterView.messageIsEmpty() == false) {
						String message = renterView.getMessage();
						app.sendMessage(Integer.parseInt(id), message);
						renterView.showMessage("Message Successfully sent!");
					}
				}
			} else if (result == 1 && renterView.IDBoxIsEmpty() == true) {
				renterView.showMessage("One or more fields missing");
			}
		}
	}
}
