package org.opendolphin.example.masterdetail;

import org.opendolphin.core.comm.Command;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.Slot;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.CommandHandler;

import static org.opendolphin.example.masterdetail.ApplicationConstants.ITEM_TYPE;
import static org.opendolphin.example.masterdetail.ApplicationConstants.*;

import java.util.List;

public class ApplicationAction extends DolphinServerAction {

	public void registerIn(ActionRegistry actionRegistry) {

		actionRegistry.register(ApplicationConstants.COMMAND_INIT, new CommandHandler<Command>() {
			public void handleCommand(Command command, List<Command> response) {
				Model model = new Model(getServerDolphin(), ITEM_MASTER_DETAIL_ID, ITEM_TYPE, () -> newDTO(null, null));

				// Create PM:
				getServerDolphin().presentationModel("pm1", ITEM_TYPE, newDTO(PM1_NAME, PM1_GREETING));
				getServerDolphin().presentationModel("pm2", ITEM_TYPE, newDTO(PM2_NAME, PM2_GREETING));
			}

			private DTO newDTO(String name, String greeting) {
				return new DTO(new Slot(ATT_NAME, name), new Slot(ATT_GREETING, greeting));
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

