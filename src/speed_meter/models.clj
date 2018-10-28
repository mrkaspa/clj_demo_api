(ns speed-meter.models
  (:require [toucan.models :refer [defmodel IModel]]
            [clojure.spec.alpha :as s]))

(def email-regex #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")
(s/def ::email-type (s/and string? #(re-matches email-regex %)))
(s/def ::email ::email-type)
(s/def ::pass string?)
(s/def ::user
  (s/keys :req-un [::email ::pass]))

(defmodel User :users
  IModel
  (types [_]
         {:name :lowercase-string}))
