package net.thumbtack.school.database.model;

import java.util.Objects;

public class Trainee {
//    Класс Trainee - студент Школы программиста
    private int id; //Для несохраненного в БД Trainee это поле имеет значение 0, после сохранения значение присваивается БД
    private String firstName;
    private String lastName;
    private int rating;


    public Trainee(){

    }

    public Trainee(int id, String firstName, String lastName, int rating){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }

    public Trainee(String firstName, String lastName, int rating){
        this(0, firstName, lastName, rating);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trainee)) return false;
        Trainee trainee = (Trainee) o;
        return getId() == trainee.getId() && getRating() == trainee.getRating() && Objects.equals(getFirstName(), trainee.getFirstName()) && Objects.equals(getLastName(), trainee.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getRating());
    }
}
