(ns hranoprovod.main
  (:require [hranoprovod.core :as core])
  (:require [hranoprovod.core :as core])
  (:use [hranoprovod.utils])
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:require [clj-yaml.core :as yaml]))

(defn load-db [file-name]
  (let [file-content (slurp file-name)]
    (yaml/parse-string file-content :keywords false)))

(defn process-items
  [foods func]
  (map
   (fn [[n items]]
     (func
      n
      (map (fn [item]
             (let [f (first item)]
               (i (key f) (val f)))) items))) foods))

(def required-opts #{:database :logfile})

(defn missing-required?
  "Returns true if opts is missing any of the required-opts"
  [opts]
  (not-every? opts required-opts))

(def cli-options
  [["-d" "--database food.yaml" "Food database file"]
   ["-l" "--logfile log.yaml" "Daily log file"]
   [nil "--yaml" "Output as yaml"]
   ["-h" "--help"]])

(defn -main [& args]
  (let [{:keys [options arguments summary errors]} (parse-opts args cli-options)]
    (if (or (:help options) (missing-required? options))
      (println summary)
      (let [food-db (process-items (load-db (:database options)) f)
            log-db (process-items (load-db (:logfile options)) d)
            resolved-food (core/resolve-food food-db)
            resolved-days (core/resolve-days log-db resolved-food)]
        (if (:yaml options) 
          (println (yaml/generate-string resolved-days))
          (run! #(core/print-day %) resolved-days))))))
