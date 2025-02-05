(def midway (fn [a b] (/ (+ a b) 2)))
(defn midway [a b] (/ (+ a b) 2))
(def midway #(/ (+ %1 %2) 2))

(midway 6 9)

(reduce midway [2 3])
(reduce midway [2 3 4])
(reduce midway [2 3 4 5])
(reduce midway [0 2 3 4 5])
(reduce midway 0 [2 3 4 5])

(reductions midway [1 2 3 4 5])

(defn square [x] (* x x))
(reduce midway (map square (range 10)))
(->> (range 10) (map square) (reduce midway))
(->> (range 10) (map square) (reductions midway))
(->> (range 10) (map square) (reductions midway) (map double))

(defn mean [l] (/ (reduce + l) (count l)))
(defn sigma
  [l]
  (let [u (mean l)]
    (->> l (map #(- % u)) (map square) mean Math/sqrt)
    ;(->> l (map (fn [x] (- x u))) (map square) mean Math/sqrt)
    )
  )

(sigma [1 2 3 4 5])
; [1 2 3 4 5] -> [-2 -1 0 1 2] -> [4 1 0 1 4] -> 2 -> 1.4142135623730951