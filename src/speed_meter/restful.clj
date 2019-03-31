(ns speed-meter.restful
  (:require [ring.middleware.params :as params]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [muuntaja.core :as m]
            [reitit.ring.coercion :as coercion]
            [reitit.ring :as ring]
            [reitit.coercion.spec]
            [speed-meter.users.handlers :as user-handlers]
            [speed-meter.specs]))

(def routes
  ["/users" {:responses {201 {:body :speed-meter.specs/user-type}}
             :post {:summary "create user"
                    :parameters {:body :speed-meter.specs/user-type}
                    :handler user-handlers/create-user}}])


(def app
  (ring/ring-handler
   (ring/router
    [routes]
    {:data {:coercion reitit.coercion.spec/coercion
            :muuntaja m/instance
            :middleware [params/wrap-params
                         muuntaja/format-middleware
                         coercion/coerce-exceptions-middleware
                         coercion/coerce-request-middleware
                         coercion/coerce-response-middleware]}})
   (ring/create-default-handler)))
