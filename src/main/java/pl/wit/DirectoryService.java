package pl.wit;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Klasa zarządzająca strukturą katalogów
 */
public class DirectoryService {

    /**
     * Pobieranie struktury katalogów na podstawie ścieżki i wyrażenia regularnego
     *
     * @param path  ścieżka do katalogu
     * @param regex wyrażenie regularne do filtrowania plików
     * @return zwracanie korzenia struktury katalogów
     * @throws IllegalArgumentException rzucenie wyjątku jeżeli ścieżka jest nieprawidłowa
     */
    public Node getDirectoryStructure(String path, String regex) throws IllegalArgumentException {

        /**
         * Utworzenie obiektu File, który reprezentuje katalog
         */
        File folder = new File(path);

        /**
         * Sprawdzenie, czy katalog istnieje i czy jest katalogiem
         * jeśli nie, wyrzucenie wyjątku IllegalArgumentException
         */
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Nieprawidłowa ścieżka: " + path);
        }
        /**
         * Utworzenie korzenia struktury katalogów
         */
        Node root = new Node(folder.getName(), folder.getPath());

        /**
         * Dodawanie plików i podkatalogów do struktury drzewa
         */
        addDirectoryToTree(root, folder, regex);

        /**
         * Zwracanie korzenia struktury katalogów
         */
        return root;
    }

    /**
     * Dodawanie katalogów i plików do drzewa struktury katalogów
     *
     * @param parent węzeł rodzica, do którego dodawane są dzieci
     * @param folder katalog do przetworzenia
     * @param regex wyrażenie regularne do filtrowania plików
     * @throws IllegalArgumentException rzucenie wyjątku jeżeli nie ma plików w katalogu
     */
    public void addDirectoryToTree(Node parent, File folder, String regex) throws IllegalArgumentException {
        /**
         * Pobieranie listy plików i katalogów w danym folderze
         */
        File[] files = folder.listFiles();

        /**
         * Sprawdzenie czy folder zawiera pliki
         * jeżeli nie to rzucenie wyjątku IllegalArgumentException
         */
        if (files == null)
            throw new IllegalArgumentException("Nie ma plików w: " + folder.getPath());

        /**
         * Kompilowanie wyrażenia regularnego do wzorca i ignorowanie wielkości liter
         */
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        /**
         * Iteracja po plikach i katalogach w folderze
         */
        for (File file : files) {

            /**
             * Tworzenie nowego węzła dla pliku lub katalogu
             */
            Node child = new Node(file.getName(), file.getPath());

            /**
             * Sprawdzanie czy plik jest katalogiem
             * jeżeli tak to dodawanie go jako dziecko i rekurencyjne przetwarzanie jego zawartości
             */
            if (file.isDirectory()) {
                parent.addChild(child);
                addDirectoryToTree(child, file, regex);

                /**
                 * Sprawdzanie czy plik pasuje do wzorca i dodawanie go jako liść
                 */
            } else if (pattern.matcher(file.getName()).find()) {
                parent.addChild(new Leaf(file.getName(), file.getPath()));
            }
        }
    }
}