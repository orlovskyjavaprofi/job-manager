package models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginCredentials
{

	@NotEmpty(message = "User nickname input must be 5 or more signs!")
	@Size(min=5, max=40)
	private String userNickName;
	
	@NotEmpty(message = "User password must be 8 or more signs!")
	@Size(min=8, max=40)
	private String userPassword;

	public String getUserNickName()
	{
		return userNickName;
	}

	public void setUserNickName(String userNickName)
	{
		this.userNickName = userNickName;
	}

	public String getUserPassword()
	{
		return userPassword;
	}

	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	@Override
	public String toString()
	{
		return "LoginCredentials [userNickName=" + userNickName + ", userPassword=" + userPassword + "]";
	}
	
	
}
