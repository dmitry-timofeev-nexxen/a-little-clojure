(ns a-little-clojure.core-test
  (:require [clojure.test :refer :all]
            [a-little-clojure.core :refer [square]]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest test-addition
  (testing "Addition function"
    (is (= 4 (+ 2 2)))
    (is (= 10 (+ 5 5)))))

(deftest test-string
  (testing "String operations"
    (is (= "HELLO" (.toUpperCase "hello")))
    (is (= "world" (.substring "hello world" 6)))))

(deftest test-custom-function
  (testing "Testing a custom function from core.clj"
    (is (= 9 (square 3)))
    (is (= 16 (square 4)))))
