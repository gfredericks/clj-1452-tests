(ns clj-1452-tests.core
  (:require [criterium.core :refer [bench]])
  (:import [java.util.concurrent ThreadLocalRandom]))

(def nums (vec (range 100)))
(def seedable? (boolean (resolve '*rand*)))

(defn threaded-shuffling
  []
  (->> (repeatedly 8
                   (fn []
                     (future
                       (->> (repeatedly 1000 #(shuffle nums))
                            (map #(reduce + %))
                            (reduce +)))))
       (doall)
       (map deref)
       (reduce +)))


(def threaded-local-shuffling
  (when seedable?
    (eval
     '(fn
        []
        (->> (repeatedly 8
                         (fn []
                           (future
                             (binding [*rand* (ThreadLocalRandom/current)]
                               (->> (repeatedly 1000 #(shuffle nums))
                                    (map #(reduce + %))
                                    (reduce +))))))
             (doall)
             (map deref)
             (reduce +))))))

(defn msg
  [s]
  (let [len (+ 6 (count s))]
    (println "")
    (println (apply str (repeat len \;)))
    (println ";;" s ";;")
    (println (apply str (repeat len \;)))))

(defn -main
  []
  (msg "Testing rand")
  (bench (rand))

  (msg "Testing shuffle")
  (bench (shuffle nums))

  (msg "Testing threaded-shuffling")
  (bench (threaded-shuffling))

  (when seedable?
    (msg "Testing threaded-local-shuffling")
    (bench (threaded-local-shuffling))))
