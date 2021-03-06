(ns cg.systems.pathfind
  (:require [cg.astar :as astar]
            [cg.common :refer :all]
            [cg.comps :refer :all]
            [cg.ecs :refer :all]
            [cg.site :as s]))

;;; PATH FIND SYSTEM

(defn get-cell-cost [cells xy] 10)

(defn filter-nbr [m xy]
  (s/passable? (s/place m xy)))

(defn get-path [from-xy to-xy map]
  (if (contacting? from-xy to-xy)
    {:xys [from-xy to-xy]}
    (if (s/connected? map from-xy to-xy)
      (astar/path from-xy to-xy 11 map get-cell-cost filter-nbr)
      nil)))

(defn path-find-add [e time map]
  (let [from-xy (round-coords (:position e))
        to-xy (round-coords (:move-to e))
        found (get-path from-xy to-xy map) 
        points (:xys found)]
    (prn :path-find from-xy to-xy found)
    (if (empty? points)
      (-> e
          (rem-c :move-to)
          (set-c (failed-job)))
      (-> e
          (rem-c :move-to)
          (set-c (path points))))))

(defn system-path-find [w time]
  (update-entities-by-cnames w (:path-find node) path-find-add time (:map w)))

