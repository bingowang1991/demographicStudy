package isen.java2.study.service;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import isen.java2.study.data.Person;
import isen.java2.study.data.stat.Stat;

import java.sql.*;
import java.util.Properties;

public class DBService {

    private final String SAVE_QUERY = "INSERT INTO `isen_project`.`person` (`lastname`, `firstname`, `sex`, `streetname`, `state`, `city`, `bloodtype`, `dateofbirth`) VALUES (?, ?,?, ?, ?, ?, ?, ?)";
    MysqlDataSource dataSource;

    public DBService(Properties properties) {
        dataSource = new MysqlDataSource();
        dataSource.setDatabaseName(properties.getProperty("db.schema"));
        dataSource.setServerName(properties.getProperty("db.server"));
        dataSource.setUser(properties.getProperty("db.username"));
        dataSource.setPort(Integer.parseInt(properties.getProperty("db.port")));
        dataSource.setPassword(properties.getProperty("db.password"));
        dataSource.setDatabaseName(properties.getProperty("db.schema"));
    }

    public void save(Person person) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY)) {
                preparedStatement.setString(1, person.getLastName());
                preparedStatement.setString(2, person.getFirstName());
                preparedStatement.setString(3, person.getSex().toString());
                preparedStatement.setString(4, person.getStreetName());
                preparedStatement.setString(5, person.getState());
                preparedStatement.setString(6, person.getCity());
                preparedStatement.setString(7, person.getBloodType());
                preparedStatement.setDate(8, Date.valueOf(person.getDateOfBirth()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeStat(Stat stat) {
        String query = stat.getQuery();

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                stat.handle(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}