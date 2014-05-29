/**
 * 
 */
package org.nerdronix.springnetty.server;

import com.alibaba.fastjson.JSON;

/**
 * @author Jason.Zhu
 * @date   2014-5-28
 * @email jasonzhu@augmentum.com.cn
 */
public class TestJSON {

	public static void main(String[] args) {
		
		Message message = new Message(1, "jason");
		System.out.println(JSON.toJSON(message));
		
		Message test = JSON.parseObject(JSON.toJSONString(message), Message.class);
		System.out.println(test);
	}
}
