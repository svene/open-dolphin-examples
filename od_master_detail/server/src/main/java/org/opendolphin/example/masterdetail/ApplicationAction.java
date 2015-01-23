package org.opendolphin.example.masterdetail;

import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.examplepms.item.ItemApi;
import org.opendolphin.examplepms.item.ItemServerAPI;
import org.opendolphinx.server.pattern.masterdetail.MasterDetailServerApi;
import org.opendolphin.examplepms.item.ItemSampleDataInitializer;

import static org.opendolphin.example.masterdetail.shared.ApplicationApi.COMMAND_INIT;

public class ApplicationAction extends DolphinServerAction {

	public void registerIn(ActionRegistry actionRegistry) {

		actionRegistry.register(COMMAND_INIT, (command, response) -> {
			MasterDetailServerApi.createNewMasterDetailModel(getServerDolphin(), ItemApi.PM_MASTER_DETAIL_ITEM_ID, ItemApi.ITEM_TYPE, ItemServerAPI.newDTOSupplier());

			new ItemSampleDataInitializer().initialize(getServerDolphin());
		});

	}

}

