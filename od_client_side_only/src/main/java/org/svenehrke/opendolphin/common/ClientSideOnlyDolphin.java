package org.svenehrke.opendolphin.common;

import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientPresentationModel;

import java.util.List;
import java.util.stream.Collectors;

public class ClientSideOnlyDolphin {

	public static ClientPresentationModel presentationModel(String pmId, List<String> attributeNames) {
		List<ClientAttribute> clientAttributes = attributeNames.stream().map(n -> new ClientAttribute(n)).collect(Collectors.toList());
		ClientPresentationModel pm = new ClientPresentationModel(pmId, clientAttributes);
		pm.setClientSideOnly(true);
		return pm;
	}
}
