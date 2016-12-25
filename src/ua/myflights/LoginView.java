package ua.myflights;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class LoginView extends JPanel {

	private JPanel panel;
	private JLabel lblLogin; 
	private JLabel lblPassword;
	private JTextField loginField;
	private JTextField passwordField;
	private JButton btnLogin;
	private JButton btnLogout;
	private JButton btnRegister;
	
	
	
	public LoginView(){
		
		panel = new JPanel();
		panel.setLayout(new MigLayout("wrap 2"));
		
		lblLogin = new JLabel("Login: ");
		lblPassword = new JLabel("Password: ");
		
		loginField = new JTextField();
		passwordField = new JTextField();
		
		btnLogin = new JButton("Login");
		btnLogout = new JButton("Logout");
		btnRegister = new JButton("Register");	
		
		panel.add(lblLogin);
		panel.add(loginField, "growx");

		panel.add(lblPassword);
		panel.add(passwordField, "growx");
		
		panel.add(btnLogin, "growx");
		panel.add(btnLogout);
		panel.add(btnRegister);
		
		
		btnLogin.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					UserController.login(loginField.getText(), passwordField.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		btnRegister.addActionListener(new ActionListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserController.register(loginField.getText(), passwordField.getText());
			}
		});

	
		btnLogout.addActionListener(new ActionListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserController.logout();
			}
		});
		
	} 
	
		
	public Dimension getPreferredSize(){
		return new Dimension(panel.getPreferredSize().width,panel.getPreferredSize().height);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
