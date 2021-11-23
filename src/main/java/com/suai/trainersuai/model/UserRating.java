package com.suai.trainersuai.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Users_results")
public class UserRating {

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

    public UserRating() {}

    public UserRating(int stat, String level, long userId) {
        this.stat = stat;
        this.lvl = level;
        this.idRegistration = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRating that = (UserRating) o;
        return stat == that.stat &&
                idRegistration == that.idRegistration &&
                Objects.equals(id, that.id) &&
                Objects.equals(lvl, that.lvl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stat, lvl, idRegistration);
    }

    public UserRating(long id, int stat, long userId) {
        this.id = id;
        this.stat = stat;
//        this.lvl = level;
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
