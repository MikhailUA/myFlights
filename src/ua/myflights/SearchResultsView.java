package ua.myflights;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
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
	
	
	public SearchResultsView(){
		
		panel = new JPanel(new MigLayout("", "[]", "[][][]"));
		
		lblSearchResults = new JLabel("Search results:");	
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		
		progressBar.setVisible(false);
				
		btnAddDestination = new JButton("Add Destination");
		
		JScrollPane SRcontainer = new JScrollPane(searchResultsTable);
		
		panel.add(lblSearchResults, "cell 0 0");
		panel.add(progressBar, "cell 0 0, growx");
		panel.add(btnAddDestination, "cell 0 2");
		panel.add(SRcontainer, "cell 0 1,growx,height 100:100:100");
		
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
	}
	
	
	public Dimension getPreferredSize(){
		return new Dimension(panel.getPreferredSize().width,panel.getPreferredSize().height);
	}
	
	public JPanel getPanel() {
		return panel;
	}

}
