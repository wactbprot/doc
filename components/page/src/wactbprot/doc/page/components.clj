(ns wactbprot.doc.page.components
  ^{:author "Thomas Bock <wactbprot@gmail.com>"
    :doc "Component library."}
  (:require [clojure.string :as string]
            [wactbprot.doc.page.utils :as u]))

(defn article [] [:article.uk-article])

(defn acc-frame [] [:ul {:uk-accordion ""}]) ;; collapsible: false

(defn acc-sheet [title content {o :open}]
  [:li (when o {:class "uk-open"})
   [:a.uk-accordion-title.uk-text-muted {:href "#"} title]
   [:div.uk-accordion-content
    [:div.uk-card.uk-card-body.uk-background-muted content]]])

(defn button [data text] [:button.uk-button.uk-button-primary.uk-button-small.doc-button  (merge {} data) text])

(defn grid [] [:div {:class "uk-grid-small uk-child-width-expand@s" :uk-grid ""}])

(defn margin [] [:p {:uk-margin ""}])

(defn label [s]
  (when (seq s) [:label.uk-form-label.uk-text-muted {:for "form-stacked-text"} s]))

;;........................................................................
;; form
;;........................................................................
(defn form-heading [s][:h4.uk-heading-line.uk-text-center.uk-text-muted [:span s]])

(defn form [] [:form.uk-form-stacked.uk-grid.uk-child-width-auto {:uk-grid ""}])

(defn form-text-input [{l :label v :value :as data} {w :width} ]
  [:div {:class (u/width-trans w)}
   (label l)
   [:div.uk-form-controls
    [:input.uk-input.uk-text-emphasis.doc-input
     (merge {:id "form-stacked-text" :value v} data)]]])

(defn form-select [{l :label v :value o :options :as data} {w :width} ]
  [:div {:class (u/width-trans w)}
   (label l)
   [:div.uk-form-controls
    (into [:select.uk-select.doc-input (merge {:id "form-horizontal-select" :selected v} data)]
           (mapv (fn [s] [:option (when (= s v) {:selected "" :value s}) s]) o) )]])
