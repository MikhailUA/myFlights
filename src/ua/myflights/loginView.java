package ua.myflights;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class loginView {

	private JFrame frame;
	private JTextField loginField;
	private JPasswordField passwordField;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginView window = new loginView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	public loginView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 222, 232);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(10, 39, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 74, 85, 14);
		frame.getContentPane().add(lblPassword);
		
		loginField = new JTextField();
		loginField.setBounds(105, 36, 91, 20);
		frame.getContentPane().add(loginField);
		loginField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(105, 71, 91, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(10, 112, 91, 23);
		frame.getContentPane().add(btnRegister);

		btnRegister.addActionListener(new ActionListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserController.register(loginField.getText(), passwordField.getText());
			}
		});
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(105, 112, 91, 23);
		frame.getContentPane().add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				try {
					UserController.login(loginField.getText(), passwordField.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		
		JLabel lblRegisterloginForm = new JLabel("Register/Login");
		lblRegisterloginForm.setBounds(10, 11, 99, 14);
	
		frame.getContentPane().add(lblRegisterloginForm);
		
	}
}
