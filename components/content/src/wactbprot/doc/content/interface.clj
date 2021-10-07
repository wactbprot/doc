(ns wactbprot.doc.content.interface
  (:require [wactbprot.doc.content.cus-in :as cus-in]
            [wactbprot.doc.content.cus-out :as cus-out]))

(defn cus-out [cus] (cus-out/out cus))

(defn cus-in [cus data] (cus-in/in cus data))
