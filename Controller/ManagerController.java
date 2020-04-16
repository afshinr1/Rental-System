package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Application;
import View.GUIView;
import View.ManagerView;

public class ManagerController {

	
	ManagerView manager;
	Application app;
	
	
ManagerController(GUIView view, Application model){
	manager =  (ManagerView) view;
	app = model;
	
	manager.addChangeStateListener(new ChangeStateListener());
	manager.addSummaryListener(new SummaryListener());
	manager.addChangeFeeListener(new ChangeFeeListener());
	manager.addChangePeriodListener(new ChangePeriodListener());
	manager.addNewListener(new NewListener());
	manager.addExistListener(new ExistListener());
	manager.addListListener(new ListListener());
}
	class ChangeStateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("LL change state listener");
			int result = manager.showState();
			if(result == 1 && manager.stateIsEmpty() == false) {
				String id = manager.getStateID();
				String newState = manager.getNewState();
				int success = app.changeStateManager(id, newState);
				if(success == 0)
					manager.showMessage("Please enter a number!");
				if(success == 1)
					manager.showMessage("Please enter a valid number for state");
				if(success == 2)
					manager.showMessage("Property ID is invalid/Does not exist!!");
				if(success == 3)
					manager.showMessage("Successfully changes property state!");
				
			}
			
			else if(result == 1 && manager.stateIsEmpty() == true) {
				manager.showMessage("Invalid field(s)");
				
			}

		}
	}
	
	class ListListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("List all Manager");
			String text = app.getAllListingsManagers();
			String[] split = text.split("\\*");
			manager.clearDisplay();
			for (int i = 0; i < split.length; i++) {
				manager.writeText(split[i]);
				manager.writeText("\n");
			}
		}
	}
	
	class SummaryListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Summary");
			String list = app.getSummary();
			String[] split = list.split("\\*");
			manager.clearDisplay();
			for (int i = 0; i < split.length; i++) {
				manager.writeText(split[i]);
				manager.writeText("\n");
			}
			
		}
	}
	
	class ChangeFeeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Change fees");
			int result = manager.showFee();
			if(result == 1 && manager.feeIsEmpty() == false) {
				String id = manager.getFeeID();
				String newFee = manager.getNewFee();
				int success = app.changeFeeManager(id, newFee);
				if(success == 0)
					manager.showMessage("Please enter a number!");
				if(success == 2)
					manager.showMessage("Property ID is invalid/Does not exist!!");
				if(success == 3)
					manager.showMessage("Successfully changes property Fee!");
				
			}
			
			else if(result == 1 && manager.feeIsEmpty() == true) {
				manager.showMessage("Invalid field(s)");
				
			}

		}
	}
	
	class ChangePeriodListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Change Period");
			int result = manager.showPeriod();
			if(result == 1 && manager.periodIsEmpty() == false) {
				String id = manager.getPeriodID();
				String newPeriod = manager.getNewPeriod();
				int success = app.changePeriodManager(id, newPeriod);
				if(success == 0)
					manager.showMessage("Please enter a number!");
				if(success == 1)
					manager.showMessage("Please enter a valid number for Period");
				if(success == 2)
					manager.showMessage("Property ID is invalid/Does not exist!!");
				if(success == 3)
					manager.showMessage("Successfully changes property Period!");
				
			}
			
			else if(result == 1 && manager.feeIsEmpty() == true) {
				manager.showMessage("Invalid field(s)");
				
			}

		}
	}
	
	class NewListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("New RegRenter");
			int result = manager.displayNewUser();

			if (result == 1 && manager.newIsEmpty() == false) {

				String username = manager.getNewUsername();
				String password = manager.getNewPassword();
			boolean check = app.addNewManager(username, password);
				if(check == false) {
					manager.showMessage("Username already exists, please try another username");
					
				}
				else {
					manager.closeLogin();
					manager.display();
				}
			} else if (result == 1 && manager.newIsEmpty() == true) {
				manager.showMessage("One or more fields missing");
			}

		}
		
	}
	
	class ExistListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Exist manager");
			int result = manager.displayExistingUser();

			if (result == 1 && manager.existIsEmpty() == false) {
				String username = manager.getExistUsername();
				String password = manager.getExistPassword();
			
				
				boolean check = app.checkManagerExist(username, password);
				if (check == true) {
					manager.closeLogin();
					manager.display();
				} else {
					manager.showMessage("Incorrect username or password.");
				}
			}  if (result == 1 && manager.existIsEmpty() == true) 
				manager.showMessage("Missing one or more fields");
			
		
		}
		
	}

}
