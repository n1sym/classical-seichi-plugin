package com.n1sym.seichi.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class SeichiSkillRepository extends BaseRepository {
  public static Integer find(Integer id) {
    try (Connection connection = connection()){
      try (PreparedStatement statement = connection.prepareStatement(
          "select * from seichi_skill_level where id = ?"
      )) {
        // get level
        statement.setInt(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
              return resultSet.getInt("level");
          }
        }
        // insert data
        try (PreparedStatement insert = connection.prepareStatement(
            "INSERT INTO seichi_skill_level (id, level) VALUES (?,?)"
        )) {
          insert.setInt(1, id);
          insert.setInt(2, 1);
          insert.executeUpdate();
        }
        // re get id
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            return resultSet.getInt("level");
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

  public static boolean set(int id, int level){
    try (Connection connection = connection();
         PreparedStatement statement = connection.prepareStatement(
          "UPDATE seichi_skill_level SET level=? WHERE id=?"
      )) {
        statement.setInt(1, level);
        statement.setInt(2, id);
        return (statement.executeUpdate() != 0);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
  }
}
