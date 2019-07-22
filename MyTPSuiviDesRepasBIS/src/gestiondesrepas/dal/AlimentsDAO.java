package gestiondesrepas.dal;

import java.util.List;

import gestiondesrepas.bo.Aliment;

public interface AlimentsDAO {
	public void insert(Aliment aliment);
	public Aliment select(int id);
	public List<Aliment> selectAll();
	public void associer(int idRepas, int idAliment);
	public List<Aliment> selectByRepas(int id);
}
