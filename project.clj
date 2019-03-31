(defproject speed_meter "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/core.match "0.3.0-alpha5"]
                 [metosin/reitit "0.3.1"]
                 [metosin/reitit-ring "0.3.1"]
                 [metosin/reitit-spec "0.3.1"]
                 [metosin/muuntaja "0.6.3"]
                 [ring "1.7.0"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [toucan "1.1.9"]
                 [migratus "1.1.4"]
                 [org.postgresql/postgresql "42.2.4"]
                 [buddy/buddy-hashers "1.3.0"]]
  :plugins [[migratus-lein "0.6.4"]]
  :migratus {:store :database
             :migration-dir "migrations"
             :db {:classname "org.postgresql.Driver"
                  :subprotocol "postgresql"
                  :subname (System/getenv "DB_URI")
                  :user (System/getenv "DB_USER")
                  :password (System/getenv "DB_PASS")}}
  :profiles {:uberjar
             {:aot :all}
             :dev
             {:main speed-meter.core-dev}
             :prod
             {:main speed-meter.core}})
