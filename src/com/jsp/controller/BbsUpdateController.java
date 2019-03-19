package com.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dao.BbsDao;
import com.jsp.dao.TransactionDao;
import com.jsp.dto.BbsDto3;

public class BbsUpdateController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bbsID = req.getParameter("id");
		BbsDao bbsDao = BbsDao.getInstance();
		BbsDto3 bbsDto = bbsDao.selectById(bbsID);

		req.setAttribute("bbsUpdate", bbsDto);

		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/bbsUpdate.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String sessionID = null;
		if (session.getAttribute("sessionID") != null) {
			sessionID = (String) session.getAttribute("sessionID");
		}

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String category = req.getParameter("category");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String bbsId = req.getParameter("bbsId");
		
		BbsDto3 bbsDto = new BbsDto3();
		bbsDto.setBbsTitle(title);
		bbsDto.setBbsCategory(category);
		bbsDto.setBbsContent(content);
		bbsDto.setBbsId(bbsId);

		TransactionDao tDao = TransactionDao.getInstance();
		int resultTrans = tDao.updateAll(bbsDto);
		req.setAttribute("resultTrans", resultTrans);
		
		RequestDispatcher rd = req.getRequestDispatcher("bbs.do");
		rd.forward(req, resp);
	}
	
}
