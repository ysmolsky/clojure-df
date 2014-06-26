(ns cg.comps
  (:require [clojure.math.numeric-tower :as math])
  (:use [cg.ecs :only [defcomp]])
  (:use cg.queue))

(def node {:move [:position :velocity]
           :guide [:position :path :speed]
           :path-find [:position :destination]
           :render [:position :renderable]
           :free-worker [:controllable :job-ready]
           :free-job [:job :free]
           })

(defn round-coords [c]
  [(math/round (:x c))
   (math/round (:y c))])

(defn coords [c]
  [(:x c)
   (:y c)])

(defcomp health []
  :dead false
  :count 100)

(defcomp destination [x y]
  :x (float x)
  :y (float y))

(defcomp path [points]
  :points (apply queue points))

(defcomp velocity [x y]
  :x (float x)
  :y (float y))

;;; how fast is the item can be. it can be recalculated depending in
;;; the weight
(defcomp speed [pix-per-sec]
  :pixsec pix-per-sec)

(defcomp position [x y]
  :x (float x)
  :y (float y))

(defcomp controllable [])

(defcomp job [kind]
  :kind kind
  :materials [])                        ; ids of material designated
                                        ; for job

(defcomp free [])

(defcomp assigned [id]
  :id id)

(defcomp job-ready [])

(defcomp job-dig [x y id]
  :x  x
  :y  y
  :id id
  :progress 5000) ;; from 1000 to 0

(defcomp job-wall [x y id]
  :x  x
  :y  y
  :id id
  :progress 1000) ;; from 1000 to 0

(defcomp stone [kind]
  :kind kind)

(defcomp keyboard [])
(defcomp renderable [char]
  :char char)

(defcomp container []
  :items []
  :weight 0
  :size 0)
