package frandz.api_test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
@Builder
@AllArgsConstructor
@Entity
@Table(name = "utilisateur")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "utilisateur_id", nullable = false)
    private Long id;
    @Getter
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    @Getter
    @Column(name = "avatar")
    private String avatar;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired = false;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked = false;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired = false;
    private Boolean enable = false;
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;


    public String setEmail(String email) {
        return this.email = email;
    }

    public String setAvatar(String avatar) {
        return this.avatar = avatar;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

}