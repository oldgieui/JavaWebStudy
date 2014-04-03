package org.nhnnext;

import java.util.ArrayList;

import org.junit.Test;

public class BbsDAOTest {

	@Test
	public void addArticle() {
		BbsDAO bbsDao = new BbsDAO();
		bbsDao.addArticle("title", "contents");
		ArrayList<BbsArticle> bbsList = bbsDao.showBoard();
		System.out.println(bbsList.size());
	}
	
	@Test
	public void deleteArticle() {
		BbsDAO bbsDao = new BbsDAO();
		bbsDao.deleteArticle(38);
		ArrayList<BbsArticle> bbsList = bbsDao.showBoard();
		System.out.println(bbsList.size());
		//db에 없는 자료를 삭제하려고 하면 false가 뜨게 하고 싶은데....
	}
	
	@Test
	public void findArticle(){
		BbsDAO dao = new BbsDAO();
		dao.findArticle(1);
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
