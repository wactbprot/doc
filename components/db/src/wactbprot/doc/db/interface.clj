(ns wactbprot.doc.db.interface
  (:require [wactbprot.doc.db.core :as core]))

(defn get-doc [conf id] (core/get-doc conf id))
