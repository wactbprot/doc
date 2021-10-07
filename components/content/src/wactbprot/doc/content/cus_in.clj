(ns wactbprot.doc.content.cus-in
  (:require [wactbprot.doc.config.interface :as c]
            [wactbprot.doc.content.utils :as u]
            [clojure.string :as string]))

(defn alt-address [{cus :Customer :as doc} p]
  (if (and (contains? (set p) :AltAddress) (not (contains? cus :AltAddress)))
    (assoc-in doc [:Customer :AltAddress] [])
    doc))

(defn alt-contact [{cus :Customer :as doc} p]
  (if (and (contains? (set p) :AltContact) (not (contains? cus :AltContact)))
    (assoc-in doc [:Customer :AltContact] [])
    doc)) 

(defn customer [doc p]
  (-> doc
      (alt-address p)
      (alt-contact p)))

(defn shipping-alt-address [doc p] {:ok true})
  
(defn invoice-alt-address [doc p] {:ok true})

(defn main-name [cus]
  (update-in
   (assoc-in cus [:Customer :Name] (get-in cus [:Customer :CustomerName]))
   [:Customer] dissoc :CustomerName))

(defn main-alt-address [doc p]
  (if-let [new-addr (main-name (get-in doc p))]
    (when-let [old-addr (get-in doc [:Customer :Address])]
      ;; go on here
      )
  {:ok true}))

(defn command [doc p cmd]
  (condp = (keyword cmd)
    :invoice-alt-address (invoice-alt-address doc p)
    :shipping-alt-address (shipping-alt-address doc p)
    :main-alt-address (main-alt-address doc p)))


(defn in [{p :path v :value t :type cmd :cmd} doc]
  (let [p (u/path p)]
    (cond
      (seq cmd) (command doc p cmd)
      (seq v) (assoc-in (customer doc p) p (if t (u/value t v) v))
      :else {:error "unkown request"})))
