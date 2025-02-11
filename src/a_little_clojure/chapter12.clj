(ns a-little-clojure.chapter12
  (:require [clojure.data.json :as json]))

(def data {:first-name "Robert"
           :last-name "Martin"
           :age 71
           :children ["Angela" "Micah" "Gina" "Justin"]}
  )

(defn -main
  [& args]
  (println (json/write-str data))
  )


