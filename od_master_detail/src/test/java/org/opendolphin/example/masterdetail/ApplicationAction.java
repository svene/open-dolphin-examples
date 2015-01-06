package org.opendolphin.example.masterdetail;

import org.opendolphin.core.comm.Command;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.Slot;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.CommandHandler;

import static org.opendolphin.example.masterdetail.ApplicationConstants.*;

import java.util.List;

public class ApplicationAction extends DolphinServerAction {

	public void registerIn(ActionRegistry actionRegistry) {
		System.out.println("ApplicationAction.registerIn");
		actionRegistry.register(ApplicationConstants.COMMAND_INIT, new CommandHandler<Command>() {
			public void handleCommand(Command command, List<Command> response) {
				Model model = new Model(getServerDolphin(), "mdid", "type", () -> new DTO(new Slot(ATT_NAME, null), new Slot(ATT_GREETING, null)));

				// Create PM:
				DTO dto = new DTO(new Slot(ATT_NAME, null), new Slot(ATT_GREETING, null));
				getServerDolphin().presentationModel(PM_APP, null, dto);

				// Init PM:
				getServerDolphin().getAt(PM_APP).getAt(ATT_NAME).setValue("Duke");
			}
		});

		actionRegistry.register(ApplicationConstants.COMMAND_GREET, new CommandHandler<Command>() {
			public void handleCommand(Command command, List<Command> response) {
				System.out.println("Server reached.");
				getServerDolphin().getAt(PM_APP).getAt(ATT_GREETING).setValue("Hey " + getServerDolphin().getAt(PM_APP).getAt(ATT_NAME).getValue() + " !");
			}
		});

	}
}

