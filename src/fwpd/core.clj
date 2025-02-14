(ns fwpd.core)

(def filename "resources/suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value)
  )

(defn parse
  [string]
  (map #(clojure.string/split % #",") (clojure.string/split-lines string))
  )

(defn mapify
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]] (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row))
         )
       rows
       )
  )

(defn glitter-filter
  [minimum-glitter records]
  (map :name (filter #(>= (:glitter-index %) minimum-glitter) records))
  )

(defn validate
  [validators record]
  (every? (fn [[k _]]
            (and (contains? record k)
                 ((validators k) (record k))))
          validators)
  )

(defn non-empty-string?
  [s]
  (and (string? s) (not (clojure.string/blank? s)))
  )

(defn positive-integer?
  [n]
  (and (integer? n) (pos? n))
  )

(def validators {:name non-empty-string? :glitter-index positive-integer?})

(defn append
  [new-suspect]
  (if (validate validators new-suspect)
    (let [formatted-suspect (str "\n" (new-suspect :name) "," (new-suspect :glitter-index))]
      (spit filename formatted-suspect :append true)
      )
    (println "Invalid suspect data, not appending.")
    )
  )

(defn map-to-csv-row
  [m]
  (clojure.string/join "," (map m[:name :glitter-index]))
  )

(defn map-to-csv
  [maps]
  (clojure.string/join "\n" (map map-to-csv-row maps))
  )

(defn -main
  [& args]
  (println (glitter-filter 3 (mapify (parse (slurp filename)))))
  (println (mapify (parse (slurp filename))))
  (println (map-to-csv (mapify (parse (slurp filename)))))

  ;(append {:name "Dracula" :glitter-index 5})
  ;(append {:name "Hasbula" :glitter-index -1})
  ;(append {:name "" :glitter-index 10})
  ;
  ;(println (slurp filename))
  )
