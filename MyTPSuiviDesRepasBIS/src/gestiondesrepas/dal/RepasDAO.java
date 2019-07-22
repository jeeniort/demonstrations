package gestiondesrepas.dal;

import java.util.List;

import gestiondesrepas.bo.Repas;

public interface RepasDAO {
	public void insert(Repas repas);
	public Repas select(int id);
	public List<Repas> selectAll();
	List<Repas> selectAllSorted();
}
