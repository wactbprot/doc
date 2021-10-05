(ns wactbprot.doc.content.customer
  (:require [wactbprot.doc.config.interface :as c]
            [wactbprot.doc.page.interface :as p]
            [clojure.string :as string]))

(defn path [{p :data-path}] (mapv keyword (string/split p #"\.")))

(defn value [m data] (assoc m :value (get-in data (path m))))

(defn content [conf data]
  (let [base-path "Customer.Address."]
    (into (p/article)
          [(p/form-heading "Allgemein")
           (into (p/form)
                 [(-> {:label "Kürzel"
                       :data-path  "Customer.Sign"
                       :data-type "string"}
                      (value data)
                      (p/form-text-input {:width :one-quarter}))

                  (-> {:label "Debitor"
                       :data-path  "Customer.DebitorenNr"
                       :data-type "int"}
                      (value data)
                      (p/form-text-input {:width :half}))

                  (-> {:label "Sprache"
                       :data-path  "Customer.Lang"
                       :data-type "string"
                       :options ["de" "en"]}
                      (value data)
                  (p/form-select {:width :one-quarter}))])
           (p/form-heading "Adresse")
           (into (p/form)
                 [(-> {:label "Adresszeile 1"
                       :data-path  "Customer.Name"
                       :data-type "string"}
                      (value data)
                      (p/form-text-input {:width :full}))

                  (-> {:label "Adresszeile 2"
                       :data-path "Customer.AddName"
                       :data-type "string"}
                      (value data)
                      (p/form-text-input {:width :full}))
                  
                  (-> {:label "Adresszeile 3"
                       :data-path "Customer.AddAddName"
                       :data-type "string"}
                      (value data)
                      (p/form-text-input {:width :full}))

                  (-> {:label "Straße Nr."
                       :data-path (str base-path "Street")
                       :data-type "string"}
                      (value data)
                      (p/form-text-input {:width :full}))

                  (-> {:label "PLZ"
                       :data-path (str base-path "Zipcode")
                       :data-type "string"}
                      (value data)
                      (p/form-text-input {:width :one-quarter}))

                  (-> {:label "Ort"
                       :data-path (str base-path "Town")
                       :data-type "string"}
                      (value data)
                      (p/form-text-input {:width :half}))

                  (-> {:label "Landeskürzel"
                       :data-path (str base-path "Land")
                       :data-type "string"}
                      (value data)
                      (p/form-text-input {:width :one-quarter}))])])))


