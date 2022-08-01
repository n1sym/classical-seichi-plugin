package com.n1sym.seichi.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseRepository {
  public static Connection connection() {
    String dbname = "development.db"; // 利用するデータベースファイル
    Connection conn = null;
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conn;
  }
}
