package controller;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyPageController extends HttpServlet
{
	public void doProcess(  HttpServletRequest req, HttpServletResponse resp )
	{
		String uri = req.getRequestURI(); // ��ü uri
		String contextPath = req.getContextPath(); // �տ� ��Ʈ���� ��θ�
		String servletName = uri.substring( contextPath.length() );
	}

	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException 
	{
		
	}

	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException 
	{

	}
	
}
