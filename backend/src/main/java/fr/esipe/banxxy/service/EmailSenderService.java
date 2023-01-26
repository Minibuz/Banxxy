package fr.esipe.banxxy.service;

public interface EmailSenderService {

    /**
     * Sends an email with the instructions for the receiver to reset his password.
     * @param toEmail The email address of the receiver. (String)
     */
    void onResetPassword(String toEmail);

    /**
     * Sends a notification email usually sent after to a user after his account has been created.
     * @param toEmail The email address of the receiver. (String)
     */
    void onCreateAccount(String toEmail);

    void onDeleteAccount(String toEmail);

    void onCreateUser(String toEmail);

    void onDeleteUser(String toEmail);
}
