(ns wactbprot.doc.db.core
  ^{:author "Thomas Bock <wactbprot@gmail.com>"
    :doc "Basic database interop. Plain HTTP."}
  (:require [cheshire.core :as che]
            [clojure.string :as string]
            [wactbprot.doc.config.interface :as c]
            [org.httpkit.client :as http]))

(defn doc-url [{db-url :db-url rev :rev} id]
  (when (and db-url id) (str db-url "/" id (when rev (str "?rev=" rev)))))

(defn result [{body :body status :status}]
  (let [body (try (che/parse-string-strict body true )
               (catch Exception e {:error (.getMessage e)}))]
    (if (< status 400)
      body
      {:error (:error body) :reason (:reason body)})))

(defn get-rev [{opt :db-opt :as conf} id]
  (let [res @(http/head (doc-url conf id) opt)]
    (when (< (:status res) 400)
      (string/replace (get-in  res [:headers :etag]) #"\"" ""))))

(defn get-doc [{opt :db-opt :as conf} id]
  (result @(http/get (doc-url conf id) opt)))

(defn del-doc [{opt :db-opt :as conf} id]
  (result @(http/delete (doc-url (assoc conf :rev (get-rev conf id)) id) opt)))

(comment
  (get-doc c/conf "foo")
  (get-rev c/conf "000_REPLICATIONS"))
