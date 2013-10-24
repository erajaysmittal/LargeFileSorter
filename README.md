LargeFileSorter
===============

This will sort large files , with in memory constraints


Design Details:-

I have implemented merge sort which is based on divide and conquer approach. Here we divide the file into smaller 
intermediate files and sort them and after sorting the smaller file me merge them. We follow this approach till 
the time file doesn't get sorted completely. 

Implementation Details:-

1. The com.sorter.service package contains TestFileSorter.java, which can be used for sorting text file.
2. The com.sorter.component contains vital components used for performing specific functionalities.
3. Sorter.java is the main entry point.
4. com.sorter.reader package contains the components used to read the source file and intermediate generated files.  
5. com.sorter.writer package contains the components used to write the sorted result to destination file.
6. com.sorter.iterator package contains components used to iterate through the source file and sort it.
7. com.sorter.util contains components used for performing data conversions.
8. com.sorter.exception package contain customized exception.
9. SorterTester.java is a test case class which contains the test cases.
10.config folder contains generate_data.pl used for generating source file with required size content. 
   

