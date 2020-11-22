(ns petar.dev.reblue.coolblue
  (:require [net.cgrand.enlive-html :as html]))

(defn available?
  "Checks for the string on coolblue if it's available"
  [html]
  (let [doc   (html/html-snippet html)
        nodes (html/select doc [:.text-color--unavailable])]
    (empty? nodes)))
