(ns a-little-clojure.chapter10)

(defn distance
  [[x1 y1 z1] [x2 y2 z2]]
  (Math/sqrt (+ (Math/pow (- x2 x1) 2)
                (Math/pow (- y2 y1) 2)
                (Math/pow (- z2 z1) 2)))
  )

(defn path-length
  [& points]
  (reduce + (map distance points (rest points)))
  )

(defn -main
  [& args]
  (println "Total distance is:" (path-length [0 0 0] [0 0 1] [0 1 1] [1 1 1]))
  )
