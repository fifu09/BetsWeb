package modelo.data;

import java.util.Date;
import java.util.List;

import dataAccess.DataAccess;
import domain.Event;

public class DataAccessJSF {
	
	DataAccess da = new DataAccess(false);
	public DataAccessJSF() {
		da.open();
	}
	
	public List<Event> getEvents(Date date){
		
		return da.getEvents(date);
	}
	
}
