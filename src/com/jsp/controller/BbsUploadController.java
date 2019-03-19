package com.jsp.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dao.TransactionDao;
import com.jsp.dto.BbsDto3;
import com.jsp.dto.BbsFileDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BbsUploadController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/bbsUpload.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "E:/temp/";

		MultipartRequest mRequest = new MultipartRequest(req, path, 1024 * 1024 * 50, "utf-8", new DefaultFileRenamePolicy());
		HttpSession session = req.getSession();
		String sessionID = null;
		
		if (session.getAttribute("sessionID") != null) {
			sessionID = (String) session.getAttribute("sessionID");
		}
		
		String category = mRequest.getParameter("category");
		String title = mRequest.getParameter("title");
		String content = mRequest.getParameter("content");

		BbsDto3 bDto = new BbsDto3();
		
		bDto.setId(sessionID);
		bDto.setBbsTitle(title);
		bDto.setBbsCategory(category);
		bDto.setBbsContent(content);

		List<BbsFileDto> bbsfDtoList = new ArrayList<BbsFileDto>();

		BbsFileDto bbsfDto = null;
		String name[] = new String[100];
		String changeName[] = new String[100];
		File file[] = new File[100];
		
		for (int i = 1; i < 50; i++) {
			name[i] = mRequest.getOriginalFileName("bbs_file" + i);
			
			if (name[i] == null) break;

			file[i] = mRequest.getFile("bbs_file" + i);
			changeName[i] = file[i].getName();

			bbsfDto = new BbsFileDto();
			bbsfDto.setOrgn_file_nm(path + name[i]);
			bbsfDto.setSave_file_nm(path + changeName[i]);

			bbsfDtoList.add(bbsfDto);
		}
		
		TransactionDao tDao = TransactionDao.getInstance();
		int resultTrans = tDao.insertAll(bDto, bbsfDtoList);
		req.setAttribute("resultTrans", resultTrans);
		RequestDispatcher rd = req.getRequestDispatcher("bbs.do");
		rd.forward(req, resp);
	}

}
