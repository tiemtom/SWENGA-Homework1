package at.fh.swenga.model;

import java.util.ArrayList;
import java.util.List;
 
public class SmartphoneService {
 
	private List<SmartphoneModel> smartphones = new ArrayList<SmartphoneModel>();
 
	// add to list
	public void addSmartphone(SmartphoneModel smartphone) {
		smartphones.add(smartphone);
	}
 
	// verify if list contains smartphone with same id
	public boolean contains(SmartphoneModel smartphone) {
		return smartphones.contains(smartphone);
	}
 
	//true if is empty
	public boolean isEmpty() {
		return smartphones.isEmpty();
	}
 
	// find smartphone with given id
	public SmartphoneModel getSmartphoneById(int id) {
		for (SmartphoneModel SmartphoneModel : smartphones) {
			if (SmartphoneModel.getId() == id) {
				return SmartphoneModel;
			}
		}
		return null;
	}
 
	// list size
	public int getSize() {
		return smartphones.size();
	}
 
	// return whole list
	public List<SmartphoneModel> getAllSmartphones() {
		return smartphones;
	}
 
	// return list with smartphones where name or brand contains search string
	public List<SmartphoneModel> getFilteredSmartphones(String searchString) {
 
		if (searchString == null || searchString.equals("")) {
			return smartphones;
		}
 
		// List for results
		List<SmartphoneModel> filteredList = new ArrayList<SmartphoneModel>();
 
		// check every smartphone
		for (SmartphoneModel SmartphoneModel : smartphones) {
 
			if ((SmartphoneModel.getName() != null && SmartphoneModel.getName().contains(searchString))
					|| (SmartphoneModel.getBrand() != null && SmartphoneModel.getBrand().contains(searchString))) {
				filteredList.add(SmartphoneModel);
			}
		}
		return filteredList;
	}
 
	// remove smartphones with same id
	public boolean remove(int id) {
		return smartphones.remove(new SmartphoneModel(id, null, null, null, null));
	}
}