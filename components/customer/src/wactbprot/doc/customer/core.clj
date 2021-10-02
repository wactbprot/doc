(ns wactbprot.doc.customer.core
  (:require [wactbprot.doc.config.interface :as c]
            [wactbprot.doc.page.interface :as p]))

(defn base-address [conf data]
  (into (form-stacked)
        [(form-text-input "Name" "CompanyA" "ff" "str")]))

(defn content [conf data]
  (base-address conf data))


