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

(1.1)  No, the total is not always the same. To explain is that
       there are two cores processing. For example, the first core
       is assigned to thread 1 in the program and the second to
       thread 2. Thread 1 send a message to counter to add(number) and
       counter then sends a signal to the "load memory" to add(number).
       After, it saves this information to the cache. Thread 2 also has
       to go through the same but is not aware of Thread 1 also performing.
       Concluding, the two thread are using the same counter procedure,
       which makes them work asynchronously.

(1.2)  Time recorded:

       First try  --> time = 0.017359s
       Second try --> time = 0.018404s
       Third try  --> time = 0.013114s
       Average = 0.016292s

(1.3) same as question 1.1

## 2. Implications for Multi-threaded Applications

How might this affect real applications? For example, if a lot of people
are simultaneously depositing money. There will be an error with multi-threads
working at the same time but unaware of one another.

## 3. Counter with ReentrantLock

answer questions 3.1 - 3.4

(3.1)  Time recorded:

       First try  --> time = 0.114867s
       Second try --> time = 0.107774s
       Third try  --> time = 0.102311s
       Average = 0.108317s

(3.2)  The results is equal to  zero and also different to problem one
       due to the Lock class. Since there is a ReentrantLock method for the
       Thread to wait for each other but is still accessing the same counter.
       The threads are still are now working on the same total value, therefore
       still adding and subtracting synchronously.

(3.3)  A ReentrantLock belong to a threads last successfully locking,
       but not yet unlocking it. Using try and finally, try will lock the thread
       that is in current work and then "finally" unlocking it for the next thread.
       A thread invoking lock will return, successfully acquiring the lock,
       when the lock is not owned by another thread.

(3.4)  The finally block is executed when an unexpected exception occurs. But in this
       code the "finally block" is entered every time in this case, to unlock.

## 4. Counter with synchronized method

answer question 4

(4.1)  Time recorded:

       First try  --> time = 0.264836s
       Second try --> time = 0.240687s
       Third try  --> time = 0.265851s
       Average = 0.257124s

(4.2)  The results are different to problem 1 as it using synchronous method making
       the total equal to 0.

(4.3)  Using the synchronous method, This guarantees that changes to the state of the
       object are visible to all threads. Therefore the threads are always waiting
       for each other and shares the same value.

## 5. Counter with AtomicLong

answer question 5

(5.1)  This method fixes problem one as this method is always working with the same value.
       For example if the value by Thread 1 is not the same as value in Thread 2, it skips the
       addition step first.
       Time recorded:

       First try  --> time = 0.029949s
       Second try --> time = 0.032166s
       Third try  --> time = 0.025700s
       Average = 0.029271s

(5.2)  For example, working on math operators like incrementing. So that it would be more precise.
       Using the methods in the Atomic class such as incrementAndGet();

## 6. Analysis of Results

answer question 6

(6.1)  Average time recorded in order of fast to slow:

       Atomic average      = 0.029271s
       Lock average        = 0.108317s
       Synchronous average = 0.257124s

(6.2)  The lock method is the best solution in my opinion. This is because the lock method
       can define which variable to be locked and unlocked. Also making the threads wait for
       each other when working on the same variable. If compared to the synchronous method. It
       is way more flexible as the synchronous has a limitation to its accessibility of values.

## 7. Using Many Threads (optional)

