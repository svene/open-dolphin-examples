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

		actionRegistry.register(ApplicationConstants.COMMAND_INIT, new CommandHandler<Command>() {
			public void handleCommand(Command command, List<Command> response) {
				Model model = new Model(getServerDolphin(), MASTER_DETAIL_FOR_ITEMS.id, ItemApi.ITEM_TYPE, () -> newDTO(null, null));

				// Create PM:
				getServerDolphin().presentationModel(P1.id, ItemApi.ITEM_TYPE, newDTO(P1.name, P1.greeting));
				getServerDolphin().presentationModel(P2.id, ItemApi.ITEM_TYPE, newDTO(P2.name, P2.greeting));
			}

			private DTO newDTO(String name, String greeting) {
				return new DTO(new Slot(ItemApi.ATT_NAME, name), new Slot(ItemApi.ATT_GREETING, greeting));
			}
		});

		actionRegistry.register(ApplicationConstants.COMMAND_GREET, (command, response) -> {
			System.out.println("Server reached.");
			getServerDolphin().getAt(PM_APP).getAt(ItemApi.ATT_GREETING).setValue("Hey " + getServerDolphin().getAt(PM_APP).getAt(ItemApi.ATT_NAME).getValue() + " !");
		});

	}
}

