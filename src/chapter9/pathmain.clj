(ns chapter9.pathmain (:require [clojure.string :as str] [chapter9.pathlength :as pl]))

(defn parse-points
  [s]
  (let [[x y] (map #(Integer/parseInt %) (str/split s #","))]
    {:x x :y y})
  )

(defn -main
  [& args]
  (let [points (map parse-points args)
        total-length (pl/length points)]
    (println "Total length:" total-length))
  )
