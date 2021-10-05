(ns wactbprot.doc.content.interface
  (:require [wactbprot.doc.content.customer :as customer]))

(defn customer [conf data] (customer/content conf data))
