(ns wactbprot.doc.content.customer
  (:require [wactbprot.doc.config.interface :as c]
            [wactbprot.doc.page.interface :as p]
            [clojure.string :as string]))

(defn path [{p :data-path}] (mapv keyword (string/split p #"\.")))

(defn value [m data] (assoc m :value (get-in data (path m))))

(defn contact [data]
  [(-> {:label "Name"
        :data-path  "Customer.Contact.Name"}
       (value data)
       (p/form-text-input {:width :one-quarter}))

   (-> {:label "Email"
        :data-path  "Customer.Contact.Email"}
       (value data)
       (p/form-text-input {:width :one-quarter}))

   (-> {:label "Telefon"
        :data-path  "Customer.Contact.Phone"}
       (value data)
       (p/form-text-input {:width :one-quarter}))

   (-> {:label "Geschlecht"
        :data-path  "Customer.Contact.Gender"
        :options ["female" "male" "other"]}
       (value data)
       (p/form-select {:width :one-quarter}))])

(defn main [data]
  [(-> {:label "Kürzel"
        :data-path  "Customer.Sign"}
       (value data)
       (p/form-text-input {:width :one-quarter}))

   (-> {:label "Debitor"
        :data-path  "Customer.DebitorenNr"
        :data-type "int"}
       (value data)
       (p/form-text-input {:width :half}))

   (-> {:label "Sprache"
        :data-path  "Customer.Lang"
        :options ["de" "en"]}
       (value data)
       (p/form-select {:width :one-quarter}))

   (-> {:label "Kommentar"
        :data-path  "Customer.Comment"}
       (value data)
       (p/form-text-input {:width :full}))])

(defn address [data]
  [(-> {:label "Adresszeile 1"
        :data-path  "Customer.Name"}
       (value data)
       (p/form-text-input {:width :full}))

   (-> {:label "Adresszeile 2"
        :data-path "Customer.AddName"}
       (value data)
       (p/form-text-input {:width :half}))
   
   (-> {:label "Adresszeile 3"
        :data-path "Customer.AddAddName"}
       (value data)
       (p/form-text-input {:width :half}))

   (-> {:label "Straße Nr."
        :data-path "Customer.Address.Street"}
       (value data)
       (p/form-text-input {:width :half}))

   (-> {:label "PLZ"
        :data-path "Customer.Address.Zipcode"}
       (value data)
       (p/form-text-input {:width :one-quarter}))

   (-> {:label "Bezirk/Distrikt"
        :data-path "Customer.Address.District"}
       (value data)
       (p/form-text-input {:width :one-quarter}))

   (-> {:label "Ort"
        :data-path "Customer.Address.Town"}
       (value data)
       (p/form-text-input {:width :half}))

   (-> {:label "Landeskürzel"
        :data-path "Customer.Address.Land"}
       (value data)
       (p/form-text-input {:width :one-quarter}))

   (-> {:label "Kategorie"
        :data-path  "Customer.Address.Category"
        :options ["EU-Ausland" "Inland" "Ausland"]}
       (value data)
       (p/form-select {:width :one-quarter}))])


(defn content [conf data]
  (into (p/acc-frame)
        [(p/acc-sheet "Adresse/Kontakt"
                      (into (p/article)
                            [(p/form-heading "Allgemein")
                             (into (p/form) (main data))
                             (p/form-heading "Adresse")
                             (into (p/form) (address data))
                             (p/form-heading "Kontakt")
                             (into (p/form) (contact data))]) {:open true})
         (p/acc-sheet "Versand"  "mmm")
         (p/acc-sheet "Rechnung" "llll")
         (p/acc-sheet "Adressen"  "mmm")
         (p/acc-sheet "Kontakte"  "mmm")]))


