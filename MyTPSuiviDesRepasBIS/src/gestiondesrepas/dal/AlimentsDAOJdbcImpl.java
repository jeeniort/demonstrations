package gestiondesrepas.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gestiondesrepas.bo.Aliment;

public class AlimentsDAOJdbcImpl implements AlimentsDAO {
	private static final String INSERT="INSERT INTO ALIMENTS(nom) VALUES(?);";
	private static final String SELECT="SELECT id, nom FROM ALIMENTS WHERE id=?;";
	private static final String SELECT_ALL="SELECT id, nom FROM ALIMENTS;";
	private static final String ASSOCIER="INSERT INTO ASSO_REP_ALI(id_repas, id_alim) VALUES(?,?);";
	private static final String SELECT_BY_REPAS=
			"SELECT alim.id, alim.nom FROM ALIMENTS alim, ASSO_REP_ALI asso WHERE alim.id=asso.id_alim AND asso.id_repas=?";

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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Aliment select(int id) {
		Aliment aliment = new Aliment();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				aliment.setId(rs.getInt(1));
				aliment.setNom(rs.getString(2));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return aliment;
	}

	@Override
	public List<Aliment> selectAll() {
		List<Aliment> aliments = new ArrayList<Aliment>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Aliment tmp = new Aliment();
				tmp.setId(rs.getInt(1));
				tmp.setNom(rs.getString(2));
				aliments.add(tmp);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return aliments;
	}

	@Override
	public void associer(int idRepas, int idAliment) {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(ASSOCIER, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, idRepas);
			pstmt.setInt(2, idAliment);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Aliment> selectByRepas(int idRepas) {
		List<Aliment> aliments = new ArrayList<Aliment>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_REPAS);
			pstmt.setInt(1, idRepas);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Aliment tmp = new Aliment();
				tmp.setId(rs.getInt(1));
				tmp.setNom(rs.getString(2));
				aliments.add(tmp);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return aliments;
	}
}
