package de.nmadev.notes;

import de.nmadev.notes.db.DataSource;
import de.nmadev.notes.db.NoteDao;
import de.nmadev.notes.db.entities.Category;
import de.nmadev.notes.db.entities.Note;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main implements Runnable {

    public static final String SPACER_STRONG = "======= ======= ======= ======= =======";
    public static final String SPACER_MEDIUM = "+++++++ +++++++ +++++++ +++++++ +++++++";
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM HH:mm");
    private Logger log = new Logger(Main.class.getSimpleName());
    private NoteDao noteDao = new NoteDao();

    public static void main(String[] args) {
        new Main().run();
    }

    @Override
    public void run() {
        try {
            System.err.println("Creating Database...");
            createDatabase();

            System.err.println("Inserting StartData...");
            insertStartData();

            System.err.println("Reading all Data...");
            noteDao.getAllCategories().forEach(this::logEntireCategory);

            System.err.println("Searching for Note Three...");
            List<Note> three = noteDao.searchNote("Three");
            log.log("Found " + three.size() + " Note(s)!");
            log.log(SPACER_STRONG);
            three.forEach(this::logNote);


        } catch (SQLException e) {
            log.log(e.getMessage());
            return;
        }
    }

    public void createDatabase() throws SQLException {
        String deleteCategoryTable = "DROP TABLE IF EXISTS `category`;";
        String createCategoryTable =
                "CREATE TABLE `category` (" +
                " `id` bigint NOT NULL AUTO_INCREMENT," +
                " `name` varchar(100) NOT NULL," +
                " `color` varchar(100) DEFAULT NULL," +
                " `noteamount` int DEFAULT '0'," +
                "  PRIMARY KEY (`id`)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

        String deleteNoteTable = "DROP TABLE IF EXISTS `note`;";
        String createNoteTable =
                "CREATE TABLE `note` (" +
                " `id` bigint NOT NULL AUTO_INCREMENT," +
                " `title` varchar(100) NOT NULL," +
                " `content` text," +
                " `creationdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                " `categoryid` bigint DEFAULT NULL," +
                "  PRIMARY KEY (`id`)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

        Connection connection = DataSource.getConnection();

        connection.prepareStatement(deleteCategoryTable).execute();
        connection.prepareStatement(createCategoryTable).execute();

        connection.prepareStatement(deleteNoteTable).execute();
        connection.prepareStatement(createNoteTable).execute();
    }

    public void insertStartData() {
        Category category1 = new Category();
        category1.setId(1);
        category1.setColor("green");
        category1.setName("Category One");
        category1.setNoteAmount(2);

        Note note1 = new Note();
        note1.setCategoryId(1);
        note1.setTitle("Note One");
        note1.setContent("Lorem ipsum dolor sit amet.");

        Note note2 = new Note();
        note2.setCategoryId(1);
        note2.setTitle("Note Two");
        note2.setContent("Lorem ipsum dolor sit amet.");

        category1.getNotes().add(note1);
        category1.getNotes().add(note2);



        Category category2 = new Category();
        category2.setId(2);
        category2.setColor("blue");
        category2.setName("Category Two");
        category1.setNoteAmount(1);

        Note note3 = new Note();
        note3.setCategoryId(2);
        note3.setTitle("Note Three");
        note3.setContent("Lorem ipsum dolor sit amet.");

        category2.getNotes().add(note3);

        try {
            noteDao.persistCategory(category1);
            noteDao.persistCategory(category2);
        } catch (SQLException e) {
            log.log(e.getMessage());
        }
    }

    public void readAll() {

    }

    private void logEntireCategory(Category category) {
        log.log(SPACER_STRONG);
        log.log(" ----- " + category.getName() + " [" + category.getColor() + "] -----");
        log.log(SPACER_STRONG);
        for (Note note : category.getNotes()) {
            logNote(note);
            log.log(SPACER_MEDIUM);
        }
        log.log("\n\n");
    }

    private void logNote(Note note) {
        log.log(note.getTitle() + " [" + note.getCreationDate().format(dateTimeFormatter) + "]");
        log.log("");
        log.log(note.getContent());
    }
}
