(ns wactbprot.doc.page.interface
  (:require [wactbprot.doc.page.core :as core]
            [wactbprot.doc.page.components :as component]))

;;........................................................................
;; page
;;........................................................................
(defn index [conf content] (core/index conf content)) 

(defn not-found [] (core/not-found))

;;........................................................................
;; components
;;........................................................................
(defn form-stacked [] (component/form-stacked))

(defn form-text-input [label value path type] (component/form-text-input label value path type))
