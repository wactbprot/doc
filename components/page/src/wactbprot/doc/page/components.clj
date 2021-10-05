(ns wactbprot.doc.page.components
  ^{:author "Thomas Bock <wactbprot@gmail.com>"
    :doc "Component library."}
  (:require [clojure.string :as string]
            [wactbprot.doc.page.utils :as u]))

(defn article [] [:article.uk-article])

(defn acc-frame [] [:ul {:uk-accordion "collapsible: false"}])

(defn acc-sheet
  ([title content]
   (acc-sheet title content {:open false}))
  ([title content {o :open}]
   [:li (when o {:class "uk-open"})
    [:a.uk-accordion-title {:href "#"} title]
    [:div.uk-accordion-content content]]))

;;........................................................................
;; form
;;........................................................................
(defn form-heading [s][:h3.uk-heading-line.uk-text-center [:span s]])

(defn form [] [:form.uk-form-stacked.uk-grid.uk-child-width-auto {:uk-grid ""}])

(defn label [s] [:label.uk-form-label.uk-text-muted {:for "form-stacked-text"} s])

(defn form-checkbox [{l :label  :as data} {w :width} ]
  [:div {:class (u/width-trans w)}
   (label l)
   [:div.uk-form-controls
    [:input.uk-checkbox (merge {:type "checkbox"} data)]]])

(defn form-text-input [{l :label v :value :as data} {w :width} ]
  [:div {:class (u/width-trans w)}
   (label l)
   [:div.uk-form-controls
    [:input.uk-input.uk-text-emphasis
     (merge {:id "form-stacked-text" :value v} data)]]])

(defn form-select [{l :label v :value o :options :as data} {w :width} ]
  [:div {:class (u/width-trans w)}
   (label l)
   [:div.uk-form-controls
    (into [:select.uk-select (merge {:id "form-horizontal-select" :selected v} data)]
           (mapv (fn [s] [:option (when (= s v) {:selected ""}) s]) o) )]])
