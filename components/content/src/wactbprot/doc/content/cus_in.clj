(ns wactbprot.doc.content.cus-in
  (:require [wactbprot.doc.config.interface :as c]
            [wactbprot.doc.content.utils :as u]
            [clojure.string :as string]))

(defn in [{p :path v :value t :type} cus] (assoc-in cus (u/path p) v))
