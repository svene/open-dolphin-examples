package org.opendolphin.example.masterdetail;

import org.junit.Test;
import org.opendolphin.client.infra.ODJ8Support;
import org.opendolphin.core.Attribute;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.example.masterdetail.shared.ItemApi;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.opendolphin.example.masterdetail.shared.ApplicationApi.*;
import static org.opendolphin.example.masterdetail.shared.MasterDetailApi.*;

public class MasterDetailApiModelInitializerTest {

	@Test
	public void test1() throws Exception {
		XInMemoryClientDolphinProvider dolphin = new XInMemoryClientDolphinProvider(Runnable::run);
		ClientDolphin clientDolphin = dolphin.getClientDolphin();
		dolphin.getServerDolphin().register(new ApplicationDirector());

		final CountDownLatch cdl = new CountDownLatch(1);
		clientDolphin.send(COMMAND_INIT, ODJ8Support.onFinishedHandler(pms -> cdl.countDown()));
		cdl.await();

		// Access to presentation models:
		List<PresentationModel> pms = clientDolphin.findAllPresentationModelsByType(ItemApi.ITEM_TYPE);
		assertEquals(2, pms.size()); // See COMMAND_INIT handler

		PresentationModel pm0 = pms.get(0);
		String pm0Name = (String) pm0.getAt(ItemApi.ATT_NAME).getValue();

		PresentationModel pm1 = pms.get(1);
		PresentationModel currentItem = clientDolphin.findPresentationModelById(getCurrentPmId(PM_MASTER_DETAIL_ITEM_ID));
		Attribute currentItemName = currentItem.getAt(ItemApi.ATT_NAME);

		assertNull(currentItemName.getValue());
		assertNull(currentItem.getAt(ItemApi.ATT_GREETING).getValue());

		// Simulate a selection change in a TableView:
		PresentationModel metaPM = clientDolphin.findPresentationModelById(getMetaPmId(PM_MASTER_DETAIL_ITEM_ID));
		metaPM.getAt(ATT_CURRENT_PM_ID).setValue(pm0.getId());
		assertNull(currentItemName.getValue());

		final CountDownLatch cdl2 = new CountDownLatch(1);
		clientDolphin.sync(cdl2::countDown);
		cdl2.await();
		assertEquals(pm0Name, currentItemName.getValue());
	}

}