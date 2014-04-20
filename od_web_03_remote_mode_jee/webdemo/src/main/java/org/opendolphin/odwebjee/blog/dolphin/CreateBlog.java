package org.opendolphin.odwebjee.blog.dolphin;

import org.opendolphin.core.Attribute;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.server.ServerAttribute;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.jee.server.CommandEvent;
import org.opendolphin.jee.server.DolphinCommandHandler;
import org.opendolphin.jee.server.ICommandHandler;
import org.opendolphin.jee.server.ModelStoreHolder;
import org.opendolphin.odwebjee.blog.boundary.Blog;
import org.opendolphin.odwebjee.blog.entity.BlogEntry;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Simple Command Handler to demonstrate how a PM-attribute is modified and the change is
 * immediately visible on the client.
 */
@Singleton
@DolphinCommandHandler(CreateBlog.CMD_ID)
@SuppressWarnings("unused")
public class CreateBlog implements ICommandHandler {

	public static final String CMD_ID = "createBlog";

	@Inject
	ModelStoreHolder modelStoreHolder;

    @Inject
	Blog blog;

	@Override
	public void handleCommand(CommandEvent commandEvent) {

		PresentationModel pm = modelStoreHolder.getModelStore().findPresentationModelById(BlogPM.PM_ID);
		final Attribute at = pm.getAt(BlogPM.ATT_TITLE);

		BlogEntry blogEntry = blog.createBlog("Design Patterns");
		ServerDolphin.changeValue(commandEvent.getResponse(), (ServerAttribute) at, blogEntry.getTitle());
	}
}
