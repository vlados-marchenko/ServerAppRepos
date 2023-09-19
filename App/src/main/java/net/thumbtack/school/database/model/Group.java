package net.thumbtack.school.database.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group {
//    Класс Group - группа Школы программиста

    private int id; //Для несохраненной в БД Group это поле имеет значение 0, после сохранения значение присваивается БД
    private String name; //Название группы
    private String room; //Номер аудитории
    private List<Trainee> trainees; //Список учащихся
    private List<Subject> subjects; //Список предметов


    public Group(){

    }
    public Group(int id, String name, String room, List<Trainee> trainees, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.trainees = trainees;
        this.subjects = subjects;
    }

    public Group(int id, String name, String room){
        this(id, name, room, new ArrayList<>(), new ArrayList<>());
    }

    public Group(String name, String room){
        this(0, name, room, new ArrayList<>(), new ArrayList<>());
    }


    public void addTrainee(Trainee trainee){
        trainees.add(trainee);
    }
//    Добавляет Trainee в Group
    public void removeTrainee(Trainee trainee){
        trainees.remove(trainee);
    }
//    Удаляет Trainee из Group
    public void addSubject(Subject subject){
        subjects.add(subject);
    }
//    Добавляет Subject в Group
    public void removeSubject(Subject subject){
        subjects.remove(subject);
    }
//    Удаляет Subject из Group


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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(List<Trainee> trainees) {
        this.trainees = trainees;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return getId() == group.getId() && Objects.equals(getName(), group.getName()) && Objects.equals(getRoom(), group.getRoom()) && Objects.equals(getTrainees(), group.getTrainees()) && Objects.equals(getSubjects(), group.getSubjects());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRoom(), getTrainees(), getSubjects());
    }
}
