(ns wactbprot.doc.db.interface
  (:require [wactbprot.doc.db.core :as core]))

(defn get-doc [conf id] (core/get-doc conf id))

(defn del-doc [conf id] (core/del-doc conf id))

(defn put-doc [conf doc] (core/put-doc conf doc))
