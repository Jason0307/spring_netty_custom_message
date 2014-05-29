/**
 * 
 */
package org.nerdronix.springnetty.coder;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

import org.nerdronix.springnetty.server.Message;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * @author Jason.Zhu
 * @date   2014-5-28
 * @email jasonzhu@augmentum.com.cn
 */
@Component("messageEncode")
public class MessageEncode extends MessageToMessageEncoder<Message>{

	/* (non-Javadoc)
	 * @see io.netty.handler.codec.MessageToMessageEncoder#encode(io.netty.channel.ChannelHandlerContext, java.lang.Object, java.util.List)
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, Message message,
			List<Object> out) throws Exception {
		String json = JSON.toJSONString(message);
		System.out.println("Json :" + json);
		out.add(Unpooled.copiedBuffer(json.getBytes()));
	}

}
