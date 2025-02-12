(ns a-little-clojure.chapter14
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def width 600)
(def height 600)

(defn create-rocket
  []
  {:x         (q/random 200 400)
   :y         height
   :vx        0
   :vy        -10
   :exploded? false
   :particles []})

(defn create-particles
  [x y]
  (for [_ (range 50)]
    {:x     x
     :y     y
     :vx    (q/random -3 3)
     :vy    (q/random -3 3)
     :alpha 255})
  )

(defn update-particle
  [p]
  (let [{:keys [x y vx vy alpha]} p]
    {:x     (+ x vx)
     :y     (+ y vy)
     :vx    vx
     :vy    (+ vy 0.1)
     :alpha (- alpha 5)})
  )

(defn update-rocket
  [rocket]
  (if (:exploded? rocket)
    (update rocket :particles #(map update-particle %))
    (let [{:keys [x y vx vy]} rocket
          new-y (+ y vy)]
      (if (< new-y 200)
        {:x x :y new-y :vx vx :vy vy :exploded? true :particles (create-particles x y)}
        {:x x :y new-y :vx vx :vy vy :exploded? false :particles []})
      )
    )
  )

(defn draw-rocket
  [rocket]
  (q/fill 255 0 0)
  (when (not (:exploded? rocket))
    (q/ellipse (:x rocket) (:y rocket) 5 20)
    )
  )

(defn draw-particles
  [particles]
  (doseq [{:keys [x y alpha]} particles]
    (q/fill 255 255 0 alpha)
    (q/ellipse x y 4 4))
  )

(defn setup
  []
  (q/frame-rate 30)
  (create-rocket)
  )

(defn update-state
  [rocket]
  (let [updated (update-rocket rocket)
        particles (:particles updated)]
    (if (and (:exploded? updated) (empty? (filter #(> (:alpha %) 0) particles)))
      (create-rocket)
      updated))
  )

(defn draw-state
  [rocket]
  (q/background 0)
  (draw-rocket rocket)
  (draw-particles (:particles rocket))
  )

(q/defsketch fireworks
             :title "Fireworks!"
             :size [width height]
             :setup setup
             :update update-state
             :draw draw-state
             :middleware [m/fun-mode]
             )

(defn -main
  [& args]
  (println "Fireworks!")
  )