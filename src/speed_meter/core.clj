(ns speed-meter.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]
            [speed-meter.restful :as rs]))
(defn -main
  [& args]
  (println "STARTING IN PROD")
  (run-jetty rs/app {:port 3000}))
