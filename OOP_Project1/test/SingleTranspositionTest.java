/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import oop_project1.SingleTransposition;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SingleTranspositionTest {
    
    public SingleTranspositionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   
    @Test
    public void compareTheFirstIndexOfVocabulary() {
    //given
    String sozlukAdi="words.txt.txt";
    String beklenen = "aalii";
    
    //when
    ArrayList<String> sozluk=SingleTransposition.sozluguAktar(sozlukAdi);
    
    //then
    assertEquals(beklenen, sozluk.get(0));    
    }
    
    @Test
    public void findingExpectedErrorWord() {
    //given
    String sozlukAdi="words.txt.txt";
    ArrayList<String> sozluk=SingleTransposition.sozluguAktar(sozlukAdi);
    
    String metin = "aaili pefrect catch mean emel";
    ArrayList<String> beklenenArray = new ArrayList();
    beklenenArray.add("aaili");
    beklenenArray.add("pefrect");
    beklenenArray.add("emel");
    Object[] beklenen = beklenenArray.toArray();
    
    //when
    ArrayList<String> yanlisKelimelerArray = SingleTransposition.yanlislariBul(metin, sozluk);
    Object[] yanlisKelimeler = yanlisKelimelerArray.toArray();
    
    //then
    assertArrayEquals(beklenen, yanlisKelimeler);    
    }
    
    
    
}
