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
	public DestinationsModel myFlightsTableModel = new DestinationsModel();

	
	
	public DestinationsView(){
		
		panel = new JPanel(new MigLayout("", "[]", "[][][]"));
		
		lblMyDestinations = new JLabel("My Destinations:");	
						
		btnDelete = new JButton("Delete");		
		btnRefresh = new JButton("Refresh");
		
		myFlightsTable = new JTable(myFlightsTableModel);
		JScrollPane myFcontainer = new JScrollPane(myFlightsTable);
		panel.add(myFcontainer);
		
		panel.add(lblMyDestinations, "cell 0 0");
		panel.add(btnRefresh, "cell 0 2");
		panel.add(btnDelete, "cell 0 2");
		
		panel.add(myFcontainer, "cell 0 1,growx,height 100:100:100");
		
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
