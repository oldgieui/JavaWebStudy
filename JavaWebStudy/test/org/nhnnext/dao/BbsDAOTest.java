package org.nhnnext.dao;

import java.util.ArrayList;

import org.junit.Test;
import org.nhnnext.dao.BbsDAO;
import org.nhnnext.dto.BbsArticle;

public class BbsDAOTest {

	@Test
	public void addArticle() {
		BbsDAO.getInstance().addArticle("oldgieui", "title", "contents");
		ArrayList<BbsArticle> bbsList = BbsDAO.getInstance().showBoard();
		System.out.println(bbsList.size());
	}
	
	@Test
	public void deleteArticle() {
		BbsDAO.getInstance().deleteArticle("oldgieui");
		ArrayList<BbsArticle> bbsList = BbsDAO.getInstance().showBoard();
		System.out.println(bbsList.size());
		//db에 없는 자료를 삭제하려고 하면 false가 뜨게 하고 싶은데....
	}
	
	@Test
	public void findArticle(){
		BbsDAO.getInstance().findArticle(1);
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
