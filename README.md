# hranoprovod-clj
Clojure sandbox implementation of life tracking tool

# Run (using babashka)

```bash
$ bb --main hranoprovod.main s -l example/log.yaml -d example/food.yaml 
2021/01/24:
  day/nonworking: 1.00
  coffee/cup: 1.00
  calories: 720.00
  fat: 54.00
  carbs: 1080.00
  protein: 72.00
  candy/snickers/bar: 1.00

2021/01/25:
  day/nonworking: 1.00
  coffee/cup: 2.00
  calories: 300.00
  fat: 22.50
  carbs: 450.00
  protein: 30.00
```

