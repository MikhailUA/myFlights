package ua.myflights;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.json.simple.parser.ParseException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

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
	private static SearchResultsTableModel myFlightsTableModel = new SearchResultsTableModel();
	

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
		frmMyflights.setVisible(true);
		
		placeTo = new JTextField();
		placeTo.setBounds(146, 56, 86, 20);
		frmMyflights.getContentPane().add(placeTo);
		placeTo.setColumns(10);
		
		placeFrom = new JTextField();
		placeFrom .setBounds(50, 56, 86, 20);
		frmMyflights.getContentPane().add(placeFrom );
		placeFrom .setColumns(10);		
		
		dateFrom = new JTextField();
		dateFrom.setBounds(50, 80, 86, 20);
		frmMyflights.getContentPane().add(dateFrom);
		dateFrom.setColumns(10);
		
		lblSearchResults = new JLabel("Search results:");
		lblSearchResults.setBounds(49, 111, 86, 20);
		frmMyflights.getContentPane().add(lblSearchResults);
		
		lblCity = new JLabel("City");
		lblCity.setBounds(15, 56, 25, 20);
		frmMyflights.getContentPane().add(lblCity);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(15, 80, 30, 20);
		frmMyflights.getContentPane().add(lblDate);
		
		btnAddDestination = new JButton("Add Destination");
		btnAddDestination.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int[] a = new int[5];
				a = searchResultsTable.getSelectedRows();
				myFlightsTableModel.addFlight(searchResultsTableModel.getRowById(a[0]));
			}
		});
		btnAddDestination.setBounds(50, 235, 117, 23);
		frmMyflights.getContentPane().add(btnAddDestination);
		
		lblUsernameUser = new JLabel("Username: user");
		lblUsernameUser.setBounds(511, 10, 104, 20);
		frmMyflights.getContentPane().add(lblUsernameUser);
		
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
		
		lblTo = new JLabel("To:");
		lblTo.setBounds(146, 33, 86, 20);
		frmMyflights.getContentPane().add(lblTo);
		
		lblFrom = new JLabel("From:");
		lblFrom.setBounds(50, 33, 86, 20);
		frmMyflights.getContentPane().add(lblFrom);
		
		list = new JList();
		list.setBounds(50, 121, 390, 50);

		searchResultsTable = new JTable(searchResultsTableModel);
		//searchResultsTable.setFillsViewportHeight(true);
		JScrollPane SRcontainer = new JScrollPane(searchResultsTable);
		SRcontainer.setBounds(49, 130, 390, 100);
	
		frmMyflights.getContentPane().add(SRcontainer);
		
		myFlightsTable = new JTable(myFlightsTableModel);
		JScrollPane myFcontainer = new JScrollPane(myFlightsTable);
		myFcontainer.setBounds(50, 299, 390, 100);
		
		frmMyflights.getContentPane().add(myFcontainer);
		
		JLabel lblMyDestinations = new JLabel("My Destinations:");
		lblMyDestinations.setBounds(50, 281, 118, 14);
		frmMyflights.getContentPane().add(lblMyDestinations);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(50, 405, 117, 23);
		frmMyflights.getContentPane().add(btnRefresh);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(174, 405, 117, 23);
		frmMyflights.getContentPane().add(btnDelete);
		
		btnDelete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				myFlightsTableModel.deleteSelected(myFlightsTable.getSelectedRow());
			}
			
		});
		
	}

	public static void show(ArrayList<Flight> flights) {
		searchResultsTableModel.addFlights(flights);
	}		
}


