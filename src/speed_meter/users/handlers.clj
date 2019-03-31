(ns speed-meter.users.handlers
  (:require [clojure.string :as string]
            [toucan.db :as db]
            [speed-meter.models :refer [User]]
            [clojure.spec.alpha :as s]))

(defn create-user [{{data :body} :parameters}]
  (let [data
        (update-in data [:user :email] string/upper-case)
        user
        (db/insert! User (:user data))]
    {:status 201
     :body {:user user}}))
