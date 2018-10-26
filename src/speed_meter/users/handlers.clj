(ns speed-meter.users.handlers
  (:require [clojure.string :as string]
            [toucan.db :as db]
            [speed-meter.models :refer [User]]))

(defn create-user [req json]
  (let [body (update-in json [:email] string/upper-case)]
    (do
      (db/insert! User body)
      {:status 200
       :body body})))
