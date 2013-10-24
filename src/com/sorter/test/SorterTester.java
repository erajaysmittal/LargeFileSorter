package com.sorter.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sorter.component.Sorter;

public class SorterTester {

	@Test
	public void test() {
		assertEquals(1, Sorter._calculateRoundCount(3, 4));
		assertEquals(2, Sorter._calculateRoundCount(4, 2));
		assertEquals(8, Sorter._calculateRoundCount(256, 2));
		assertEquals(8, Sorter._calculateRoundCount(129, 2));
		assertEquals(2, Sorter._calculateRoundCount(256, 16));
		assertEquals(2, Sorter._calculateRoundCount(256, 19));
		assertEquals(2, Sorter._calculateRoundCount(5, 4));
	}

}
