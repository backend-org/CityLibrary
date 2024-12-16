package ru.backend.UserService.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "app_user",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_fio",
                columnNames = {"firstname", "lastname", "patronymic"}
        )
)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birthYear")
    private Integer birthYear;

    public AppUser(String firstName, String lastName, String patronymic, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthYear = birthYear;
    }
}
