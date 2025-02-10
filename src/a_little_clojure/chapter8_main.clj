(defn lup
  [n]
  (if (zero? n)
    :done
    (recur (dec n)))
  )

(lup 10)

(defn fac
  [x]
  (loop [n x
         f 1]
    (if (zero? n)
      f
      (recur (dec n) (* f n)))
    )
  )

(fac 5)
(fac 10)
(fac 50)
(fac 50N)

(defn fib
  [n]
  (if (<= n 2)
    1
    (+ (fib (dec n)) (fib (- n 2)))
    )
  )

(fib 5)
(fib 10)
(map fib (range 1 20))