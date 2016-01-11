package isen.java2.study.data.stat;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by plesur on 1/5/16.
 */
public class CommonLastNamesByState implements Stat {

    private int numberOfOccurrences;
    private String query;

    public CommonLastNamesByState(int numberOfOccurrences) {
        this.numberOfOccurrences = numberOfOccurrences;
        query = "SELECT lastname, state, count(id) as total FROM person GROUP BY " +
                "state,lastname HAVING total > " + this.numberOfOccurrences + " ORDER BY total DESC, state";
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public String getDescription() {
        return "Gives the most common blood type among all people stored in the database";
    }

    @Override
    public void handle(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println("\nName: " + resultSet.getString("lastname"));
            System.out.println("State: " + resultSet.getString("state"));
            System.out.println("Number of occurrences: " + resultSet.getInt("total"));
        }
    }
}
