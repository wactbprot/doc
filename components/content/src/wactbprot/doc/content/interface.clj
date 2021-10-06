(ns wactbprot.doc.content.interface
  (:require [wactbprot.doc.content.customer :as customer]))

(defn customer [data] (customer/content data))
