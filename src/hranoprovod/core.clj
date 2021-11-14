(ns hranoprovod.core
  (:require [clojure.string :as string])
  (:use [hranoprovod.utils]))

(def max-depth 10)

(defn get-foods
  "Returns only food definitions"
  [food]
  (filter (fn [i] (= (i :type) :food)) food))

(defn get-days
  "Returns only day definitions"
  [food]
  (filter (fn [i] (= (i :type) :day)) food))

(defn group-ingredients
  "Sums ingredients by name"
  [ingredients]
  (map
   (fn [[name ing]]
     (i name (reduce + 0 (map :qty ing))))
   (into [] (group-by :name ingredients))))


(defn multiply
  [factor ingredients]
  (map #(i (% :name) (* (% :qty) factor)) ingredients))

(def resolve-item
  "Resolves single food item"
  (memoize
   (fn [food all-foods depth]
     (if (= depth 0)
       food
       (f
        (food :name)
        (group-ingredients
         (flatten
          (map
           (fn [ingredient]
             (let [found (first (filter (fn [t] (= (t :name) (ingredient :name))) all-foods))]
               (if (nil? found)
                 ingredient
                 (multiply (ingredient :qty) ((resolve-item found all-foods (- depth 1)) :ingredients)))))
           (food :ingredients)))))))))

(defn resolve-food
  "Resolves all food items"
  [food]
  (map
   (fn [i]
     (resolve-item i food max-depth))
   food))

(defn resolve-day
  "Resolves single day"
  [day resolved-food]
  (d
   (day :date)
   (group-ingredients
    (flatten
     (map
      (fn [item]
        (let [found (first (filter #(= (% :name) (item :name)) resolved-food))]
          (if (nil? found)
            item
            (multiply (item :qty) (found :ingredients)))))
      (day :items))))))


(defn resolve-days
  "Resolves days"
  [days resolved-food]
  (map #(resolve-day % resolved-food) days))

(defn format-item-qty
  "Formats item's qty"
  [qty]
  (if (number? qty) (format "%.2f" (float qty)) "NaN"))

(defn print-day
  "Prints a day"
  [day]
  (printf
   "%s:%n%s%n%n"
   (day :date)
   (string/join
    "\n"
    (map #(format "  %s: %s" (% :name) (format-item-qty (% :qty))) (day :items)))))
