"""
use this script to generate a list to debug
KthSmallest. java
In this lists, Kth is just k
"""

"""
import random
data = list(range(1,10000000))
random.shuffle(data)
for i in data:
    print(i)
"""

"""

#print your data twice and Kth will be... k/2
for j in range(20):
    import random
    data = list(range(1,100))
    random.shuffle(data)
    for i in data:
        print(i)

"""
import random
import sys
numbers = [random.randrange(1,101,1) for _ in range(int(sys.argv[1]))]
for i in numbers:
    print(i)
