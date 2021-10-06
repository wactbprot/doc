(ns wactbprot.doc.content.customer
  (:require [wactbprot.doc.config.interface :as c]
            [wactbprot.doc.page.interface :as p]
            [clojure.string :as string]))

(defn path [{p :data-path}] (mapv keyword (string/split p #"\.")))

(defn info [m data] (assoc m :value (get-in data (path m))))


(defn category [data base layout]
  (-> {:label "Kategorie"
       :data-path  (str base "Category")
       :options ["EU-Ausland" "Inland" "Ausland"]}
      (info data)
      (p/form-select layout)))

(defn gender [data base layout]
  (-> {:label "Geschlecht"
       :data-path  (str base "Gender")
       :options ["" "female" "male" "other"]}
      (info data)
      (p/form-select layout)))

(defn lang [data base layout]
  (-> {:label "Sprache"
       :data-path  (str base "Lang")
       :options ["de" "en"]}
      (info data)
      (p/form-select layout)))

(defn contact [data base]
  [(-> {:label "Name"
        :data-path  (str base "Name")}
       (info data)
       (p/form-text-input {:width :one-quarter}))

   (-> {:label "Email"
        :data-path  (str base "Email")}
       (info data)
       (p/form-text-input {:width :one-quarter}))

   (-> {:label "Telefon"
        :data-path  (str base "Phone")}
       (info data)
       (p/form-text-input {:width :one-quarter}))

   (gender data base {:width :one-quarter})])


(defn main [data]
  (let [base "Customer."]
  [(-> {:label "Kürzel"
        :data-path  (str base "Sign")}
       (info data)
       (p/form-text-input {:width :one-quarter}))

   (-> {:label "Debitor"
        :data-path  (str base "DebitorenNr")
        :data-type "int"}
       (info data)
       (p/form-text-input {:width :half}))

   (-> {:label "Sprache"
        :data-path  (str base "Lang")
        :options ["de" "en"]}
       (info data)
       (p/form-select {:width :one-quarter}))

   (-> {:label "Kommentar"
        :data-path  (str base "Comment")}
       (info data)
       (p/form-text-input {:width :full}))]))

(defn address-name [data base layout prop-name] 
  [(-> {:label "Adresszeile 1"
        :data-path  (str base prop-name)}
       (info data)
       (p/form-text-input layout))])

(defn address-tail [data base]
  [(-> {:label "Adresszeile 2"
        :data-path (str base "AddName")}
       (info data)
       (p/form-text-input {:width :half}))
   
   (-> {:label "Adresszeile 3"
        :data-path (str base "AddAddName")}
       (info data)
       (p/form-text-input {:width :half}))

   (-> {:label "Straße Nr."
        :data-path (str base "Address.Street")}
       (info data)
       (p/form-text-input {:width :half}))

   (-> {:label "PLZ"
        :data-path (str base "Address.Zipcode")}
       (info data)
       (p/form-text-input {:width :one-quarter}))

   (-> {:label "Bezirk/Distrikt"
        :data-path (str base "Address.District")}
       (info data)
       (p/form-text-input {:width :one-quarter}))

   (-> {:label "Ort"
        :data-path (str base "Address.Town")}
       (info data)
       (p/form-text-input {:width :half}))

   (-> {:label "Landeskürzel"
        :data-path (str base "Address.Land")}
       (info data)
       (p/form-text-input {:width :one-quarter}))
   
   (category data (str base "Address.") {:width :one-quarter})])

(defn main-contact [data] (contact data "Customer.Contact."))

(defn shipping-contact [data] (contact data "Customer.Shipping."))

(defn invoice-contact [data] (contact data "Customer.Invoice."))

(defn main-address [data]
  (let [base "Customer."]
    (into (address-name data base {:width :full} "Name")
          (address-tail data base))))

(defn sub-address [data base]
  (into (address-name data base {:width :full} "CustomerName")
        (address-tail data base)))

(defn content [data]
  (into (p/acc-frame)
        [(p/acc-sheet "Allgemein/Adresse/Kontakt"
                      (into (p/article)
                            [(p/form-heading "Allgemein")
                             (into (p/form) (main data))
                             (p/form-heading "Adresse")
                             (into (p/form) (main-address data))
                             (p/form-heading "Kontakt")
                             (into (p/form) (main-contact data))]) {:open true})
         (p/acc-sheet "Versand"
                      (into (p/article)
                            [(p/form-heading "Versand Adresse")
                             (into (p/form) (sub-address data "Customer.Shipping."))
                             (p/form-heading "Versand Kontakt")
                             (into (p/form) (shipping-contact data))]))
         (p/acc-sheet "Rechnung"
                      (into (p/article)
                            [(p/form-heading "Rechnungsadresse")
                             (into (p/form) (sub-address data "Customer.Invoice"))
                             (p/form-heading "Rechnungskontakt")
                             (into (p/form) (invoice-contact data))]))
         (p/acc-sheet "Auswahl Adressen"  "mmm")
         (p/acc-sheet "Auswahl Kontakte"  "mmm")]))


