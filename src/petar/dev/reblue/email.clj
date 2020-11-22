(ns petar.dev.reblue.email
  (:require [postmark.core :as postmark]))

(defmacro swallow-exceptions [& body]
  `(try ~@body (catch Exception e#)))

(defn notify
  "Sends out an email once a new item is available"
  [api-key item]
  (let [pm (postmark/postmark api-key "petar@petar.dev")
        email {:to "petar@petar.dev"
               :subject (format "%s became available on %s" (:name item) (:store item))
               :text (format "Go buy it at: %s" (:url item))}]
    (swallow-exceptions (pm email))))

(comment
  (def api-key "")
  (def pm (postmark/postmark api-key "petar@petar.dev"))
  (swallow-exceptions (pm {:to "petar@petar.dev"
                           :subject "A new thing arrived!"
                           :text "THIS THING JUST ARRIVED!"})))

