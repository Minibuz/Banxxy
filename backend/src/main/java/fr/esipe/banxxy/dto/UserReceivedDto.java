package fr.esipe.banxxy.dto;

public class UserReceivedDto {
    private final String firstName;
    private final String lastName;
    private final String mail;
    private final String userName;
    private final String password;
    private final String advisorId;

    public UserReceivedDto(String firstName, String lastName, String mail, String userName, String password, String advisorId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.userName = userName;
        this.password = password;
        this.advisorId = advisorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Long getAdvisorId() {
        return Long.getLong(advisorId);
    }
}
