package com.postalcode;

/**
 * 
 * @author rramteke
 *
 */
public class Range {

	private String lower;
	private String upper;
	
	
	
	public Range(String lower, String upper) {
		super();
		this.lower = lower;
		this.upper = upper;
	}



	public String getLower() {
		return lower;
	}



	public void setLower(String lower) {
		this.lower = lower;
	}



	public String getUpper() {
		return upper;
	}



	public void setUpper(String upper) {
		this.upper = upper;
	}



	@Override 
	
	public String toString() {
		return "[" +  lower   + ","  + upper  + "]"  ;
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Range)) {
            return false;
        }

        Range range = (Range) o;

        return range.lower == lower && 
        		range.upper == upper ;
    }

    @Override
    public int hashCode() {
    	String result = lower + upper;
        return Integer.valueOf(result);
    }
	
}
