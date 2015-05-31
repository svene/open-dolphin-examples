package com.mycompany;

import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.ServerAttribute;
import org.opendolphin.core.server.Slot;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;

import static com.mycompany.ApplicationConstants.*;

public class ApplicationAction extends DolphinServerAction{

    public void registerIn(ActionRegistry actionRegistry) {

        actionRegistry.register(ApplicationConstants.COMMAND_INIT, (command, response) -> {
			// Create PM:
			DTO dto = new DTO( new Slot(ATT_NAME, null), new Slot(ATT_GREETING, null) );
			getServerDolphin().presentationModel(PM_APP, null, dto);

			// Init PM:
			getServerDolphin().getAt(PM_APP).getAt(ATT_NAME).setValue("Duke");
		});

        actionRegistry.register(ApplicationConstants.COMMAND_GREET, (command, response) -> {
			String name = (String) getServerDolphin().getAt(PM_APP).getAt(ATT_NAME).getValue();
            ServerAttribute greeting = getServerDolphin().getAt(PM_APP).getAt(ATT_GREETING);


			// Use 'tryboom' as name to throw exception outside of try/catch:
			if (! name.startsWith("try")) {
				checkName(name);
			}

			// Use 'boom' as name to throw exception inside of try/catch:
            try {
				checkName(name);
				greeting.setValue("Hey " + name + " !");
			} catch (Exception e) {
				greeting.setValue("Exception caught: " + e.getMessage());
			}
		});

    }

    private void checkName(String name) {
        if (name.contains("boom")) {
			throw new RuntimeException("boom");
		}
    }
}
