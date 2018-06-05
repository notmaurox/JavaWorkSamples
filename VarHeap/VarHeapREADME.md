## dHeap.java
Implements dHeapInterface to create a class used for creating heaps with varying numbers of children per node. Uses an array to create heap but tracks data as if it was in a tree.

## dHeapInterface.java
Defines the methods that must be included in dHeap class

## dHeapTester.java
Contains junit tests used to test functionality of dHeap on various conditions in a tree.

## EDF.java
Takes in a file name as a parameter and uses it to populate a MyPriorityQueue object and prioritizes tasks based upon specific parameter. In this case reads file passed in on command line to create record objects. Will throw errors if the file provided on command line fails to open or is entered incorrectly. 

Input file must be of form...
```
schedule #Process #Deadline #duration
run #Time

schedule breakfast 1000 45
schedule study 1400 280
schedule dinner 1725 30
run 350 

schedule sleep 70 95
schedule coffee 80 20
run 70
schedule facebook 90 35
run 100
```

## MyPriorityQueue.java
Uses a binary dHeap object to provide the functionality of a priority queue with add and poll methods.

## Record.java
Class used to store tasks with process name, duration, and deadline. Also contains methods for comparing. Also contains getter methods.
