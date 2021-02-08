package net.dev4any1;

import net.dev4any1.grizzlygoose.Serverless;

public class App {
	public static void main(String... args) {
		Serverless.startDaemon(JerseyGuiceApplication.class, GuiceConfigContextListener.class,
				ProbesBinder.bind());
	}
}
