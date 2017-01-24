"""
use this script to generate a list to debug
KthSmallest. java
In this lists, Kth is just k
"""

import random
data = list(range(1,100))
random.shuffle(data)
for i in data:
    print(i)

#print your data twice and Kth will be... k/2
random.shuffle(data)
for i in data:
    print(i)
