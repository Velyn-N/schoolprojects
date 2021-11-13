package de.nmadev;

import io.quarkus.runtime.*;

import javax.enterprise.context.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;

@Path(value = "/rest/messages")
@ApplicationScoped @Startup
public class WebOut {
	private static final DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:MM:ss");
	private static WebOut instance;
	
	private List<String> out = new ArrayList<>();
	
	public WebOut() {
		write("Application started.");
	}
	
	public static WebOut getInstance() {
		return instance;
	}
	
	public void write(String msg) {
		out.add(LocalDateTime.now().format(dtFormat) + " > " + msg);
	}
	
	@GET @Produces(MediaType.TEXT_PLAIN)
	public String getMessages() {
		String collect = out.stream().collect(Collectors.joining("\n"));
		return collect;
	}
}
