(ns circle-points.core
  (:gen-class)
  (:require [quip.core :as qp]
            [circle-points.scenes.level-01 :as level-01]))

(defn setup
  "The initial state of the game"
  []
  {:point-count 200
   :rotation-fraction level-01/psi
   :power 0.1})

(defn init-scenes
  "Map of scenes in the game"
  []
  {:level-01 (level-01/init)})

;; Configure the game
(def circle-points-game
  (qp/game {:title          "circle-points"
            :size           [800 800]
            :setup          setup
            :init-scenes-fn init-scenes
            :current-scene  :level-01}))

(defn -main
  "Run the game"
  [& args]
  (qp/run circle-points-game))
