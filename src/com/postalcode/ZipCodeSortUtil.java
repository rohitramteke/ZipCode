package com.postalcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Comparator.comparing;

/**
 * 
 * @author rramteke
 *
 */
public class ZipCodeSortUtil {

	private static final Logger LOGGER = Logger.getLogger(ZipCodeSortUtil.class.getName());

	/**
	 * This method will take sorted range values and prepares merge list.
	 * 
	 * @param input
	 * @return List<Range>
	 */
	public static List<Range> mergePostalCodeZipRanges(List<Range> input) {
		if (input == null || input.isEmpty()) {
			return Collections.emptyList();
		} else {
			boolean validationError = false;
			for (Range range : input) {
				if(!(range.getLower()!=null && !"".equals(range.getLower()))){
					LOGGER.warning("Incorrect Input, either null or empty lower value provided ");
					validationError = false;
					return Collections.emptyList();
				}
				if(!(range.getUpper()!=null && !"".equals(range.getUpper()))){
					LOGGER.warning("Incorrect Input, either null or empty upper value provided ");
					validationError = false;
					return Collections.emptyList();
				}
				// Validate input for all numeric value
				if(!(range.getLower().chars().allMatch( Character::isDigit ) && range.getUpper().chars().allMatch( Character::isDigit ))){
					LOGGER.warning("Incorrect Input, please provide numeric values ");
					validationError = false;
					return Collections.emptyList();
				}
				// Validate input for lower bound should not be greater than upper bound
				if(Integer.parseInt(range.getLower()) > Integer.parseInt(range.getUpper())){
					LOGGER.warning("Incorrect Input, lower value > upper value ");
					validationError = false;
					return Collections.emptyList();
				}
			}
			
			// sort postal code ranges based on lower bound value
			input.sort(comparing(Range::getLower));
		}

		List<Range> output = new ArrayList();

		String lower = null;
		String upper = null;
		Range prevRange = input.get(0); // get the first range value at index 0
										// and lower bound and upper bound
		LOGGER.info("First range::" + prevRange);
		lower = prevRange.getLower();
		upper = prevRange.getUpper();
		for (int i = 1; i < input.size(); i++) {

			Range currentRange = input.get(i);
			LOGGER.info("currentRange::" + currentRange);
			// check whether current range is falls in between minVal and maxval
			if (isRangeBetweenMinAndMax(currentRange, lower, upper)) {
				LOGGER.info("Range between min and max ");
				// skip record and continue with rest of the values in the list
				continue;
			} else {

				String presentRangeMaxVal = currentRange.getUpper();
				LOGGER.info("presentRangeMaxVal:: " + presentRangeMaxVal);
				String presentRangeMinVal = currentRange.getLower();
				LOGGER.info("presentRangeMinVal:: " + presentRangeMinVal);

				LOGGER.info("presentRangeMinVal <= upper:: "
						+ (Integer.parseInt(presentRangeMinVal) <= Integer.parseInt(upper)));
				if (Integer.parseInt(presentRangeMinVal) <= Integer.parseInt(upper)) { // update
																						// upper
																						// bound
																						// value
																						// and
																						// continue
					upper = (Integer.parseInt(upper) > Integer.parseInt(presentRangeMaxVal)) ? upper
							: presentRangeMaxVal;
					LOGGER.info("upper:: " + upper);
				} else {
					// add prev range values to output and update min and max
					// values
					LOGGER.info("else:: " + new Range(lower, upper));
					output.add(new Range(lower, upper));
					lower = presentRangeMinVal;
					upper = presentRangeMaxVal;

				}

			}

		}

		// add the the range values calculated from iteration
		output.add(new Range(lower, upper));
		return output;
	}

	/**
	 * This method will be used to check Range object is falls in between min
	 * and max value
	 * 
	 * @param range
	 * @param min
	 * @param max
	 * @return <tt>true</tt> if <tt>range</tt> is falls between <tt>min</tt> and
	 *         <tt> max </tt>
	 */
	public static boolean isRangeBetweenMinAndMax(Range range, String min, String max) {
		if (Integer.parseInt(max) >= Integer.parseInt(range.getUpper())
				&& Integer.parseInt(range.getLower()) <= Integer.parseInt(min)) {
			return true;
		} else {
			return false;
		}

	}

}
