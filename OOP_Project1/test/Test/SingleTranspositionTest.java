package Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Map;
import oop_project1.SingleTransposition;
import org.junit.Test;
import static org.junit.Assert.*;


public class SingleTranspositionTest {
    
   
    @Test
    public void compareTheFirstIndexOfVocabulary() {
    //given
    String sozlukAdi="words.txt";
    String beklenen = "aalii";
    
    //when
    ArrayList<String> sozluk=SingleTransposition.sozluguAktar(sozlukAdi);
    
    //then
    assertEquals(beklenen, sozluk.get(0));                                      //sozlugun aktarılıp aktarıladığının kontrolü
    }
    
    @Test
    public void findingExpectedErrorWord() {
    //given
    String sozlukAdi="words.txt";
    ArrayList<String> sozluk=SingleTransposition.sozluguAktar(sozlukAdi);       
    
    String metin = "aaili pefrect catch mean emel";                             //TextArea'dan çekilebilecek örnek bir metin. UnitTest gerçekleştirimi için.
    ArrayList<String> beklenenArray = new ArrayList();
    beklenenArray.add("aaili");
    beklenenArray.add("pefrect");
    beklenenArray.add("emel");
    Object[] beklenen = beklenenArray.toArray();
    
    //when
    ArrayList<String> yanlisKelimelerArray = SingleTransposition.yanlislariBul(metin, sozluk);
    Object[] yanlisKelimeler = yanlisKelimelerArray.toArray();
    
    //then
    assertArrayEquals(beklenen, yanlisKelimeler);                               //iki arrayi kıyaslayıp beklenen değerler ile algoritmanın getirdiği değerler eşit mi diye bakıyoruz.
    }
    
    @Test
    public void findingAnErrorCorrectedorNot() {
        //given
         Map<String, String> duzeltilen;                                        //yanlis kelimeyi referans alarak doğru kelimeye ulaşabiliyoruz.
         ArrayList<String> yanlisArray = new ArrayList();
        yanlisArray.add("aaili");
        yanlisArray.add("pefrect");
        yanlisArray.add("cehck");
        
        
        //when
        ArrayList<String> sozluk = SingleTransposition.sozluguAktar("words.txt");
        duzeltilen = SingleTransposition.yanlislariDuzelt(yanlisArray, sozluk);
        
        //then
        assertEquals((String)duzeltilen.get("pefrect"),"perfect");            //PEFRECT singleTransposition hatasıdır. PERFECT olup olmadığını kontrol ediyoruz.
        assertEquals((String)duzeltilen.get("cehck"),"check");    
      
    }
     
    @Test
     public void withAssertTrue_findingAnErrorCorrectedorNot() {                //Üstteki testin farklı örnekle ve assertTrue ile gerçekleştirimi
        //given
         Map<String, String> duzeltilen;                                      
         ArrayList<String> yanlisArray = new ArrayList();
        yanlisArray.add("aaili");
        yanlisArray.add("pefrect");
        yanlisArray.add("cehck");
        
        
        //when
        ArrayList<String> sozluk = SingleTransposition.sozluguAktar("words.txt");
        duzeltilen = SingleTransposition.yanlislariDuzelt(yanlisArray, sozluk);
        
        boolean check = duzeltilen.get("cehck").equals("check");                //assertTrue için tanımlanan boolean
        
        //then
        assertTrue(check);
      
    }



}
