(ns a-little-clojure.chapter8
  (:gen-class))

(def Cd 1/100000)
(def g 32.174)
(def TIC 1/1000)

(defn air-density
  [h]
  (+ (* -0.0000375085 (Math/pow h 3))
     (* 0.0035692521 (Math/pow h 2))
     (* -0.1132617585 h)
     1.2225038068)
  )

(defn gravity
  [v]
  (- v (* g TIC))
  )

(defn drag
  [h v]
  (let [av (abs v)
        sv (pos? v)
        av (- av (* Cd (air-density (/ h 1000)) av av TIC))]
    (if sv
      av
      (- av))
    )
  )

(defn track
  []
  (loop [x 0 y 0 xv 500 yv 1500 t 0 maxy 0]
    (if (and (> t 0) (<= y 0))
      (do
        (println "\nSimulation Results:")
        (println "=======================")
        (printf "Time to hit the ground: %.2f seconds\n" (float t))
        (printf "Horizontal distance: %.2f feet\n" (float x))
        (printf "Final vertical position (y): %.2f feet\n" (float y))
        (printf "Maximum altitude reached: %.2f feet\n" (float maxy))
        (println "======================="))
      (recur (+ x (* TIC xv))
             (+ y (* TIC yv))
             (drag y xv)
             (gravity (drag y yv))
             (+ t TIC)
             (max maxy y)))
    )
  )

(defn find-fractions
  [target-sum]
  (loop [n 1 sum 0]
    (if (>= sum target-sum)
      n
      (recur (inc n) (+ sum (/ 1 n))))
    )
  )

(defn word-wrap
  [s n]
  (if (<= (count s) n)
    s
    (let [last-space (clojure.string/last-index-of s " " n)
          split-point (if (nil? last-space) n last-space)
          join-point (if (nil? last-space) n (inc last-space))]
      (str (subs s 0 split-point) "\n" (word-wrap (subs s join-point) n))
      )
    )
  )

(defn -main
  [& args]
  ;(track)
  ;(println (find-fractions 10))
  (println (word-wrap "Four score and seven years ago our fathers brought forth upon this continent a new nation conceived in liberty and dedicated to the proposition that all men are created equal", 20))
  )