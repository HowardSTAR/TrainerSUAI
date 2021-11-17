package com.suai.trainersuai.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "Users_results")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Session_id")
    private Long id;

    @Column(name = "Player_statistics")
    private int stat;

    @Column(name = "Game_difficulty_lvl")
    private String lvl;

    @Column(name = "Users_ssid")
    private long idRegistration;

    public User() {}

    public User(int stat, String level, long userId) {
        this.stat = stat;
        this.lvl = level;
        this.idRegistration = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public long getIdRegistration() {
        return idRegistration;
    }

    public void setIdRegistration(long idRegistration) {
        this.idRegistration = idRegistration;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", stat=" + stat +
                ", lvl='" + lvl + '\'' +
                ", idRegistration=" + idRegistration +
                '}';
    }

}
