package org.nerdronix.springnetty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 
 * Author: liuzhaoyang
 * 
 * Date: 2013-4-7
 * 
 * Time: 20:49:13
 */

public class HelloClient {

	public static void main(String[] args) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				// pipeline.addLast("frameDecoder", new
				// LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
				// pipeline.addLast("frameEncoder", new
				// LengthFieldPrepender(4));
				pipeline.addLast("decoder",
						new MessageToMessageDecoder<ByteBuf>() {

							@Override
							protected void decode(ChannelHandlerContext ctx,
									ByteBuf in, List<Object> out)
									throws Exception {
								int length = in.readableBytes();
								byte[] bytes = in.readBytes(length).array();
								String str = new String(bytes, "UTF-8");
								System.out.println(" client decode str:" + str);
								Message message = JSON.parseObject(str, Message.class);
								System.out.println(" client decode message:" + message);
								out.add(message);

							}
						});
				pipeline.addLast("encoder",
						new MessageToMessageEncoder<Message>(){

							@Override
							protected void encode(ChannelHandlerContext ctx,
									Message message, List<Object> out)
									throws Exception {
								String json = JSON.toJSONString(message);
								out.add(Unpooled.copiedBuffer(json.getBytes()));
								System.out.println(" Encode ");
								System.out.println(" Arg1 :" + message);
								System.out.println(" Arg2 :" + out);
								
							}});
				pipeline.addLast("handler", new HelloClientHandler());
			}
		});
		b.option(ChannelOption.SO_KEEPALIVE, true);
		ChannelFuture f = b.connect("127.0.0.1", 8090).sync();
	//	f.channel().writeAndFlush(new Message(3,"Marco"));
		f.channel().closeFuture().sync();

	}

	private static class HelloClientHandler extends
			SimpleChannelInboundHandler<Message> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty
		 * .channel.ChannelHandlerContext, java.lang.Object)
		 */
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, Message msg)
				throws Exception {

			System.out.println(" Receive message from server : " + msg);
			ctx.channel().writeAndFlush(new Message(5,"zhubao"));
		}

		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {

			System.out.println(" Client Connected : ");
			ctx.writeAndFlush(new Message(2,"Tester"));
		}

		@Override
		public void channelReadComplete(ChannelHandlerContext ctx)
				throws Exception {
			ctx.flush();
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
				throws Exception {
			super.exceptionCaught(ctx, cause);
		}
		
		

	}

}