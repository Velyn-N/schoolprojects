package de.nmadev.notes.db;

import de.nmadev.notes.db.entities.Category;
import de.nmadev.notes.db.entities.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDao {

    private Logger log = LoggerFactory.getLogger(NoteDao.class);

    public List<Category> getAllCategories() {
        List<Category> allCategories = new ArrayList<>();
        try {
            Connection con = DataSource.getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM category;");
            log.info(statement.toString());
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

    public List<Note> findNotesOfCategory(long categoryId) {
        List<Note> notes = new ArrayList<>();
        try {
            Connection con = DataSource.getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM note WHERE categoryid = ?;");
            log.info(statement.toString());
            statement.setLong(1, categoryId);
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
            return new ArrayList<>();
        }
        return notes;
    }

    public void mergeNote(Note note) throws SQLException {
        Connection con = DataSource.getConnection();

        PreparedStatement statement = con.prepareStatement("UPDATE note SET title = ?, content = ?, creationDate = ?, categoryid = ? where id = ?;");
        log.info(statement.toString());

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
        log.info(statement.toString());

        statement.setString(1, category.getName());
        statement.setString(2, category.getColor());
        statement.setInt(3, category.getNoteAmount());
        statement.setLong(4, category.getId());

        statement.executeQuery();
    }

    public void persistNote(Note note) throws SQLException {
        Connection con = DataSource.getConnection();

        PreparedStatement statement = con.prepareStatement("INSERT INTO note VALUES (title = ?, content = ?, creationdate = ?, categoryid = ?);");
        log.info(statement.toString());

        statement.setString(1, note.getTitle());
        statement.setString(2, note.getContent());
        statement.setTimestamp(3, Timestamp.valueOf(note.getCreationDate()));
        statement.setLong(4, note.getCategoryId());

        statement.executeQuery();
    }

    public void persistCategory(Category category) throws SQLException {
        Connection con = DataSource.getConnection();

        PreparedStatement statement = con.prepareStatement("INSERT INTO category VALUES (name = ?, color = ?, noteamount = ?);");
        log.info(statement.toString());

        statement.setString(1, category.getName());
        statement.setString(2, category.getColor());
        statement.setInt(3, category.getNoteAmount());

        statement.executeQuery();
    }

    public List<Note> searchNote(String searchString) {
        ArrayList<Note> foundNotes = new ArrayList<>();
        try {
            Connection con = DataSource.getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM note WHERE title LIKE '%'+?+'%' OR content LIKE '%'+?+'%';");
            log.info(statement.toString());

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
