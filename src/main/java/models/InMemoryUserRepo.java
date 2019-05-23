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
			userObj.setUserLoginState(false);
			result = true;
			System.out.println("add Uer to repo: \n"+userObj.toString());
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

	public User findUser(User userForSearch)
	{
	   User userFound = null;
	   for (User user : this.getListOfUsers())
	   {
		  if (userForSearch.equals(user)) {
			  userFound = user;
			  break;
		  }
	   }		
	   
	   return userFound;
	}

	public User findUserByGivenName(String userNickName)
	{
		User userFound = null;

		for (User user : this.getListOfUsers())
		{
           if(user.getUserNickName().equals(userNickName)) {
        	       userFound = user;
//        	       System.out.println(userFound.toString());
        	       break;
           }
		}

		return userFound;
	}

}
