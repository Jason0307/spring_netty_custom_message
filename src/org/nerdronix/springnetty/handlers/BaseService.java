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
public interface BaseService{

	public void doLogic(ChannelHandlerContext ctx, Message msg);
}
