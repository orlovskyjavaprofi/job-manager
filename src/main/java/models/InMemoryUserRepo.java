package models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepo
{
	private List<User> ListOfUsers;

	public InMemoryUserRepo()
	{
		ListOfUsers = new ArrayList<User>();
	}

	public Boolean addUser(User userObj)
	{
		Boolean result = false;

		if (userObj != null)
		{
			result = true;
			ListOfUsers.add(userObj);
		}
		
		return result;
	}

	public List<User> getListOfUsers()
	{
		return ListOfUsers;
	}

	public void setListOfUsers(List<User> listOfUsers)
	{
		ListOfUsers = listOfUsers;
	}

	@Override
	public String toString()
	{
		return "InMemoryUserRepo [ListOfUsers=" + ListOfUsers + "]";
	}

}
