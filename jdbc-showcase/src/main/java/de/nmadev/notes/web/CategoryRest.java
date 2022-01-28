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

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Category getSingleCategory(@PathParam("id") String id) {
        long l = NumberUtils.toLong(id, -1L);
        if (l > -1) {
            return noteDao.findCategory(l);
        }
        return null;
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategories() {
        return noteDao.getAllCategories();
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
