(defproject clj-1452-tests "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[criterium "0.4.3"]]
  :jvm-opts ^:replace []
  :main clj-1452-tests.core
  :profiles
  {:clj-1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}
   :clj-1452 {:dependencies [[com.gfredericks/clojure "1.6.0-CLJ1452"]]}})
