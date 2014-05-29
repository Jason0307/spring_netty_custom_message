/**
 * 
 */
package org.nerdronix.springnetty.handlers;

import io.netty.channel.ChannelHandlerContext;

import org.nerdronix.springnetty.server.Message;

/**
 * @author Jason.Zhu
 * @date   2014-5-29
 * @email jasonzhu@augmentum.com.cn
 */
public class HelpHandler extends ServerHandler{

	/* (non-Javadoc)
	 * @see org.nerdronix.springnetty.handlers.BaseService#doLogic(io.netty.channel.ChannelHandlerContext, org.nerdronix.springnetty.server.Message)
	 */
	@Override
	public void doLogic(ChannelHandlerContext ctx, Message msg) {
		
		
	}

}
