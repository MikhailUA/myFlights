package ua.myflights;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;

public class SearchResultsView extends JPanel {
	
	private JPanel panel;
	private JLabel lblSearchResults;
	private JProgressBar progressBar;
	private JButton btnAddDestination;
	private JTable searchResultsTable;
	private SearchResultsTableModel searchResultsTableModel;
	
	public SearchResultsView(){
		
		panel = new JPanel(new MigLayout("", "[]", "[][][]"));
		
		lblSearchResults = new JLabel("Search results:");	
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);		
		progressBar.setVisible(false);
				
		btnAddDestination = new JButton("Add Destination");
		
		searchResultsTableModel = new SearchResultsTableModel();
		searchResultsTable = new JTable(searchResultsTableModel);
		
		JScrollPane SRcontainer = new JScrollPane(searchResultsTable);
		panel.add(lblSearchResults, "cell 0 0");
		panel.add(progressBar, "cell 0 0, growx");
		panel.add(btnAddDestination, "cell 0 2");
		panel.add(SRcontainer, "cell 0 1, growx, height 200:200:200");		
		
		btnAddDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date(0);
				if (MyFlights.getLoggedInUser() != null){
					int[] a = new int[5];
					a = searchResultsTable.getSelectedRows();
					Flight fSelected = searchResultsTableModel.getRowById(a[0]);
					MyFlights.window.destinationsView.myFlightsTableModel.addFlight(fSelected);
					DestinationController.addDestionation(fSelected);
				}else{
					System.out.println("Please log in");
				}
			}
		});
	}
	
	
	public Dimension getPreferredSize(){
		return new Dimension(panel.getPreferredSize().width,panel.getPreferredSize().height);
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void show(ArrayList<Flight> flights) {
		if (flights == null){
			JOptionPane.showMessageDialog(null, "Service Unavailable. Too many requests");
		}else{
			searchResultsTableModel.addFlights(flights);
		}
	}
	
	
}
