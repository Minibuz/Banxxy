package fr.esipe.banxxy.dto;

public class UserDetailDto {
    private final String firstName;
    private final String lastName;
    private final String userName;
    private final Long userId;
    private final String password;
    private final String mail;

    public UserDetailDto(String firstName, String lastName, String userName, Long userId, String password, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }
}
