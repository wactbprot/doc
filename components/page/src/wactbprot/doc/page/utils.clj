(ns wactbprot.doc.page.utils
  ^{:author "Thomas Bock <wactbprot@gmail.com>"
    :doc "Utils for the component library."}
  (:require [clojure.string :as string]))

(defn width-trans [w]
  (condp = w
    :one-quarter "uk-width-1-4"
    :half "uk-width-1-2"
    :three-quarter "uk-width-3-4"
    :full "uk-width-1-1"
    :one-sixth "uk-width-1-6"
    :two-sixth "uk-width-2-6"
    :three-sixth "uk-width-3-6"
    :four-sixth "uk-width-4-6"
    :five-sixth "uk-width-5-6"))
