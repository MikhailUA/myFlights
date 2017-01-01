package ua.myflights;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.parser.ParseException;

import net.miginfocom.swing.MigLayout;

public class SearchFormView extends JPanel {
	
	private JPanel panel;
	private JTextField placeTo;
	private JTextField placeFrom;
	private JLabel lblTo;
	private JLabel lblFrom;
	private JTextField dateFrom;
	private JButton btnSearch;
	private JLabel lblCity;
	private JLabel lblDate;
	
	public SearchFormView(){
		
		panel = new JPanel(new MigLayout("", "[35.00][85.00][85.00][]", "[][][]"));		
		
		placeTo = new JTextField();
		placeFrom = new JTextField();
		dateFrom = new JTextField();
		btnSearch = new JButton("Search");	
		lblCity = new JLabel("City");
		lblDate = new JLabel("Date");		
		lblTo = new JLabel("To:");
		lblFrom = new JLabel("From:");	
		
		panel.add(lblFrom, "cell 1 0");	
		panel.add(lblTo,   "cell 2 0");
		panel.add(lblCity, "cell 0 1");
		panel.add(lblDate, "cell 0 2");	

		panel.add(placeFrom, "cell 1 1, growx");
		panel.add(placeTo,   "cell 2 1, growx");
		
		panel.add(dateFrom,  "cell 1 2, growx");		
		panel.add(btnSearch, "cell 3 1");
		
		placeTo.setText("HRK-sky");
		placeFrom.setText("KIEV-sky");
		dateFrom.setText("2016-12-25");
		
		btnSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

					try {
						SearchController.searchFlights(placeFrom.getText(), placeTo.getText(), dateFrom.getText());
					} catch (IOException | InterruptedException
							| ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
