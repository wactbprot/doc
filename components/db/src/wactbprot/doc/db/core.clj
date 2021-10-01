(ns wactbprot.doc.db.core
  ^{:author "Thomas Bock <wactbprot@gmail.com>"
    :doc "Basic database interop. Plain HTTP."}
  (:require [cheshire.core :as che]
            [clojure.string :as string]
            [org.httpkit.client :as http]))

(defn result [{body :body status :status}]
  (let [body (try
               (che/parse-string-strict body true )
               (catch Exception e
                 {:error (.getMessage e)}))]
    (if (< status 400)
      body
      {:error (:error body) :reason (:reason body)})))

(defn get-rev [url opt]
  (let [res @(http/head url opt)]
    (when-not (contains? res :error)
      (string/replace (get-in  [:headers :etag]) #"\"" ""))))

(defn doc-url [{db-url :db-url rev :rev} id]
  (when (and db-url id) (str db-url "/" id (when rev (str "?rev=" rev)))))

(defn get-doc [{opt :db-opt :as conf} id]
  (result @(http/get (doc-url conf) opt)))

(defn del-doc [{opt :db-opt :as conf} id]
  (let [url (doc-url conf id)]
  (result @(http/delete (doc-url (assoc conf :rev (get-rev url opt))) opt))))