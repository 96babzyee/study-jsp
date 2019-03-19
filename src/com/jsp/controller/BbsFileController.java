package com.jsp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dao.BbsFileDao;

public class BbsFileController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fileId = req.getParameter("id");
		BbsFileDao bbsfDao = BbsFileDao.getInstance();
		String bbsFile = bbsfDao.selectById(fileId);

		InputStream is = null;
		OutputStream os = null;

		try {
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attatchment; filename=" + bbsFile);

			byte b[] = new byte[1024];
			is = new FileInputStream(bbsFile);
			os = resp.getOutputStream();
			int data = 0;
			while ((data = is.read(b, 0, b.length)) != -1) {
				os.write(b, 0, data);
			}
			os.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {os.close();} catch (IOException e) {};
			}
			if (is != null) {
				try {is.close();} catch (IOException e) {};
			}
		}
	}
}
