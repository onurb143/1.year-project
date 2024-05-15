package GUI;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollPane;
import database.*;
import model.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainMenu extends JDialog {
	private List<Foderplan> foderplaner;
	private FoderplanDBIF foderplanDB;
	private final JPanel panel = new JPanel();
	private DefaultListModel<String> foderplanList;
	
	public MainMenu() {
		
		
		try {
			foderplanDB = new FoderplanDB();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		try {
			foderplaner = foderplanDB.hentFoderplaner();
		} catch (DataAccessException e) {

			e.printStackTrace();
		}
		setTitle("Stald Gane - Main Menu");
		getContentPane().setLayout(null);
		getContentPane().setLayout(new GridLayout(1, 0,0,0));
		getContentPane().add(panel);
		setSize(650, 450);
		panel.setLayout(null);
	
	
		
		JLabel Title = new JLabel("Opret Ny Foderplan");
		Title.setFont(new Font("Tahoma", Font.BOLD, 14));
		Title.setBounds(10, 11, 146, 25);
		panel.add(Title);
		
		JButton btnNewButton = new JButton("Ny plan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrerFoderplan registrerFoderplan = new RegistrerFoderplan();
				/*DISPOSE_ON_CLOSE er en konstant i klassen JDialog som gør at vinduet 
				//frigiver alle ressourcer og lukkes, når brugeren klikker på krydset i øverste
				// højre hjørne på det oprettede vindue af objektet registrerFoderplan
				*/
		        registrerFoderplan.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        registrerFoderplan.setModal(true);
		        registrerFoderplan.pack();
		        registrerFoderplan.setVisible(true);
			}
		});
		btnNewButton.setBounds(31, 47, 89, 23);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 84, 384, 266);
		panel.add(scrollPane);
		
		JLabel FoderplanTitle = new JLabel("Oprettede Foderplaner");
		FoderplanTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		scrollPane.setColumnHeaderView(FoderplanTitle);
		
		foderplanList = new DefaultListModel<>();
		fetchFoderplaner();
		
		JList<String> fpList = new JList<>(foderplanList);
		scrollPane.setViewportView(fpList);
	}

	private void fetchFoderplaner() {
	    try {
	        foderplaner = foderplanDB.hentFoderplaner();
	        for (Foderplan fp : foderplaner) {
	            foderplanList.addElement("Foderplan | ID " + fp.getID());
	        }
	        fetchUpdater();
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private void fetchUpdater() throws DataAccessException, SQLException {
	    List<Foderplan> newFoderplaner = foderplanDB.hentFoderplaner();
	    Foderplan latestObject = foderplaner.get(foderplaner.size() - 1);
	    for (Foderplan fp : newFoderplaner) {
	    	if (latestObject.getID() < fp.getID()) {
	    		foderplaner.add(fp);
	    		foderplanList.addElement("Foderplan | ID " + fp.getID());
	    	}
	    }
	    new Thread(() -> {
	        try {
	            waitThreeSeconds();
	        } catch (InterruptedException | DataAccessException | SQLException e) {
	            e.printStackTrace();
	        }
	    }).start();
	}

	private void waitThreeSeconds() throws InterruptedException, DataAccessException, SQLException {
	    Thread.sleep(3000);
	    fetchUpdater();
	}

}
