package com.suai.trainersuai.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "Reg_inf")
public class RegistrationUser {

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
    private String email;

    @Column(name = "Users_telephone_number")
    private String phone;

    @Column(name = "Users_special_info")
    private String info;

    @Column(name = "Users_avatar")
    private String avatar;

    @Column(name = "Users_password")
    private String password;

    public RegistrationUser() {
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

    public RegistrationUser(String name, String secondName, String thirdName, String email, String phone, String info, String avatar, String password) {
        this.name = name;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.email = email;
        this.phone = phone;
        this.info = info;
        this.avatar = avatar;
        this.password = password;
    }
}
