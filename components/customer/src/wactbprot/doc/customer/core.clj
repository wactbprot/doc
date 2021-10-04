(ns wactbprot.doc.customer.core
  (:require [wactbprot.doc.config.interface :as c]
            [wactbprot.doc.page.interface :as p]))

(defn content [conf data]
  (let [base-path "Customer.Address"]
    (into (p/article)
          [(p/form-heading "Allgemein")
           (into (p/form)
                 [(p/form-text-input {:label "Kürzel"
                                     :value "CompanyA"
                                     :path  "Customer.Name"
                                     :type "string"}
                                     {:width :one-quarter})
                  (p/form-text-input {:label "Debitor"
                                      :value "CompanyA"
                                      :path  "Customer.Name"
                                      :type "string"}
                                     {:width :half})
                  (p/form-select {:label "Sprache"
                                  :value "de"
                                  :path (str base-path "Lang")
                                  :type "string"
                                  :options ["de" "en"]}
                                 {:width :one-quarter})])
           (p/form-heading "Adresse")
           (into (p/form)
                [(p/form-text-input {:label "Adresszeile 1"
                                     :value "CompanyA"
                                     :path  "Customer.Name"
                                     :type "string"}
                                    {:width :full})
                 (p/form-text-input {:label "Adresszeile 2"
                                     :value "CA"
                                     :path "Customer.AddName"
                                     :type "string"}
                                    {:width :full})
                 (p/form-text-input {:label "Adresszeile 3"
                                     :value "CompanyA"
                                     :path "Customer.AddAddName"
                                     :type "string"}
                                    {:width :full})
                 (p/form-text-input {:label "Straße Nr."
                                     :value "CompanyA"
                                     :path (str base-path "Street")
                                     :type "string"}
                                    {:width :full})
                 (p/form-text-input {:label "PLZ"
                                     :value "CompanyA"
                                     :path (str base-path "Zipcode")
                                     :type "string"}
                                    {:width :one-quarter})
                 (p/form-text-input {:label "Ort"
                                     :value "CompanyA"
                                     :path (str base-path "Town")
                                     :type "string"}
                                    {:width :three-quarter})])])))


