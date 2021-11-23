package com.suai.trainersuai.to;

import java.util.Objects;

public class UserToRating {

    private String firstName;
    private String secondName;
    private String thirdName;
    private int rating;

    public UserToRating() { }

    public UserToRating(String firstName, String secondName, String thirdName, int rating) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.rating = rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "UserToRating{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", thirdName='" + thirdName + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserToRating that = (UserToRating) o;
        return rating == that.rating &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(secondName, that.secondName) &&
                Objects.equals(thirdName, that.thirdName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, thirdName, rating);
    }
}
