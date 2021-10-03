(ns wactbprot.doc.customer.core
  (:require [wactbprot.doc.config.interface :as c]
            [wactbprot.doc.page.interface :as p]))

(defn base-address [conf data]
  (let [base-path "Customer.Address"]
    (into (p/form-stacked)
          [(p/form-text-input {:label "Name"
                             :value "CompanyA"
                             :path (str base-path "Name")
                             :type "string"}
                            {:width :three-quarter})
           (p/form-text-input {:label "Sign"
                             :value "CA"
                             :path (str base-path "Name")
                             :type "string"}
                              {:width :one-quarter})
           (p/form-text-input {:label "AddName"
                             :value "CompanyA"
                             :path (str base-path "Name")
                             :type "string"}
                              {:width :three-quarter})
           (p/form-text-input {:label "AddAddName"
                             :value "CompanyA"
                             :path (str base-path "Name")
                             :type "string"}
                              {:width :three-quarter})])))

(defn content [conf data]
  (base-address conf data))


