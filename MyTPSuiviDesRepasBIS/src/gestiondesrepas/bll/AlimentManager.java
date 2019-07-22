package gestiondesrepas.bll;

import java.util.List;

import gestiondesrepas.bo.Aliment;
import gestiondesrepas.bo.Repas;
import gestiondesrepas.dal.AlimentsDAO;
import gestiondesrepas.dal.AlimentsDAOJdbcImpl;

public class AlimentManager {
	private AlimentsDAO dao;
	
	public AlimentManager() {
		this.dao = new AlimentsDAOJdbcImpl();
	}
	
	public Aliment ajouter(Aliment aliment) {
		this.dao.insert(aliment);
		return aliment;
	}
	
	public Aliment get(int id) {
		return dao.select(id);
	}
	
	public List<Aliment> getAll() {
		return dao.selectAll();
	}

	public void ajouterAvecAssociation(Repas repasInsere) {
		for (Aliment aliment : repasInsere.getAliments()) {
			Aliment alimentInsere = this.ajouter(aliment);
			this.associer(repasInsere.getId(), alimentInsere.getId());
		}
	}

	private void associer(int idRepas, int idAliment) {
		this.dao.associer(idRepas, idAliment);
	}

	public void alimenterRepas(List<Repas> repas) {
		for (Repas rep : repas) {
			rep.setAliments(this.dao.selectByRepas(rep.getId()));
		}
	}
}
