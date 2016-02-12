(ns guestbook.test.db.core
  (:require [guestbook.db.core :as db]
            [guestbook.db.migrations :as migrations]
            [clojure.test :refer :all]
            [clojure.java.jdbc :as jdbc]
            [config.core :refer [env]]
            [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (migrations/migrate ["migrate"])
    (f)))

(deftest test-users
  (jdbc/with-db-transaction [t-conn db/conn]
    (jdbc/db-set-rollback-only! t-conn)
    (is (= 1 (db/save-message!
               {:name      "Sam"
                :message   "Hello Mr.Smith"
                :timestamp (java.util.Date.)} {:connection t-conn})))
    (is (= [{:id           1
             :name         "Sam"
             :message      "Hello Mr.Smith"}]
           (db/get-messages {:id "1"} {:connection t-conn})))))
