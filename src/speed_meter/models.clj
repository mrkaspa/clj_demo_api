(ns speed-meter.models
  (:require [toucan.models :refer [defmodel IModel]]))

(defmodel User :users
  IModel
  (types [_]
         {:name :lowercase-string}))
