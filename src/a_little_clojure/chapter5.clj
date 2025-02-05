(defn mean [l] (/ (reduce + l) (count l)))
(defn sigma
  [l]
  (let [u (mean l)]
    (->> l (map #(- % u)) (map square) mean Math/sqrt)
    )
  )

(defn subtract-mean [l]
  (let [u (mean l)]
    (map (fn [x] (- x u)) l)                                ; Can't find solution without anonymous function
    ;(map #(- % u) l) ;alternative
    )
  )

(subtract-mean [1 2 3 4 5])

(defn sigma
  [l]
  (Math/sqrt (mean (map square (subtract-mean l))))
  )

; [1 2 3 4 5] -> [-2 -1 0 1 2] -> [4 1 0 1 4] -> 2 -> 1.4142135623730951
(sigma [1 2 3 4 5])


(defn popularity
  [l]
  (reduce (fn [x acc] (+ (/ x 2) acc)) l)
  ; (reduce #(+ (/ %1 2) %2) l) ; alternative
  )

(double (popularity [100 150 90 200 120 150 90]))
; [100 150 90 200 120 150 90] -> [200 90 200 120 150 90] -> [190 200 120 150 90] -> [295 120 150 90] -> [267.5 150 90] -> [283.75 90] -> 231.875


(defn shipping
  [l]
  (reduce (fn [[x y] [dx dy]] [(+ x dx) (+ y dy)]) [0 0] l))

(shipping [[1 1] [0 0] [2 0] [-1 2] [-1 -1]])
(shipping [[1 1] [2 3] [0 0] [1 2] [2 0] [-1 2] [-1 -1]])