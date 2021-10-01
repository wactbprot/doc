(ns wactbprot.doc.page.core
  ^{:author "Thomas Bock <wactbprot@gmail.com>"
    :doc "Gives the frame for all the specific content"}
  (:require [hiccup.form :as hf]
            [hiccup.page :as hp]
            [clojure.string :as string]))

(defn not-found []
  (hp/html5
   [:h1 "404 Error!"]
   [:b "Page not found!"]
   [:p [:a {:href ".."} "Return to main page"]]))

;;........................................................................
;; nav
;;........................................................................
(defn nav [conf]
  [:div.uk-navbar-container
   {:uk-navbar ""}
   [:div.uk-navbar-center
    [:ul.uk-navbar-nav
     [:li [:a {:target "_blank"
               :href "https://gitlab1.ptb.de/vaclab/repliclj"} "gitlab"]]
     [:li [:a {:target "_blank"
               :href "http://a75438:5601/app/discover#/view/6fde0090-06ff-11ec-a0ed-9fa5b8b37aed"} "elasticsearch"]]
     [:li [:a { :target "_blank"
               :href "https://docs.couchdb.org/en/main/replication/index.html"} "repli docu"]]]]])

;;........................................................................
;; body
;;........................................................................
(defn body [conf content]
  [:body#body
   (nav conf)
   [:div.uk-container.uk-padding.uk-margin
    [:article.uk-article
     [:h4.uk-article-title.uk-text-uppercase.uk-heading-line.uk-text-center.uk-text-muted
      [:a.uk-link-reset {:href ""} "doc"]]
     [:p.uk-article-meta "today" #_(u/date)]
     [:p.uk-text-lead content]]]
   (hp/include-js "/js/uikit.js")
   (hp/include-js "/js/uikit-icons.js")])

;;........................................................................
;; head
;;........................................................................
(defn head [conf]
  [:head [:title "repliclj"]
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
   (hp/include-css "/css/uikit.css")])

;;........................................................................
;; index
;;........................................................................
(defn index [conf content]
  (hp/html5
   (head conf)
   (body conf content)))
