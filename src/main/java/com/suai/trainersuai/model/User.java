package com.suai.trainersuai.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "Reg_inf", uniqueConstraints = {
                @UniqueConstraint(columnNames = "Users_ssid"),
                @UniqueConstraint(columnNames = "Users_email")
      })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Users_ssid")
    private Long id;

    @Column(name = "Users_name")
    private String name;

    @Column(name = "Users_second_name")
    private String secondName;

    @Column(name = "Users_third_name")
    private String thirdName;

    @Column(name = "Users_email")
    @NonNull
    @Email
    private String email;

    @Column(name = "Users_telephone_number")
    private String phone;

    @Column(name = "Users_special_info")
    private String info;

    @Column(name = "Users_avatar")
    private String avatar;

    @Column(name = "Users_password")
    private String password;

    public User() {
    }

    @Override
    public String toString() {
        return "RegistrationUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", thirdName='" + thirdName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", info='" + info + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User(String name, String secondName, String thirdName, String email, String phone, String info, String avatar, String password) {
        this.name = name;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.email = email;
        this.phone = phone;
        this.info = info;
        this.avatar = avatar;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNew() {
        return id == null;
    }

}
