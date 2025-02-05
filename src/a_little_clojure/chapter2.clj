(defn square
  [x]
  (* x x))

(defn albert
  [m]
  (* (square 299792458) m))

(defn mix
  [s1 s2]
  (apply str (map str s1 s2)))

(defn mean
  [l]
  (/ (apply + l) (count l)))

(defn pow
  [n e]
  (apply * (repeat e n)))

(defn pos-neg
  [l]
  (concat (filter pos? l) (filter zero? l) (filter neg? l)))

(pos-neg [6 -1 0 4 -2 0])

(defn pythag?
  [a b c]
  (= (square c) (+ (square a) (square b))))

(pythag? 3 4 5)
(pythag? 3 4 6)