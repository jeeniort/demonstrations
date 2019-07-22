package gestiondesrepas.bo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Repas {
	private int id;
	private LocalDate date;
	private LocalTime time;
	List<Aliment> aliments;
	
	public Repas() {}

	public Repas(LocalDate date, LocalTime time) {
		super();
		this.date = date;
		this.time = time;
	}

	public List<Aliment> getAliments() {
		return aliments;
	}

	public void setAliments(List<Aliment> aliments) {
		this.aliments = aliments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	
}
