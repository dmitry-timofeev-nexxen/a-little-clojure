(defn quad
  [a b c]
  (let [d (- (* b b) (* 4 a c))]
    (cond
      (zero? a) (/ (- c) b)
      (neg? d) "imaginary"
      (zero? d) (/ (- b) (* 2 a))
      :else [(/ (+ (- b) (Math/sqrt d)) (* 2 a)) (/ (- (- b) (Math/sqrt d)) (* 2 a))]))
  )

(quad 1 2 1)
(quad 1 2 3)
(quad 0 2 3)
(quad 1 1 0)
(quad 1 -0.25 -0.125)


(defn fizzbuzz
  [n]
  (for [i (range 1 (inc n))] ; new collection, no side effects
    (cond
      (zero? (rem i 15)) "FizzBuzz"
      (zero? (rem i 5)) "Buzz"
      (zero? (rem i 3)) "Fizz"
      :else i
      )
    )
  )

(fizzbuzz 20)
(fizzbuzz 100)


(def points [["O" 0 0] ["A" 1 1] ["B" 1 2] ["C" 2 3] ["D" 4 5]])

(defn square
  [x]
  (* x x))

(defn distance
  [x1 x2 y1 y2]
  (Math/sqrt (+ (square (- x1 x2)) (square (- y1 y2))))
  )

(defn distances
  [points]
  (doseq [p1 points p2 points :when (not= p1 p2)] ; sorts through the collection, with side effects
    (println
      (str (first p1) "-" (first p2)) (distance (second p1) (second p2) (last p1) (last p2)))
    )
  )

(distances points)