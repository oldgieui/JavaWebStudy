package org.nhnnext;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BbsDAOTest {

	@Test
	public void add() {
		BbsDAO bbs = new BbsDAO();
		bbs.addArticle("title", "contents");
		ArrayList<Bbs> bbsList = bbs.showArticles();
		System.out.println(bbsList.size());
	}
	
//	@Test
//	public void modify() throws Exception {
//		BbsDAO bbsDao = new BbsDAO();
//		int id = 1;
//		bbsDao.update(id, "title2", "conents2");
//		Bbs bbs = bbsDao.findById(id);
//		assertEquals(bbs.getTitle(), "title2");
//		assertEquals(bbs.getContents(), "contents");
//		
//	}

}
