package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import Model.Application;
import View.GUIChoice;
import View.GUIView;
import View.LandlordView;
import View.ManagerView;
import View.RegRenterView;
import View.RenterView;

public class Controller {
	private GUIChoice GUIChoice;
	private Application model;

	public Controller(GUIChoice choice, Application theModel) {
		GUIChoice = choice;
		model = theModel;

		GUIChoice.addLandLordListener(new LandLordListener());
		GUIChoice.addManagerListener(new ManagerListener());
		GUIChoice.addRenterListener(new RenterListener());
		GUIChoice.addRegRenterListener(new RegisteredListener());
	}

	class LandLordListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GUIChoice.setVisible(false);
			GUIView landlord = new LandlordView();
			LandlordController LLController = new LandlordController(landlord, model);
			landlord.setVisible(true);
		}

	}

	class ManagerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GUIChoice.setVisible(false);
			GUIView manager = new ManagerView();
			ManagerController MController = new ManagerController(manager, model);
			manager.setVisible(true);
		}
	}

	class RenterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GUIChoice.setVisible(false);
			GUIView renter = new RenterView();
			renter.setVisible(true);
			RenterController RController = new RenterController(renter, model);

		}
	}

	class RegisteredListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GUIChoice.setVisible(false);
			GUIView regRenter = new RegRenterView(GUIChoice);
			RegRenterController regController = new RegRenterController(regRenter, model);
			regRenter.setVisible(true);
		}
	}

	public static void main(String[] args) {
		
		GUIChoice choice = new GUIChoice();
		Application app = new Application();
		Controller control = new Controller(choice, app);
		choice.setVisible(true);
	}
}
