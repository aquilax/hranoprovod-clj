# hranoprovod-clj

Clojure sandbox implementation of life tracking tool [hranoprovod-cli]()

## Run (using babashka)

```bash
$ bb --main hranoprovod.main -l example/log.yaml -d example/food.yaml 
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

## YAML output

```bash
$ bb --main hranoprovod.main  -l example/log.yaml -d example/food.yaml --yaml
- type: day
  date: 2021/01/24
  items:
  - {type: item, name: day/nonworking, qty: 1}
  - {type: item, name: coffee/cup, qty: 1}
  - {type: item, name: calories, qty: 720.0}
  - {type: item, name: fat, qty: 54.0}
  - {type: item, name: carbs, qty: 1080.0}
  - {type: item, name: protein, qty: 72.0}
  - {type: item, name: candy/snickers/bar, qty: 1}
- type: day
  date: 2021/01/25
  items:
  - {type: item, name: day/nonworking, qty: 1}
  - {type: item, name: coffee/cup, qty: 2}
  - {type: item, name: calories, qty: 300.0}
  - {type: item, name: fat, qty: 22.5}
  - {type: item, name: carbs, qty: 450.0}
  - {type: item, name: protein, qty: 30.0}
```

