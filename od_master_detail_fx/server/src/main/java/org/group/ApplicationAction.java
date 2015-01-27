package org.group;

import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.examplepms.person.PersonApi;
import org.opendolphin.examplepms.person.PersonSampleDataInitializer;
import org.opendolphin.examplepms.person.PersonServerAPI;
import org.opendolphinx.server.pattern.masterdetail.MasterDetailServerApi;

import static org.group.ApplicationConstants.COMMAND_INIT;

public class ApplicationAction extends DolphinServerAction{

    public void registerIn(ActionRegistry actionRegistry) {

        actionRegistry.register(COMMAND_INIT, (command, response) -> {

            MasterDetailServerApi.createNewMasterDetailModel(getServerDolphin(), PersonApi.PM_MASTER_DETAIL_PERSON_ID, PersonApi.PERSON_TYPE, PersonServerAPI.newDTOSupplier());
            new PersonSampleDataInitializer().initialize(getServerDolphin());
        });

    }
}
