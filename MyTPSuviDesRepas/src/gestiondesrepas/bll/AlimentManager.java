package gestiondesrepas.bll;

import java.util.List;

import gestiondesrepas.bo.Aliment;
import gestiondesrepas.bo.Repas;
import gestiondesrepas.dal.AlimentDAO;
import gestiondesrepas.dal.AlimentDaoJdbcImpl;

public class AlimentManager {
	private AlimentDAO alimentDao;
	
	public AlimentManager() {
		this.alimentDao = new AlimentDaoJdbcImpl();
	}
	
	public void insererMultiple(int idRepas, List<Aliment> aliments) {
		for(Aliment aliment : aliments) {
			alimentDao.insert(aliment);
			alimentDao.associer(idRepas, aliment.getId());
		}
	}

	public void selectAlimentByRepas(List<Repas> repas) {
		for(Repas rep : repas) {
			List<Aliment> aliments = alimentDao.selectByRepasId(rep.getId());
			rep.setAliments(aliments);
		}
	}
}
