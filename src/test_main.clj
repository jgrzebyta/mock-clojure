(ns test-main
  (:require [mock.version :as v]))

(defn get-version []
  (v/version))

(defn print-demo []
  (println (format "demo version: %s" (get-version))))
