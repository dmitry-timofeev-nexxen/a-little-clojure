(ns a-little-clojure.converter
  (:require
    [ring.adapter.jetty :as j]
    [compojure.core :refer [defroutes GET]]
    [compojure.route :as route]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
    [hiccup2.core :as h]))

(defn ftoc
  [fahr-s]
  (let [fahr (Double/parseDouble fahr-s)
        celsius (format "%.2f" (* 5/9 (- fahr 32)))]
    (str (h/html [:h1 "FTOC"] [:br] fahr-s "°F = " celsius "°C")))
  )

(defn ctof
  [cels-s]
  (let [cels (Double/parseDouble cels-s)
        fahrenheit (format "%.2f" (+ 32 (* 9/5 cels)))]
    (str (h/html [:h1 "CTOF"] [:br] cels-s "°C = " fahrenheit "°F")))
  )

(defroutes app-routes
           (GET "/ftoc/:fahr" [fahr] (ftoc fahr))
           (GET "/ctof/:cels" [cels] (ctof cels))
           (route/not-found "Not Found"))

(def handler
  (wrap-defaults app-routes site-defaults)
  )

(defn -main
  [& args]
  (j/run-jetty handler {:port 3000})
  )