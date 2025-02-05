(defn fac
  [n]
  (apply * (range 1 (inc n)))
  )

(defn fac
  ([n] (fac n 1N))
  ([n f] (if (= n 1) f (fac (dec n) (*' n f))))
  )

(defn fac
  ([n] (fac n 1N))
  ([n f] (if (= n 1) f (recur (dec n) (*' n f))))
  )

(fac 20)
(fac 21)
(fac 50)
(fac 100)
(fac 1000)
(fac 10000)


(time (apply + (repeat 1000000 1N)))
(time (apply + (repeat 1000000 1)))

(/ (fac 20) (fac 30))

(/ 1M 2)
(+ 1M 1e-50M)
(/ 1M 3)
(with-precision 20 (/ 1M 3))

(defn pow
  ([n x] (pow n x 1N))
  ([n x acc] (if (< x 1) acc (pow n (dec x) (*' acc n))))
  )

(pow 2 10)
(pow 10 30)
(pow 1.6 10)
(pow 1.6M 10)

(defn sin-term-sign
  [n]
  (- (- (rem n 4) 2))
  )

(range 1 10 2)
(map sin-term-sign (range 1 10 2))

(defn sin-term
  [x n]
  (* (sin-term-sign n) (/ (pow x n) (fac n)))
  )

(sin-term 1 1)
(sin-term 1 3)
(sin-term 1 5)

(defn sin
  [x n]
  (let [ns (range 1 n 2)
        terms (map (fn [n] (sin-term x n)) ns)]
    (apply + terms))
  )

(sin 1 5)
(sin 1 10)
(sin 1 20)
(with-precision 20 (bigdec (sin 1 20)))
(Math/sin Math/PI)
(sin Math/PI 30)
(sin (/ Math/PI 2) 30)
(sin 100 30)
(with-precision 20 (bigdec (sin 100 30)))