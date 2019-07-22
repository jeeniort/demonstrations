package gestiondesrepas.bo;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Repas {
	int id;
	private LocalDate localDate;
	private LocalTime localTime;
	private List<Aliment> aliments;
	
	public Repas() {}
	
	public Repas(LocalDate date, LocalTime time, List<Aliment> aliments) {
		super();
		this.localDate = date;
		this.localTime = time;
		this.aliments = aliments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}
	
	public Date getLocalDateAsDate() {
		return Date.valueOf(localDate);
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
	
	public void setLocalDateFromDate(Date date) {
		this.localDate = date.toLocalDate();
	}

	public LocalTime getLocalTime() {
		return localTime;
	}
	
	public Time getLocalTimeAsTime() {
		Time time = Time.valueOf(localTime);
		return time;
	}

	public void setLocalTime(LocalTime localTime) {
		this.localTime = localTime;
	}
	
	public void setLocalTimeFromTime(Time time) {
		this.localTime = time.toLocalTime();
	}

	public List<Aliment> getAliments() {
		return aliments;
	}

	public void setAliments(List<Aliment> aliments) {
		this.aliments = aliments;
	}
}
