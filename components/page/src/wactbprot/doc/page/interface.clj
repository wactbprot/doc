(ns wactbprot.doc.page.interface
  (:require [wactbprot.doc.page.core :as core]
            [wactbprot.doc.page.components :as component]))

;;........................................................................
;; page
;;........................................................................
(defn index [content] (core/index content)) 

(defn not-found [] (core/not-found))

;;........................................................................
;; components
;;........................................................................

(defn acc-frame [] (component/acc-frame))

(defn button [data text] (component/button data text))

(defn acc-sheet
  ([title content] (component/acc-sheet title content {:open false}))
  ([title content layout] (component/acc-sheet title content layout)))

(defn article [] (component/article))

(defn grid [] (component/grid))

(defn margin [] (component/margin))

(defn form-heading [s] (component/form-heading s))

(defn form [] (component/form))

(defn form-text-input [data layout] (component/form-text-input data layout))

(defn form-select [data layout] (component/form-select data layout))
