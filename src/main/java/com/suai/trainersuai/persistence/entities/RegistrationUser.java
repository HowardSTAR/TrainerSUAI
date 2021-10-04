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

}
