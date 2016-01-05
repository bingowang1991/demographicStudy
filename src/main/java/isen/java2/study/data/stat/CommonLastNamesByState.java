package isen.java2.study.data.stat;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by plesur on 1/5/16.
 */
public class CommonLastNamesByState implements Stat {

    private final String DESCRIPTION = "Gives the most common blood type among all people stored in the database";
    private int numberOfOccurrences;
    private final String QUERY = "SELECT lastname, state, count(id) as total FROM person GROUP BY\n" +
            "state,lastname HAVING total > " + numberOfOccurrences + " ORDER BY total DESC, state";

    public CommonLastNamesByState(int numberOfOccurrences) {
        this.numberOfOccurrences = numberOfOccurrences;
    }

    @Override
    public String getQuery() {
        return QUERY;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
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
