package org.svenehrke.opendolphin.common;

import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientPresentationModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.opendolphin.extension.javafxclient.JavaFXApplicationParameters.clientDolphin;

public class ClientSideOnlyDolphin {

	public static ClientPresentationModel presentationModel(String pmId, String...attributeNames) {
		List<ClientAttribute> clientAttributes = Arrays.asList(attributeNames).stream().map(n -> new ClientAttribute(n)).collect(Collectors.toList());
		return presentationModel(pmId, clientAttributes);
	}

	public static ClientPresentationModel presentationModel(final String pmId, ClientAttribute...clientAttributes) {
		return presentationModel(pmId, Arrays.asList(clientAttributes));
	}

	private static ClientPresentationModel presentationModel(final String pmId, final List<ClientAttribute> clientAttributes) {
		ClientPresentationModel pm = new ClientPresentationModel(pmId, clientAttributes);
		pm.setClientSideOnly(true);
		clientDolphin.getModelStore().add(pm);
		return pm;
	}
}
