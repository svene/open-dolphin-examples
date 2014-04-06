package org.opendolphin.odwebjee;

import org.opendolphin.core.Tag;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.CreatePresentationModelCommand;
import org.opendolphin.core.server.ServerAttribute;
import org.opendolphin.core.server.ServerPresentationModel;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Singleton
public class ODJEECreatePresentationModelCommandHandler implements ODJEEAction {

	@Inject
	ODJEEModelStore modelStore;

	public void consumeCommand(@Observes ODJEECommandEvent event) {
		Command cmd = event.getCommand();
		String id = cmd.getId();
		if (!"CreatePresentationModel".equals(id)) return;
		CreatePresentationModelCommand command = (CreatePresentationModelCommand) cmd;
		System.out.println("MakeItLongerAction.consumeCommand");

		List<ServerAttribute> attributes = new LinkedList();
		for (Map<String, Object> attr : command.getAttributes()) {
			ServerAttribute attribute = new ServerAttribute((String) attr.get("propertyName"), attr.get("value"), (String) attr.get("qualifier"), Tag.tagFor.get(attr.get("tag")));
			Object idObj = attr.get("id");
			Integer idInt = (Integer) idObj;
			attribute.setId(idInt);
			attributes.add(attribute);
		}
		ServerPresentationModel model = new ServerPresentationModel(command.getPmId(), attributes);
		model.setPresentationModelType(command.getPmType());
		modelStore.getModelStore().add(model);
		System.out.println("ODJEECreatePresentationModelAction.consumeCommand");
	}}
