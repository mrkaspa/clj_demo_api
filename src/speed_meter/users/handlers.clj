(ns speed-meter.users.handlers
  (:require [clojure.string :as string]
            [toucan.db :as db]
            [speed-meter.models :refer [User] :as m]
            [clojure.spec.alpha :as s]))

(defn create-user [req json]
  (if (s/valid? :speed-meter.models/user json)
    (let [body (update-in json [:email] string/upper-case)]
      (do
        (db/insert! User body)
        {:status 200
         :body (s/explain-data :speed-meter.models/user json)}))
    {:status 422
     :body (s/explain-data :speed-meter.models/user json)}))
