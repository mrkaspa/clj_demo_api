(ns speed-meter.utils
  (:require [cheshire.core]
            [clojure.spec.alpha :as s]
            [clojure.core.match :refer [match]]))

(def api-headers
  {"Content-Type" "application/json"})

(defn do-json
  [{:keys [body] :as req} f]
  (let [json
        (->
         body
         slurp
         (cheshire.core/parse-string true))
        res (f json)]
    (->
     res
     (update-in [:headers] #(merge % api-headers))
     (update-in [:body] cheshire.core/generate-string))))

(defn validate-input
  [data schema]
  (let [errors (s/explain-data schema data)]
    (if (nil? errors)
      :ok
      [:error errors])))

(defn do-json-and-validate
  [req schema f]
  (do-json
   req
   (fn [json]
     (match [(validate-input json schema)]
      [:ok] (f req json)
      [[:error errors]] {:status 422
                         :body errors}))))
