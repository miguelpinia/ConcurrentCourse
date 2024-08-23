# Concurrent course repository

This repository contains code implementations of algorithms from the
book of The Art of Multiprocessor Programming by Nir Shavit and
Maurice Herlihy.

## Algorithms implemented

### Mutual exclusion

   - LockOne
   - LockTwo
   - Peterson Lock


## How to run the code

  The code is implemented using Java 21 and we try to follow the code
  guidelines used in the book. In the case of use new language
  features, it will be comented directly in the code of each class.

  Project compilation is done using the command:

  ``` shell
  mvn clean compileszx
  ```

  Each class has its own test, and it can be executed running the next
  command:

  ```shell
  mvn test -Dtest=LockOneTest
  ```

  Where the name of the test is the name of the class (such classes
  were listed in the previous section) together with the suffix `Test`
