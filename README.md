# Steps

You can run the program(com.postalcode.test.ZipCodeSortUtilTest) as standalone. Right Click-> Run as junit

# Program

1) Create a Range class with lower upper limit of zip codes
2) Sort the given input by provided lower range
3) Loop it through the range after sorting.
4) Check lower value in previous range
	a) If not in range skip the record
	b) If in range, replace the upper value which ever is maximum in both current and previous range 
5) Replace the previous range with current range to continue with the check in loop