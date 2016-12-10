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
	private JTable searchResultsTable;
	private static SearchResultsTableModel searchResultsTableModel;
	private JTextField dateFrom;
	private JLabel lblSearchResults;
	private JLabel lblCity;
	private JLabel lblDate;
	private JButton btnAddDestination;
	private JLabel lblUsernameUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainView window = new mainView();
					window.frmMyflights.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
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
		frmMyflights.setBounds(100, 100, 633, 465);
		frmMyflights.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMyflights.getContentPane().setLayout(null);
		
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
		btnAddDestination.setBounds(50, 235, 117, 23);
		frmMyflights.getContentPane().add(btnAddDestination);
		
		lblUsernameUser = new JLabel("Username: user");
		lblUsernameUser.setBounds(511, 10, 104, 20);
		frmMyflights.getContentPane().add(lblUsernameUser);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				try {
					SearchController.searchFlights(placeFrom.getText(),placeTo.getText(),dateFrom.getText());
					SearchController.showSearchResults();
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
	//	frame.getContentPane().add(list);
		
		searchResultsTableModel = new SearchResultsTableModel();
		searchResultsTable = new JTable(searchResultsTableModel);
		searchResultsTable.setFillsViewportHeight(true);
		JScrollPane container = new JScrollPane(searchResultsTable);
		container.setBounds(49, 130, 390, 100);
	
		frmMyflights.getContentPane().add(container);
		
	}

	public static void show(ArrayList<Flight> flights) {
		searchResultsTableModel.add(flights);
	}		
	
	
	public SearchResultsTableModel getSearchResultsTableModel(){
		return searchResultsTableModel;
	}
}


class SearchResultsTableModel extends AbstractTableModel{

	private String[] columnNames = {"From", "To","Price"};
	private ArrayList<Flight> flights;

	public SearchResultsTableModel(){
		flights = new ArrayList<Flight>();
	}
	
	public void add(ArrayList<Flight> flightsFounded){
		flights.addAll(flightsFounded);
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return flights.size();
	}

	@Override
	public Object getValueAt(int row, int column) {

		Flight fl = flights.get(row);
		Object value = null;
		
		switch (column){
			case 0:
				value = fl.getOriginPlace();
			break;
			case 1:
				value = fl.getDestinationPlace();
			break;
			case 2:
				value = fl.getPrice();
			break;
		}
		
		return value;
	}
	
	 public String getColumnName(int col) {
        return columnNames[col].toString();
    }
 	
}  