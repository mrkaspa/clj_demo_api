(ns speed-meter.core-dev
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]
            [speed-meter.restful :as rs]))
(defn -main
  [& args]
  (println "STARTING IN DEV")
  (run-jetty (wrap-reload #'rs/app) {:port 3000}))
