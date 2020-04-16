package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Application;
import Model.Criteria;
import View.GUIChoice;
import View.GUIView;
import View.LandlordView;

public class LandlordController {
	LandlordView landlord;
	Application app;

	LandlordController(GUIView view, Application model) {
		landlord = (LandlordView) view;
		app = model;

		landlord.addRegisterListener(new RegisterListener());
		landlord.addPayistener(new PayFeeListener());
		landlord.addStateListener(new StateListener());
		landlord.addInfoListener(new InfoListener());
		landlord.addListListener(new ListListener());
		landlord.addLogoutListener(new LogoutListener());
		landlord.addEmailListener(new EmailListener());

	}
	
	
	class EmailListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Email landlord");
			String list = "";
			list = app.getLandlordEmails();
			String[] split = list.split("\\*");
			landlord.clearDisplay();
			for (int i = 0; i < split.length; i++) {
				landlord.writeText(split[i]);
				landlord.writeText("\n");
			}
		}
		
	}
	
	class LogoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			landlord.setVisible(false);
			GUIChoice choice = new GUIChoice();
			Controller controller = new Controller(choice, app);
			choice.setVisible(true);
		}
	}

	
	class InfoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!landlord.infoBoxIsEmpty()) {
				String fname = landlord.getFname();
				String lname = landlord.getLname();
				String email = landlord.getEmail();
				app.newLandlord(fname, lname, email);
				landlord.closeInfoBox();
				landlord.display();
			} else
				landlord.showMessage("One or more fields empty!");

		}
	}

	class ListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("List all");
			String text = app.getLandlordListings();
			String[] split = text.split("\\*");
			landlord.clearDisplay();
			for (int i = 0; i < split.length; i++) {
				landlord.writeText(split[i]);
				landlord.writeText("\n");
			}
		}

	}

	class RegisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("LL register listener");
			int info = landlord.propertyInfo();
			if (info == 1 && landlord.infoIsEmpty() == false) {
				int furnished = 0;
				String id = landlord.getID();
				String address = landlord.getAdd();
				String houseType = landlord.getHouse();
				int bath = Integer.parseInt(landlord.getBath());
				int bed = Integer.parseInt(landlord.getBed());
				String furn = landlord.getFurn();
				String quad = landlord.getQuad();
				if (furn.compareTo("Y") == 0)
					furnished = 2;
				else if (furn.compareTo("N") == 0)
					furnished = 1;
				Criteria c = new Criteria(houseType, bed, bath, furnished, quad);
				int checkReg = app.registerNewProperty(id, address, c);
				if (checkReg == 0)
					landlord.showMessage("Please enter a valid ID");
				if (checkReg == 1)
					landlord.showMessage("Property ID already taken, please try a different ID");
				if (checkReg == 2)
					landlord.showMessage("Register Successful!");

			} else if (info == 1 && landlord.infoIsEmpty() == true) {
				landlord.showMessage("One or more fields are missing!");
			}
		}
	}

	class PayFeeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("LL pay listener");
			int result = landlord.showIDDialogBox();
			if (result == 1 && landlord.idBoxIsEmpty() == false) {
				String id = landlord.getIdBox();
				boolean check = app.pay(id);
				if (check == false)
					landlord.showMessage("Error. Property is either already paid or you do not own such property!");
				else if (check == true)
					landlord.showMessage("Successful!");
			} else if (result == 1 && landlord.idBoxIsEmpty() == true) {
				landlord.showMessage("Invalid field");
			}

		}
	}

	class StateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("LL change state listener");
			int result = landlord.showState();
			if(result == 1 && landlord.stateIsEmpty() == false) {
				String id = landlord.getStateID();
				String newState = landlord.getNewState();
				int success = app.changeStateLandlord(id, newState);
				if(success == 0)
					landlord.showMessage("Please enter a number!");
				if(success == 1)
					landlord.showMessage("Please enter a valid number for state");
				if(success == 2)
					landlord.showMessage("Property is not owned by you or you havent paid for the property!");
				if(success == 3)
					landlord.showMessage("Successfully changes property state!");
				
			}
			
			else if(result == 1 && landlord.stateIsEmpty() == true) {
				landlord.showMessage("Invalid field(s)");
				
			}

		}

	}

}
