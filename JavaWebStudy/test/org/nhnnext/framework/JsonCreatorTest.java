package org.nhnnext.framework;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.nhnnext.dao.ResvDAO;
import org.nhnnext.dto.Reservation;

public class JsonCreatorTest {
	private JsonCreator creator;
	
	@Before
	public void setupo() {
		creator = JsonCreator.getInstance();
	}
	

	@Test
	public void createPair() {
		String test = creator.createNameValuePair("testname", "testValue");
		String test2 = creator.createNameValuePair("testname", 1.234);
		System.out.println(test);
		System.out.println(test2);
	}
	
	@Test
	public void createObject(){
		String test = creator.createNameValuePair("testname", 1);
		String test2 = creator.createNameValuePair("testname2", "testValue2");
		String test3 = creator.createNameValuePair("testname3", "testValue3");
		
		String object = creator.createObject(test, test2, test3);
		System.out.println(object);
	}
	
	@Test
	public void createArray(){
		String test = creator.createNameValuePair("testname", "testValue");
		String test2 = creator.createNameValuePair("testname2", "testValue2");
		String test3 = creator.createNameValuePair("testname3", "testValue3");
		String test4 = creator.createNameValuePair("testname4", "testValue4");
		String test5 = creator.createNameValuePair("testname5", "testValue5");
		String test6 = creator.createNameValuePair("testname6", "testValue6");
		String test7 = creator.createNameValuePair("testname7", "testValue7");
		String test8 = creator.createNameValuePair("testname8", "testValue8");
		
		String object = creator.createObject(test, test2);
		String object2 = creator.createObject(test3, test4);
		String object3 = creator.createObject(test5, test6);
		String object4 = creator.createObject(test7, test8);

		String array = creator.createArray("testArray", object, object2, object3, object4);
		System.out.println(array);
	}
	
	@Test
	public void test(){
		ArrayList<Reservation> resvList = ResvDAO.getInstance().getResvList("LINK2-3");
		System.out.println(resvList.size());
		ArrayList<String> list = new ArrayList<String>();
		for (Reservation resv : resvList) {
			String rid = creator.createNameValuePair("RID", resv.getRid());
			String userId = creator.createNameValuePair("USERID", resv.getUserId());
			String placeName = creator.createNameValuePair("PLACENAME", resv.getPlaceName());
			String purpose = creator.createNameValuePair("PURPOSE", resv.getPurpose());
			String date = creator.createNameValuePair("DATE", resv.getDate());
			String startTime= creator.createNameValuePair("STARTTIME", resv.getStartTime());
			String endTime = creator.createNameValuePair("ENDTIME", resv.getEndTime());
			String submitTime = creator.createNameValuePair("SUBMITTIME", resv.getSubmitTime());
			list.add(creator.createObject(rid, userId, placeName, purpose, date, startTime, endTime, submitTime));
		}
		System.out.println(creator.createArray("test", list));
	}
	

}
