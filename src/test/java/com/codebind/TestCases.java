package com.codebind;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCases {

	@Test
	public void test1() {
		Calculator cal = new Calculator();
		int result = cal.add(2, 3);
		assertEquals("not correct",5,result);
	}
	
	@Test
	public void test2() {
		Calculator cal = new Calculator();
		int result = cal.subtract(5, 2);
		assertEquals("not correct",3,result);
	}
	
	@Test
	public void test3() {
		Calculator cal = new Calculator();
		int result = cal.multiply(2, 3);
		assertEquals("not correct",6,result);
	}
	
	@Test
	public void test4() {
		Calculator cal = new Calculator();
		int result = cal.divide(10, 2);
		assertEquals("not correct",5,result);
	}

}
