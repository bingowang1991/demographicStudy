package isen.java2.study.data.stat;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by plesur on 1/5/16.
 */
public class MostCommonBloodType implements Stat {

    private final String QUERY = "SELECT bloodtype, count(id) as total FROM person GROUP BY bloodtype ORDER BY total DESC";

    private final String DESCRIPTION = "Gives the most common blood type among all people stored in the database";

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
        if (resultSet.next()) {
            System.out.println("\nBlood type: " + resultSet.getString("bloodtype"));
        }
    }
}
