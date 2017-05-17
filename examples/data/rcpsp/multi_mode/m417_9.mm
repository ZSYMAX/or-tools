************************************************************************
file with basedata            : cm417_.bas
initial value random generator: 1075993679
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  18
horizon                       :  131
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     16      0       13        8       13
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        4          3           5   6   7
   3        4          2           8   9
   4        4          3           9  10  11
   5        4          3          11  12  15
   6        4          3           9  12  13
   7        4          1          17
   8        4          2          13  16
   9        4          2          15  17
  10        4          2          13  16
  11        4          1          14
  12        4          2          14  16
  13        4          2          14  15
  14        4          1          17
  15        4          1          18
  16        4          1          18
  17        4          1          18
  18        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     3       0    8    0    5
         2     3      10    0    9    0
         3     9       0    8    0    4
         4     9      10    0    0    5
  3      1     4       4    0    0    9
         2     4       0    7    0    9
         3     8       5    0    0    8
         4     9       3    0    0    8
  4      1     1       0    6    7    0
         2     6       0    4    0    9
         3     7       9    0    0    8
         4     8       2    0    0    8
  5      1     1       0    7    0    5
         2     6       8    0    4    0
         3     8       0    6    3    0
         4    10       0    5    0    5
  6      1     1       0   10    9    0
         2     4       5    0    8    0
         3     5       4    0    0    1
         4     5       0   10    8    0
  7      1     1       0    7    9    0
         2     3       9    0    0    9
         3     6       0    7    0    6
         4     8       0    5    7    0
  8      1     4       6    0    8    0
         2     4       0    4    0   10
         3     5       0    2    7    0
         4     7       4    0    7    0
  9      1     2       0   10    8    0
         2     3      10    0    7    0
         3     5       7    0    6    0
         4     7       0    6    4    0
 10      1     2       0    6    9    0
         2     5       7    0    0    5
         3    10       0    4    0    3
         4    10       6    0    0    3
 11      1     2       0    4    7    0
         2     5       0    4    0    2
         3     7       5    0    4    0
         4     9       0    4    4    0
 12      1     1       0    5    6    0
         2     3       5    0    0    8
         3     3       4    0    4    0
         4     7       0    4    0    8
 13      1     1       0    8    0    1
         2     4       0    7    5    0
         3     7       0    4    0    1
         4    10       6    0    5    0
 14      1     1       7    0    9    0
         2     2       0    6    0    7
         3     4       5    0    5    0
         4     8       4    0    0    6
 15      1     1       8    0    0    5
         2     4       0    8    0    4
         3     7       4    0    6    0
         4     9       0    7    0    3
 16      1     5       0    6    0    9
         2     6       5    0    1    0
         3     9       3    0    0    9
         4     9       0    1    0    9
 17      1     3       0    9    8    0
         2     4       0    5    0    7
         3     4       5    0    0    9
         4     6       0    4    0    7
 18      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
    9    8   80   73
************************************************************************