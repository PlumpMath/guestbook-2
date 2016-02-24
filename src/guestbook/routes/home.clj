(ns guestbook.routes.home
  (:require [guestbook.layout :as layout]
            [guestbook.db.core :as db]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.response :refer [response status]]))
            ;[bouncer.core :as b]
            ;[bouncer.validators :as v]))
            ;[clojure.java.io :as io]))
            ;[ring.util.http-response :refer [ok]]
            ;[ring.util.response :as response]

(defn home-page []
  (layout/render "home.html"))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/messages" [] (response (db/get-messages)))
  (GET "/about" [] (about-page)))



;Old version for HTML/Ajax updates

;(defn validate-message [params]
;  (first
;    (b/validate
;      params
;      :name v/required
;      :message [v/required [v/min-count 10]])))

;(defn save-message! [{:keys [params]}]
;  (if-let [errors (validate-message params)]
;    (-> {:errors errors} response (status 400))
;    (do
;      (db/save-message!
;        (assoc params :timestamp (java.util.Date.)))
;      (response {:status :ok}))))
