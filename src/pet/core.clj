(ns pet.core
  (:gen-class))

(def pet-age-multiplier {'dog 7 'cat 5 'fish 10})

;(defn -main
;  [pet age]
;  (println (str "Pet: " pet ", age: " (* (pet-age-multiplier pet) age)))
;  )

;(defn -main
;  [age]
;  (doseq [[pet pet-age] pet-age-multiplier]
;    (println (str "Pet: " pet ", age: " (* age pet-age)))
;    )
;  )

(defn calculate-age
  [pet-name pet-age pet-type]
  (let [ratio (get pet-age-multiplier pet-type)]
    (println (str pet-name " is " (* pet-age ratio) " years old."))
    )
  )

(calculate-age "Fido" 3 'dog)
(calculate-age "Whiskers" 4 'cat)
(calculate-age "Fibi" 1 'fish)