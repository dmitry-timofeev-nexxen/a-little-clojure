(defn pow
  ([n x] (pow n x 1N))
  ([n x acc] (if (< x 1) acc (pow n (dec x) (*' acc n))))
  )

(defn sin-term-sign
  [n]
  (- (- (rem n 4) 2))
  )

(defn sin-term
  [x n]
  (* (sin-term-sign n) (/ (pow x n) n))
  )

(defn arctan
  [x n]
  (let [ns (range 1 n 2)
        terms (map (fn [n] (sin-term x n)) ns)]
    (apply + terms))
  )

(arctan 1 20)
(with-precision 20 (bigdec(* (arctan 1 2000) 4)))
(Math/PI)
(- (Math/PI) (* (arctan 1 2000) 4))


(with-precision 20 (bigdec(* (arctan 1 2000000) 4))) ; (StackOverflowError)


(defn sin-term-for-1
  [n]
  (* (sin-term-sign n) (/ 1.0 n))
  )

(defn arctan-for-1
  [n]
  (let [ns (range 1 n 2)
        terms (map (fn [n] (sin-term-for-1 n)) ns)]
    (apply + terms))
  )

(with-precision 20 (bigdec(* (arctan-for-1 2000000) 4))) ; 3.1415916535897743M

(defn pi-gen
  [n]
  (let [var1 (* 12 (arctan 1/38 n))
        var2 (* 20 (arctan 1/57 n))
        var3 (* 7 (arctan 1/239 n))
        var4 (* 24 (arctan 1/268 n))]
    (with-precision n (bigdec(* (+ var1 var2 var3 var4) 4)))
    )
  )

(pi-gen 100)
(pi-gen 1000)


