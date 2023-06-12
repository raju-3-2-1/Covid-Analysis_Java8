package com.mindtree.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.mindtree.Entity.entity;
import com.mindtree.Exception.InvalidStateCodeException;
import com.mindtree.Exception.DAOException;

public class DAOInterfaceImpl implements DAOInterface{


	@Override
	public ArrayList<String> getAllStates() throws SQLException   {
		
	
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid_data1","root","Challaraju321@");
			
			Statement stmt=con.createStatement();
			String s="SELECT state FROM covid_data1";
			ResultSet rs=stmt.executeQuery(s);
			ArrayList<String> statesList=new ArrayList<String>();
			while(rs.next()) {
				String ss=rs.getString("state");
				statesList.add(ss);
				
			}
			con.close();
			return statesList;
				
	
		
		
	}
	
	public ArrayList<String> getDistrictNames(String m) throws  SQLException {
		
		
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid_data1","root","Challaraju321@");
			Statement stmt=con.createStatement();
//			String s="SELECT district FROM covid_data1 where state=?";
			PreparedStatement s=con.prepareStatement("SELECT district FROM covid_data1 where state=?");
			s.setString(1, m);
			ResultSet rs=s.executeQuery();
			ArrayList<String> districtList=new ArrayList<String>();
			while(rs.next()) {
				String ss=rs.getString("district");
				districtList.add(ss);
			}
	
		con.close();
		return districtList;

	}
	
public ArrayList<entity> getDataWithInDate(Date startDate,Date endDate) throws SQLException{
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid_data1","root","Challaraju321@");
	Statement stmt=con.createStatement();
//	String s="SELECT district FROM covid_data1 where state=?";
	PreparedStatement s=con.prepareStatement("SELECT * FROM covid_data1 WHERE date BETWEEN ? AND ? ");
		
	s.setDate(1, startDate);
	s.setDate(2, endDate);
	ResultSet rs=s.executeQuery();
	ArrayList<entity> districtList=new ArrayList<entity>();
	while(rs.next()) {
		String st=rs.getString("state");
		String di=rs.getString("district");
		String te=rs.getString("tested");
		String conf=rs.getString("confirmed");
		String reco=rs.getString("recovered");
		int id=rs.getInt("id");
		Date dat=rs.getDate("date");
		entity ee=new entity( id,  dat,  st,  di,  te,  conf,  reco);
		
		districtList.add(ee);
	} 
	con.close();
	
	return districtList;
}

public ArrayList<entity> comparingData(Date startDate,Date endDate,String StateCode) throws SQLException{
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid_data1","root","Challaraju321@");
	Statement stmt=con.createStatement();
//	String s="SELECT district FROM covid_data1 where state=?";
	PreparedStatement s=con.prepareStatement("SELECT * FROM covid_data1 WHERE date BETWEEN ? AND ? having state=? ");
	s.setDate(1, startDate);
	s.setDate(2, endDate);
	s.setString(3, StateCode);
	ResultSet rs=s.executeQuery();
	ArrayList<entity> stateList=new ArrayList<entity>();
	while(rs.next()) {
		String st=rs.getString("state");
		String di=rs.getString("district");
		String te=rs.getString("tested");
		String conf=rs.getString("confirmed");
		String reco=rs.getString("recovered");
		int id=rs.getInt("id");
		Date dat=rs.getDate("date");
		entity ee=new entity( id,  dat,  st,  di,  te,  conf,  reco);
		
		stateList.add(ee);
	} 
	con.close();
	return stateList;
}
}
