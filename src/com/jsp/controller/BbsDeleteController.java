package com.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dao.BbsDao;
import com.jsp.dao.BbsFileDao;
import com.jsp.dao.TransactionDao;
import com.jsp.dto.BbsDto3;

public class BbsDeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bbsID = req.getParameter("id");
		
		BbsFileDao bbsfDao = BbsFileDao.getInstance();
		int result1 = bbsfDao.delete(Integer.parseInt(bbsID));
		
		BbsDao bbsDao = BbsDao.getInstance();
		int result2 = bbsDao.delete(Integer.parseInt(bbsID));
		
		int deleteResult = result1 + result2;
		req.setAttribute("deleteResult", deleteResult);
		
		RequestDispatcher rd = req.getRequestDispatcher("bbs.do");
		rd.forward(req, resp);
	}
	
}
