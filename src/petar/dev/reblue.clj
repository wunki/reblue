(ns petar.dev.reblue
  (:gen-class)
  (:require [aero.core :refer [read-config]]
            [petar.dev.reblue.html :as html]
            [petar.dev.reblue.email :as email]
            [petar.dev.reblue.coolblue :as _coolblue]))

(defn check-availability
  "Checks the availability of a single item"
  [item]
  (let [html (html/fetch (:url item))]
    (assoc item :is-available? ((ns-resolve
                                 'petar.dev.reblue.coolblue
                                 (:availability-fn item)) html))))

(defn print-availability
  "Pretty prints the availability in the terminal."
  [item]
  (if (:is-available? item)
    (println (str (:name item) " at " (:store item) ": available."))
    (println (str (:name item) " at " (:store item) ": not available."))))

(defn -main
  "Checks the list of sites and sends an email if one of them is available"
  [& _args]
  (let [config (read-config "items.edn")
        items (map check-availability (:items config))
        new-items (filter :is-available? items)]
    (dorun (map (partial email/notify (:postmark-api-key config)) new-items))
    (dorun (map print-availability items))))

(comment
  (def config (read-config "items.edn"))
  (def items (map check-availability (:items config)))
  (def item (first items))
  (def html (html/fetch (:url item)))
  (def sender (partial email/notify (:postmark-api-key config)))
  (check-availability (first (:items config))))
