# valentum-Aufgaben

Sehr geehrtes Valentum Team,

ich möchte mich zu allererst bedanken, für die Chance, mich bei Ihnen zu beweisen!

In diesem Directory ist meine Lösung für die Labyrinth- und Fibonacci-Aufgabe zu finden.

Zu den Lösungen ein paar kurze Worte:

Zum Lösen des Labyrinths habe zunächst an Dijkstra gedacht, um kürzeste Wege zu finden.
Dijkstra verwendet jedoch eine priorisierte Queue, die in diesem Fall überflüssig, ist Aufgrund der Kantengewichte von 1 von jedem Knoten zu jedem weiteren Knoten.
Aufgrund der Kantengeweichte von 1 muss man zudem auch keine Distanzen zu S doppelt aktualisieren.
Ein später bearbeiteter Knoten hat immer die gleiche Distanz oder eine größere Distanz als ein vorherig bearbeiteter Knoten.
Somit kommt man auf einen simplen BFS algorithmus, der die Distanz einmalig aktualisiert.

Zu Testzwecken habe ich einen MazeGenerator gebaut, der zufällig generierte Labyrinthe ausgibt und löst.
Um den gewählten Weg besser nachvollziehen zu können hat man bei den zufällig generierten Labyrinthen zusätzlich die möglichkeit sich den Weg anzeigen zu lassen.



Bei der Fibonacci-Aufgabe habe ich zwei wichtige Punkte gefunden, die es zu beachten galt.

Zunächst werden natürlich Fibonacci-Zahlen von bis zu 5000 sehr groß. Um dieses Problem zu lösen, habe ich ein BigInteger verwendet.

Und das zweite Problem war natürlich die Laufzeit, um 523 Fibonacci-Zahlen effizient zu berechnen.
Hierfür habe ich die Zahlen nicht Rekursiv, sondern iterativ gelöst.
Ich sortiere in meiner Lösung alle Zahlen vor der Bearbeitung und berechne dann auf dem Weg zur größten Zahl gleich auch die kleineren Zahlen automatisch mit.
Somit wird im worst case praktisch ein mal Fiboncacci(5000) berechnet.


Es war zudem gewünscht:
  - Eine ehrliche Zeitangabe, also in welcher Zeit Sie die Aufgabe erledigt haben
      - Labyrinth 4-5 Stunden: (inklusive Generator, Aufzeigen des Lösungsweges)
            Die Idee zur Lösung kam sehr schnell. Ich habe jedoch noch nicht sehr viel Erfahrung gesammelt mit der Strukturierung von Programmen.
            Ich habe es so gut wie möglich versucht für sie so verständlich wie möglich zu gestalten, damit meine Gedankengänge klar sind.
      - Fibonacci 30 Min
      
  - Was waren die Schwierigkeiten und was war einfach?
      - schwierigkeiten hatte ich wie erwähnt in der Strukturierung. In diesem Bereich möchte ich im Folgenden auf professioneller Ebene viel dazu lernen
        um auch effizient und in einem größeren Team Software entwickeln zu können.
      - Leicht ist mir gefallen, das finden der richtigen Lösungswege.
        Auf Seiten wie Hackerrank habe ich das effiziente einsetzen von Algorithmen geübt und 
        hatte die Lösungen deswegen praktisch schon im Kopf.
        
        
Vielen Dank nochmals für diese Möglichkeit und ich hoffe Ich konnte Sie mit meiner Lösung überzeugen.
