package br.com.ifood.adminservice.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor(onConstructor_ = @Builder)
@Entity
@Table(name = "user")
public class User {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Setter(AccessLevel.PRIVATE)
    @NonNull
    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Setter(AccessLevel.PRIVATE)
    @NonNull
    @Column(name = "username", nullable = false)
    private String username;

    @Setter(AccessLevel.PRIVATE)
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @Setter(AccessLevel.PRIVATE)
    @Builder.Default
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Deprecated
    User(){

    }

    public static User of(String fullname, String username, String password){
        return User.builder()
                .fullname( Objects.requireNonNull(fullname, "Full name is required"))
                .username(Objects.requireNonNull(username, "Username is required"))
                .password(Objects.requireNonNull(password, "Password is required"))
                .active(true)
                .build();
    }

}
