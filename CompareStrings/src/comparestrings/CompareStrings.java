/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparestrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mehmet Anıl TAYSİ
 */
public class CompareStrings {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String word1 = "full-text";
        String words[] = {"cook", "kelime", "finally", "gracefullya", "ibrahimovic", "emellllllll","full-text"};

        List<String> decreasedWords = new ArrayList<>();

        for (String word : words) {

            if (word1.equals(word)) {
                System.out.println(word1);
            } else if(word1.length()==word.length()) {
               
                
                char[] word1CA = word1.toCharArray();
                char[] wordCA = word.toCharArray();

                Arrays.sort(word1CA);
                Arrays.sort(wordCA);

                int comp = 0;

                for (int i = 0; i < wordCA.length; i++) {
                    if (wordCA[i] == word1CA[i]) {
                    } else {
                        comp++;
                    }

                }
                int iki = 0;
                if (comp == 0) {

                    word1CA = word1.toCharArray();
                    wordCA = word.toCharArray();

                    for (int i = 0; i < wordCA.length; i++) {
                        if (wordCA[i] != word1CA[i]) {
                            iki++;
                        }
                    }
                    if (iki == 2) {
                        System.out.println(word);
                    }
                }
            }

        }

    }
}
