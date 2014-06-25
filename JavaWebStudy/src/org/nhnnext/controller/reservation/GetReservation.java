package org.nhnnext.controller.reservation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nhnnext.dao.ResvDAO;
import org.nhnnext.dto.Reservation;
import org.nhnnext.framework.JsonCreator;
import org.nhnnext.framework.Controller;

public class GetReservation implements Controller{

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String getPlaceName = req.getParameter("placeName");
		ArrayList<Reservation> resvList = ResvDAO.getInstance().getResvList(getPlaceName);
		System.out.println(resvList.size());
		ArrayList<String> ajaxObjList = new ArrayList<String>();
		JsonCreator jc = JsonCreator.getInstance();
		for (Reservation resv : resvList) {
			String rid = jc.createNameValuePair("RID", resv.getRid());
			String userId = jc.createNameValuePair("USERID", resv.getUserId());
			String userName = jc.createNameValuePair("USERNAME", resv.getUserName());
			String placeName = jc.createNameValuePair("PLACENAME", resv.getPlaceName());
			String purpose = jc.createNameValuePair("PURPOSE", resv.getPurpose());
			String date = jc.createNameValuePair("DATE", resv.getDate());
			String startTime= jc.createNameValuePair("STARTTIME", resv.getStartTime());
			String endTime = jc.createNameValuePair("ENDTIME", resv.getEndTime());
			String submitTime = jc.createNameValuePair("SUBMITTIME", resv.getSubmitTime());
			ajaxObjList.add(jc.createObject(rid, userId, userName, placeName, purpose, date, startTime, endTime, submitTime));
		}
		
		String array = jc.createArray(ajaxObjList);
		
		PrintWriter out = resp.getWriter();
		out.println(array);
		
		
//		{
//		    "title" : "new stand",
//		    "newsCount" : 10,
//		    "newsTitle" : ["chosun", "munhwa", "edaily", "kyunghyang", "gentleman"]
//		}
//		+-----+----------+-----------+---------+------------+-----------+----------+---------------------+
//		| RID | USERID   | PLACENAME | PURPOSE | DATE       | STARTTIME | ENDTIME  | SUBMITTIME          |
//		+-----+----------+-----------+---------+------------+-----------+----------+---------------------+
//		|   2 | oldgieui | LINK 2-3  | 수업    | 2014-05-31 | 08:32:12  | 12:02:12 | 2014-06-03 05:18:26 |
//		
	}

}
