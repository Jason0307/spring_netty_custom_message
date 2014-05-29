package org.nerdronix.springnetty.handlers;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.nerdronix.springnetty.server.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("serverHandler")
@Sharable
public abstract class ServerHandler extends SimpleChannelInboundHandler<Message> implements BaseService{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		//System.out.println("Receive data from client : " + msg);
		//ctx.channel().writeAndFlush(new Message(4,"Owen"));
		//ctx.channel().writeAndFlush(msg);
		doLogic(ctx,msg);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Channel is activie.\n");
		ctx.writeAndFlush(new Message(4,"Owen"));
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("\nChannel is disconnected.");
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		super.exceptionCaught(ctx, cause);
	}
	
	
	
}
