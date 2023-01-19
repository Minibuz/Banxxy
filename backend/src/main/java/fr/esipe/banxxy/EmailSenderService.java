package fr.esipe.banxxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Returns a SimpleMailMessage with a receiver, subject, and body passed as parameters.
     * The sender of the email is "banxxy.esipe@gmail.com".
     *
     * @param toEmail The email address of the receiver of the to-be-sent email. (String)
     * @param subject The subject of the to-be-sent email. (String)
     * @param body The body of the to-be-sent email. (String)
     * @return message A SimpleMailMessage with the sender, receiver, subject and body set.
     */
    private SimpleMailMessage createEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        String sender = "banxxy.esipe@gmail.com";
        message.setFrom(sender);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        return message;
    }

    /**
     * Sends an email with the instructions for the receiver to reset his password.
     * @param toEmail The email address of the receiver. (String)
     */
    public void onResetPassword(String toEmail) {

        mailSender.send(createEmail(
                toEmail,
                "Demande de réinitialisation de mot de passe",
                "Une demande de réinitialisation du mot de passe de votre compte bancaire vient d'être effectué.\n" +
                        "Si cette demande ne vient pas de vous, veuillez ignorer ce mail et contacter votre conseiller Banxxy.\n" +
                        "Si vous êtes à l'origine de cette demande, veuillez cliquer sur le lien suivant : -------------------------"));
    }

    /**
     * Sends a notification email usually sent after to a user after his account has been created.
     * @param toEmail The email address of the receiver. (String)
     */
    public void onCreateAccount(String toEmail) {
        mailSender.send(createEmail(toEmail,
                "Validation de création de votre compte Banxxy",
                "Félicitations, vous venez de créer votre compte Banxxy.\n" +
                        "Pour vous connecter, veuillez vous rendre à l'adresse suivante : --------------------\n" +
                        "Votre conseiller Banxxy."));
    }
}
