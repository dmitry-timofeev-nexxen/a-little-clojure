(ns alphabet-cipher.alphabet-cipher)

(defn char-to-index
  [c]
  (- (int c) (int \a))
  )

(defn index-to-char
  [i]
  (char (+ (int \a) i))
  )

(defn repeat-keyword
  [keyword message]
  (let [keyword-length (count keyword)
        message-length (count message)]
    (if (< keyword-length message-length)
      (apply str (take message-length (cycle keyword)))
      (subs keyword 0 message-length)
      )
    )
  )

(defn shift-char
  [m k shift-fn]
  (index-to-char (mod (shift-fn (char-to-index m) (char-to-index k)) 26))
  )

(defn encode
  [keyword message]
  (let [repeated-keyword (repeat-keyword keyword message)]
    (apply str (map (fn [m k] (shift-char m k +)) message repeated-keyword))
    )
  )

(defn decode
  [keyword message]
  (let [repeated-keyword (repeat-keyword keyword message)]
    (apply str (map (fn [m k] (shift-char m k -)) message repeated-keyword))
    )
  )

(defn find-repeating-keyword [s]
  (let [n (count s)]
    (loop [i 1]
      (let [prefix (subs s 0 i)
            repeated (apply str (take n (cycle prefix)))]
        (if (= repeated s)
          prefix
          (recur (inc i))
          )
        )
      )
    )
  )

(defn decipher
  [cipher message]
  (let [repeated-keyword (apply str (map (fn [c m] (shift-char c m -)) cipher message))]
    (find-repeating-keyword repeated-keyword)
    )
  )

(defn -main
  [& args]
  (println (encode "strongkey" "helloiamdimanicetomeetyou"))
  (println (decode "strongkey" "zxczbokqbafrbvioxmexvhlue"))
  (println (decipher "zxczbokqbafrbvioxmexvhlue" "helloiamdimanicetomeetyou"))
  )
