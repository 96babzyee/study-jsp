package com.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dao.MemberDao;
import com.jsp.dto.MemberDto;

public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/login.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글깨짐 방지
		req.setCharacterEncoding("utf-8");
		
		// 파라미터 받아오기
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		 
		MemberDao mDao = MemberDao.getInstance(); // singleton pattern(싱글톤)

		int loginResult = mDao.login(id,password);
		
		if(loginResult==1) {
			req.setAttribute("loginResult", loginResult);
			HttpSession session = req.getSession();
			session.setAttribute("sessionID", id);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/welcome.jsp");
			rd.forward(req, resp);
		}
		else {
			req.setAttribute("loginResult", 0);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/login.jsp");
			rd.forward(req, resp);
		}
	}

}
