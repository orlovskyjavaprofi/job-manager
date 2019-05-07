package com.frontend.jobmanger.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobManagerErrorController implements ErrorController
{
	private static final String ERROR_PATH = "/error";

	@RequestMapping(ERROR_PATH)
	public String internalServerError(HttpServletRequest request)
	{
		String resultPage = "errorPage";
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		resultPage = checkTheRequestStateOfWebPage(request, resultPage, status);

		return resultPage;
	}

	private String checkTheRequestStateOfWebPage(HttpServletRequest request, String resultPage, Object status)
	{
		if (status != null)
		{
			Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
			
			resultPage = casePageNotExist(resultPage, statusCode);
		}
		
		return resultPage;
	}

	private String casePageNotExist(String resultPage, Integer statusCode)
	{
		if (statusCode == HttpStatus.NOT_FOUND.value())
		{
			System.out.println("Web page status: "+statusCode);
			resultPage = "pageNotAvailable";
		}
		
		return resultPage;
	}

	@Override
	public String getErrorPath()
	{
		return ERROR_PATH;
	}
}
