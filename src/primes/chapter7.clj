(ns primes.chapter7
  (:require [clojure.set :as set]))

(defn find-primes [n]
  (let [ns (range 2 (inc n))]
    (loop [primes (set ns)
           candidates ns]
      (if (empty? candidates)
        (sort primes)
        (let [p (first candidates)]
          (let [multiples (set (range (* p p) (inc n) p))]
            (recur (set/difference primes multiples) (rest candidates))))
        )
      )
    )
  )

(defn -main
  [& args]
  (println (find-primes 30))
  (println (find-primes 1000))
  (println (find-primes 10000))
  )