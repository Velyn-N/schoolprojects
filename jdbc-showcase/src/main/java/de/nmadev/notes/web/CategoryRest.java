package de.nmadev.notes.web;

import de.nmadev.notes.db.NoteDao;
import de.nmadev.notes.db.entities.Category;
import org.apache.commons.lang3.math.NumberUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/rest/categories")
public class CategoryRest {

    NoteDao noteDao = new NoteDao();

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategories() {
        return noteDao.getAllCategories();
    }

    @Path("/all/withnotes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategoriesWithNotes() {
        List<Category> categories = noteDao.getAllCategories();

        for (Category category : categories) {
            category.setNotes(noteDao.findNotesOfCategory(category.getId()));
        }
        return categories;
    }


    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(Category category) {
        try {
            noteDao.persistCategory(category);
            return new Response(false);
        } catch (SQLException e) {
            return new Response(true, e.getMessage());
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(Category category) {
        try {
            noteDao.mergeCategory(category);
            return new Response(false);
        } catch (SQLException e) {
            return new Response(true, e.getMessage());
        }
    }
}
