package org.opendolphin.example.masterdetail;

import org.junit.Test;
import org.opendolphin.client.example.masterdetail.Items;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.example.masterdetail.shared.ItemApi;
import org.opendolphinx.client.misc.J8ClientSupport;
import org.opendolphinx.client.pattern.masterdetail.MasterDetailClientApi;
import org.opendolphinx.combined.misc.InMemoryClientDolphinProvider;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.opendolphin.example.masterdetail.shared.ApplicationApi.COMMAND_INIT;
import static org.opendolphin.example.masterdetail.shared.ItemApi.PM_MASTER_DETAIL_ITEM_ID;

public class MasterDetailClientTest {

	@Test
	public void test1() throws Exception {
		ClientDolphin clientDolphin = initDolphin();

		MasterDetailClientApi masterDetail = new MasterDetailClientApi(clientDolphin, PM_MASTER_DETAIL_ITEM_ID, ItemApi.ITEM_TYPE);

		// Access to presentation models:
		List<PresentationModel> pms = masterDetail.findAllPresentationModels();
		assertEquals(2, pms.size()); // See COMMAND_INIT handler

		PresentationModel pm0 = pms.get(0);
		assertNotNull(Items.getName(pm0));

		PresentationModel currentItem = masterDetail.getCurrentItem();
		assertNull(Items.getName(currentItem));

		masterDetail.setCurrentPMId(pm0.getId()); // Simulate a selection change in a TableView
		assertNull(Items.getName(currentItem)); // new value not there yet (needs a clientDolphin.sync(), see below)

		CountDownLatch cdl = new CountDownLatch(1);
		clientDolphin.sync(cdl::countDown);
		cdl.await();
		assertEquals(Items.getName(pm0), Items.getName(currentItem));
	}

	private ClientDolphin initDolphin() throws Exception {
		InMemoryClientDolphinProvider dolphin = new InMemoryClientDolphinProvider(Runnable::run);
		ClientDolphin clientDolphin = dolphin.getClientDolphin();
		dolphin.getServerDolphin().register(new ApplicationDirector());

		final CountDownLatch cdl = new CountDownLatch(1);
		clientDolphin.send(COMMAND_INIT, J8ClientSupport.onFinishedHandler(pms -> cdl.countDown()));
		cdl.await();

		return clientDolphin;
	}

}