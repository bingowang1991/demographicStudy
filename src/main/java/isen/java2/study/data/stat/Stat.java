package isen.java2.study.data.stat;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by plesur on 1/4/16.
 */
public interface Stat {

    String getQuery();

    String getDescription();

    void handle(ResultSet resultSet) throws SQLException;


}
