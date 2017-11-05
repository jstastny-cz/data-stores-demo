package org.jboss.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PeopleFinderInPostgres implements PeopleFinder {

	private String jdbcUrl;
	private String username;
	private String password;

	public PeopleFinderInPostgres(String jdbcUrl, String username, String password) {
		super();
		this.jdbcUrl = jdbcUrl;
		this.username = username;
		this.password = password;
	}

	@Override
	public List<Person> findAll() throws Exception {
		List<Person> list = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM people");
			while (rs.next()) {
				list.add(new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
			}
		}
		return list;
	}

	@Override
	public List<Person> findAllSortedByLastNameDesc() throws Exception {
		List<Person> list = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM people ORDER BY lastName DESC");
			while (rs.next()) {
				list.add(new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
			}
		}
		return list;
	}

	@Override
	public Person findPersonWithHighestStreetNumber() throws Exception {
		Person p = null;
		try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM people ORDER BY street_number DESC LIMIT 1");
			while (rs.next()) {
				p = new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
			}
		}
		return p;
	}
}
