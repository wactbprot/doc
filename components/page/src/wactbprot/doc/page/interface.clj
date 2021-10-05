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

(defn acc-frame [] (component/acc-frame))

(defn acc-sheet [title content] (component/acc-sheet title content))

(defn article [] (component/article))

(defn form-heading [s] (component/form-heading s))

(defn form [] (component/form))

(defn form-text-input [data layout] (component/form-text-input data layout))

(defn form-select [data layout] (component/form-select data layout))

(defn form-checkbox [data layout] (component/form-checkbox data layout))
