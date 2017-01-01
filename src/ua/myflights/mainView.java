package ua.myflights;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

public class mainView extends JFrame{

	private JFrame frmMyflights;
	private JLabel lblUsernameUser;
	private DestinationsModel myFlightsTableModel = new DestinationsModel();
	private SearchResultsTableModel searchResultsTableModel = new SearchResultsTableModel();
	public SearchResultsView searchResultsView = new SearchResultsView();
	public DestinationsView destinationsView = new DestinationsView();
	
	static mainView window;
	
	public mainView(){
		initialize();
	}
	
	private void initialize(){

		frmMyflights = new JFrame();
		frmMyflights.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMyflights.getContentPane().setLayout(new MigLayout());
		frmMyflights.setTitle("myFlights");
		
		lblUsernameUser = new JLabel("Logged out");
		
		frmMyflights.add(lblUsernameUser, "cell 0 0");
		frmMyflights.add(new SearchFormView().getPanel(), "cell 0 1");
		frmMyflights.add(new LoginView().getPanel(), "cell 0 1, wrap");
		frmMyflights.add(searchResultsView.getPanel(), "wrap, pushx, growx");
		frmMyflights.add(destinationsView.getPanel(), "pushx, growx");
	
		frmMyflights.pack();
		frmMyflights.setVisible(true);
		
	}
	
	/*
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new mainView2();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}*/

	public DestinationsModel getMyFlightsTableModel() {
		return myFlightsTableModel;
	}

	public SearchResultsTableModel getSearchResultsTableModel() {
		return searchResultsTableModel;
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
		///fromBoxModel.addPlaces(suggestedPlaces);		
	}
	
	public void showLoggedInUsername(String username){
		lblUsernameUser.setVisible(true);
		lblUsernameUser.setText("Logged in as: " + username);
	}	
	
	public void hideLoggedInUsername(){
		lblUsernameUser.setText("Logged out");
	}
	
	public void refreshDestinationTable(){
		System.out.println("ref mainView method");
		myFlightsTableModel.refresh();
	}
	
	
	
	
}
