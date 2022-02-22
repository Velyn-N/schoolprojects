package de.nmadev.notes;

import de.nmadev.notes.db.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main implements Runnable {

    public static void main(String[] args) {
        new Main().run();
    }

    @Override
    public void run() {
        try {
            createDatabase();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    public void createDatabase() throws SQLException {
        String createCategoryTable = "DROP TABLE IF EXISTS `category`;" +
                "CREATE TABLE `category` (" +
                "  `id` bigint NOT NULL AUTO_INCREMENT," +
                "  `name` varchar(100) NOT NULL," +
                "  `color` varchar(100) DEFAULT NULL," +
                "  `noteamount` int DEFAULT '0'," +
                "  PRIMARY KEY (`id`)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

        String createNoteTable = "DROP TABLE IF EXISTS `note`;" +
                "CREATE TABLE `note` (" +
                "  `id` bigint NOT NULL AUTO_INCREMENT," +
                "  `title` varchar(100) NOT NULL," +
                "  `content` text," +
                "  `creationdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "  `categoryid` bigint DEFAULT NULL," +
                "  PRIMARY KEY (`id`)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

        Connection connection = DataSource.getConnection();

        connection.prepareStatement(createCategoryTable).execute();
        connection.prepareStatement(createNoteTable).execute();
    }

    public void insertData() {

    }

    public void readData() {

    }

    public void deleteDatabase() {

    }
}
