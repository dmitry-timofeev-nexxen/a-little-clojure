(ns letters.chapter7 (:require [clojure.set :as set] [clojure.string :as string]))

(def alphabet (set (map char (range 97 123))))

(defn find-missing
  [str]
  (string/join "" (set/difference alphabet (set str)))
  )

(defn -main
  [& args]
  (println (find-missing "bob"))
  (println (find-missing "alice"))
  (println (find-missing "daniel"))
  )