package com.demo.test1;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * nio主要考虑缓冲区，使用Buffer，除了boolean类型以外，其他所有自己的缓冲区类
 * 
 * 【1】主要是实现核心方法：allocate()方法实现对缓冲区的管理，并且管理的方式几乎一致
 * 
 * 【2】缓冲区实现存取数据的两大核心操作 put() 存放数据 get() 获取数据
 * 
 * 【3】缓冲区中的四个核心属性
 * 
 * private int mark = -1; private int position = 0; 表示缓冲区中正在被操作的数据的位置 private
 * int limit; 界限，表示缓冲区可操作数据的大小，一旦limit以后，数据就不可以再进行读写 private int capacity;
 * 表示缓冲区中存放数据的最大容量，一旦声明就不能更改声明
 */

public class NioTest1 {

	@Test
	public void test1() {
		// 声明一个指定大小的缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		System.out.println("位置:" + byteBuffer.position());
		System.out.println("容量：" + byteBuffer.capacity());

		System.out.println("-----put----------");
		// 使用put方式存入数据
		String str = "abcde";
		byteBuffer.put(str.getBytes());
		System.out.println("位置:" + byteBuffer.position());

		System.out.println("-------get------");
		System.out.println(byteBuffer.get(3));

		// 切换读取数据模式
		byteBuffer.flip();

	}

}
