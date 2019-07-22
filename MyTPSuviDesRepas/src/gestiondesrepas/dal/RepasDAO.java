package gestiondesrepas.dal;

import java.util.List;

import gestiondesrepas.bo.Repas;

public interface RepasDAO {
	public void insert(Repas repas);
	public List<Repas> selectAll();
}
