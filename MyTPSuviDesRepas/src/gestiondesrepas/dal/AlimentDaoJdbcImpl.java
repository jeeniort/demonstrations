package gestiondesrepas.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gestiondesrepas.bo.Aliment;

public class AlimentDaoJdbcImpl implements AlimentDAO {
	private static final String INSERT = "INSERT INTO aliments(nom) VALUES(?)";
	private static final String ASSOCIATION = "INSERT INTO asso_rep_ali(id_repas, id_alim) VALUES(?,?);";
	private static final String SELECT_BY_REPAS_ID = 
			"SELECT nom FROM asso_rep_ali ara, aliments ali "
					+ " WHERE ara.id_repas = ? AND ara.id_alim=ali.id;";

	@Override
	public void insert(Aliment aliment) {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, aliment.getNom());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				aliment.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	@Override
	public void associer(int idRepas, int idAliment) {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(ASSOCIATION, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, idRepas);
			pstmt.setInt(2, idAliment);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	@Override
	public List<Aliment> selectByRepasId(int id) {
		List<Aliment> aliments = new ArrayList<Aliment>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_REPAS_ID, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, id);
			ResultSet rs  = pstmt.executeQuery();
			
			while(rs.next())
			{
				Aliment tmp = new Aliment();
				tmp.setNom(rs.getString(1));
				aliments.add(tmp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return aliments;
	}

}
