package de.nmadev;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import io.quarkus.runtime.Startup;

@ServerEndpoint("/webConsole")
@Path("/webConsoleActions")
@ApplicationScoped @Startup
public class WebOut {
	private static final DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:MM:ss");
	private static WebOut instance;
	
	private Session socketSession;

	private List<String> out = new ArrayList<>();
	
	public WebOut() {
		write("Application started.");
		instance = this;
	}
	
	public static WebOut getInstance() {
		return instance;
	}
	
	public void write(String msg) {
		String timedMsg = LocalDateTime.now().format(dtFormat) + " > " + msg;
		out.add(timedMsg);
		System.out.println(timedMsg);

		broadcast(out.stream().collect(Collectors.joining("<br>")));
	}
	
	@OnOpen
    public void onOpen(Session session) {
		socketSession = session;
		write("WebSocket connected!");
	}

    private void broadcast(String message) {
		if (socketSession != null) {
			socketSession.getAsyncRemote().sendObject(message, result -> {
				if (result.getException() != null) {
					System.out.println("Unable to broadcast to WebConsole: " + message);
				}
			});
		}
    }

	@Path("/clear") @POST
	public void clear() {
		out.clear();
		broadcast("");
	}
}
