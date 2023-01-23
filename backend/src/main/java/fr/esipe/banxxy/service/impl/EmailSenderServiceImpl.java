package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;

    @Autowired
    EmailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

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

    @Override
    public void onResetPassword(String toEmail) {

        mailSender.send(createEmail(
                toEmail,
                "Demande de réinitialisation de mot de passe",
"""
Une demande de réinitialisation du mot de passe de votre compte bancaire vient d'être effectué.
Si cette demande ne vient pas de vous, veuillez ignorer ce mail et contacter votre conseiller Banxxy.
Si vous êtes à l'origine de cette demande, veuillez cliquer sur le lien suivant : -------------------------
"""));
    }

    @Override
    public void onCreateAccount(String toEmail) {
        mailSender.send(createEmail(toEmail,
                "Validation de création de votre compte Banxxy",
"""
Félicitations, vous venez de créer votre compte Banxxy.
Pour vous connecter, veuillez vous rendre à l'adresse suivante : --------------------
Votre conseiller Banxxy.
"""));
    }
}
