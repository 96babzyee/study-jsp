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

public class JoinController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/join.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 깨짐 방지
		req.setCharacterEncoding("utf-8");
		
		// 파라미터받아오기
		 String id = req.getParameter("id");
		 String password = req.getParameter("password");
		 String name = req.getParameter("name");
		 String email = req.getParameter("email");
		 String marketing = req.getParameter("marketing");

		// MemberDao, MemberDto
		MemberDao mDao = MemberDao.getInstance(); // singleton pattern(싱글톤)
		MemberDto mDto = new MemberDto();
		mDto.setId(id);
		mDto.setPassword(password);
		mDto.setName(name);
		mDto.setEmail(email);
		mDto.setMarketing(marketing);
		int joinResult = mDao.join(mDto);
		
		if(joinResult==1) {
			// 가입 성공
			req.setAttribute("joinResult", joinResult);
			HttpSession session = req.getSession();
			session.setAttribute("sessionID", id);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/welcome.jsp");
			rd.forward(req, resp);
		}
		else {
			// 가입 실패
			req.setAttribute("joinResult", 0);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/join.jsp");
			rd.forward(req, resp);
		}
		
	}
}
