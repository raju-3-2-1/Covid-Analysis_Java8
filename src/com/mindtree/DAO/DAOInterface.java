package com.mindtree.DAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mindtree.Entity.entity;
import com.mindtree.Exception.InvalidStateCodeException;

public interface DAOInterface {

	public ArrayList<String> getAllStates() throws SQLException;
	public ArrayList<String> getDistrictNames(String m) throws InvalidStateCodeException, SQLException;
	public ArrayList<entity> getDataWithInDate(Date startDate,Date endDate) throws SQLException;
	public ArrayList<entity> comparingData(Date startDate,Date endDate,String StateCode) throws SQLException;
}
