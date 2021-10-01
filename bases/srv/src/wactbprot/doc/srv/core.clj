(ns wactbprot.doc.srv.core
  ^{:author "Thomas Bock <wactbprot@gmail.com>"
    :doc "Webserver delivers pages, reads and writes to the database."}
  (:require [compojure.route :as route]
            [com.brunobonacci.mulog :as µ]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [org.httpkit.server :refer [run-server]]
            [ring.middleware.json :as middleware]
            
            [wactbprot.doc.config.interface :as c]
            [wactbprot.doc.customer.interface :as customer]
            [wactbprot.doc.page.interface :as page])
  (:gen-class))

(defonce server (atom nil))

(defroutes app-routes
  (GET "/customer" [] (page/index c/conf (customer/content c/conf {})))
  (route/resources "/")
  (route/not-found (page/not-found)))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-response)))

(defn stop [c]
  (when @server (@server :timeout 100)
        (reset! server nil)))

(defn start [{srv :server}]
  (µ/log ::start :message "start server")
  (reset! server (run-server app srv)))

(defn -main [& args]
  (µ/log ::-main :message "call -main")
  (start c/conf))

(comment
  (start c/conf)
  ;; think about wher the update happens
  ;; is thist enough:
  ;; POST to /customer/:cust-name endpoint with
  ;; {:path
  ;; :value
  ;; :type}
  )
