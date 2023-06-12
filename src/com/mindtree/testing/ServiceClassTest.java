package com.mindtree.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.mindtree.DAO.DAOInterface;
import com.mindtree.DAO.DAOInterfaceImpl;
import com.mindtree.Entity.entity;
import com.mindtree.Exception.DAOException;
import com.mindtree.Exception.InvalidDateException;
import com.mindtree.Exception.InvalidDateRangeException;
import com.mindtree.Exception.NoDataFoundException;
import com.mindtree.service.ServiceClass;

class ServiceClassTest {

	DAOInterface daoInterface=new DAOInterfaceImpl();	
	ServiceClass serviceClass;
	@Test
public void testGetStatesNameValidData() {
		
		ArrayList<String> gs=new ArrayList<String>();
		gs.add("JK");
		gs.add("JH");
		gs.add("AP");
		gs.add("JK");
		gs.add("MP");
		gs.add("KL");
		gs.add("KL");
		BiPredicate<ArrayList<String>,String> p=(fl,s)->fl.contains(s);
		ArrayList<String> fl=new ArrayList<String>();
		ArrayList<String> lis=new ArrayList<String>();
		lis.add("JK");lis.add("JH");lis.add("AP");lis.add("MP");lis.add("KL");
		for(String s:gs) {
			if(!p.test(fl, s))
				fl.add(s);
		}
		assertEquals(fl, lis);
//	assertTrue(p.test(fl, "PO"));
	}
	
	
	
	@Test
	public void testGetDistricts() {
		
		ArrayList<String> districtList=new ArrayList<String>();
		districtList.add("Samba");
		districtList.add("Kupwara");
		districtList.add("Chatra"); 	districtList.add("Hooghly"); 	districtList.add("Jamtara");
		
		ArrayList<String> sortedDistrictList = new ArrayList<String>();
		
		sortedDistrictList.add("Chatra");sortedDistrictList.add("Hooghly");sortedDistrictList.add("Jamtara");sortedDistrictList.add("Kupwara");
		sortedDistrictList.add("Samba");
		System.out.println(sortedDistrictList);
		
		List<String> fl= districtList.stream().sorted().collect(Collectors.toList());
		assertTrue(fl.equals(sortedDistrictList));
		
	}

ToIntFunction<String> f=s->Integer.parseInt(s);
BiPredicate<String, String> pp=(a,b)->a.isEmpty() || b.isEmpty();

    @Test
	public void testGetDataBetweenRange() throws NoDataFoundException, InvalidDateException, ParseException, InvalidDateRangeException, DAOException {

		String st="07-07-2020";
		String et="07-08-2020";
		int ds=f.applyAsInt(st.substring(0,2));
		int dm=f.applyAsInt(st.substring(3,5));
		int dy=f.applyAsInt(st.substring(6));
		int es=f.applyAsInt(et.substring(0,2));
		int em=f.applyAsInt(et.substring(3,5));
		int ey=f.applyAsInt(et.substring(6));

		if(pp.test(st, et) || pp.test(st, et))
			throw new NoDataFoundException("No Data Present");
			if( bp.test(dm, ds)) 
				throw new InvalidDateException("Invalid Start date, Please Check your Input ");
			if(bp.test(em, es))
				throw new InvalidDateException("Invalid end date, Please Check your Input");
		if(!(isValidDate(ds,dm,dy, p) && isValidDate(es,em,ey, p)))
			throw new InvalidDateException("Invalid  date, Please Check your Input");
		
		
		java.util.Date date2= new SimpleDateFormat("dd-MM-yyyy").parse(et); 
		java.util.Date date1= new SimpleDateFormat("dd-MM-yyyy").parse(st);
		java.sql.Date sd = new java.sql.Date(date1.getTime());
		java.sql.Date ed = new java.sql.Date(date2.getTime());
		
		
			if(sd.after(ed))
				throw new InvalidDateRangeException("Invalid Date Range, Please Check your Input");
			

			
		ArrayList<entity> gd=new ArrayList<entity>();
		gd.add(new entity(3106, new Date(8-8-2020) , "WB","Kolkata","0","684","628"));
		gd.add(new entity(1,new Date(07-8-2020) , "JK", "Samba", "0", "11", "0"));
		gd.add(new entity(2, new Date(07-8-2020) , "JK","Kupwara","0","28","6"));
		gd.add(new entity(22, new Date(07-8-2020) , "HP","Kinnaur","0","0","2"));
		gd.add(new entity(23, new Date(07-8-2020) , "HP","Bilaspur","0","4","1"));

		ArrayList<entity> finalSortedList=new ArrayList<entity>();
		finalSortedList.add(new entity(1,new Date(07-8-2020) , "JK", "Samba", "0", "11", "0"));
		finalSortedList.add(new entity(2, new Date(07-8-2020) , "JK","Kupwara","0","28","6"));
		finalSortedList.add(new entity(22, new Date(07-8-2020) , "HP","Kinnaur","0","0","2"));
		finalSortedList.add(new entity(23, new Date(07-8-2020) , "HP","Bilaspur","0","4","1"));
		finalSortedList.add(new entity(3106, new Date(8-8-2020) , "WB","Kolkata","0","684","628"));
		Comparator<entity> comparator = (c1, c2) -> { 
	        return Long.valueOf(c1.getDate().getTime()).compareTo(c2.getDate().getTime()); 
	};

	Collections.sort(gd, comparator);
	
	assertTrue(finalSortedList.equals(gd));
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
	 Predicate<Integer> p=year->((year % 4 == 0) && (year % 100 != 0)) ||  (year % 400 == 0);
	    BiPredicate<Integer, Integer> bp=(m,d)->((m < 1 || m > 12) ||  (d < 1 || d > 31));
	    
	    
	    
@Test	    
public void testIsValidDate() {
	assertEquals(isValidDate(31, 04, 2022, p), true);
}


@Test
public void testSumOfConfirmedCases() {
	ArrayList<entity> gd=new ArrayList<entity>();
	gd.add(new entity(3106, new Date(8-8-2020) , "WB","Kolkata","0","684","628"));
	gd.add(new entity(1,new Date(07-8-2020) , "JK", "Samba", "0", "11", "0"));
	gd.add(new entity(2, new Date(07-8-2020) , "JK","Kupwara","0","28","6"));
	gd.add(new entity(22, new Date(07-8-2020) , "HP","Kinnaur","0","0","2"));
	gd.add(new entity(23, new Date(07-8-2020) , "HP","Bilaspur","0","4","1"));
	HashMap<Date, Integer> fsl=new HashMap<Date, Integer>();
	for(entity i:gd) {
		if(fsl.containsKey(i.getDate())) {
			Integer k=fsl.get(i.getDate());
			fsl.put( i.getDate(), Integer.parseInt(i.getConfirmed())+k);
	}
		else
			fsl.put(i.getDate(), Integer.parseInt(i.getConfirmed()));
		
	}
assertEquals(fsl.get(new Date(07-8-2020)),43);
	
}

}
