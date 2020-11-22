(ns petar.dev.reblue.coolblue
  (:require [net.cgrand.enlive-html :as enlive]))

(defn available?
  "Checks for the string on coolblue if it's available"
  [html]
  (let [doc   (enlive/html-snippet html)
        nodes (enlive/select doc [:.text-color--unavailable])]
    (empty? nodes)))

(comment
  (def html (petar.dev.reblue.html/fetch "https://www.coolblue.nl/product/871396/amd-ryzen-5-5600x.html"))
  (available? html))
