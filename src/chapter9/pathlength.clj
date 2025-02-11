(ns chapter9.pathlength (:require [chapter9.geometry :as geo]))

(defn length
  [points]
  (reduce + (map geo/distance points (rest points)))
  )
