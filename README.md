# pojekt_java_1

Program ma czytać dane z pliku o nazwie podanej w linii komend, albo z stdin jeśli użytkownik nie wyspecyfikował nazwy pliku. Wyniki zapisywane mają być na stdout albo, jeśli użyto opcji -o fname, do pliku o wskazanej nazwie.

We wczytanym tekście należy wyszukać akapity. Akapit składa się z jednej lub więcej sąsiadujących ze sobą linii, przy czym pierwszym znakiem w tych liniach musi być litera.

Akapity należy przeformatować. Polega to na połączeniu wszystkich linii składających się na akapit w jedną całość, a następnie połamaniu jej tak, aby długości powstających linii nie przekraczały założonego maksimum. Wartość tego maksimum domyślnie wynosi 78, ale użycie opcji -w width pozwala to zmienić.

W całym przetwarzanym tekście (czyli również w tych liniach, które nie wchodzą w skład akapitów) należy dokonać rozwinięcia makr. Makra definiuje się przy pomocy opcji -m name=text, przy czym nazwa makra musi składać się wyłącznie z liter, a tekst rozwinięcia może zawierać dowolne znaki. Jeśli tekst zawiera słowo identyczne z nazwą jakiegoś makra, to należy na miejsce tego słowa wstawić rozwinięcie makra.

Przykład użycia makr:

echo 'John said: "foo xyzzy bar, also xyz^2=foo".' | java Projekt -m xyz=26.75 -m foo=bar
John said: "bar xyzzy bar, also 26.75^2=bar".

App:
  Klasa
