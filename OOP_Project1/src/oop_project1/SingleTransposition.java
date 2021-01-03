package oop_project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SingleTransposition {

    public static ArrayList<String> sozluguAktar(String dosyaAdi) {             //sozlugu tekrar tekrar farklı yerlerde cagırmak gerektigi için 
                                                                                //sozluk cagırma metodu dusunuldu.
        ArrayList<String> sozluk = new ArrayList<>();
        BufferedReader bf = null;
        try {
            String currentLine;
            bf = new BufferedReader(new FileReader(dosyaAdi));
            while ((currentLine = bf.readLine()) != null) {
                sozluk.add(currentLine);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
            } catch (IOException ex) {
            }
        }
        return sozluk;
    }

    public static ArrayList<String> yanlislariBul(String metin, ArrayList<String> sozluk) {

        ArrayList<String> yanlisKelimeler = new ArrayList<>();                  //MainFormdaki TextAreaya girilecek metinin yanlis kelimelerini verilen sozluge göre bulmak.
                                                                                //Formdaki yanlısları bul tusu üzerinden yanlislar tespit edilebilir.
        String[] tokens = metin.split("[^a-zA-Z]+");

        for (String token : tokens) {
            if (!sozluk.contains(token)) {
                yanlisKelimeler.add(token.toLowerCase());
            }
        }
        return yanlisKelimeler;
    }

    public static Map<String, String> yanlislariDuzelt(ArrayList<String> yanlisKelimeler, ArrayList<String> sozluk) {

        Map<String, String> duzeltilen = new HashMap<>();                       //SingleTransposition gereğini yerine getiren metod. 
                                                                                //MainFormda yanlislariDuzelt seceneği üzerinden gerceklestirilebilir.
        for (String kelime : sozluk) {

            for (String yanlisKelime : yanlisKelimeler) {

                if (yanlisKelime.length() == kelime.length()) {

                    char[] kelimeKarakter = kelime.toCharArray();
                    char[] yanlisKelimeKarakter = yanlisKelime.toCharArray();

                    Arrays.sort(kelimeKarakter);
                    Arrays.sort(yanlisKelimeKarakter);

                    int comp = 0;

                    for (int i = 0; i < kelimeKarakter.length; i++) {
                        if (kelimeKarakter[i] == yanlisKelimeKarakter[i]) {
                        } else {
                            comp++;
                        }
                    }

                    int maksYerDegistirme = 0;
                    if (comp == 0) {

                        kelimeKarakter = kelime.toCharArray();
                        yanlisKelimeKarakter = yanlisKelime.toCharArray();

                        for (int i = 0; i < kelimeKarakter.length; i++) {
                            if (kelimeKarakter[i] != yanlisKelimeKarakter[i]) {
                                maksYerDegistirme++;
                            }
                        }
                    }

                    if (maksYerDegistirme == 2) {
                        duzeltilen.put(yanlisKelime, kelime);
                    }

                }

            }

        }
        return duzeltilen;
    }
}
