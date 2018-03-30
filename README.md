## Threads and Synchronization

This lab illustrates the problem of synchronization when many threads are operating on a shared object.  The general issue is called "thread safety", and it is a major cause of errors in computer software.

## Assignment

To the problems on the lab sheet and record your answers here.

1. Record average run times.
2. Write your explanation of the results.  Use good English and proper grammar.  Also use good Markdown formatting.

## ThreadCount run times

These are the average runtime using 3 or more runs of the application.
The Counter class is the object being shared by the threads.
The threads use the counter to add and subtract values.

| Counter class           | Limit              | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|
| Unsynchronized counter  |                    |                 |
| Using ReentrantLock     |                    |                 |
| Syncronized method      |                    |                 |
| AtomicLong for total    |                    |                 |

## 1. Using unsynchronized counter object

answer the questions (1.1 - 1.3)

(1.1) No, the total is not always the same. To explain is that
      there are two cores processing. For example, the first core
      is assigned to thread 1 in the program and the second to
      thread 2. Thread 1 send a message to counter to add(number) and
      counter then sends a signal to the "load memory" to add(number).
      After, it saves this information to the cache. Thread 2 also has
      to go through the same but is not aware of Thread 1 also performing.
      Concluding, the two thread are using the same counter procedure,
      which makes them work asynchronously.

(1.2) First try  --> time = 0.017359
      Second try --> time = 0.018404
      Third try  --> time = 0.013114

(1.3) same as question 1.1

## 2. Implications for Multi-threaded Applications

How might this affect real applications? For example, if a lot of people
are simultaneously depositing money. There will be an error with multi-threads
working at the same time but unaware of one another.

## 3. Counter with ReentrantLock

answer questions 3.1 - 3.4

(3.1) First try  --> time = 0.234239
      Second try --> time = 0.248832
      Third try  --> time = 0.238264

(3.2) The results are not equal to zero and also different to problem one
      due to the Lock class. Since there is a ReentrantLock method for the
      Thread to wait for each other but is still accessing the same counter.4
      The threads are still not using working on the same total value, therefore
      still adding and subtracting values separately.


## 4. Counter with synchronized method

answer question 4 - 

## 5. Counter with AtomicLong

answer question 5

## 6. Analysis of Results

answer question 6

## 7. Using Many Threads (optional)

