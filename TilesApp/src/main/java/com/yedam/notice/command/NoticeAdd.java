package com.yedam.notice.command;

import java.io.*;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class NoticeAdd implements command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 등록 처리
		// form:multipart/form-data => 처리(MultipartRequest)
		// 생성자매개값 : 요청정보, 저장경로, 최대파일사이즈지정, 인코딩방식, 리네임정책

		String savePath = req.getServletContext().getRealPath("/upload");
		int maxSize = 1024 * 1024 * 10; // 파일 사이즈
		String encoding = "utf-8"; // 인코딩 방식

		try {
			// 파일업로드 서블릿

			MultipartRequest multi = //
					new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());

			String title = multi.getParameter("title");
			String subject = multi.getParameter("subject");
			String writer = multi.getParameter("writer");
			String fileName = "";

			Enumeration<?> files = multi.getFileNames();
			while (files.hasMoreElements()) {
				String file = (String) files.nextElement();
				System.out.println(file);

				fileName = multi.getFilesystemName(file);
			}
			
			// NoticeVO 생성
			NoticeVO vo = new NoticeVO();
			
			vo.setAttachFile(fileName);
			vo.setNoticeSubject(subject);
			vo.setNoticeTitle(title);
			vo.setNoticeWriter(writer);
			
			NoticeService service = new NoticeServiceImpl();
			service.addNotice(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "noticeList.do";
	}

}
