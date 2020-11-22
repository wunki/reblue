(ns petar.dev.reblue
  (:gen-class)
  (:require [aero.core :refer [read-config]]
            [clj-http.client :as client]))

(defn get-html
  "Returns the body of a HTML request"
  ([url]
   (let [headers {"User-Agent" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:82.0) Gecko/20100101 Firefox/82.0"}]
     (get-html url headers)))
  ([url headers]
   (let [resp (client/get url {:headers       headers
                               :cookie-policy :none})]
     (:body resp))))

(defn print-availability
  [item]
  (let [html (get-html (:url item))]
    (if ((:availability-checker item) html)
      (println (:name item) "is AVAILABLE at" (:store item))
      (println (:name item) "is NOT available at" (:store item)))))

(defn -main
  "Checks the list of sites and sends an email if one of them is available"
  [& _args]
  (let [config (read-config "items.edn")]
    (doall (map print-availability (:items config)))))
