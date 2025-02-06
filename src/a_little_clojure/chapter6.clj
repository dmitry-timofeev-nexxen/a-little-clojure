(ns a-little-clojure.chapter6
  (:gen-class))

(defn process-transactions
  [accounts transactions]
  (reduce
    (fn [acc {:keys [type account amount rate]}]
      (cond
        (= type :deposit)
        (update-in acc [account :balance] + amount)

        (= type :withdrawal)
        (update-in acc [account :balance] - amount)

        (= type :interest)
        (reduce
          (fn [acc k] (update-in acc [k :balance] (fn [bal] (+ bal (* bal rate)))))
          acc
          (keys acc)
          )

        :else acc))
    accounts
    transactions
    )
  )

(process-transactions
  {1 {:balance 0 :name "bob"} 2 {:balance 100 :name "bill"}}
  [{:type :deposit :account 1 :amount 100} {:type :withdrawal :account 2 :amount 50} {:type :interest :rate 1/100}]
  )


; the result is not correct. Most likely the conditions of task are not completely clear to me
; or I made a mistake in implementing the algorithm.
(defn handle-roll
  [game roll]
  (let [att-units (:attackers game)
        def-units (:defenders game)
        att-dice-count (min 3 att-units)
        def-dice-count (min 2 def-units)
        att-roll (take att-dice-count roll)
        def-roll (take def-dice-count (drop att-dice-count roll))
        att-dice (sort > att-roll)
        def-dice (sort > def-roll)]
    (let [new-game (cond
                     (<= (first att-dice) (first def-dice))
                     (update game :defenders dec)

                     (<= (first def-dice) (first att-dice))
                     (update game :attackers dec)

                     :else game)]
      (if (and (>= (count att-dice) 2) (>= (count def-dice) 2))
        (let [new-game-2 (cond
                           (<= (second att-dice) (second def-dice))
                           (update new-game :defenders dec)
                           :else (update new-game :attackers dec))]
          new-game-2)
        new-game))))

(defn play-risk
  [game rolls]
  (reduce handle-roll game rolls)
  )

(def game {:attackers 10 :defenders 10})
(def rolls [[1 1 1 3 3]
            [1 1 6 5 1]
            [6 6 1 5 5]
            [3 4 4 5 4]
            [3 5 4 6 2]
            [3 3 6 5 4]
            [5 6 3 4]
            [4 5 2 3]
            [3 3 1 1]])

(defn -main
  [& args]
  (println (play-risk game rolls))
  )