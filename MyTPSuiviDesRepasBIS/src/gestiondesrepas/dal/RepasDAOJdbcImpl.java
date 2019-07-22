package gestiondesrepas.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gestiondesrepas.bo.Repas;

public class RepasDAOJdbcImpl implements RepasDAO {
	private static final String INSERT="INSERT INTO REPAS(date_repas, time_repas) VALUES(?,?);";
	private static final String SELECT="SELECT id, date_repas, time_repas FROM REPAS WHERE id=?;";
	private static final String SELECT_ALL="SELECT id, date_repas, time_repas FROM REPAS;";
	private static final String SELECT_ALL_SORTED="SELECT id, date_repas, time_repas FROM REPAS order by date_repas DESC, time_repas DESC;";
	
	@Override
	public void insert(Repas repas) {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setDate(1, repas.getLocalDateAsDate());
			pstmt.setTime(2, repas.getLocalTimeAsTime());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				repas.setId(rs.getInt(1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Repas select(int id) {
		Repas repas = new Repas();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				repas.setId(rs.getInt(1));
				repas.setLocalDateFromDate(rs.getDate(2));
				repas.setLocalTimeFromTime(rs.getTime(3));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return repas;
	}

	@Override
	public List<Repas> selectAll() {
		List<Repas> repas = new ArrayList<Repas>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Repas tmp = new Repas();
				tmp.setId(rs.getInt(1));
				tmp.setLocalDateFromDate(rs.getDate(2));
				tmp.setLocalTimeFromTime(rs.getTime(3));
				repas.add(tmp);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return repas;
	}
	
	@Override
	public List<Repas> selectAllSorted() {
		List<Repas> repas = new ArrayList<Repas>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_SORTED);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Repas tmp = new Repas();
				tmp.setId(rs.getInt(1));
				tmp.setLocalDateFromDate(rs.getDate(2));
				tmp.setLocalTimeFromTime(rs.getTime(3));
				repas.add(tmp);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return repas;
	}

}
