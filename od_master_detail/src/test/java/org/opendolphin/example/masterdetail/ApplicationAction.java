package org.opendolphin.example.masterdetail;

import org.opendolphin.core.comm.Command;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.CommandHandler;

import static org.opendolphin.example.masterdetail.ApplicationApi.*;

import java.util.List;

public class ApplicationAction extends DolphinServerAction {

	public void registerIn(ActionRegistry actionRegistry) {

		actionRegistry.register(ApplicationApi.COMMAND_INIT, new CommandHandler<Command>() {
			public void handleCommand(Command command, List<Command> response) {
				MasterDetailModelInitializer.initialize(getServerDolphin(), PM_MASTER_DETAIL_ITEM_ID, ItemApi.ITEM_TYPE, ItemApi.newDTOSupplier() );

				new SampleDataInitializer().initialize(getServerDolphin());
			}

		});

	}

}

