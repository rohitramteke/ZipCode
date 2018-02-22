package com.postalcode.test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import com.postalcode.Range;
import com.postalcode.ZipCodeSortUtil;

import junit.framework.TestCase;

/**
 * 
 * @author rramteke
 *
 */
public class ZipCodeSortUtilTest extends TestCase {

	private static final Logger LOGGER = Logger.getLogger(ZipCodeSortUtilTest.class.getName());

	/**
	 * Input for test case
	 * 
	 * @return
	 */
	List<Range> firstInput() {
		Range r1 = new Range("49679", "52015");
		Range r2 = new Range("49800", "50000");
		Range r3 = new Range("51500", "53479");
		Range r4 = new Range("45012", "46937");
		Range r5 = new Range("54012", "59607");
		Range r6 = new Range("45500", "45590");
		Range r7 = new Range("45999", "47900");
		Range r8 = new Range("44000", "45000");
		Range r9 = new Range("43012", "45950");

		List<Range> input = new ArrayList();
		input.add(r1);
		input.add(r2);
		input.add(r3);
		input.add(r4);
		input.add(r5);

		input.add(r6);
		input.add(r7);
		input.add(r8);
		input.add(r9);

		return input;

	}

	/**
	 * Input for test case
	 * 
	 * @return
	 */
	List<Range> secondInput() {
		Range r1 = new Range("94133", "94133");
		Range r2 = new Range("94200", "94299");
		Range r3 = new Range("94600", "94699");

		List<Range> input = new ArrayList();
		input.add(r1);
		input.add(r2);
		input.add(r3);

		return input;

	}

	/**
	 * Input for test case
	 * 
	 * @return
	 */
	List<Range> thirdInput() {
		Range r1 = new Range("94133", "94133");
		Range r2 = new Range("94200", "94299");
		Range r3 = new Range("94226", "94399");

		List<Range> input = new ArrayList();
		input.add(r1);
		input.add(r2);
		input.add(r3);

		return input;

	}

	/**
	 * First test
	 */
	@Test
	public void testMergePostalCodeZipRangesFirst() {
		List<Range> input = firstInput();

		Range r1 = new Range("43012", "47900");
		Range r2 = new Range("49679", "53479");
		Range r3 = new Range("54012", "59607");
		List<Range> output = new ArrayList<Range>();
		output.add(r1);
		output.add(r2);
		output.add(r3);
		assertEquals(output, ZipCodeSortUtil.mergePostalCodeZipRanges(input));
		output.forEach(i -> LOGGER.info(i.toString()));
	}

	/**
	 * Second test
	 */
	@Test
	public void testMergePostalCodeZipRangesSecond() {
		List<Range> input = secondInput();

		Range r1 = new Range("94133", "94133");
		Range r2 = new Range("94200", "94299");
		Range r3 = new Range("94600", "94699");

		List<Range> output = new ArrayList<Range>();
		output.add(r1);
		output.add(r2);
		output.add(r3);
		assertEquals(output, ZipCodeSortUtil.mergePostalCodeZipRanges(input));
		output.forEach(i -> LOGGER.info(i.toString()));
	}

	/**
	 * Third test
	 */
	@Test
	public void testMergePostalCodeZipRangesThird() {
		List<Range> input = thirdInput();

		Range r1 = new Range("94133", "94133");
		Range r2 = new Range("94200", "94399");

		List<Range> output = new ArrayList<Range>();
		output.add(r1);
		output.add(r2);
		assertEquals(output, ZipCodeSortUtil.mergePostalCodeZipRanges(input));
		output.forEach(i -> LOGGER.info(i.toString()));
	}

}
