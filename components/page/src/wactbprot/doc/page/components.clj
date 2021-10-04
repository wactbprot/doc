(ns wactbprot.doc.page.components
  ^{:author "Thomas Bock <wactbprot@gmail.com>"
    :doc "Component library."}
  (:require [clojure.string :as string]
            [wactbprot.doc.page.utils :as u]))

(defn article [] [:article.uk-article])

;;........................................................................
;; form
;;........................................................................
(defn form-heading [s][:h3.uk-heading-line.uk-text-center [:span s]])

(defn form [] [:form.uk-form-stacked.uk-grid {:uk-grid ""}])

(defn form-text-input [{label :label value :value :as data} {width :width} ]
  [:div {:class (u/width-trans width)}
   [:label.uk-form-label {:for "form-stacked-text"} label]
   [:div.uk-form-controls
    [:input.uk-input
     (merge {:id "form-stacked-text" :value value} data)]]])

(defn form-select [{label :label value :value opts :options :as data} {width :width} ]
  [:div {:class (u/width-trans width)}
   [:label.uk-form-label {:for "form-stacked-text"} label]
   [:div.uk-form-controls
    (into [:select.uk-select (merge {:id "form-horizontal-select" :selected value} data)]
           (mapv (fn [s] [:option (when (= s value) {:selected ""}) s]) opts) )]])
