package com.demo.test1;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * nio��Ҫ���ǻ�������ʹ��Buffer������boolean�������⣬���������Լ��Ļ�������
 * 
 * ��1����Ҫ��ʵ�ֺ��ķ�����allocate()����ʵ�ֶԻ������Ĺ������ҹ���ķ�ʽ����һ��
 * 
 * ��2��������ʵ�ִ�ȡ���ݵ�������Ĳ��� put() ������� get() ��ȡ����
 * 
 * ��3���������е��ĸ���������
 * 
 * private int mark = -1; private int position = 0; ��ʾ�����������ڱ����������ݵ�λ�� private
 * int limit; ���ޣ���ʾ�������ɲ������ݵĴ�С��һ��limit�Ժ����ݾͲ������ٽ��ж�д private int capacity;
 * ��ʾ�������д�����ݵ����������һ�������Ͳ��ܸ�������
 */

public class NioTest1 {

	@Test
	public void test1() {
		// ����һ��ָ����С�Ļ�����
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		System.out.println("λ��:" + byteBuffer.position());
		System.out.println("������" + byteBuffer.capacity());

		System.out.println("-----put----------");
		// ʹ��put��ʽ��������
		String str = "abcde";
		byteBuffer.put(str.getBytes());
		System.out.println("λ��:" + byteBuffer.position());

		System.out.println("-------get------");
		System.out.println(byteBuffer.get(3));

		// �л���ȡ����ģʽ
		byteBuffer.flip();

	}

}
