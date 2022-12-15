package modelo.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import domain.Event;
import domain.Question;
import modelo.data.DataAccessJSF;


public class Bean {
	private Date fecha;
	private String fechaString;
	private String nuevaPregunta;
	private double apuestaMinima;
	private Event evento;
	private Question pregunta;
	private DataAccessJSF da = new DataAccessJSF();
	private List<Event> eventos = new ArrayList<Event>();
	private List<Question> preguntas = new ArrayList<Question>();
	
	
	public Bean() {
		fecha = new Date();
		evento = new Event();
		pregunta = new Question();
	}
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getFechaString() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fecha);
	}
	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}
	public String getNuevaPregunta() {
		return nuevaPregunta;
	}
	public void setNuevaPregunta(String nuevaPregunta) {
		this.nuevaPregunta = nuevaPregunta;
	}
	public Event getEvento() {
		return evento;
	}
	public void setEvento(Event evento) {
		this.evento = evento;
	}
	public Question getPregunta() {
		return pregunta;
	}
	public void setPregunta(Question pregunta) {
		this.pregunta = pregunta;
	}
	public DataAccessJSF getDa() {
		return da;
	}
	public void setDa(DataAccessJSF da) {
		this.da = da;
	}
	public List<Event> getEventos() {
		return eventos;
	}
	public void setEventos(List<Event> eventos) {
		this.eventos = eventos;
	}
	public List<Question> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<Question> preguntas) {
		this.preguntas = preguntas;
	}
	
	
	
	public void onDateSelect(SelectEvent evento) {
		fecha = (Date) evento.getObject();
		eventos = da.getEvents(fecha);
	}
	public void eventoSelected(AjaxBehaviorEvent e) {
		preguntas = evento.getQuestions();
		System.out.println(preguntas.toString());
	}
}
