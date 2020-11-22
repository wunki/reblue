(ns petar.dev.reblue
  (:gen-class)
  (:require [clj-http.client :as client]
            [net.cgrand.enlive-html :as html]))

(defn get-html
  "Returns the body of a HTML request"
  ([url]
   (let [headers {"User-Agent" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:82.0) Gecko/20100101 Firefox/82.0"}]
     (get-html url headers)))
  ([url headers]
   (let [resp (client/get url {:headers       headers
                               :cookie-policy :none})]
     (:body resp))))

(defn is-available?
  "Tells us if a specific item is deliverable"
  [html]
  (let [doc   (html/html-snippet html)
        nodes (html/select doc [:.text-color--unavailable])]
    (empty? nodes)))

(defn -main
  "Checks the list of sites and sends an email if one of them is available"
  [& args]
  (let [doc (get-html "https://www.coolblue.nl/product/871392/amd-ryzen-9-5950x.html")]
    (if (is-available? doc)
      (println "The thing you wanted is available...")
      (println "The thing you wanted is NOT available..."))))

(comment
  (def html-5950 (get-html "https://www.coolblue.nl/product/871392/amd-ryzen-9-5950x.html"))
  (def html-intel (get-html "https://www.coolblue.nl/product/823397/intel-core-i9-9900k.html"))
  (is-available? html-intel)
  (is-available? html-5950))