package testmatick;

import javax.mail.MessagingException;

public class Main {
    public static void main(String[] args) throws MessagingException {
        EmailGmail check = new EmailGmail();
        check.getLetters();
    }
}
