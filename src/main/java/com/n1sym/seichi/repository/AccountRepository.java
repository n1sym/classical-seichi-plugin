package com.n1sym.seichi.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.UUID;

public class AccountRepository extends BaseRepository {
  public static Integer find(UUID uuid) {
    try (Connection connection = connection()) {
      try (PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM account WHERE uuid = ?")) {
        statement.setString(1, uuid.toString());
        // get id
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            return resultSet.getInt("id");
          }
        }
        // insert id
        try (PreparedStatement insert = connection.prepareStatement(
            "INSERT INTO account (uuid) VALUES(?)")) {
          insert.setString(1, uuid.toString());
          insert.executeUpdate();
        }
        // re get id
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            return resultSet.getInt("id");
          }
        }
      } catch (SQLException e) {
        connection.rollback();
        throw e;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    throw new RuntimeException("SQlite Connection failed.");
  }
}
