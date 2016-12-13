package ua.myflights;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class SearchBoxModel implements ComboBoxModel{

	ArrayList <Place> Places;
	String [] PlacesSuggested = {"sdfs ", "sdfsddd"};	
	
	String selection = null;
	
	public SearchBoxModel (){
		Places = new ArrayList<Place>();	
	}
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		Place place = Places.get(index);	
		return place.getPlaceName();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return Places.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return selection;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		// TODO Auto-generated method stub
		selection = (String) anItem;
	}
	

	public void addPlaces(ArrayList<Place> suggestedPlaces) {
		// TODO Auto-generated method stub
		for (int i = 0; i < suggestedPlaces.size(); i++){
			System.out.println("d");
			PlacesSuggested[i] = suggestedPlaces.get(i).getPlaceName();
		}
	
	}
	
}
