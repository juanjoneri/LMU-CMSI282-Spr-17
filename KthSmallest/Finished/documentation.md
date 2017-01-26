# Experiments run on Select.java
### To confirm expected linear runtime

## Procedure
Use a python script to generate a list of n random numbers *(n given by standard in)* and then use unixes
built in time command to measure the runtime of the KthSmallest algorithm. Repeat each measurement 5 times
and compute the average for each n.

We repeated the procedure for 9 lists containing increasing amount of data, using the same computer,
plugged in, within the same hour to avoid external variables.

## Commands

### Generating a list of n random integers in the range 1-100 to ensure lots of repetitions
python3 generate_list.py [n] > list.txt

### Timing the algorithm in Ubuntu
time java Select [k] < list.txt
*generally we set k = n/2 to avoid BAD DATA (ensure k is in range)*

## Results

| STEPS | TIME (s) |
| ----- | -------- |
| 1E5   | 0.146    |
| 1E6   | 0.2714   |
| 5E6   | 0.7774   |
| 8E6   | 2.598    |
| 1E7   | 3.1622   |
| 2E7   | 10.811   |
| 3E7   | 12.5682  |
| 4E7   | 20.016   |
| 5E7   | 28.61175 |

*E here represents 10^, and time represents the avg of 5 measurements*
When plotted in Excel, this data follows a linear relationship wiht an R^2 = 0.9766
which is what we were expecting to see.
