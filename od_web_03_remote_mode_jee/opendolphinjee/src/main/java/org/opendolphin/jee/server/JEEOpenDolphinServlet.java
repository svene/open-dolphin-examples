package org.opendolphin.jee.server;

import org.opendolphin.core.comm.Codec;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.JsonCodec;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/dolphin"})
public class JEEOpenDolphinServlet extends HttpServlet {

	@Inject
	CommandDispatcher commandDispatcher;
	private final static Logger LOGGER = Logger.getLogger(JEEOpenDolphinServlet.class.getName());


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("DolphinServlet: session: " + req.getSession().getId());
		Codec codec = new JsonCodec();
		String requestJson = getPostData(req);
		List<Command> commands = codec.decode(requestJson);
		List<Command> results = commandDispatcher.dispatchCommands(commands);
		String jsonResponse = codec.encode(results);
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.getWriter().write(jsonResponse);
		resp.getWriter().flush();
		resp.getWriter().close();
		URI uri;
		Confirmation confirmation;
			return Response.created(uri).entity(confirmation).build();
		Response.status(Response.Status.CONFLICT).header("x-error-msg", "don't be concurrent");
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
