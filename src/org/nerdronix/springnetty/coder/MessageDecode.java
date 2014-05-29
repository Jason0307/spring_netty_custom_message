/**
 * 
 */
package org.nerdronix.springnetty.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import org.nerdronix.springnetty.server.Message;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * @author Jason.Zhu
 * @date 2014-5-28
 * @email jasonzhu@augmentum.com.cn
 */
@Component("messageDecode")
public class MessageDecode extends MessageToMessageDecoder<ByteBuf> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.netty.handler.codec.MessageToMessageDecoder#decode(io.netty.channel
	 * .ChannelHandlerContext, java.lang.Object, java.util.List)
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
			List<Object> out) throws Exception {

		int length = msg.readableBytes();
		byte[] bytes = msg.readBytes(length).array();
		String str = new String(bytes, "UTF-8");
		Message message = JSON.parseObject(str, Message.class);
		out.add(message);

	}

}
