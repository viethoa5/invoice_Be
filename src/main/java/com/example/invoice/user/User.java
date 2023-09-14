package com.example.invoice.user;

import com.example.invoice.invoice.Invoice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int UserId;
    @Getter
    @Column(name = "Gmail")
    private String gmail;
    @Getter
    @Column(name = "UserPassword")
    private String userPassword;
    @Getter
    @Column(name = "Position")
    private String position;
    @Getter
    @Column(name = "Fullname")
    private String fullName;
    @JsonIgnore
    @Getter
    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Invoice> invoicesFromAgent;
    @JsonIgnore
    @Getter
    @Setter
    @OneToMany(mappedBy = "consultant", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Set<Invoice> invoicesFromConsultant;
    @Setter
    @Getter
    private boolean isAdmin;
    @Getter
    @Setter
    private boolean isActive;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    public User(int id) {
        this.UserId = id;
    }
    public User() {}
    public User(String username, String password, String fullName, String position) {
        this.gmail = username;
        this.userPassword = password;
        this.fullName = fullName;
        this.position = position;
        this.isAdmin = false;
        this.isActive = false;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.gmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setFullName(String fullname) {
        this.fullName = fullname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getUserId() {
        return UserId;
    }

}
