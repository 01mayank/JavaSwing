package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import javax.swing.border.BevelBorder;

import constants.InventoryConstants;

public class Login {

	private static JFrame frame;
	private static JTextField username_field;
	private static JPasswordField password_field;
	private static final String usernameEmptyErrorKey = "Please Enter Username !";
	private static final String passwordEmptyErrorKey = "Please Enter Password !";
	private static final String invalidLoginDetailErrorKey = "Invalid Credentials !";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.CYAN);
		frame.getContentPane().setVisible(true);
		frame.setBounds(100, 100, 795, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setTitle(InventoryConstants.appTitle);
		Image icon = Toolkit.getDefaultToolkit().getImage(InventoryConstants.titleImg);
		frame.setIconImage(icon);
		
		JLabel lblPleaseLoginTo = new JLabel("Please login to access application");
		lblPleaseLoginTo.setFont(new Font("Algerian", Font.PLAIN, 17));
		lblPleaseLoginTo.setBounds(242, 38, 322, 29);
		frame.getContentPane().add(lblPleaseLoginTo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(143, 106, 490, 331);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(99, 57, 96, 25);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(99, 131, 96, 25);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		username_field = new JTextField();
		username_field.setBounds(240, 59, 149, 25);
		panel.add(username_field);
		username_field.setColumns(10);
		
		password_field = new JPasswordField();
		password_field.setBounds(240, 131, 149, 25);
		panel.add(password_field);
		password_field.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(181, 204, 111, 32);
		panel.add(btnLogin);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validateLogin();
			}
		});
	}
	
	
	public static void validateLogin() {
		if(username_field.getText().isEmpty())
			JOptionPane.showMessageDialog(null, usernameEmptyErrorKey);
		else if(password_field.getText().isEmpty())
			JOptionPane.showMessageDialog(null, passwordEmptyErrorKey);
		else {
			if(username_field.getText().equals(InventoryConstants.username) && password_field.getText().equals(InventoryConstants.password)) {
				frame.dispose();
				Home.showHomePage();
			}
			else
				JOptionPane.showMessageDialog(null, invalidLoginDetailErrorKey);
		}
	}
}
