package org.nhnnext.framework;

import java.util.ArrayList;

import org.junit.Test;
import org.nhnnext.dao.ResvDAO;
import org.nhnnext.dto.Reservation;

public class JsonCreatorTest {

	@Test
	public void createPair() {
		String test = JsonCreator.getInstance().createNameValuePair("testname", "testValue");
		String test2 = JsonCreator.getInstance().createNameValuePair("testname", 1.234);
		System.out.println(test);
		System.out.println(test2);
	}
	
	@Test
	public void createObject(){
		String test = JsonCreator.getInstance().createNameValuePair("testname", 1);
		String test2 = JsonCreator.getInstance().createNameValuePair("testname2", "testValue2");
		String test3 = JsonCreator.getInstance().createNameValuePair("testname3", "testValue3");
		
		String object = JsonCreator.getInstance().createObject(test, test2, test3);
		System.out.println(object);
	}
	
	@Test
	public void createArray(){
		String test = JsonCreator.getInstance().createNameValuePair("testname", "testValue");
		String test2 = JsonCreator.getInstance().createNameValuePair("testname2", "testValue2");
		String test3 = JsonCreator.getInstance().createNameValuePair("testname3", "testValue3");
		String test4 = JsonCreator.getInstance().createNameValuePair("testname4", "testValue4");
		String test5 = JsonCreator.getInstance().createNameValuePair("testname5", "testValue5");
		String test6 = JsonCreator.getInstance().createNameValuePair("testname6", "testValue6");
		String test7 = JsonCreator.getInstance().createNameValuePair("testname7", "testValue7");
		String test8 = JsonCreator.getInstance().createNameValuePair("testname8", "testValue8");
		
		String object = JsonCreator.getInstance().createObject(test, test2);
		String object2 = JsonCreator.getInstance().createObject(test3, test4);
		String object3 = JsonCreator.getInstance().createObject(test5, test6);
		String object4 = JsonCreator.getInstance().createObject(test7, test8);

		String array = JsonCreator.getInstance().createArray("testArray", object, object2, object3, object4);
		System.out.println(array);
	}
	
	@Test
	public void test(){
		ArrayList<Reservation> resvList = ResvDAO.getInstance().getResvList("LINK2-3");
		System.out.println(resvList.size());
		ArrayList<String> list = new ArrayList<String>();
		for (Reservation resv : resvList) {
			String rid = JsonCreator.getInstance().createNameValuePair("RID", resv.getRid());
			String userId = JsonCreator.getInstance().createNameValuePair("USERID", resv.getUserId());
			String placeName = JsonCreator.getInstance().createNameValuePair("PLACENAME", resv.getPlaceName());
			String purpose = JsonCreator.getInstance().createNameValuePair("PURPOSE", resv.getPurpose());
			String date = JsonCreator.getInstance().createNameValuePair("DATE", resv.getDate());
			String startTime= JsonCreator.getInstance().createNameValuePair("STARTTIME", resv.getStartTime());
			String endTime = JsonCreator.getInstance().createNameValuePair("ENDTIME", resv.getEndTime());
			String submitTime = JsonCreator.getInstance().createNameValuePair("SUBMITTIME", resv.getSubmitTime());
			list.add(JsonCreator.getInstance().createObject(rid, userId, placeName, purpose, date, startTime, endTime, submitTime));
		}
		System.out.println(JsonCreator.getInstance().createArray("test", list));
	}
	

}
