package com.n1sym.seichi.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Optional;

public class AccountNameRepository extends BaseRepository {
  public static Optional<String> find(int id) {
    try (Connection connection = connection()) {
      try (PreparedStatement statement = connection.prepareStatement(
          "select * from account_name where id = ?")) {
        statement.setInt(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            return Optional.of(resultSet.getString("name"));
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return Optional.empty();
  }

  public static boolean create(int id, String name){
    try (
      Connection connection = connection();
      PreparedStatement statement = connection.prepareStatement("INSERT INTO account_name (id, name) VALUES(?,?)")) {
        statement.setInt(1, id);
        statement.setString(2, name);
        if (statement.executeUpdate() != 0){
          return true;
        }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return false;
  }
}
