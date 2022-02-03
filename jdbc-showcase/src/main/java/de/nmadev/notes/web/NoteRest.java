package de.nmadev.notes.web;

import de.nmadev.notes.db.NoteDao;
import de.nmadev.notes.db.entities.Note;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/rest/notes")
public class NoteRest {

    NoteDao noteDao = new NoteDao();

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNote(Note note) {
        try {
            noteDao.persistNote(note);
            return new Response(false);
        } catch (SQLException e) {
            return new Response(true, e.getMessage());
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNote(Note note) {
        try {
            noteDao.mergeNote(note);
            return new Response(false);
        } catch (SQLException e) {
            return new Response(true, e.getMessage());
        }
    }
}
