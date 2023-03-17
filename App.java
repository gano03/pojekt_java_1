
//Paweł Ganobis
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static boolean zapis_do_pliku = false, wczytaj_z_pliku = false;
    public static String plik_we_nzw, plik_wy_nzw;
    public static List<String> makra = new ArrayList<>();
    public static int akapit_szer = 78;

    public static void main(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            if ((args[i].contains(".txt")) && !wczytaj_z_pliku) {
                wczytaj_z_pliku = true;
                plik_we_nzw = args[i];
            }
            if (args[i].equals("-o")) {
                zapis_do_pliku = true;
                plik_wy_nzw = args[i + 1];
                ++i;
            }
            if (args[i].equals("-m")) {
                makra.add(args[i + 1]);
            }
            if (args[i].equals("-w")) {
                akapit_szer = Integer.parseInt(args[i + 1]);
            }
        }

        try {
            wczytywanie();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void wczytywanie() throws IOException {
        FileReader plik_we;
        BufferedReader bufor_we;
        FileWriter plik_wy;
        Scanner sc;
        if (wczytaj_z_pliku) {
            plik_we = new FileReader(plik_we_nzw);
            bufor_we = new BufferedReader(plik_we);
            sc = new Scanner(bufor_we);
        } else {
            sc = new Scanner(System.in);
            plik_we = null;
            bufor_we = null;
        }
        sc.useDelimiter("\\s*\n\n\\s*");
        while (sc.hasNext()) {
            Akapit ak = new Akapit(sc.next());
            ak.makra_operacje();
            ak.akapit_poczatek();
            ak.akapit_formatowanie();
            if (zapis_do_pliku) {

                plik_wy = new FileWriter(plik_wy_nzw, true);
                plik_wy.write(ak.toString() + System.lineSeparator());
                plik_wy.close();
            } else {
                System.out.println(ak);
            }

        }
        if (wczytaj_z_pliku) {
            plik_we.close();
        }
        sc.close();

    }
}

class Akapit {
    public String akapit;

    public Akapit(String akapit) {
        this.akapit = akapit;
    }

    public void akapit_poczatek() {
        int i = 0;
        for (; i < this.akapit.length(); ++i) {
            char c = this.akapit.charAt(i);
            if ((c > 64 && c < 91) || (c > 96 && c < 123)) {
                break;
            }
        }
        this.akapit = this.akapit.substring(i);
    }

    public void makra_operacje() {
        for (String m : App.makra) {
            String[] makra_tab = m.split("=");
            this.akapit = this.akapit.replaceAll("\\b" + makra_tab[0] + "\\b", makra_tab[1]);
        }
    }

    public void akapit_formatowanie() {
        this.akapit = this.akapit.replaceAll("\n", " ");
        String akapit_tab[] = this.akapit.split("\\b");
        String sum = "";
        String element = akapit_tab[0];
        for (int i = 0; i < akapit_tab.length; ++i) {
            int n = i + 1;
            if (n == akapit_tab.length) {
                break;
            }
            if ((element.length() + akapit_tab[n].length()) <= App.akapit_szer) {
                if (i == akapit_tab.length - 2) {
                    sum += element + akapit_tab[n];
                    break;
                }
                element += akapit_tab[n];
            } else {
                if (i == akapit_tab.length - 2) {
                    sum += element + "\n" + akapit_tab[n];
                    break;
                }
                sum += element + "\n";
                element = akapit_tab[n];
            }
        }
        this.akapit = sum;
    }

    public String toString() {
        return this.akapit + "\n";
    }
}
