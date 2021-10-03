(ns wactbprot.doc.page.components
  ^{:author "Thomas Bock <wactbprot@gmail.com>"
    :doc "Component library."}
  (:require [clojure.string :as string]))

(defn width-trans [w]
  (condp = w
    :one-quarter "uk-width-1-4"
    :half "uk-width-1-2"
    :three-quarter "uk-width-3-4"
    :full "uk-width-1-1"))

;;........................................................................
;; form
;;........................................................................
(defn form-stacked [] [:form.uk-form-stacked.uk-grid {:uk-grid ""}])

(defn form-text-input [{label :label value :value path :path type :type} {width :width} ]
  [:div {:class (width-trans width)}
   [:label.uk-form-label {:for "form-stacked-text"} label]
   [:div.uk-form-controls
    [:input.uk-input {:id "form-stacked-text"
                      :type "text"
                      :value value
                      :val-type type
                      :val-path path}]]])


