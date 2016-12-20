package ua.myflights;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

class SearchResultsTableModel extends AbstractTableModel{

	private String[] columnNames = {"From", "To", "Price", "Duration", "Departure", "Arrival"};
	private ArrayList<Flight> flights;

	public SearchResultsTableModel(){
		flights = new ArrayList<Flight>();
	}
	
	public void addFlights(ArrayList<Flight> flightsFounded){
		flights = flightsFounded;
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
		return flights.size();
	}

	@Override
	public Object getValueAt(int row, int column) {

		Flight fl = flights.get(row);
		Object value = null;
		
		switch (column){
			case 0:
				value = fl.getOriginStationName();
			break;
			case 1:
				value = fl.getDestinationStationName();
			break;
			case 2:
				value = fl.getPrice();
			break;
			case 3:
				value = fl.getDuration();
			break;
			case 4:
				value = fl.getDepartureTime();
			break;
			case 5:
				value = fl.getArrivalTime();
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
	
	public void clear(){
		flights.clear();
		fireTableDataChanged();
	}
	
	
}  