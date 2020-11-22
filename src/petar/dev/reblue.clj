(ns petar.dev.reblue
  (:gen-class)
  (:require [aero.core :refer [read-config]]
            [petar.dev.reblue.html :as html]))

(defn check-availability
  "Checks the availability of a single item"
  [item]
  (let [html (html/fetch (:url item))]
    (assoc item :is-available? ((resolve (:availability-fn item)) html))))

(defn print-availability
  "Pretty prints the availability in the terminal."
  [item]
  (if (:is-available? item)
    (println (:name item) "is AVAILABLE at" (:store item))
    (println (:name item) "is NOT available at" (:store item))))

(defn -main
  "Checks the list of sites and sends an email if one of them is available"
  [& _args]
  (let [config (read-config "items.edn")
        items (map check-availability (:items config))]
    (dorun (map print-availability items))))

(comment
  (def config (read-config "items.edn"))
  (def item (first (:items config)))
  (def html (html/fetch (:url item)))
  (def available-fn (resolve(:availability-fn item)))
  (check-availability (first (:items config))))
  