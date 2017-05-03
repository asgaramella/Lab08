package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode,StateAbb,StateNme " + "FROM country " + "ORDER BY StateAbb ";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			List<Country> paesi=new ArrayList<Country>();

			while (rs.next()) {
				//System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				Country c=new Country(rs.getInt("ccode"),rs.getString("StateNme"),rs.getString("StateAbb"));
				paesi.add(c);
			}

			conn.close();
			return paesi;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- loadAllCountries");
			throw new RuntimeException("Database Error");
		}
	}

	public List<Border> getCountryPairs(int anno) {
		
		String sql =" select state1no, state2no "+
				"from contiguity "+
				"where year<=? and conttype=?";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			st.setInt(2, 1);

			ResultSet rs = st.executeQuery();
			List<Border> bordi=new ArrayList<Border>();

			while (rs.next()) {
				Border b=new Border(rs.getInt("state1no"),rs.getInt("state2no"));
				bordi.add(b);
				
			}

			conn.close();
			return bordi;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- getCountryPairs");
			throw new RuntimeException("Database Error");
		}

		
		
	}
}
