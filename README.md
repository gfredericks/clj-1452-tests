# clj-1452-tests

Tests for [CLJ-1452](http://dev.clojure.org/jira/browse/CLJ-1452).

`rand` is slightly slower, while `shuffle` is insignificantly
faster. Using shuffle from 8 threads is insignificantly slower,
but switching to a `ThreadLocalRandom` manually in the patched
version results in a 2.5x speedup.


## Usage

Running on my 8 core linode vm:

```
$ echo "Clojure 1.6.0"; lein with-profile base,clj-1.6 run; echo "Clojure 1.6.0 with *rand*"; lein with-profile base,clj-1452 run
Clojure 1.6.0

;;;;;;;;;;;;;;;;;;
;; Testing rand ;;
;;;;;;;;;;;;;;;;;;
WARNING: Final GC required 1.261632096547911 % of runtime
Evaluation count : 644646900 in 60 samples of 10744115 calls.
             Execution time mean : 61.297605 ns
    Execution time std-deviation : 7.057249 ns
   Execution time lower quantile : 56.872437 ns ( 2.5%)
   Execution time upper quantile : 84.483045 ns (97.5%)
                   Overhead used : 16.319772 ns

Found 6 outliers in 60 samples (10.0000 %)
	low-severe	 1 (1.6667 %)
	low-mild	 5 (8.3333 %)
 Variance from outliers : 75.5119 % Variance is severely inflated by outliers

;;;;;;;;;;;;;;;;;;;;;
;; Testing shuffle ;;
;;;;;;;;;;;;;;;;;;;;;
Evaluation count : 4780800 in 60 samples of 79680 calls.
             Execution time mean : 12.873832 µs
    Execution time std-deviation : 251.388257 ns
   Execution time lower quantile : 12.526871 µs ( 2.5%)
   Execution time upper quantile : 13.417559 µs (97.5%)
                   Overhead used : 16.319772 ns

Found 3 outliers in 60 samples (5.0000 %)
	low-severe	 3 (5.0000 %)
 Variance from outliers : 7.8591 % Variance is slightly inflated by outliers

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Testing threaded-shuffling ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Evaluation count : 420 in 60 samples of 7 calls.
             Execution time mean : 150.863290 ms
    Execution time std-deviation : 2.313755 ms
   Execution time lower quantile : 146.621548 ms ( 2.5%)
   Execution time upper quantile : 155.218897 ms (97.5%)
                   Overhead used : 16.319772 ns
Clojure 1.6.0 with *rand*

;;;;;;;;;;;;;;;;;;
;; Testing rand ;;
;;;;;;;;;;;;;;;;;;
Evaluation count : 781707720 in 60 samples of 13028462 calls.
             Execution time mean : 63.679152 ns
    Execution time std-deviation : 1.798245 ns
   Execution time lower quantile : 61.414851 ns ( 2.5%)
   Execution time upper quantile : 67.412204 ns (97.5%)
                   Overhead used : 13.008428 ns

Found 3 outliers in 60 samples (5.0000 %)
	low-severe	 3 (5.0000 %)
 Variance from outliers : 15.7596 % Variance is moderately inflated by outliers

;;;;;;;;;;;;;;;;;;;;;
;; Testing shuffle ;;
;;;;;;;;;;;;;;;;;;;;;
Evaluation count : 4757940 in 60 samples of 79299 calls.
             Execution time mean : 12.780391 µs
    Execution time std-deviation : 240.542151 ns
   Execution time lower quantile : 12.450218 µs ( 2.5%)
   Execution time upper quantile : 13.286910 µs (97.5%)
                   Overhead used : 13.008428 ns

Found 1 outliers in 60 samples (1.6667 %)
	low-severe	 1 (1.6667 %)
 Variance from outliers : 7.8228 % Variance is slightly inflated by outliers

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Testing threaded-shuffling ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Evaluation count : 420 in 60 samples of 7 calls.
             Execution time mean : 152.471310 ms
    Execution time std-deviation : 8.769236 ms
   Execution time lower quantile : 147.954789 ms ( 2.5%)
   Execution time upper quantile : 161.277200 ms (97.5%)
                   Overhead used : 13.008428 ns

Found 3 outliers in 60 samples (5.0000 %)
	low-severe	 3 (5.0000 %)
 Variance from outliers : 43.4058 % Variance is moderately inflated by outliers

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Testing threaded-local-shuffling ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Evaluation count : 960 in 60 samples of 16 calls.
             Execution time mean : 64.462853 ms
    Execution time std-deviation : 1.407808 ms
   Execution time lower quantile : 62.353265 ms ( 2.5%)
   Execution time upper quantile : 67.197368 ms (97.5%)
                   Overhead used : 13.008428 ns

Found 1 outliers in 60 samples (1.6667 %)
	low-severe	 1 (1.6667 %)
 Variance from outliers : 9.4540 % Variance is slightly inflated by outliers
```

## License

Copyright © 2014 Gary Fredericks

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
