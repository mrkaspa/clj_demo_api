(ns speed-meter.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]
            [speed-meter.restful :as rs]
            [toucan.db :as db]
            [toucan.models :as models]))
(defn -main
  [& args]
  (do
    (println "STARTING IN PROD")
    (db/set-default-db-connection!
     {:classname "org.postgresql.Driver"
      :subprotocol "postgresql"
      :subname (System/getenv "DB_URI")
      :user (System/getenv "DB_USER")
      :password (System/getenv "DB_PASS")})
    (models/set-root-namespace! 'speed-meter.models)
    (run-jetty rs/app {:port 3000})))
