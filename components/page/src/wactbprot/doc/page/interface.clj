(ns wactbprot.doc.page.interface
  (:require [wactbprot.doc.page.core :as core]))

(defn index [conf content] (core/index conf content)) 

(defn not-found [] (core/not-found))

