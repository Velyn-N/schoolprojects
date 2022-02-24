package de.nmadev.notes.db;

import de.nmadev.notes.Logger;
import de.nmadev.notes.db.entities.Category;
import de.nmadev.notes.db.entities.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDao {

    private Logger log = new Logger(NoteDao.class.getSimpleName());

    public List<Category> getAllCategories() {
        List<Category> allCategories = new ArrayList<>();
        try {
            Connection con = DataSource.getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM category;");
            log.log(statement.toString());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();

                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));
                category.setColor(resultSet.getString("color"));
                category.setNoteAmount(resultSet.getInt("noteamount"));

                category.setNotes(findNotesOfCategory(category.getId()));

                allCategories.add(category);
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        return allCategories;
    }

    public List<Note> findNotesOfCategory(long categoryId) {
        List<Note> notes = new ArrayList<>();
        try {
            Connection con = DataSource.getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM note WHERE categoryid = ?;");
            statement.setLong(1, categoryId);
            log.log(statement.toString());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Note note = new Note();

                note.setId(resultSet.getLong("id"));
                note.setTitle(resultSet.getString("title"));
                note.setContent(resultSet.getString("content"));
                note.setCreationDate(resultSet.getTimestamp("creationdate").toLocalDateTime());
                note.setCategoryId(resultSet.getLong("categoryid"));

                notes.add(note);
            }
        } catch (SQLException e) {
            log.log(e.getMessage());
            return new ArrayList<>();
        }
        return notes;
    }

    public void persistNote(Note note) throws SQLException {
        Connection con = DataSource.getConnection();

        PreparedStatement statement = con.prepareStatement("INSERT INTO note (title, content, creationdate, categoryid) VALUES (?, ?, ?, ?);");

        statement.setString(1, note.getTitle());
        statement.setString(2, note.getContent());
        statement.setTimestamp(3, Timestamp.valueOf(note.getCreationDate()));
        statement.setLong(4, note.getCategoryId());
        log.log(statement.toString());

        statement.executeUpdate();
    }

    public void persistCategory(Category category) throws SQLException {
        Connection con = DataSource.getConnection();

        PreparedStatement statement = con.prepareStatement("INSERT INTO category (name, color, noteamount) VALUES (?, ?, ?);");
        statement.setString(1, category.getName());
        statement.setString(2, category.getColor());
        statement.setInt(3, category.getNoteAmount());

        log.log(statement.toString());

        statement.executeUpdate();

        if (!category.getNotes().isEmpty()) {
            for (Note note : category.getNotes()) {
                persistNote(note);
            }
        }
    }

    public List<Note> searchNote(String searchString) {
        ArrayList<Note> foundNotes = new ArrayList<>();
        try {
            Connection con = DataSource.getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM note WHERE title LIKE ? OR content LIKE ?;");

            String searchStringMod = "%" + searchString + "%";
            statement.setString(1, searchStringMod);
            statement.setString(2, searchStringMod);
            log.log(statement.toString());

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
            log.log(e.getMessage());
            return new ArrayList<>();
        }
        return foundNotes;
    }
}
