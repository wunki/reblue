(ns petar.dev.reblue.html
  (:require [clj-http.client :as client]))

(defn fetch
  "Returns the body of a HTML request"
  ([url]
   (let [headers {"User-Agent" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.16; rv:82.0) Gecko/20100101 Firefox/82.0"}]
     (fetch url headers)))
  ([url headers]
   (let [resp (client/get url {:headers       headers
                               :cookie-policy :none})]
     (:body resp))))