(ns project.core
  (:gen-class))

;         !!!IMPORTANT Global Variables!!!
;                        |
;                        V
;global variables (adjust to model different scenarios) <----------------------------- !!!IMPORTANT Global Variables!!!
(def objAMass 143823752348) ;mass of object A in kg (point particle)
(def objBMass 100000000000) ;mass of object B in kg (point particle)
(def objAPos 8) ;distance from object A in m to the right of the origin
(def objBPos 19) ;distance from object B in m to the right of the origin
(def ABHeight 1500) ;distance of object A and object B from the earth in m

;;returns position of object A
(defn getNextPosA [currTime]
  (+ objAPos (int (* 0.5 (reduce * (replicate 2 currTime)) (/ (* 0.0000000000674 objBMass) (reduce * (replicate 2 (- objBPos objAPos))))))))
  
;;returns position of object B
(defn getNextPosB [currTime]
  (- objBPos (int (* 0.5 (reduce * (replicate 2 currTime)) (/ (* 0.0000000000674 objAMass) (reduce * (replicate 2 (- objBPos objAPos))))))))

;;returns line
(defn buildLine [currHeight currPosA currPosB charLeft]
  (cond
    (< charLeft 0) (str \tab (int (Math/sqrt (/ (- ABHeight currHeight) 4.9))) \s \newline)
    (= charLeft 30) (str currHeight \m \: \tab (buildLine currHeight currPosA currPosB (- charLeft 1)))
    (= (- 29 charLeft) currPosA) (str \A (buildLine currHeight currPosA currPosB (- charLeft 1)))
    (= (- 29 charLeft) currPosB) (str \B (buildLine currHeight currPosA currPosB (- charLeft 1)))
    :else (str \. (buildLine currHeight currPosA currPosB (- charLeft 1)))))

;;returns line after optional collision
(defn buildLineCollision [currHeight position charLeft]
  (cond
    (< charLeft 0) (str \tab (int (Math/sqrt (/ (- ABHeight currHeight) 4.9))) \s \newline)
    (= charLeft 30) (str currHeight \m \: \tab (buildLineCollision currHeight position (- charLeft 1)))
    (= (- 29 charLeft) position) (str \X (buildLineCollision currHeight position (- charLeft 1)))
    :else (str \. (buildLineCollision currHeight position (- charLeft 1)))))

;;returns remaining graph after optional collision
(defn graphCollision [currHeight position]
  (cond
    (< currHeight 0) "\tEarthEarthEarthEarthEarthEarth"
    (= (mod (- ABHeight currHeight) (/ ABHeight 20)) 0) (str (buildLineCollision currHeight position 30) (graphCollision (- currHeight 1) position))
    :else (graphCollision (- currHeight 1) position)))
  
;;returns graph
(defn graphABPos [currHeight currPosA currPosB]
  (cond
    (< currHeight 0) "\tEarthEarthEarthEarthEarthEarth"
    (>= currPosA currPosB) (graphCollision currHeight currPosA)
    (= (mod (- ABHeight currHeight) (/ ABHeight 20)) 0) (str (buildLine currHeight currPosA currPosB 30) (graphABPos (- currHeight 1) (getNextPosA (Math/sqrt (/ (- ABHeight (- currHeight 1)) 4.9))) (getNextPosB (Math/sqrt (/ (- ABHeight (- currHeight 1)) 4.9)))))
    :else (graphABPos (- currHeight 1) (getNextPosA (Math/sqrt (/ (- ABHeight (- currHeight 1)) 4.9))) (getNextPosB (Math/sqrt (/ (- ABHeight (- currHeight 1)) 4.9))))))

;;checks for invalid input and prints graph
(defn -main []
  (cond
    (or (<= objAMass 0) (<= objBMass 0)) (print "error: all masses must be positive")
    (or (< objAPos 0) (< objBPos 0)) (print "error: positions cannot be negative")
    (>= objAPos objBPos) (print "error: position of object A must be less than that of object B")
    (or (>= objAPos 30) (>= objBPos 30)) (print "error: both positions must be less than 30 m")
    (< ABHeight 0) (print "error: height cannot be negative")
    (> ABHeight 1500) (print "error: height is too large and may result in stack overflow")
    :else (print (str "----------------------------------------------------------------------------------------------------\nGraph A:\n       (0m)      (10m)     (20m)    (30m)\n\t|         |         |        |\n\tV         V         V        V\n" (graphABPos ABHeight objAPos objBPos) "\n----------------------------------------------------------------------------------------------------\n* Graph A (above) approximately models the positions of two point particles as they descend to Earth.\n* The approximate time elapsed is displayed to the right of each line.\n* In the event of a collision, the combination of object A and object B is denoted by \"X\".\n* To alter the initial conditions, simply adjust the values of the clearly labeled global variables towards the top of the source code.\n* Warning! Some initial conditions are invalid and will produce errors.\n* This program was composed in Clojure April 4, 2020 by Ray Barnett and Ali Alshehri as a submission for their final project in\n  CSC372 taught by Professor Collberg.\n* Thank you again for all the accomodations.\n----------------------------------------------------------------------------------------------------\n"))))

