package gestiondesrepas.bll;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import gestiondesrepas.bo.Repas;
import gestiondesrepas.dal.RepasDAO;
import gestiondesrepas.dal.RepasDAOJdbcImpl;

public class RepasManager {
	private RepasDAO dao;
	
	public RepasManager() {
		this.dao = new RepasDAOJdbcImpl();
	}
	
	public Repas ajouter(LocalDate date, LocalTime time) {
		Repas repas = new Repas();
		repas.setLocalDate(date);
		repas.setLocalTime(time);
		this.dao.insert(repas);
		return repas;
	}
	
	public Repas get(int id) {
		return dao.select(id);
	}
	
	public List<Repas> getAll() {
		return dao.selectAll();
	}
	
	public List<Repas> getAllSorted() {
		return dao.selectAllSorted();
	}
}
