package businessLogic;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.List;


import domain.Question;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import modelo.data.DataAccessHibernate;
import modelo.data.DataAccessInterface;

public class BLFacadeImplementation  implements BLFacade {
	DataAccessInterface dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		dbManager = new DataAccessHibernate();
	}
	
    public BLFacadeImplementation(DataAccessInterface da)  {
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		da.initializeDB();
		dbManager=da;		
	}

  
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		
		Question qry=null;
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
		 qry=dbManager.createQuestion(event,question,betMinimum);	
		
		return qry;
   }
   
   @Override
	public Event createEvent(String description, Date date) throws EventFinished, QuestionAlreadyExist {
		
		Event ev = dbManager.createEvent(description, date);
		return ev;
	}

    	
	public List<Event> getEvents(Date date)  {
		
		List<Event>  events=dbManager.getEvents(date);
		
		return events;
	}
	
    	
	 public void initializeBD(){
		dbManager.initializeDB();
	}

}

