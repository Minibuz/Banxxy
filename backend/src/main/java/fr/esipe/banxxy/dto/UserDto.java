package fr.esipe.banxxy.dto;

public class UserDto {
    private final String firstName;
    private final String lastName;
    private final Long userId;

    public UserDto(String firstName, String lastName, Long userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getUserId() {
        return userId;
    }
}
