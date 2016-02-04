package com.razorthink.junit.beans;

import java.util.HashSet;

/**
 * Created by shams on 2/3/16.
 */
public class SimpleDAO extends BaseDAO {

    public boolean createTable() {
      return executeQuery("create table IF NOT EXISTS message(id int primary key AUTO_INCREMENT NOT NULL, message varchar(200));");
    }

    public boolean saveMessage(String message) {
        return executeQuery("insert into message(message)values('"+message+"');");
    }

    public HashSet<String> getDataFromData() {
        return executeQueryAndReturn("select *  from message;" );
    }
}
