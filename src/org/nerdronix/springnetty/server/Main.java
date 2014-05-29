package org.nerdronix.springnetty.server;

import org.apache.log4j.Logger;
import org.nerdronix.springnetty.cfg.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
	
	public static final Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		logger.debug("Starting application context.");
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		ctx.registerShutdownHook();
	}

}
