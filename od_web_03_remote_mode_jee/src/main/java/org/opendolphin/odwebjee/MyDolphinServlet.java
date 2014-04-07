package org.opendolphin.odwebjee;

import org.opendolphin.core.comm.Codec;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.JsonCodec;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/dolphin"})
public class MyDolphinServlet extends HttpServlet {

	@Inject
	ODJEEModelStoreHolder modelStoreHolder;

	@Inject
	private Event<ODJEECommandEvent> commandEvent;

	@Inject @Any
	private Instance<ODJEECommandHandler> commandHandlers;

	private Map<String, ODJEECommandHandler> commandHandlersByName = new HashMap<>();

	@PostConstruct
	void initialize() {
		for (ODJEECommandHandler commandHandler : commandHandlers) {
			String commandHandlerName = commandHandler.getClass().getName();
			System.out.println(commandHandlerName);
			// here a map from commandHandlerName to ODJEECommandHandler could be initialized.
			// but here the CDI event mechanism is used.
			// TODO: show the map approach in a second demo
			String name = commandHandler.getClass().getSimpleName()
				.replace("ODJEE", "")
				.replace("Command", "")
				.replace("Handler", "");
			commandHandlersByName.put(name, commandHandler);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Codec codec = new JsonCodec();
		String requestJson = getPostData(req);
		List<Command> commands = codec.decode(requestJson);
		List<Command> results = new ArrayList<>();
		for (Command command : commands) {
			commandHandlers.select(ODJEECreatePresentationModelCommandHandler.class);
			ODJEECommandEvent ce = new ODJEECommandEvent(command);
//			commandEvent.fire(ce);
			String name = command.getId();
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
			ODJEECommandHandler commandHandler = commandHandlersByName.get(name);
			commandHandler.handleCommand(ce);
			results.addAll(ce.getResponse());
		}
		String jsonResponse = codec.encode(results);
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.getWriter().write(jsonResponse);
		resp.getWriter().flush();
		resp.getWriter().close();
	}

	public static String getPostData(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = req.getReader();
			reader.mark(10000);

			String line;
			do {
				line = reader.readLine();
				sb.append(line).append("\n");
			} while (line != null);
			reader.reset();
			// do NOT close the reader here, or you won't be able to get the post data twice
		} catch(IOException e) {
			throw new RuntimeException("getPostData couldn't.. get the post data", e);  // This has happened if the request's reader is closed
		}

		return sb.toString();
	}
}
