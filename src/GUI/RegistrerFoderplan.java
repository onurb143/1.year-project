package GUI;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import controller.FoderplanController;
import database.DataAccessException;
import database.FoderDB;
import database.HestDB;
import model.Foder;
import model.FoderLinje;
import model.Foderplan;
import model.Hest;
import com.toedter.calendar.JDateChooser;


public class RegistrerFoderplan extends JDialog {
	private final JPanel panel = new JPanel();
	private JCheckBox cbMorgen;
	private JCheckBox cbMiddag;
	private JCheckBox cbAften;
	private JTextField textField;
	private FoderLinje foderlinje;
	private List<FoderLinje> foderlinjer;
	private List<Hest> hesteListe;
	private FoderplanController fpc;
	private Foderplan fp;
	private JComboBox<String> comboBoxHest;
	private JComboBox<String> comboBoxFoder;
	private JComboBox<String> comboBoxTilføjetFoder;
	private LocalDate startDate;
	private LocalDate endDate;

	public RegistrerFoderplan() {
		JButton tilføjFoderKnap = new JButton("Tilføj foder til planen");
		JButton GodkendPlanKnap = new JButton("Godkend foderplan");
		JButton godkendDatoKnap = new JButton("Godkend tidsrum");
		JButton vælgHestKnap = new JButton("OK");
		vælgHestKnap.setEnabled(false);
		fp = null;
		fpc = new FoderplanController();
		foderlinjer = new ArrayList<>();
		HestDB hestDB = null;
		try {
			hestDB = new HestDB();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		try {
			hesteListe = hestDB.hentAlleHeste();
		} catch (DataAccessException e1) {

			e1.printStackTrace();
		}

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("Stald Gane - Registrering af foderplan");
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		getContentPane().add(panel);
		panel.setLayout(null);
		Dimension windowSize = new Dimension(475, 600);
		setPreferredSize(windowSize);

		JLabel lblStartDato = new JLabel("Start Dato:");
		lblStartDato.setBounds(11, 8, 207, 23);
		panel.add(lblStartDato);

		JLabel lblHyppighed = new JLabel("Hyppighed:");
		lblHyppighed.setBounds(11, 84, 207, 42);
		panel.add(lblHyppighed);

		JPanel hyppighedPanel = new JPanel();
		hyppighedPanel.setBounds(218, 84, 218, 42);
		hyppighedPanel.setLayout(new GridLayout(1, 0));
		cbMorgen = new JCheckBox("Morgen");
		cbMiddag = new JCheckBox("Middag");
		cbAften = new JCheckBox("Aften");
		hyppighedPanel.add(cbMorgen);
		hyppighedPanel.add(cbMiddag);
		hyppighedPanel.add(cbAften);
		panel.add(hyppighedPanel);

		JLabel lblHeste = new JLabel("Vælg Hest:");
		lblHeste.setBounds(11, 187, 113, 35);
		panel.add(lblHeste);

		JLabel lblFoder1 = new JLabel("Vælg Foder:");
		lblFoder1.setBounds(11, 233, 207, 42);
		panel.add(lblFoder1);

		JLabel lblMængde1 = new JLabel("Vælg Mængde:");
		lblMængde1.setBounds(11, 275, 207, 42);
		panel.add(lblMængde1);

		JLabel lblTilfjFoderTil = new JLabel("Tilføj foder til plan");
		lblTilfjFoderTil.setBounds(11, 317, 207, 42);
		panel.add(lblTilfjFoderTil);

		tilføjFoderKnap.setEnabled(false);
		tilføjFoderKnap.setBounds(218, 317, 218, 42);
		panel.add(tilføjFoderKnap);

		tilføjFoderKnap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Hent det valgte foder
				String valgtFoderNavn = (String) comboBoxFoder.getSelectedItem();
				
				// Check efter om der er angivet mængde af foder
				if (textField.getText().length() < 1) {
					JOptionPane.showMessageDialog(null, "Skal angive mængde af foder i vægt", "Fejl", JOptionPane.ERROR_MESSAGE);
				} else {
					boolean containsOnlyNumbers = true;
					
					for (int i = 0; i < textField.getText().length(); i++) {
			            char c = textField.getText().charAt(i);
			            if (c < '0' || c > '9') {
			                containsOnlyNumbers = false;
			                break;
			            }
			        }
					if (containsOnlyNumbers != true) {
						JOptionPane.showMessageDialog(null, "Skal angive vægt af foder i tal", "Fejl", JOptionPane.ERROR_MESSAGE);
					} else {
						// Hent mængden fra tekstfeltet
						int maengde = Integer.parseInt(textField.getText());
						
						// opret en ny foderlinje med foder og mængden
						FoderLinje fl = fpc.tilføjFoder(valgtFoderNavn, maengde);
						GodkendPlanKnap.setEnabled(true);
		
						// Tilføj foderlinjen til den eksisterende JBox liste over tilføjet foder
						String foderOgMaengde = valgtFoderNavn + " " + maengde + "gram";
						comboBoxTilføjetFoder.addItem(foderOgMaengde);
		
						// Nulstil tekstfeltet
						textField.setText("");
					}
				}
			}
		});
		
		JDateChooser startDateChooser = new JDateChooser();
		startDateChooser.setBounds(218, 8, 218, 23);
		panel.add(startDateChooser);
		
		JDateChooser endDateChooser = new JDateChooser();
		endDateChooser.setBounds(218, 42, 218, 23);
		panel.add(endDateChooser);

		GodkendPlanKnap.setEnabled(false);
		GodkendPlanKnap.setBounds(134, 431, 177, 42);
		panel.add(GodkendPlanKnap);

		GodkendPlanKnap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tilføjFoderKnap.setEnabled(false);
				GodkendPlanKnap.setEnabled(false);
				comboBoxFoder.setEnabled(false);
				textField.setEnabled(false);
				comboBoxTilføjetFoder.setEnabled(false);
				GodkendPlanKnap.setEnabled(false);
				try {
					fpc.godkendFoderplan();
					dispose();
				} catch (DataAccessException e1) {

					e1.printStackTrace();
				}
			}
		});

		comboBoxHest = new JComboBox<>();
		comboBoxHest.setEnabled(false);
		comboBoxHest.setBounds(218, 187, 218, 35);
		panel.add(comboBoxHest);

		try {
			for (Hest hest : hesteListe) {
				comboBoxHest.addItem(hest.getNavn());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		comboBoxFoder = new JComboBox<String>();
		comboBoxFoder.setEnabled(false);
		comboBoxFoder.setBounds(218, 233, 218, 35);
		panel.add(comboBoxFoder);

		try {
			FoderDB foderDB = new FoderDB();
			List<Foder> foderListe = foderDB.hentAltFoder();
			for (Foder foder : foderListe) {
				comboBoxFoder.addItem(foder.getFoderNavn());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		comboBoxTilføjetFoder = new JComboBox<String>();
		comboBoxTilføjetFoder.setEnabled(false);
		comboBoxTilføjetFoder.setBounds(218, 366, 218, 35);
		panel.add(comboBoxTilføjetFoder);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(218, 277, 218, 35);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblListeOverTilfjet = new JLabel("Liste over tilføjet foder");
		lblListeOverTilfjet.setBounds(11, 367, 207, 32);
		panel.add(lblListeOverTilfjet);

		godkendDatoKnap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (startDateChooser.getDate() == null || endDateChooser.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Skal input en periode", "Fejl", JOptionPane.ERROR_MESSAGE);
				} else {
					Date selectedStartDate = startDateChooser.getDate();
					Date selectedEndDate = endDateChooser.getDate();
					Instant instantOfStartDate = selectedStartDate.toInstant();
					Instant instantOfEndDate = selectedEndDate.toInstant();
					LocalDate startDate = instantOfStartDate.atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate endDate = instantOfEndDate.atZone(ZoneId.systemDefault()).toLocalDate();
					
					int hyppighed = getSelectedHyppighed();
					if (hyppighed < 1) {
						JOptionPane.showMessageDialog(null, "Skal minimum vælge 1 tidspunkt på dagen", "Fejl", JOptionPane.ERROR_MESSAGE);
					} else {
						fp = fpc.registrerFoderplan(startDate, endDate, hyppighed);
						if(fp == null) {
							JOptionPane.showMessageDialog(null, "Skal input korrekt datoer", "Fejl", JOptionPane.ERROR_MESSAGE);
						} else {
							startDateChooser.setEnabled(false);
							endDateChooser.setEnabled(false);
							cbMorgen.setEnabled(false);
							cbMiddag.setEnabled(false);
							cbAften.setEnabled(false);
							vælgHestKnap.setEnabled(true);
							comboBoxHest.setEnabled(true);
							godkendDatoKnap.setEnabled(false);
						}
					}
				}
			}
		});
		godkendDatoKnap.setBounds(247, 137, 148, 23);
		panel.add(godkendDatoKnap);

		vælgHestKnap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tilføj valgte hest
				Hest hest = null;
				String valgtHestNavn = (String) comboBoxHest.getSelectedItem();
				for (Hest h : hesteListe) {
					if (h.getNavn().equals(valgtHestNavn)) {
						hest = h;
					}
				}
				try {
					Hest res = null;
					res = fpc.tilføjHest(hest.getHestCHR());
					if (res == null) {
						JOptionPane.showMessageDialog(null, "Skal vælge en hest", "Fejl", JOptionPane.ERROR_MESSAGE);
					} else {
						vælgHestKnap.setEnabled(false);
						comboBoxHest.setEnabled(false);
						tilføjFoderKnap.setEnabled(true);
						comboBoxFoder.setEnabled(true);
						textField.setEnabled(true);
						comboBoxTilføjetFoder.setEnabled(true);
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		vælgHestKnap.setBounds(134, 193, 75, 23);
		panel.add(vælgHestKnap);
		
		JLabel lblSlutDato = new JLabel("Slut Dato:");
		lblSlutDato.setBounds(11, 42, 207, 23);
		panel.add(lblSlutDato);
	}

	private int getSelectedHyppighed() {
		int res = 0;
		if (cbMorgen.isSelected()) {
			res += 1;
		}
		if (cbMiddag.isSelected()) {
			res += 1;
		}
		if (cbAften.isSelected()) {
			res += 1;
		}
		return res;
	}
}
