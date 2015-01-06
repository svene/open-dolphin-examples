package org.opendolphin.example.masterdetail;

import org.junit.Test;
import org.opendolphin.core.client.ClientDolphin;

import java.util.concurrent.CountDownLatch;

import static org.opendolphin.client.infra.ODLambdaSupport.onFinishedHandler;

public class ModelTest {

	@Test
	public void test1() throws Exception {
		XInMemoryClientDolphinProvider dolphin = new XInMemoryClientDolphinProvider(Runnable::run);
		ClientDolphin clientDolphin = dolphin.getClientDolphin();
		dolphin.getServerDolphin().register(new ApplicationDirector());

		final CountDownLatch cdl = new CountDownLatch(1);
		clientDolphin.send(ApplicationConstants.COMMAND_INIT, onFinishedHandler(pms -> cdl.countDown()));
		cdl.await();

		final CountDownLatch cdl2 = new CountDownLatch(1);
		clientDolphin.sync(cdl2::countDown);
		cdl2.await();
	}

}