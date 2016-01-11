package isen.java2.study.data.stat;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by plesur on 1/5/16.
 */
public class AverageAgeByState implements Stat {

    @Override
    public String getQuery() {
        return "SELECT state, AVG(YEAR(CURRENT_TIMESTAMP) - YEAR(dateofbirth) - " +
                "(RIGHT(CURRENT_TIMESTAMP, 5) < RIGHT(dateofbirth, 5))) as age FROM person" +
                " GROUP BY state  ORDER BY age DESC";
    }

    @Override
    public String getDescription() {
        return "Gives the age average of each state.";
    }

    @Override
    public void handle(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println("\nState: " + resultSet.getString("state"));
            System.out.println("Average age: " + resultSet.getInt("age"));
        }
    }
}
