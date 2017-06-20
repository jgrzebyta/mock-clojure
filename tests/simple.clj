(ns simple
  (:require [clojure.test :as t]
            [test-main :as tm]))


(t/deftest simple-test
  (let [version-no (tm/get-version)]
    (t/is (some? version-no))
    (t/is (string? version-no))))
