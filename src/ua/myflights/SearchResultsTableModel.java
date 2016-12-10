package ua.myflights;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

class SearchResultsTableModel extends AbstractTableModel{

	private String[] columnNames = {"From", "To","Price"};
	private ArrayList<Flight> flights;

	public SearchResultsTableModel(){
		flights = new ArrayList<Flight>();
	}
	
	public void addFlights(ArrayList<Flight> flightsFounded){
		flights.addAll(flightsFounded);
		fireTableDataChanged();
	}
	
	public void addFlight(Flight f){
		flights.add(f);
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
 	
	public Flight getRowById(int id){
		return flights.get(id);
	}
	 
	public void deleteSelected(int id){
		flights.remove(id);
		fireTableDataChanged();
	}
	
	
}  