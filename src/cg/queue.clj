(ns cg.queue)

;;; Queues

(defn queue [& args]
  (into (clojure.lang.PersistentQueue/EMPTY) args))

(defmethod print-method clojure.lang.PersistentQueue
  [o, w]
  (print-method '<- w)
  (print-method (seq o) w)
  (print-method '-< w))
