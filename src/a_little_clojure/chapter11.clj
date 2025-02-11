(ns a-little-clojure.chapter11)

(defn parse-line
  [line]
  (let [[_ product price quantity] (re-find #"Product: (.+) Price:([\d.]+) Quantity: (\d+)" line)]
    {:product  product
     :price    (Double/parseDouble price)
     :quantity (Integer/parseInt quantity)}
    )
  )

(defn format-line
  [{:keys [product price quantity]} subtotal]
  (let [total (* price quantity)
        new-subtotal (+ subtotal total)]
    [(format "%20s X%-2d @%-5.2f =%6.2f   | %6.2f" product quantity price total new-subtotal) new-subtotal]
    )
  )

(defn generate-receipt
  [filename]
  (let [lines (clojure.string/split-lines (slurp filename))
        items (map parse-line lines)
        result (reduce (fn [[acc subtotal] item]
                         (let [[line new-subtotal] (format-line item subtotal)]
                           [(conj acc line) new-subtotal]))
                       [[] 0.0] items)]
    (println "- - - The receipt")
    (doseq [line (first result)]
      (println line))
    )
  )

(defn -main
  [& args]
  (generate-receipt "resources/items.txt")
  )
