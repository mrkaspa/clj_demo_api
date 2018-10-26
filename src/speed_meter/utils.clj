(ns speed-meter.utils
  (:require [cheshire.core]))

(def api-headers
  {"Content-Type" "application/json"})

(defn do-json
  [{:keys [body] :as req} f]
  (let [json
        (->
         body
         slurp
         (cheshire.core/parse-string true))
        res (f req json)]
    (->
     res
     (update-in [:headers] #(merge % api-headers))
     (update-in [:body] cheshire.core/generate-string))))
