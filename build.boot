(set-env! :source-paths #{"src"}
          :project 'demo/mock-clojure
          :dependencies '[[org.clojure/clojure "1.8.0"]
                          [org.clojure/tools.cli "0.3.5"]
                          [org.clojure/tools.logging "0.3.1"]
                          [boot/core "2.7.1" :scope "test"]
                          [degree9/boot-semver "1.7.0-SNAPSHOT" :scope "test"]])

(require '[clojure.test :as t]
         '[degree9.boot-semver :refer :all]
         '[boot.util :as util]
         '[boot.core :as core])


(task-options!
 version {:minor 'zero :patch 'four :include false :generate 'mock.version} ;; version.properties file is not needed anymore
 pom {:project (get-env :project)})

(deftask develop
  "Build SNAPSHOT version of jar"
  []
  (version :pre-release 'snapshot :develop true))

(deftask testing "Attach tests/ directory" []
   (set-env! :source-paths #(conj % "tests")))

(deftask run-test "Run unit tests" []
  (comp
   (testing)
   (require '[simple])
   (t/run-all-tests)))

(deftask build-sources
  "Build standalone version" []
  (comp
   (pom)
   (sift :add-resource ["src/"])
   (build-jar)
   (target)))
