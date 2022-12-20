package modelo.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import configuration.UtilDate;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import modelo.bean.HibernateUtil;

public class DataAccessHibernate implements DataAccessInterface {

	@Override
	public void initializeDB() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}
			System.out.println("---------------------------------------------------------------------------");
			Event ev1 = createEvent("Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = createEvent("Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = createEvent("Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = createEvent("Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = createEvent("Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = createEvent("Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = createEvent("Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = createEvent("Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = createEvent("Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = createEvent("Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = createEvent("Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = createEvent("Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = createEvent("Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = createEvent("Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = createEvent("Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = createEvent("Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = createEvent("Málaga-Valencia", UtilDate.newDate(year, month, 28));
			Event ev18 = createEvent("Girona-Leganés", UtilDate.newDate(year, month, 28));
			Event ev19 = createEvent("Real Sociedad-Levante", UtilDate.newDate(year, month, 28));
			Event ev20 = createEvent("Betis-Real Madrid", UtilDate.newDate(year, month, 28));
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("---------------------------------------------------------------------------");
			createQuestion(ev1, "¿Quién ganará el partido?", 1);
			createQuestion(ev1, "¿Quién meterá el primer gol?", 2);
			createQuestion(ev11, "¿Quién ganará el partido?", 1);
			createQuestion(ev11, "¿Cuántos goles se marcarán?", 2);
			createQuestion(ev17, "¿Quién ganará el partido?", 1);
			createQuestion(ev17, "¿Habrá goles en la primera parte?", 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		// TODO Auto-generated method stub
		if(!existQuestion(event,question)) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Question pregunta = new Question(question, betMinimum, event);
			pregunta.setEvent(event);
			session.save(pregunta);
			session.getTransaction().commit();
			System.out.println("Pregunta "+question+" creada");
			System.out.println("---------------------------------------------------------------------------");
			return pregunta;
		}else {
			return null;
		}
	}
	
	@Override
	public Event createEvent(String description, Date date) throws EventFinished, QuestionAlreadyExist {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Event evento = new Event(description, date);
		session.save(evento);
		session.getTransaction().commit();
		System.out.println("Evento "+description+" creado");
		System.out.println("---------------------------------------------------------------------------");
		return evento;
	}

	@Override
	public List<Event> getEvents(Date eventDate) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("select e from Event e where e.eventDate = :eventDate");
		q.setParameter("eventDate", eventDate);
		List<Event> resultado = q.list();
		session.getTransaction().commit();
		return resultado;
	}
	@Override
	public List<Question> getQuestions(Event evento) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("select e from Question e where e.event.eventNumber = :eventNumber");
		q.setParameter("eventNumber", evento.getEventNumber());
		List<Question> resultado = q.list();
		session.getTransaction().commit();
		return resultado;
	}

	@Override
	public boolean existQuestion(Event event, String question) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("select e from Question e where e.event.eventNumber = :eventNumber AND e.question = :questionString");
		q.setParameter("eventNumber", event.getEventNumber());
		q.setParameter("questionString", question);
		List<Question> resultado = q.list();
		session.getTransaction().commit();
		return !resultado.isEmpty();
		
	}
}
