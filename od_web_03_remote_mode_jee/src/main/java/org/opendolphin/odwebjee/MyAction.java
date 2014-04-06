package org.opendolphin.odwebjee;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;

@Singleton
public class MyAction {

	public void consumeMessageEvent(@Observes MyMessageEvent msgEvent) {
		System.out.println("MyAction.consumeMessageEvent: message: " + msgEvent.getMessage());
	}
}
