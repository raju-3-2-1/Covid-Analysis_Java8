package com.mindtree.service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import com.mindtree.DAO.DAOInterface;
import com.mindtree.DAO.DAOInterfaceImpl;
import com.mindtree.Entity.entity;
import com.mindtree.Exception.DAOException;
import com.mindtree.Exception.InvalidDateException;
import com.mindtree.Exception.InvalidDateRangeException;
import com.mindtree.Exception.InvalidStateCodeException;
import com.mindtree.Exception.NoDataFoundException;
import com.mindtree.Exception.getStatesNamesException;

 interface interf{
	 public  ArrayList<String> getList11(ArrayList<String> gs);
	 
}


public class ServiceClass {
	
	 static int MAX_VALID_YR = 9999;
	    static int MIN_VALID_YR = 1800;
	    
	    Predicate<Integer> p=year->((year % 4 == 0) && (year % 100 != 0)) ||  (year % 400 == 0);
	    BiPredicate<Integer, Integer> bp=(m,d)->((m < 1 || m > 12) ||  (d < 1 || d > 31));
DAOInterface daoInterface=new DAOInterfaceImpl();

ToIntFunction<String> f=s->Integer.parseInt(s);

static BiPredicate<ArrayList<String>,String> p2=(fl,s)->fl.contains(s);

public void getStatesName() throws DAOException {
	ArrayList<String> gs;
	try {
		gs = daoInterface.getAllStates();
	} catch (SQLException e) {
		
		throw new DAOException("error occured related to database kindly check");
	}
		
		interf i=ServiceClass::getList;
		ArrayList<String> finalList= i.getList11(gs);
		

		finalList.stream().forEach(s->System.out.println(s));
	


}

public static ArrayList<String> getList(ArrayList<String> gs){
	ArrayList<String> fl=new ArrayList<String>();
	for(String s:gs) {
		if(!p2.test(fl, s))
			fl.add(s);
	}
	return fl;
}

public void getDistricts(String s) throws InvalidStateCodeException, DAOException {
	ArrayList<String> gd=new ArrayList<String>();
//	Predicate<ArrayList<String>> p=(g)->g.isEmpty();
	
		try {
			gd=daoInterface.getDistrictNames(s);
		
		} catch (SQLException e) {
			throw new DAOException("Error querying in Database", e);
		}
		Predicate<ArrayList<String>> p=(g)->g.isEmpty();
		if((p.test(gd)))
			throw new InvalidStateCodeException("Invalid State Code Please Check your Input");
		
		interf i=ServiceClass::getList;
		ArrayList<String> finalList= i.getList11(gd);
		
		ArrayList<String> fl=(ArrayList<String>) finalList.stream().sorted().collect(Collectors.toList());
		
		//METHOD REFERENCE
		String[] array=fl.stream().toArray(String[]::new);
		for(String mm:array)
			System.out.println(mm);
		
}

public void getDataBetweenRange(String st, String et) throws InvalidDateException, InvalidDateRangeException, ParseException, DAOException, NoDataFoundException {


	int dy=f.applyAsInt(st.substring(0,4));
	int dm=f.applyAsInt(st.substring(5,7));
	int ds=f.applyAsInt(st.substring(8));
	int es=f.applyAsInt(et.substring(8));
	int em=f.applyAsInt(et.substring(5,7));
	int ey=f.applyAsInt(et.substring(0,4));
//	Predicate<Integer> p=year->((year % 4 == 0) && (year % 100 != 0)) ||  (year % 400 == 0);
//	BiPredicate<Integer, Integer> bp=(m,d)->((m < 1 || m > 12) ||  (d < 1 || d > 31));
	if(pp.test(st, et) || pp.test(st, et))
		throw new NoDataFoundException("No Data Present");
		if( bp.test(dm, ds)) 
			throw new InvalidDateException("Invalid Start date, Please Check your Input ");
		if(bp.test(em, es))
			throw new InvalidDateException("Invalid end date, Please Check your Input");
	if(!(isValidDate(ds,dm,dy, p) && isValidDate(es,em,ey, p)))
		throw new InvalidDateException("Invalid  date, Please Check your Input");
	
	
	java.util.Date date2= new SimpleDateFormat("yyyy-MM-dd").parse(et); 
	java.util.Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(st);
	java.sql.Date sd = new java.sql.Date(date1.getTime());
	java.sql.Date ed = new java.sql.Date(date2.getTime());
	
	
		if(sd.after(ed))
			throw new InvalidDateRangeException("Invalid Date Range, Please Check your Input");
		

		
	ArrayList<entity> gd;
	try {
		gd = daoInterface.getDataWithInDate(sd, ed);
	} catch (SQLException e1) {
		throw new DAOException("Error querying in Database", e1);
	}
	
	Comparator<entity> comparator = (c1, c2) -> { 
        return Long.valueOf(c1.getDate().getTime()).compareTo(c2.getDate().getTime()); 
};

Collections.sort(gd, comparator);
for(entity e:gd) {
	System.out.println(e.tString());
}
  // System.out.println("Sorted List : " +list); 
		}



	public     boolean isValidDate(Integer d,Integer m,Integer y,Predicate<Integer> p)
{
			// If year, month and day
			// are not in given range
					
			
			// Handle February month
			// with leap year
			if (m == 2)
			{
			if (p.test(y))
			 return (d <= 29);
			else
			 return (d <= 28);
			}
			
			// Months of April, June,
			// Sept and Nov must have
			// number of days less than
			// or equal to 30.
			if (m == 4 || m == 6 ||
			m == 9 || m == 11)
			return (d <= 30);
			
			return true;
}
	BiPredicate<String, String> pp=(a,b)->a.isEmpty() || b.isEmpty();
	
	public  void comparisonData(String st, String et,String firstStateCode,String secoStateCode) throws InvalidDateException, ParseException, InvalidDateRangeException, SQLException, NoDataFoundException {
		int dy=f.applyAsInt(st.substring(0,4));
		int dm=f.applyAsInt(st.substring(5,7));
		int ds=f.applyAsInt(st.substring(8));
		int es=f.applyAsInt(et.substring(8));
		int em=f.applyAsInt(et.substring(5,7));
		int ey=f.applyAsInt(et.substring(0,4));
		
		if(pp.test(st, et) || pp.test(firstStateCode, secoStateCode))
			throw new NoDataFoundException("No Data Present");

		if( bp.test(dm, ds)) 
			throw new InvalidDateException("Invalid Start date, Please Check your Input ");
		if(bp.test(em, es))
			throw new InvalidDateException("Invalid end date, Please Check your Input");
		if(!(isValidDate(ds,dm,dy, p) && isValidDate(es,em,ey, p)))
			throw new InvalidDateException("Invalid  date, Please Check your Input");
	
	
	java.util.Date date2= new SimpleDateFormat("yyyy-MM-dd").parse(et); 
	java.util.Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(st);
	java.sql.Date sd = new java.sql.Date(date1.getTime());
	java.sql.Date ed = new java.sql.Date(date2.getTime());
	
	
		if(sd.after(ed))
			throw new InvalidDateRangeException("Invalid Date Range, Please Check your Input");
		
		
	ArrayList<entity> firstStateList=daoInterface.comparingData(sd, ed, firstStateCode);
	
	ArrayList<entity> secondStateList=daoInterface.comparingData(sd, ed, secoStateCode);
	
	
	
	
	
	HashMap<Date, Integer> fsList=getData(firstStateList);
	HashMap<Date, Integer> ssList=getData(secondStateList);

	Set<Date> keys = new HashSet(fsList.keySet());
	keys.addAll(ssList.keySet());
	
	

	ArrayList<Date> list = new ArrayList<Date>();
	list.addAll(keys);
	System.out.println("Date"+"  \t     "+"firstStateCode"+"          "+"First State Confirmed Total"+"       "+"second StateCode"+"       "+"First State Confirmed Total");
for(Date ff:list) {
	if(ssList.containsKey(ff) && fsList.containsKey(ff))
	   System.out.println(ff+"       "+firstStateCode+"                   "+fsList.get(ff)+"\t\t\t\t\t"+secoStateCode+"\t\t\t"+ssList.get(ff));
	else if(!fsList.containsKey(ff))
		System.out.println(ff+"       "+firstStateCode+"                  "+"0"+"\t\t\t\t\t"+secoStateCode+"\t\t\t\t"+ssList.get(ff));
	else
		System.out.println(ff+"       "+firstStateCode+"                  "+fsList.get(ff)+"\t\t\t\t\t"+secoStateCode+"\t\t\t\t"+"0");
}
	}
	
	public static HashMap<Date, Integer> getData(ArrayList<entity> stateList) {
		HashMap<Date, Integer> fsl=new HashMap<Date, Integer>();
		for(entity i:stateList) {
			if(fsl.containsKey(i.getDate())) {
				Integer k=fsl.get(i.getDate());
				fsl.put( i.getDate(), Integer.parseInt(i.getConfirmed())+k);
		}
			else
				fsl.put(i.getDate(), Integer.parseInt(i.getConfirmed()));
			
		}
		return fsl;
	}
	
}
