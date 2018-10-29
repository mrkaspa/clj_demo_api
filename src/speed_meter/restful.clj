(ns speed-meter.restful
  (:require [compojure.core :refer [GET POST PUT DELETE defroutes]]
            [compojure.route :as route]
            [toucan.db :as db]
            [toucan.models :as models]
            [speed-meter.utils :as utils]
            [speed-meter.users.handlers :as user-handlers]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.http-response :refer [ok not-found created]]))

(defroutes app-routes
  (POST "/" req
    (utils/do-json
     req
     user-handlers/create-user))
  (route/not-found "Not Found"))

(def app
  (do
    (db/set-default-db-connection!
     {:classname "org.postgresql.Driver"
      :subprotocol "postgresql"
      :subname "//localhost/speed"
      :user "liftit"
      :password "jokalive"})
    (models/set-root-namespace! 'speed-meter.models)
    (wrap-defaults
     app-routes
     (update-in site-defaults [:security :anti-forgery] (fn [_] false)))))
