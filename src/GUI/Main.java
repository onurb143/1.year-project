package GUI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import database.*;
import model.*;

public class Main {
	
	// * Launch the application.
		public static void main(String[] args) {
			try {
				DBConnection.getInstance().getConnection();
				Login dialog = new Login();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}