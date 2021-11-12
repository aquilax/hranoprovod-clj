(ns hranoprovod.db
  (:use [hranoprovod.utils]))

(def db [(f "sweets/candy/100g"
            [(i "kcal" 400)
             (i "carbs" 70)
             (i "fat" 0)
             (i "protein" 0)])
         (f "sweets/candy/pc"
            [(i "sweets/candy/100g" 0.06)])
         (f "bread/rye/100g"
            [(i "kcal" 360)
             (i "carbs" 70)
             (i "protein" 10)
             (i "fat" 5)])
         (d "2021-09-23"
            [(i "sweets/candy/100g" 0.30)
             (i "sweets/candy/pc" 0.10)])
         (d "2021-10-23"
            [(i "bread/rye/100g" 0.60)
             (i "sweets/candy/pc" 0.30)])])
