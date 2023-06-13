package com.mindtree.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

import com.mindtree.DAO.DAOInterface;
import com.mindtree.DAO.DAOInterfaceImpl;
import com.mindtree.Entity.entity;
import com.mindtree.Exception.DAOException;
import com.mindtree.Exception.InvalidDateException;
import com.mindtree.Exception.InvalidDateRangeException;
import com.mindtree.Exception.InvalidStateCodeException;
import com.mindtree.Exception.NoDataFoundException;
import com.mindtree.service.ServiceClass;



public class controllerclass {
	public static void mainin() {
	
		System.out.println("***************Welcome to Covid Analysis Application System****************"+"\n1.Get State Names"+"\n2.Get District Name for given States"+
	        "\n3.Display data by state with in Date Range"+"\n4.Display confirmed cases by Comparing two states for a given date range"+"\n5.exit");
	
	}
	public static void main(String[] args) throws InvalidStateCodeException, InvalidDateException, InvalidDateRangeException, ParseException, SQLException, NoDataFoundException  {
		ServiceClass obj1=new ServiceClass();
		
		boolean flag=true;
		Scanner sc=new Scanner(System.in);
		do {
			mainin();
			System.out.println("Please select an option: ");
			int choice=sc.nextInt();
			switch(choice) {
			case 1:
	
				obj1.getStatesName();
				break;
			case 2:
				System.out.println("Please Enter the State Code: ");
				String s=sc.next();
				obj1.getDistricts(s);
				break;
			case 3:
				System.out.println("Please Enter the StartDate : ");
				String st=sc.next();
				System.out.println("Please Enter the  EndDate: ");
				String et=sc.next();

				
				System.out.println("Date "+"      state "+"    confirmed");
				obj1.getDataBetweenRange(st, et);
				break;
			case 4:
				System.out.println("Please Enter the StartDate : ");
				String std=sc.next();
				System.out.println("Please Enter the  EndDate: ");
				String end=sc.next();
				System.out.println("Please Enter the FirstStateCode: ");
				String state1=sc.next();
				System.out.println("Please Enter the SecondStateCode: ");
				String state2=sc.next();
			
				
				obj1.comparisonData(std,end,state1,state2);
				
				
				break;
			case 5:
				
				System.out.println("*********Thanking you for using Covid Analysis  application**********");
				System.exit(0);
			default:
				System.out.println("Please enter valid choice");
				break;
			}
		}while(flag);

	}
	
}
