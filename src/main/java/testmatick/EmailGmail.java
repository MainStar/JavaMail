package testmatick;

import javax.mail.*;
import java.util.Properties;

public class EmailGmail extends Authenticator {

    private static final String login = "vladislav.postoyan@gmail.com";
    private static final String password = "12345pos";
    private static final String subject = "Tahometer.com - time-sheet for the period from 05 March to 11 March is closed";
    private Properties prop;

    public void getLetters() throws MessagingException {

        prop = System.getProperties();
        prop.put("mail.store.protocol", "pop3");
        prop.put("mail.pop3s.host", "pop.gmail.com");
        prop.put("mail.pop3s.port", "995");
        prop.put("mail.pop3.starttls.enable", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", "relay.jangosmtp.net");
        prop.put("mail.smtp.port", "25");


        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });
        Store store = session.getStore("pop3s");
        store.connect("pop.gmail.com", login, password);

        Folder inboxFolder = store.getFolder("Inbox");
        inboxFolder.open(Folder.READ_ONLY);
        Message [] messages = inboxFolder.getMessages();

        checkSubjects(subject, messages);
    }

    private void checkSubjects(String subject, Message[] messages) throws MessagingException {

        for (int i = 0; i < messages.length; i++){
            if (messages[i].getSubject().equals(subject)){
                System.out.println(messages[i].getSubject());
            }
        }
    }
}