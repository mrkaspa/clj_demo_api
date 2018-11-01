(ns speed-meter.users.handlers
  (:require [clojure.string :as string]
            [toucan.db :as db]
            [speed-meter.models :refer [User] :as m]
            [clojure.spec.alpha :as s]))

(defn create-user [req json]
  (let [user (update-in json [:email] string/upper-case)
        user (db/insert! User user)]
    {:status 200
     :body user}))
