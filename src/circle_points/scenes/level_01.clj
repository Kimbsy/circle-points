(ns circle-points.scenes.level-01
  (:require [quil.core :as q]
            [quip.sprite :as qpsprite]
            [quip.utils :as qpu]))

(def grey [57 57 58])
(def orange [255 133 82])

(defn draw-level-01
  "Called each frame, draws the current scene to the screen"
  [{:keys [point-count rotation-fraction power] :as state}]
  (qpu/background grey)
  (q/no-stroke)
  (qpu/fill orange)

  (doseq [i (range point-count)]
    (let [distance (* (Math/pow (/ i (dec point-count)) power)
                      (/ (q/height) 2))
          angle (* 2 Math/PI rotation-fraction i)
          x (+ (/ (q/width) 2)
               (* distance (Math/cos angle)))
          y (+ (/ (q/height) 2)
               (* distance (Math/sin angle)))]
      (q/ellipse x y 10 10))))

(def psi (/ (+ 1 (Math/sqrt 5)) 2))

(defn update-level-01
  [state]
  (-> state
      (update :rotation-fraction #(min (+ % 0.001) psi))
      (update :power #(min (+ % 0.001) 0.5))
      (update :point-count #(max (- % 0.4) 1.1))))

(defn init
  "Initialise this scene"
  []
  {:draw-fn draw-level-01
   :update-fn update-level-01})
