package school21.spring.service.models;

import java.util.Objects;

public class User {
    private Long ID;
    private String email;

    public User() {
    }

    public User(Long ID, String email) {
        this.ID = ID;
        this.email = email;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", email='" + email + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ID.equals(user.ID) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, email);
    }
}
