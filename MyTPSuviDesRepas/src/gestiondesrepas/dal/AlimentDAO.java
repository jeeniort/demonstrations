package gestiondesrepas.dal;

import java.util.List;

import gestiondesrepas.bo.Aliment;

public interface AlimentDAO {
	public void insert(Aliment aliment);
	public void associer(int idRepas, int idAliment);
	public List<Aliment> selectByRepasId(int id);
}
