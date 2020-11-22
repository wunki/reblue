(ns petar.dev.reblue
  (:gen-class)
  (:require [aero.core :refer [read-config]]
            [petar.dev.reblue.html :as html]))

(defn print-availability
  [item]
  (let [html (html/fetch (:url item))]
    (if ((:availability-checker item) html)
      (println (:name item) "is AVAILABLE at" (:store item))
      (println (:name item) "is NOT available at" (:store item)))))

(defn -main
  "Checks the list of sites and sends an email if one of them is available"
  [& _args]
  (let [config (read-config "items.edn")]
    (dorun (map print-availability (:items config)))))
