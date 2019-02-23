package com.bc.mor;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) throws IOException {
		context = new ClassPathXmlApplicationContext(new String[] { "conf\\applicationProvider.xml" });
		context.start();
		synchronized (Main.class) {
			while (true) {
				try {
					Main.class.wait();
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
