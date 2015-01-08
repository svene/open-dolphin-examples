package org.opendolphin.example.masterdetail;

import org.junit.Test;
import org.opendolphin.core.Attribute;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientDolphin;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.opendolphin.client.infra.ODLambdaSupport.onFinishedHandler;
import static org.opendolphin.example.masterdetail.ApplicationConstants.*;

public class MasterDetailModelTest {

	@Test
	public void test1() throws Exception {
		XInMemoryClientDolphinProvider dolphin = new XInMemoryClientDolphinProvider(Runnable::run);
		ClientDolphin clientDolphin = dolphin.getClientDolphin();
		dolphin.getServerDolphin().register(new ApplicationDirector());

		final CountDownLatch cdl = new CountDownLatch(1);
		clientDolphin.send(COMMAND_INIT, onFinishedHandler(pms -> cdl.countDown()));
		cdl.await();

		// Access to presentation models:
		List<PresentationModel> pms = clientDolphin.findAllPresentationModelsByType(ItemApi.ITEM_TYPE);
		assertEquals(2, pms.size()); // See COMMAND_INIT handler
		PresentationModel pm0 = pms.get(0);
		PresentationModel pm1 = pms.get(1);
		PresentationModel currentItem = clientDolphin.findPresentationModelById(MASTER_DETAIL_FOR_ITEMS.currentPMId);
		Attribute currentItemName = currentItem.getAt(ItemApi.ATT_NAME);

		assertNull(currentItemName.getValue());
		assertNull(currentItem.getAt(ItemApi.ATT_GREETING).getValue());

		// Simulate a selection change in a TableView:
		PresentationModel metaPM = clientDolphin.findPresentationModelById(MASTER_DETAIL_FOR_ITEMS.metaPMId);
		metaPM.getAt(MasterDetailsApi.ATT_CURRENT_PM_ID).setValue(pm0.getId());
		assertNull(currentItemName.getValue());

		final CountDownLatch cdl2 = new CountDownLatch(1);
		clientDolphin.sync(cdl2::countDown);
		cdl2.await();
		assertEquals(P1.name, currentItemName.getValue());
	}

}