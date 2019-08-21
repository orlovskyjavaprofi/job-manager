package utils;

public interface EmailService
{
	boolean sendSimpleEmailMessage(String toRecipient, String subjectOfEamil, String bodyOfEmail);
}
