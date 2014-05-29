package org.nerdronix.springnetty.handlers;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import org.nerdronix.springnetty.coder.MessageDecode;
import org.nerdronix.springnetty.coder.MessageEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Just a dummy protocol mainly to show the ServerBootstrap being initialized.
 */
@Component
@Qualifier("springProtocolInitializer")
public class StringProtocolInitializer extends ChannelInitializer<SocketChannel> {

	@Autowired
	MessageEncode messageEncode;
	
	@Autowired
	MessageDecode messageDecode;
	
	@Autowired
	ServerHandler serverHandler;
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("decoder", messageDecode);
		pipeline.addLast("handler", serverHandler);
		pipeline.addLast("encoder", messageEncode);
	}
	
	public MessageEncode getMessageEncode() {
		return messageEncode;
	}

	public void setMessageEncode(MessageEncode messageEncode) {
		this.messageEncode = messageEncode;
	}

	public MessageDecode getMessageDecode() {
		return messageDecode;
	}

	public void setMessageDecode(MessageDecode messageDecode) {
		this.messageDecode = messageDecode;
	}



	public ServerHandler getServerHandler() {
		return serverHandler;
	}
	
	public void setServerHandler(ServerHandler serverHandler) {
		this.serverHandler = serverHandler;
	}
}
