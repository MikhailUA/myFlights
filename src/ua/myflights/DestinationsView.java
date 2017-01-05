package ua.myflights;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;

public class DestinationsView extends JPanel {
	
	private JPanel panel;
	private JLabel lblMyDestinations;
	private JProgressBar progressBar;
	private JButton btnDelete;
	private JButton btnRefresh;
	private JTable myFlightsTable;
	public DestinationsTableModel myFlightsTableModel = new DestinationsTableModel();
	
	public DestinationsView(){
		
		panel = new JPanel(new MigLayout());
		
		lblMyDestinations = new JLabel("My Destinations:");	
						
		btnDelete = new JButton("Delete");		
		btnRefresh = new JButton("Refresh");
		
		myFlightsTable = new JTable(myFlightsTableModel);
		
		myFlightsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		myFlightsTable.getColumnModel().getColumn(1).setPreferredWidth(30);
		myFlightsTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		myFlightsTable.getColumnModel().getColumn(3).setPreferredWidth(30);
		myFlightsTable.getColumnModel().getColumn(4).setPreferredWidth(30);
		
		JScrollPane myFcontainer = new JScrollPane(myFlightsTable);
		panel.add(myFcontainer);
		
		panel.add(lblMyDestinations, "wrap");
		panel.add(myFcontainer, "wrap, pushx, growx, height 150:150:150");
		
		panel.add(btnRefresh, "cell 0 2");
		panel.add(btnDelete, "cell 0 2");		
		
		btnRefresh.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DestinationController.refreshDestinations(MyFlights.getLoggedInUser().getId());
				}
		});
		
		btnDelete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int[] a = new int[5];
				a = myFlightsTable.getSelectedRows();
				Flight fSelected = myFlightsTableModel.getRowById(a[0]);
				
				myFlightsTableModel.deleteSelected(myFlightsTable.getSelectedRow());
				DestinationController.deleteDestination(fSelected.getDistId(), MyFlights.getLoggedInUser().getId());
			}
			
		});
		
	}
	
	
	public Dimension getPreferredSize(){
		return new Dimension(panel.getPreferredSize().width,panel.getPreferredSize().height);
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void showMyDestinations(ArrayList<Flight> flights) {
		if (flights != null){
			myFlightsTableModel.addFlights(flights);
		}
	}
	

}
