(ns wactbprot.doc.page.components
  ^{:author "Thomas Bock <wactbprot@gmail.com>"
    :doc "Component library."}
  (:require [clojure.string :as string]))

(defn form-stacked [] [:form.uk-form-stacked])

(defn form-text-input [label value path type]
  [:div.uk-margin
   [:label.uk-form-label {:for "form-stacked-text"} label]
   [:div.uk-form-controls
    [:input.uk-input {:id "form-stacked-text"
                      :type "text"
                      :value value
                      :val-type type
                      :val-path path}]]])
