package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.*;
import java.util.*;

public class JdbcService {

    public static void insertTrainee(Trainee trainee) throws SQLException {
        String insertQuery = "INSERT INTO trainee(id, firstName, lastName, rating) VALUES(?, ?, ?, ?)";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
            stmt.setNull(1, Types.INTEGER);
            stmt.setString(2, trainee.getFirstName());
            stmt.setString(3, trainee.getLastName());
            stmt.setInt(4, trainee.getRating());
            stmt.executeUpdate(); //возвращает количество измененных строк
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    trainee.setId(generatedKeys.getInt(1));
                }
            }
        }
    }
//    Добавляет Trainee в базу данных.

    public static void updateTrainee(Trainee trainee) throws SQLException{
        String updateQuery = "UPDATE trainee SET firstName = ?, lastName = ?, rating = ? WHERE id = ?";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(updateQuery)){
            stmt.setString(1, trainee.getFirstName());
            stmt.setString(2, trainee.getLastName());
            stmt.setInt(3, trainee.getRating());
            stmt.setInt(4, trainee.getId());
            stmt.executeUpdate();
        }
    }
//    Изменяет ранее записанный Trainee в базе данных. В случае ошибки выбрасывает
//    SQLException.

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException{
        String selectQuery = "SELECT * FROM trainee WHERE id = ?";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(selectQuery)){
            stmt.setInt(1, traineeId);
            try(ResultSet rs = stmt.executeQuery()) { //executeQuery() возвращает ResultSet
                if(rs.next()){
                    int id = rs.getInt("id");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    int rating = rs.getInt("rating");
                    return new Trainee(id, firstName, lastName, rating);
                }
            }
        }
        return null;

    }
//    Получает Trainee из базы данных по его ID, используя метод получения “по именам полей”.
//    Если Trainee с таким ID нет, возвращает null.

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException{
        String selectQuery = "SELECT * FROM trainee WHERE id = ?";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(selectQuery)){
            stmt.setInt(1, traineeId);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    int id = rs.getInt(1);
                    String firstName = rs.getString(2);
                    String lastName = rs.getString(3);
                    int rating = rs.getInt(4);
                    return new Trainee(id, firstName, lastName, rating);
                }
            }
        }
        return null;
    }
//    Получает Trainee из базы данных по его ID, используя метод получения “по номерам полей”.
//    Если Trainee с таким ID нет, возвращает null.

    public static List<Trainee> getTraineesUsingColNames() throws SQLException{
        String selectQuery = "SELECT * FROM trainee";
        Connection connection = JdbcUtils.getConnection();
        List<Trainee> traineeList = new ArrayList<>();
        try(PreparedStatement stmt = connection.prepareStatement(selectQuery);
            ResultSet rs = stmt.executeQuery(selectQuery)){
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int rating = rs.getInt("rating");
                traineeList.add(new Trainee(id, firstName, lastName, rating));
            }
        }
        return traineeList;
    }
//    Получает все Trainee из базы данных, используя метод получения “по именам полей”. Если ни
//    одного Trainee в БД нет, возвращает пустой список.

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException{
        String selectQuery = "SELECT * FROM trainee";
        Connection connection = JdbcUtils.getConnection();
        List<Trainee> traineeList = new ArrayList<>();
        try(PreparedStatement stmt = connection.prepareStatement(selectQuery);
            ResultSet rs = stmt.executeQuery(selectQuery)){
            while(rs.next()){
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int rating = rs.getInt(4);
                traineeList.add(new Trainee(id, firstName, lastName, rating));
            }
        }
        return traineeList;
    }
//    Получает все Trainee из базы данных, используя метод получения “по номерам полей”. Если ни
//    одного Trainee в БД нет, возвращает пустой список.

    public static void deleteTrainee(Trainee trainee) throws SQLException{
        String deleteQuery = "DELETE FROM trainee WHERE id = ?";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(deleteQuery)){
            stmt.setInt(1, trainee.getId());
            stmt.executeUpdate(); //возвращает количество измененных строк
        }
    }
//    Удаляет Trainee из базы данных.

    public static void deleteTrainees() throws SQLException{
        String deleteQuery = "DELETE FROM trainee";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(deleteQuery)){
            stmt.executeUpdate();
        }
    }
//    Удаляет все Trainee из базы данных

    public static void insertSubject(Subject subject) throws SQLException{
        String insertQuery = "INSERT INTO subject VALUES(?, ?)";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
            stmt.setNull(1, Types.INTEGER);
            stmt.setString(2, subject.getName());
            stmt.executeUpdate();
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    subject.setId(generatedKeys.getInt(1));
                }
            }
        }
    }
//    Добавляет Subject в базу данных

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException{
        String selectQuery = "SELECT * FROM subject WHERE id = ?";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(selectQuery)){
            stmt.setInt(1, subjectId);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    return new Subject(id, name);
                }
            }
        }
        return null;
    }
//    Получает Subject из базы данных по его ID, используя метод получения “по именам полей”.
//    Если Subject с таким ID нет, возвращает null.

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException{
        String selectQuery = "SELECT * FROM subject WHERE id = ?";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(selectQuery)){
            stmt.setInt(1, subjectId);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    return new Subject(id, name);
                }
            }
        }
        return null;
    }
//    Получает Subject из базы данных по его ID, используя метод получения “по номерам полей”.
//    Если Subject с таким ID нет, возвращает null.

    public static void deleteSubjects() throws SQLException{
        String deleteQuery = "DELETE FROM subject";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(deleteQuery)){
            stmt.executeUpdate(deleteQuery);
        }
    }
//    Удаляет все Subject из базы данных.

    public static void insertSchool(School school) throws SQLException{
        String insertQuery = "INSERT INTO school VALUES(?, ?, ?)";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
            stmt.setNull(1, Types.INTEGER);
            stmt.setString(2, school.getName());
            stmt.setInt(3, school.getYear());
            stmt.executeUpdate();
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if(generatedKeys.next()){
                    school.setId(generatedKeys.getInt(1));
                }
            }
        }
    }
//    Добавляет School в базу данных

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException{
        String selectQuery = "SELECT * FROM school WHERE id = ?";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(selectQuery)){
            stmt.setInt(1, schoolId);
            try(ResultSet rs = stmt.executeQuery()){
               if(rs.next()){
                   int id = rs.getInt("id");
                   String name = rs.getString("name");
                   int year = rs.getInt("year");
                   return new School(id, name, year);
               }
            }
        }
        return null;
    }
//    Получает School из базы данных по ее ID, используя метод получения “по именам полей”. Если
//    School с таким ID нет, возвращает null.

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException{
        String selectQuery = "SELECT * FROM school WHERE id = ?";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(selectQuery)){
            stmt.setInt(1, schoolId);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    int year = rs.getInt(3);
                    return new School(id, name, year);
                }
            }
        }
        return null;
    }
//    Получает School из базы данных по ее ID, используя метод получения “по номерам полей”.
//    Если School с таким ID нет, возвращает null.

    public static void deleteSchools() throws SQLException{
        String deleteQuery = "DELETE FROM school";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(deleteQuery)){
            stmt.executeUpdate(deleteQuery);
        }
    }
//    Удаляет все School из базы данных. Если список Group в School не пуст, удаляет все Group для
//    каждой School.

//     `group`

    public static void insertGroup(School school, Group group) throws SQLException{
        String insertQuery = "INSERT INTO `group` VALUES (?, ?, ?, ?)";
        Connection connection = JdbcUtils.getConnection();
        try(PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
            stmt.setNull(1, Types.INTEGER);
            stmt.setString(2, group.getName());
            stmt.setString(3, group.getRoom());
            stmt.setInt(4, school.getId());
            stmt.executeUpdate();
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    group.setId(generatedKeys.getInt(1));
                }
            }
        }
    }
//    Добавляет Group в базу данных, устанавливая ее принадлежность к школе School.

    public static School getSchoolByIdWithGroups(int id) throws SQLException{
        String selectQuery = "SELECT * FROM SCHOOL INNER JOIN `group` ON schoolId = school.id WHERE school.id = ?";
        Connection connection = JdbcUtils.getConnection();
        boolean flag = true;
        School school = new School();
        try(PreparedStatement stmt = connection.prepareStatement(selectQuery)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){ // executeQuery возвращает ResultSet
                while(rs.next()){
                    if(flag){
                        int oneMoreId = rs.getInt(1);
                        String name = rs.getString(2);
                        int year = rs.getInt(3);
                        school = new School(oneMoreId, name, year);
                        flag = false;
                    }
                    int oneMoreId = rs.getInt(4);
                    String name = rs.getString(5);
                    String room = rs.getString(6);
                    school.addGroup(new Group(oneMoreId, name, room));
                }
            }
        }
        return school;
    }
//    Получает School по ее ID вместе со всеми ее Group из базы данных. Если School с таким ID нет,
//    возвращает null. Метод получения (по именам или номерам полей) - на Ваше усмотрение.

    public static List<School> getSchoolsWithGroups() throws SQLException{
        String selectQuery = "SELECT * FROM school INNER JOIN `group` ON schoolId = school.id";
        Connection connection = JdbcUtils.getConnection();
        Map<Integer, School> map = new HashMap<>();
        try(PreparedStatement stmt = connection.prepareStatement(selectQuery);
            ResultSet rs = stmt.executeQuery(selectQuery)){
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int year = rs.getInt(3);
                map.putIfAbsent(id, new School(id, name, year));
                int idGroup = rs.getInt(4);
                String nameOfGroup = rs.getString(5);
                String room = rs.getString(6);
                map.get(id).addGroup(new Group(idGroup, nameOfGroup, room));
            }
            return new ArrayList<>(map.values());
        }
    }
//    Получает список всех School вместе со всеми их Group из базы данных. Если ни одной School в
//    БД нет, возвращает пустой список. Метод получения (по именам или номерам полей) - на Ваше
//    усмотрение.


}
