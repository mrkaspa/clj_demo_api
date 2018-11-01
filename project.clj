(defproject speed_meter "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/core.match "0.3.0-alpha5"]
                 [metosin/compojure-api "2.0.0-alpha27"]
                 [ring "1.7.0"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [ring/ring-json "0.4.0"]
                 [metosin/ring-http-response "0.9.0"]
                 [cheshire "5.8.1"]
                 [toucan "1.1.9"]
                 [migratus "1.1.4"]
                 [org.postgresql/postgresql "42.2.4"]
                 [buddy/buddy-hashers "1.3.0"]]
  :plugins [[migratus-lein "0.6.4"]]
  :migratus {:store :database
             :migration-dir "migrations"
             :db {:classname "org.postgresql.Driver"
                  :subprotocol "postgresql"
                  :subname "//localhost/speed"
                  :user "mrkaspa"
                  :password "jokalive"}}
  :profiles {:uberjar
             {:aot :all}
             :dev
             {:main speed-meter.core-dev}
             :prod
             {:main speed-meter.core}})
