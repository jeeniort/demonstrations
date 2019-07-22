package gestiondesrepas.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import gestiondesrepas.bo.Repas;

public class RepasDAOJdbcImpl implements RepasDAO {
	private final String INSERT = "INSERT INTO repas(date_repas, time_repas) VALUES(?,?); ";
	private final String SELECT_ALL = "SELECT id, date_repas, time_repas FROM repas;";

	@Override
	public void insert(Repas repas) {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setDate(1, Date.valueOf(repas.getDate()));
			pstmt.setTime(2, Time.valueOf(repas.getTime()));
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				repas.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	@Override
	public List<Repas> selectAll() {
		List<Repas> repas = new ArrayList<Repas>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL, PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs  = pstmt.executeQuery();
			
			while(rs.next())
			{
				Repas tmp = new Repas();
				tmp.setId(rs.getInt(1));
				tmp.setDate(rs.getDate(2).toLocalDate());
				tmp.setTime(rs.getTime(3).toLocalTime());
				repas.add(tmp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return repas;
	}

}
