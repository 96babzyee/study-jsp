package com.jsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dao.BbsDao;
import com.jsp.dto.BbsDto3;

public class BbsController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int startNum = 0;
		int perpage = 20;
		
		BbsDao bbsDao = BbsDao.getInstance();
		List<BbsDto3> list = bbsDao.selectAll(startNum, perpage);
		
		req.setAttribute("list", list);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/bbs.jsp");
		rd.forward(req, resp);
	}

}
