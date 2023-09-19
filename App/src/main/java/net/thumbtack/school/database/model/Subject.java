package net.thumbtack.school.database.model;

import java.util.Objects;

public class Subject {
//    Класс Subject - предмет в Школе программиста

    private int id; //Для несохраненного в БД Subject это поле имеет значение 0, после сохранения значение присваивается БД
    private String name; //Название предмета.


    public Subject(){

    }
    public Subject(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Subject(String name){
        this(0, name);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return getId() == subject.getId() && Objects.equals(getName(), subject.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
