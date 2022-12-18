package modelo.data;

import java.util.Date;
import java.util.List;

import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.QuestionAlreadyExist;

public class DataAccessJSF {
	
	DataAccess da = new DataAccess();
	public DataAccessJSF() {
		da.open();
	}
	
	public List<Event> getEvents(Date date){
		
		return da.getEvents(date);
	}
	
	public Question createQuestion(Event ev,String question, float betMinimum) throws QuestionAlreadyExist {
        return da.createQuestion(ev, question, betMinimum);
    }
	
}
