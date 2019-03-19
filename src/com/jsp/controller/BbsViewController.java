package com.jsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dao.BbsDao;
import com.jsp.dao.BbsFileDao;
import com.jsp.dto.BbsDto3;
import com.jsp.dto.BbsFileDto;

public class BbsViewController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String bbsId = req.getParameter("id");
		BbsDao bbsDao = BbsDao.getInstance();
		BbsDto3 bbsDto = new BbsDto3();
		int result = bbsDao.hitUpdate(bbsId);
		bbsDto = bbsDao.selectById(bbsId);

		req.setAttribute("bbsView", bbsDto);

		BbsFileDao bbsfDao = BbsFileDao.getInstance();
		
		List<BbsFileDto> files = bbsfDao.selectByBbsId(bbsId);
		req.setAttribute("files", files);

		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/bbsView.jsp");
		rd.forward(req, resp);

	}
}
