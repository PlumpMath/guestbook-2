(ns guestbook.ws
  (:require [taoensso.sente :as sente]))

(let [connection (sente/make-channel-socket! "/ws" {:type :auto})]
  (def ch-chsk (:ch-recv connection)) ;ChannelSocket's receive channel
  (def send-message! (:send-fn connection)))
