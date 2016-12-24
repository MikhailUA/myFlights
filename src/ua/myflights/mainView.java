package ua.myflights;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.json.simple.parser.ParseException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class mainView {

	private JFrame frmMyflights;
	private JTextField placeTo;
	private JTextField placeFrom;
	private JLabel lblTo;
	private JLabel lblFrom;
	private JList list;
	private JTextField dateFrom;
	private JLabel lblSearchResults;
	private JLabel lblCity;
	private JLabel lblDate;
	private JButton btnAddDestination;
	private JLabel lblUsernameUser;
	
	private JTable searchResultsTable;
	private static SearchResultsTableModel searchResultsTableModel = new SearchResultsTableModel();
	
	private JTable myFlightsTable;
	private static DestinationsModel myFlightsTableModel = new DestinationsModel();
	
	private static SearchBoxModel fromBoxModel = new SearchBoxModel();
	private JPanel mainPanel;
	private JTextField loginField;
	private JPasswordField passwordField;
	private JButton btnLogout;
	private JButton btnLogin;
	private JProgressBar progressBar; 

	/**
	 * Create the application.
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	public mainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMyflights = new JFrame();
		frmMyflights.setTitle("myFlights");
		frmMyflights.setBounds(100, 100, 633, 527);
		frmMyflights.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMyflights.getContentPane().setLayout(null);
		//frmMyflights.getContentPane().setLayout(new CardLayout(0,0));

		frmMyflights.setVisible(true);
		
		JPanel mainPanel = new JPanel();
		//JPanel loginPanel = new JPanel();
		frmMyflights.getContentPane().add(mainPanel);	
		//mainPanel.setLayout(null);
		
		placeTo = new JTextField();
		placeTo.setBounds(146, 56, 86, 20);
		frmMyflights.getContentPane().add(placeTo);
		//mainPanel.add(placeTo);
		placeTo.setColumns(10);
		placeTo.setText("HRK-sky");
		
		placeFrom = new JTextField();
		placeFrom .setBounds(50, 56, 86, 20);
		frmMyflights.getContentPane().add(placeFrom );
		//mainPanel.add(placeFrom );
		placeFrom .setColumns(10);
		placeFrom.setText("KIEV-sky");
		
		dateFrom = new JTextField();
		dateFrom.setBounds(50, 80, 86, 20);
		frmMyflights.getContentPane().add(dateFrom);
		//mainPanel.add(dateFrom);
		dateFrom.setColumns(10);
		dateFrom.setText("2016-12-25");
		
		lblSearchResults = new JLabel("Search results:");
		lblSearchResults.setBounds(49, 111, 118, 20);
		frmMyflights.getContentPane().add(lblSearchResults);
		//mainPanel.add(lblSearchResults);
		
		this.progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setBounds(141, 116, 456, 10);
		frmMyflights.getContentPane().add(progressBar);
		progressBar.setVisible(false);
		
		lblCity = new JLabel("City");
		lblCity.setBounds(15, 56, 25, 20);
		frmMyflights.getContentPane().add(lblCity);
		//mainPanel.add(lblCity);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(15, 80, 30, 20);
		frmMyflights.getContentPane().add(lblDate);
		//mainPanel.add(lblDate);
		
		btnAddDestination = new JButton("Add Destination");
		btnAddDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date(0);
				if (MyFlights.getLoggedInUser() != null){
					int[] a = new int[5];
					a = searchResultsTable.getSelectedRows();
					Flight fSelected = searchResultsTableModel.getRowById(a[0]);
					myFlightsTableModel.addFlight(fSelected);
					DestinationController.addDestionation(fSelected);
				}else{
					System.out.println("Please log in");
				}
			}
		});
		btnAddDestination.setBounds(50, 235, 130, 23);
		frmMyflights.getContentPane().add(btnAddDestination);
		//mainPanel.add(btnAddDestination);
		
		lblUsernameUser = new JLabel("Logged out");
		lblUsernameUser.setBounds(50, 11, 150, 20);
		frmMyflights.getContentPane().add(lblUsernameUser);
		//mainPanel.add(lblUsernameUser);
		lblUsernameUser.setVisible(true);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					SearchController.searchFlights(placeFrom.getText(), placeTo.getText(), dateFrom.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnSearch.setBounds(242, 55, 91, 23);
		frmMyflights.getContentPane().add(btnSearch);
		//mainPanel.add(btnSearch);
		
		lblTo = new JLabel("To:");
		lblTo.setBounds(146, 33, 86, 20);
		frmMyflights.getContentPane().add(lblTo);
		//mainPanel.add(lblTo);
		
		lblFrom = new JLabel("From:");
		lblFrom.setBounds(50, 33, 86, 20);
		frmMyflights.getContentPane().add(lblFrom);
		//mainPanel.add(lblFrom);
		
		list = new JList();
		list.setBounds(50, 121, 390, 50);

		searchResultsTable = new JTable(searchResultsTableModel);
		//searchResultsTable.setFillsViewportHeight(true);
		JScrollPane SRcontainer = new JScrollPane(searchResultsTable);
		SRcontainer.setBounds(49, 130, 548, 100);
		frmMyflights.getContentPane().add(SRcontainer);
		//mainPanel.add(SRcontainer);
		
		myFlightsTable = new JTable(myFlightsTableModel);
		JScrollPane myFcontainer = new JScrollPane(myFlightsTable);
		myFcontainer.setBounds(50, 299, 547, 100);
		frmMyflights.getContentPane().add(myFcontainer);
		//mainPanel.add(myFcontainer);
		
		JLabel lblMyDestinations = new JLabel("My Destinations:");
		lblMyDestinations.setBounds(50, 281, 118, 14);
		frmMyflights.getContentPane().add(lblMyDestinations);
		//mainPanel.add(lblMyDestinations);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(50, 405, 117, 23);
		frmMyflights.getContentPane().add(btnRefresh);
		//mainPanel.add(btnRefresh);
		
		btnRefresh.addActionListener(new ActionListener(){
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DestinationController.refreshDestinations(MyFlights.getLoggedInUser().getId());
				}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(174, 405, 117, 23);
		frmMyflights.getContentPane().add(btnDelete);
		//mainPanel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				int[] a = new int[5];
				a = myFlightsTable.getSelectedRows();
				Flight fSelected = myFlightsTableModel.getRowById(a[0]);
				
				myFlightsTableModel.deleteSelected(myFlightsTable.getSelectedRow());
				DestinationController.deleteDestination(fSelected.getDistId(), MyFlights.getLoggedInUser().getId());
			}
			
		});
		
		JComboBox comboBox = new JComboBox(fromBoxModel);		
		comboBox.setEditable(true);
		comboBox.setBounds(242, 79, 91, 22);		
		frmMyflights.getContentPane().add(comboBox);
		//mainPanel.add(comboBox);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(424, 14, 59, 14);
		frmMyflights.getContentPane().add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(424, 36, 69, 17);
		frmMyflights.getContentPane().add(lblPassword);
		
		loginField = new JTextField("admin");
		loginField.setBounds(493, 11, 86, 20);
		frmMyflights.getContentPane().add(loginField);
		loginField.setColumns(10);
		
		passwordField = new JPasswordField("pass");
		passwordField.setBounds(493, 33, 86, 20);
		frmMyflights.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(407, 55, 86, 23);
		frmMyflights.getContentPane().add(btnLogin);
		
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
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(493, 79, 86, 23);
		frmMyflights.getContentPane().add(btnRegister);

		btnRegister.addActionListener(new ActionListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserController.register(loginField.getText(), passwordField.getText());
			}
		});

		btnLogout = new JButton("Logout");
		btnLogout.setBounds(493, 55, 86, 23);
		frmMyflights.getContentPane().add(btnLogout);
		btnLogout.setVisible(true);
		
		btnLogout.addActionListener(new ActionListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserController.logout();
			}
		});
		
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String selected = comboBox.getSelectedItem().toString();
				try {
				    if (e.getStateChange() == ItemEvent.SELECTED) {
				    	SearchController.searchPlaces(selected);
			        }
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
	}

	public void show(ArrayList<Flight> flights) {
		if (flights == null){
			JOptionPane.showMessageDialog(null, "Service Unavailable. Too many requests");
		}else{
			searchResultsTableModel.addFlights(flights);
		}
	}
	
	public void showMyDestinations(ArrayList<Flight> flights) {
		if (flights != null){
			myFlightsTableModel.addFlights(flights);
		}
	}
	
	public void clearTables(){
		searchResultsTableModel.clear();
		myFlightsTableModel.clear();
	}
	
	
	public void updateSearchBox(ArrayList<Place> suggestedPlaces) {
		fromBoxModel.addPlaces(suggestedPlaces);		
	}
	
	public void showLoggedInUsername(String username){
		lblUsernameUser.setVisible(true);
		lblUsernameUser.setText("Logged in as: " + username);
	}	
	
	public void hideLoggedInUsername(){
		lblUsernameUser.setText("Logged out");
	}
	
	public void startProgressBar(){
		this.progressBar.setVisible(true);
	}
	
	public void stopProgressBar(){
		this.progressBar.setVisible(false);
	}
	
	public void refreshDestinationTable(){
		System.out.println("ref mainView method");
		myFlightsTableModel.refresh();
	}
	
}


