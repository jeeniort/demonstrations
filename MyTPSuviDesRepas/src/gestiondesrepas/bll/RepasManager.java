package gestiondesrepas.bll;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import gestiondesrepas.bo.Repas;
import gestiondesrepas.dal.RepasDAO;
import gestiondesrepas.dal.RepasDAOJdbcImpl;

public class RepasManager {
	private RepasDAO repasDao;
	
	public RepasManager() {
		this.repasDao = new RepasDAOJdbcImpl();
	}

	public Repas inserer(LocalDate dateRepas, LocalTime timeRepas) {
		Repas repas = new Repas(dateRepas, timeRepas);
		repasDao.insert(repas);
		return repas;
	}

	public List<Repas> selectAll() {
		return repasDao.selectAll();
	}
}
