package fr.esipe.banxxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    private SimpleMailMessage createEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        String sender = "banxxy.esipe@gmail.com";
        message.setFrom(sender);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        return message;
    }

    public void onResetPassword(String toEmail) {

        mailSender.send(createEmail(
                toEmail,
                "Demande de réinitialisation de mot de passe",
                "Une demande de réinitialisation du mot de passe de votre compte bancaire vient d'être effectué.\n" +
                        "Si cette demande ne vient pas de vous, veuillez ignorer ce mail et contacter votre conseiller Banxxy.\n" +
                        "Si vous êtes à l'origine de cette demande, veuillez cliquer sur le lien suivant : -------------------------"));
    }

    public void onCreateAccount(String toEmail) {
        mailSender.send(createEmail(toEmail,
                "Validation de création de votre compte Banxxy",
                "Félicitations, vous venez de créer votre compte Banxxy.\n" +
                        "Pour vous connecter, veuillez vous rendre à l'adresse suivante : --------------------\n" +
                        "Votre conseiller Banxxy."));
    }
}
