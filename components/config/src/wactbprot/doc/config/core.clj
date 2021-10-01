(ns wactbprot.doc.config.core)

(def defaults {:server {:port 8012}
               :db-prot "http"
               :db-host "localhost"
               :db-port 5984
               :db-name "vl_db"})

(defn db-base-url [{db-port :db-port db-host :db-host db-prot :db-prot :as c}]
  (let [lt-host (or (System/getenv "DB_HOST") db-host)
        usr     (System/getenv "CAL_USR")
        pwd     (System/getenv "CAL_PWD")]
    (str db-prot "://" (when (and usr pwd) (str usr ":" pwd "@"))  db-host ":" db-port)))

(defn db-url [{db-name :db-name :as c}] (str (db-base-url c) "/" db-name))

(def conf (assoc defaults
                 :db-base-url (db-base-url defaults)
                 :db-url (db-url defaults)))
