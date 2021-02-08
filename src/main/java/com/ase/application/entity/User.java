package com.ase.application.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 5, max = 50, message = "Your full name must be between 5 to 50  characters long.")
    private String fullName;

    @NotNull
    @Size(min = 5, max = 20, message = "Username must be between 5 to 20  characters long.")
//    @Pattern(regexp = "^[a-zA-Z0-9] + $", message = "Username must be alphanumeric with no space")
    private String userName;

    @NotEmpty
    @Size(min = 6, max = 20, message = "User password must be between  to 20  characters long.")
    private String userPassword;

    @Email
    private String userMail;

    @NotEmpty
    @Pattern(regexp = "^[0-9]+$", message = "Your Contact number must be numeric ")
    private String userContact;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(userMail, user.userMail) &&
                Objects.equals(userContact, user.userContact) &&
                userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash( fullName, userName, userPassword, userMail, userContact, userType);
    }

    @Override
    public String toString() {
        return "User{" +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userContact='" + userContact + '\'' +
                ", userType=" + userType +
                '}';
    }
}
