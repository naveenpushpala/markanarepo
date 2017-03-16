package com.marakana.contacts.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.marakana.contacts.entities.Contact;

public class ContactRepository {
	private DataSource ds;
	
	public ContactRepository() throws NamingException{
		Context context = new InitialContext();
		 ds = (DataSource) context.lookup("java:comp/env/jdbc/trainingdb");
		context.close();
	}
	
	public void init() throws NamingException, SQLException{
		//initialize database
		Connection connection = ds.getConnection();
		Statement statement = connection.createStatement();
		String sql = "create table contact(id integer generated by default as identity primary key,name varchar(20),address_id integer, foreign key(address_id) references address(id))";
		statement.execute(sql);
		statement.close();
		connection.close();
		}
	
	
	public List<Contact> findAll() throws SQLException{
		Connection connection = ds.getConnection();
		Statement statement = connection.createStatement();
		try {
			ResultSet resultSet = statement.executeQuery("select * from contact ");
			List<Contact> contacts = new ArrayList<Contact>();
			try {
				while (resultSet.next()){
					contacts.add(unmarshal(resultSet));
			}return contacts;
			
		}
			finally {
				resultSet.close();
			}

		} finally {
			statement.close();
			connection.close();
		}		
	}
	
	
	public Contact find(long id) throws SQLException{
		Connection connection = ds.getConnection();
		Statement statement = connection.createStatement();
		try {
			ResultSet resultSet = statement.executeQuery("select * from contact where id = " + id);
			try {
				if (!resultSet.next())
					return null;
				else
					return unmarshal(resultSet);
			} finally {
				resultSet.close();
			}

		} finally {
			statement.close();
			connection.close();
		}
		
		
	} 
	
	private Contact unmarshal(ResultSet resultSet) throws SQLException {
		Contact contact = new Contact();
		contact.setId(resultSet.getLong("id"));
		contact.setName(resultSet.getString("name"));
		contact.setAddressId(resultSet.getLong("address_id"));
		return contact;
	}

	public void create(Contact contact) throws SQLException{
		Connection connection = ds.getConnection();
		Statement statement = connection.createStatement();
		try {
			statement.executeUpdate("Insert into contact(name,address_id)values('"+contact.getName()+ "','" +contact.getAddressId() + "')",Statement.RETURN_GENERATED_KEYS);
			ResultSet generatedKeys = statement.getGeneratedKeys();
			try {
				if (generatedKeys.next())
					
					contact.setId(generatedKeys.getLong("id"));
			} finally {
				generatedKeys.close();
			}

		} finally {
			statement.close();
			connection.close();
		}
		
	}
	
	public void update(Contact contact) throws SQLException{
		
		Connection connection = ds.getConnection();
		try{
		Statement statement = connection.createStatement();
		try {
			statement.executeUpdate("update contact set name= '" +contact.getName() + "', address_id='" +contact.getAddressId() + "' where id= " +contact.getId());
			} finally {
				statement.close();
			}
		}
		 finally {
			connection.close();
		}
		
	}
		
		
	public void delete(Contact contact) throws SQLException{
		Connection connection = ds.getConnection();
		try{
		Statement statement = connection.createStatement();
		try {
			statement.executeUpdate("delete from contact where id= '" +contact.getId());
			} finally {
				statement.close();
			}
		}
		 finally {
			connection.close();
		}
		
		
		
		
	}
}
