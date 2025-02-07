(ns rock-paper-scissors.core
  (:gen-class))

(def move {"r" :rock "p" :paper "s" :scissors})
(def moves [:rock :paper :scissors])
(def beats #{[:rock :scissors] [:scissors :paper] [:paper :rock]})

(defn play-round
  [player-move]
  (let [computer-move (rand-nth moves)]
    {:player   player-move
     :computer computer-move
     :result
     (cond
       (= player-move computer-move) :draw

       (contains? beats [player-move computer-move]) :win

       :else :lose
       )
     }
    )
  )

(defn play-game
  []
  (loop [score {:win 0 :lose 0 :draw 0}]
    (println (str "Wins: " (:win score) ", Losses: " (:lose score) ", Draws: " (:draw score)))
    (println "Choose (r)ock, (p)aper, (s)cissors, or (q)uit.")
    (let [line (read-line)
          move (get move line)]
      (cond
        (= "q" line)
        (println "Quitting game.")

        (nil? move)
        (do
          (println line "is an invalid move")
          (recur score)
          )

        :else
        (let [round (play-round move)
              result-string (cond
                              (= :win (:result round)) "You win!"
                              (= :lose (:result round)) "You lose!"
                              (= :draw (:result round)) "It's a draw!"
                              )
              ]
          (println (str "You: " (name (:player round)) ", Computer: " (name (:computer round)) ", " result-string))
          (recur (update score (:result round) inc))
          )
        )
      )
    )
  )

(defn -main [& args]
  (println "Welcome to the Rock, Paper, Scissors championship!")
  (loop []
    (println "Ready to play? Type y, n, or q to quit.")
    (let [line (read-line)]
      (cond
        (= "y" line)
        (do
          (println "Great! Let's start the game!")
          (play-game)
          (recur)
          )

        (= "n" line)
        (do
          (println "You are not ready? Then I'll wait.")
          (Thread/sleep 5000)
          (recur)
          )

        (= "q" line) (println "See you next time!")

        :else
        (do
          (println "I don't understand your input. Please try again.")
          (recur)))
      )
    )
  )