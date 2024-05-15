package GUI;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.MedarbejderController;
import model.Medarbejder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;


public class Login extends JDialog {

	private static final int JLabel = 0;
	private final JPanel contentPanel = new JPanel();
	private MedarbejderController controller;

	/**
	 * Create a dialog.
	 */
	public Login() {
		setTitle(" Stald Gane - Login");
		getContentPane().setLayout(null);
		
		controller = new MedarbejderController();
		
		setBounds(2000, 500, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu kontrolpanel = new MainMenu();
		        kontrolpanel.setVisible(true);
		        dispose();
				}
			}
		);
		btnLogin.setBounds(163, 112, 89, 23);
		contentPanel.add(btnLogin);
		
//		JLabel lblMedarbejderId = new JLabel("");
//		lblMedarbejderId.setBounds(85, 121, 95, 14);
//		contentPanel.add(lblMedarbejderId);
		
		JLabel lblNewLabel = new JLabel("Stald Gane");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(154, 30, 136, 34);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("For dig som elsker heste ");
		lblNewLabel_1.setBounds(152, 73, 155, 14);
		contentPanel.add(lblNewLabel_1);
		
		

			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}
}
	
	
	
		
	

