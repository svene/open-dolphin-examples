package org.opendolphin.client.infra;

import org.opendolphin.core.client.ClientPresentationModel;
import org.opendolphin.core.client.comm.OnFinishedHandler;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ODJ8Support {

	public static OnFinishedHandler onFinishedHandler(Consumer<List<ClientPresentationModel>> onFinishedConsumer) {
		return onFinishedHandler(onFinishedConsumer, null);
	}

	public static OnFinishedHandler onFinishedHandler(Consumer<List<ClientPresentationModel>> onFinishedConsumer, Consumer<List<Map>> onFinishedDataConsumer) {
		return new OnFinishedHandler() {
			@Override
			public void onFinished(List<ClientPresentationModel> presentationModels) {
				if (onFinishedConsumer != null) {
					onFinishedConsumer.accept(presentationModels);
				}
			}

			@Override
			public void onFinishedData(List<Map> data) {
				if (onFinishedDataConsumer != null) {
					onFinishedDataConsumer.accept(data);
				}
			}
		};
	}
}
