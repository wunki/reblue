(ns petar.dev.reblue-test
  (:require [clojure.test :refer :all]
            [petar.dev.reblue :refer :all]))

(defn read-product
  "Reads a HTML file"
  [product]
  (slurp (str "test/resources/" product ".html")))

(deftest check-coolblue-availability
  (testing "Check that an item is not available on coolblue"
    (let [html (read-product "3080")]
      (is (false? (is-available? html)))))
  (testing "Check that an item IS available on coolblue"
    (let [html (read-product "3700")]
      (is (true? (is-available? html))))))

(comment
  (def html (read-product "3080"))
  (is-deliverable? html))
