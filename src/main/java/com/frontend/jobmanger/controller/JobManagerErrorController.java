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
			resultPage = caseCheckOrginOfErrorAndShowPageAgain(resultPage, statusCode, request);
		}
		
		return resultPage;
	}

	private String caseCheckOrginOfErrorAndShowPageAgain(String resultPage, Integer statusCode, HttpServletRequest actualRequest)
	{
		String failedRequestUri = (String) actualRequest.getAttribute( RequestDispatcher.ERROR_REQUEST_URI.toString() );
		
		if (statusCode == HttpStatus.BAD_REQUEST.value())
		{
			//To:Do log when something goes wrong!
			System.out.println("Web page error status: "+statusCode+ " "+failedRequestUri);		
			resultPage = caseOfErrorDetailsShowThePreviosPageBeforeError(failedRequestUri);

		}
		
		return resultPage;
	}

	private String caseOfErrorDetailsShowThePreviosPageBeforeError(String failedRequestUri)
	{ 
		String defaultPage = "errorPage"; 
		if(failedRequestUri.equals("/submitNewUserReg")) {
			defaultPage = "userFormForRegNotFilled";
		}
		 
		return defaultPage;
	}

	@Override
	public String getErrorPath()
	{
		return ERROR_PATH;
	}
}
