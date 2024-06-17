#!/usr/bin/env bb

(require '[babashka.process :refer [process]])

(defn read-tasks [from]
  ;; read from `from`, split lines, remove blank lines:
    (->> (slurp from)
         (str/split-lines)
         (remove clojure.string/blank?)))

(defn task-thread [task-str]
  (process {:out :string
            :err :string}
            task-str))

(defn up-one-line []
    (print (str (char 27) "[A")))

(defn done? [task]
  (not (.isAlive (:proc task))))

(defn tasks-finished? [tasks]
  (every? done? tasks))

(defn status [task]
  (if-not (:done? task)
    (str "Running")
    (str "Finished")))

(defn task-status-str [n task-name task]
  (let [{:keys [exit err out]} (deref task)]
    (if (zero? exit)
    "✓"
    (do
     (dotimes [_ n] (println))
     (println (format "
'%s': Error %d
STDERR:
%s
STDOUT:
%s
" task-name exit err out))
     (System/exit -1)))))


(defn select-nth-char [n]
  (let [spinner-chars "⠋⠙⠹⠸⠼⠴⠦⠧⠇⠏"]
    (str (char (nth spinner-chars n)))))

(defn report [n tasks]
  (dotimes [_ (count tasks)] (up-one-line))
  (doseq [[task-name task] tasks]
      (if (not (done? task))
      (println (format "Running '%s'..." task-name) (select-nth-char n))
      (println (format "Running '%s'..." task-name)
               (task-status-str (count tasks)
                                task-name task)))))

(let [tasks (map (juxt identity task-thread) (read-tasks *in*))]
  (doseq [[task-name task] tasks]
    (println (format " Started '%s'..." task-name)))
  (let [n (atom 0)]
    (while (not (tasks-finished? (map second tasks)))
      (report @n tasks)
      (swap! n (fn [n] (mod (inc n) 10)))
      (Thread/sleep 100))
    (report n tasks)))
