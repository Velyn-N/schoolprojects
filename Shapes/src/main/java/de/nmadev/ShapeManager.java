package de.nmadev;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import de.nmadev.shapes.Circle;
import de.nmadev.shapes.Line;
import de.nmadev.shapes.Rectangle;

@Path("/shapes")
public class ShapeManager {
    
    private @Inject WebOut wOut;

    @Path("/line") @POST
    public void line(Line line) {
        if (line != null) {
            wOut.write(line.toString());
            line.callDisplay();
        } else {
            wOut.write("Incorrect Shape send: " + line);
        }
    }

    @Path("/rectangle") @POST
    public void rectangle(Rectangle rectangle) {
        if (rectangle != null) {
            wOut.write(rectangle.toString());
            rectangle.callDisplay();
        } else {
            wOut.write("Incorrect Shape send: " + rectangle);
        }
    }

    @Path("/circle") @POST
    public void circle(Circle circle) {
        if (circle != null) {
            wOut.write(circle.toString());
            circle.callDisplay();
        } else {
            wOut.write("Incorrect Shape send: " + circle);
        }
    }
}
