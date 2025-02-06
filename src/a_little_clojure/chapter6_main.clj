(def m {"one" 1 "two" 2})
(def me (first m))
(def not-me ["one" 1])

(map #(str (first %) ":" (second %)) m)
(map (fn [x] (str (first x) ":" (second x))) m)
(->> m (map #(str (first %) ":" (second %))))

(assoc m "three" 3)
(def m2 (assoc m "three" 3))
(dissoc m2 "three")
(update m "one" inc)

(def bob {:bob {:first "Bobby" :last "Martin"}})
(def bill {:bill {:first "Billy" :last "Smith"}})

(def people (merge bob bill))
(:bob people)
(:bill people)
(get-in people [:bob :first])
(get-in people [:bob :last])
(get-in people [:bill :first])
(get-in people [:bill :last])

(assoc-in people [:bob :first] "Robert")

(def people2 (-> people
                 (assoc-in [:bob :first] "Robert")
                 (assoc-in [:bill :first] "William"))
  )

(def people3 (-> people2
                 (assoc-in [:bob :age] 72)
                 (assoc-in [:bill :age] 61))
  )

(update-in people3 [:bob :age] inc)
(reduce #(update-in %1 [(key %2) :age] inc) people3 people3)
;(reduce (fn [acc [k v]] (update-in acc [k :age] inc)) people3 people3)