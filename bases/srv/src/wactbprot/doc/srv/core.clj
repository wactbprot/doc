(ns wactbprot.doc.srv.core
  ^{:author "Thomas Bock <wactbprot@gmail.com>"
    :doc "Webserver delivers pages, reads and writes to the database."}
  (:require [compojure.route :as route]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [org.httpkit.server :refer [run-server]]
            [ring.middleware.json :as middleware]
            
            [wactbprot.doc.config.interface :as c]
            [wactbprot.doc.db.interface :as db]
            [wactbprot.doc.customer.interface :as customer]
            [wactbprot.doc.page.interface :as page])
  (:gen-class))

(defonce server (atom nil))

(defroutes app-routes
  (GET "/customer/:id" [:as req] (page/index c/conf (customer/content c/conf (db/get-doc c/conf (get-in req [:route-params :id])))))
  (route/resources "/")
  (route/not-found (page/not-found)))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-response)))

(defn stop [c]
  (when @server (@server :timeout 100)
        (reset! server nil)))

(defn start [{srv :server}] (reset! server (run-server #'app srv)))

(defn -main [& args] (start c/conf))

(comment
  (start c/conf))
