package fr.esipe.banxxy.dto.user;

public class UserReceivedDto {
    private String firstName;
    private String lastName;
    private String mail;
    private String userName;

    private String password;
    private String advisorId;

    public UserReceivedDto(String firstName, String lastName,
                           String mail, String userName,
                           String password, String advisorId) {
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAdvisorIdAsLong() {
        return Long.parseLong(advisorId);
    }

    public String getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }
}
