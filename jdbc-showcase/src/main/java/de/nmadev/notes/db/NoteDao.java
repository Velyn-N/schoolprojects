package de.nmadev.notes.db;

import de.nmadev.notes.db.entities.Category;
import de.nmadev.notes.db.entities.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDao {

    public List<Note> getAllNotes() {
        List<Note> allNotes = new ArrayList<>();
        try {
            Connection con = DataSource.getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM note;");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Note note = new Note();

                note.setId(resultSet.getLong("id"));
                note.setTitle(resultSet.getString("title"));
                note.setContent(resultSet.getString("content"));
                note.setCreationDate(resultSet.getTimestamp("creationdate").toLocalDateTime());
                note.setCategoryId(resultSet.getLong("categoryid"));

                allNotes.add(note);
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        return allNotes;
    }

    public List<Category> getAllCategories() {
        List<Category> allCategories = new ArrayList<>();
        try {
            Connection con = DataSource.getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM category;");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();

                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));
                category.setColor(resultSet.getString("color"));
                category.setNoteAmount(resultSet.getInt("noteamount"));

                allCategories.add(category);
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        return allCategories;
    }

    public Category findCategory(long id) {
        try {
            Connection con = DataSource.getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM category WHERE id = ?;");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Category category = new Category();

            resultSet.first();

            category.setId(resultSet.getLong("id"));
            category.setName(resultSet.getString("name"));
            category.setColor(resultSet.getString("color"));
            category.setNoteAmount(resultSet.getInt("noteamount"));

            return category;
        } catch (SQLException e) {
            return null;
        }
    }

    public void mergeNote(Note note) throws SQLException {
        Connection con = DataSource.getConnection();

        PreparedStatement statement = con.prepareStatement("UPDATE note SET title = ?, content = ?, creationDate = ?, categoryid = ? where id = ?;");

        statement.setString(1, note.getTitle());
        statement.setString(2, note.getContent());
        statement.setTimestamp(3, Timestamp.valueOf(note.getCreationDate()));
        statement.setLong(4, note.getCategoryId());
        statement.setLong(5, note.getId());

        statement.executeQuery();
    }

    public void mergeCategory(Category category) throws SQLException {
        Connection con = DataSource.getConnection();

        PreparedStatement statement = con.prepareStatement("UPDATE category SET name = ?, color = ?, noteamount = ? WHERE id = ?;");

        statement.setString(1, category.getName());
        statement.setString(2, category.getColor());
        statement.setInt(3, category.getNoteAmount());
        statement.setLong(4, category.getId());

        statement.executeQuery();
    }

    public void persistNote(Note note) throws SQLException {
        Connection con = DataSource.getConnection();

        PreparedStatement statement = con.prepareStatement("INSERT INTO note VALUES (title = ?, content = ?, creationdate = ?, categoryid = ?);");

        statement.setString(1, note.getTitle());
        statement.setString(2, note.getContent());
        statement.setTimestamp(3, Timestamp.valueOf(note.getCreationDate()));
        statement.setLong(4, note.getCategoryId());

        statement.executeQuery();
    }

    public void persistCategory(Category category) throws SQLException {
        Connection con = DataSource.getConnection();

        PreparedStatement statement = con.prepareStatement("INSERT INTO category VALUES (name = ?, color = ?, noteamount = ?);");

        statement.setString(1, category.getName());
        statement.setString(2, category.getColor());
        statement.setInt(3, category.getNoteAmount());

        statement.executeQuery();
    }

    public List<Note> searchNote(String searchString) {
        ArrayList<Note> foundNotes = new ArrayList<>();
        try {
            Connection con = DataSource.getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM note WHERE title LIKE '%?%' OR content LIKE '%?%';");

            statement.setString(1, searchString);
            statement.setString(2, searchString);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Note note = new Note();

                note.setId(resultSet.getLong("id"));
                note.setTitle(resultSet.getString("title"));
                note.setContent(resultSet.getString("content"));
                note.setCreationDate(resultSet.getTimestamp("creationdate").toLocalDateTime());
                note.setCategoryId(resultSet.getLong("categoryid"));

                foundNotes.add(note);
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        return foundNotes;
    }
}
