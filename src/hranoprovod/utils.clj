(ns hranoprovod.utils)

(defn f [name ingredients] {:type :food :name name :ingredients ingredients})
(defn i [name qty] {:type :item :name name :qty qty})
(defn d [date items] {:type :day :date date :items items})
