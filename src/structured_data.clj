(ns structured-data)

(defn do-a-thing [x]
  (let [xx (+ x x)]
    (Math/pow xx xx)))

(defn spiff [v]
  (+ (get v 0) (get v 2)))

(defn cutify [v]
  (conj v "<3"))

(defn spiff-destructuring [v]
  (let [[x y z] v]
    (+ x z)))

(defn point [x y]
  [x y])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])

(defn width [[[x y][xx yy]]]
  (- xx x))

(defn height [[[x y][xx yy]]]
  (- yy y))

(defn square? [rectangle]
  (== (height rectangle) (width rectangle))

  )

(defn area [rectangle]
  (* (height rectangle) (width rectangle)))

(defn contains-point? [[[x0 y0][x1 y1]] [x y]]
  (and (<= x0 x x1) (<= y0 y y1)))

(defn contains-rectangle? [outer [bot top]]
  (and (contains-point? outer bot) (contains-point? outer top)))

(defn title-length [book]
  (count (:title book)))

(defn author-count [book]
  (count (:authors book)))

(defn multiple-authors? [book]
  (if (> (count (:authors book)) 1) true false))

(defn add-author [book new-author]
  (assoc book :authors (conj (:authors book) new-author)))

(defn alive? [author]
  (if (contains? author :death-year) false true))

(defn element-lengths [collection]
  (map count collection))

(defn second-elements [collection]
  (map second collection))

(defn titles [books]
  (map :title books))

(defn monotonic? [a-seq]
  (cond
    (apply <= a-seq) true
    (apply >= a-seq) true
    :else false))

(defn stars [n]
  (apply str (apply concat (repeat n "*"))))

(defn star2s [n]
   (apply str ( concat (repeat n "*"))))

(defn toggle [a-set elem]
  (if (contains? a-set elem)
    (disj a-set elem)
    (conj a-set elem)))

(defn contains-duplicates? [a-seq]
  (if (== (count a-seq) (count (set a-seq)))
    false
    true))

(defn old-book->new-book [book]
   (assoc book :authors (set (:authors book))))

(defn has-author? [book author]
  (contains? (:authors book) author))

(defn authors [books]
  (set (apply concat (map :authors books))))

(defn all-author-names [books]
 (set (map :name (authors books))))

(defn author->string [author]
  (let
    [nameA (:name author)
     birth (:birth-year author)
     death (:death-year author)]
    (if birth (str nameA " (" birth " - " death ")") (str nameA))))
      



(defn authors->string [authors]
  (apply str (interpose ", " (map author->string authors))))

(defn book->string [book]
  (str (:title book) ", written by " (authors->string (:authors book))))

(defn books->string [books]
  (let [strBooks (apply str (interpose ", " (map book->string books)))
        nbr (count books)]
    (cond
      (== nbr 0) "No books."
      (== nbr 1) (str "1 book. " strBooks ".")
      :else (str nbr " books. " strBooks "."))))

(defn books-by-author [author books]
  (filter (fn [book] (has-author? book author)) books))

(defn author-by-name [name authors]
   (first (filter (fn [author] (if (= (:name author) name) true false)) authors)))

(defn living-authors [authors]
  (filter alive? authors))

(defn has-a-living-author? [book]
  (if (empty? (living-authors (:authors book))) false true))

(defn books-by-living-authors [books]
  (filter has-a-living-author? books))

; %________%
