package com.spring.mvc.commons.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BoardInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("게시판 인터셉터 발동!");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			System.out.println("회원 인증 실패!");
			//response.sendRedirect("/");
			
			// 응답형태 지정
			response.setContentType("text/html; charset=utf-8");
			
			// out객체를 만들어서 자바소스로 자바스크립트를 사용.
			PrintWriter out = response.getWriter();
			String element = "<script>"
							+ "alert('로그인을 해야해요!');"
							+ "location.href='/';"
							+ "</script>";
			out.print(element);//출력
			out.flush();//브라우저 출력버퍼 비우기
			out.close();//객체반납
			
			return false;
		}
		
		return true;
	}
	
}









