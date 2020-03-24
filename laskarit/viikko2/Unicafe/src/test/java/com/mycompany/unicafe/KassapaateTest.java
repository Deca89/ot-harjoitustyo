/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juuri
 */
public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(10);
    }
    
 @Test
 public void uudenKassanKassaOikein() {
     assertTrue(100000 == kassa.kassassaRahaa());
 }
 
 @Test
 public void uudenKassanMyynnitOikein() {
     assertTrue(0 == kassa.maukkaitaLounaitaMyyty()+kassa.edullisiaLounaitaMyyty());
 }
 
 @Test
 public void maksuRiittavaMaukasVaihtorahatOikein() {
     assertTrue(100 == kassa.syoMaukkaasti(500));
 }
 
 @Test
 public void maksuRiittavaEdullinenVaihtorahatOikein() {
     assertTrue(260 == kassa.syoEdullisesti(500));
 }
 
 @Test
 public void maksuRiittavaMaukasMyyty() {
     kassa.syoMaukkaasti(500);
     assertTrue(kassa.maukkaitaLounaitaMyyty()==1);
 }
    
 @Test
 public void maksuRiittavaEdullinenMyyty() {
     kassa.syoEdullisesti(500);
     assertTrue(kassa.edullisiaLounaitaMyyty()==1);
 } 
 
 @Test
 public void maksuRiittamatonMaukasEiMyyty() {
     int palaute = kassa.syoMaukkaasti(50) + kassa.maukkaitaLounaitaMyyty() + kassa.kassassaRahaa();
     assertTrue(palaute == 100050);
 }
 
 @Test
 public void maksuRiittamatonEdullinenEiMyyty() {
     int palaute = kassa.syoEdullisesti(50) + kassa.edullisiaLounaitaMyyty() + kassa.kassassaRahaa();
     assertTrue(palaute == 100050);
 } 
 
 @Test
 public void maksuRiittavaMaukasKorttiostoOnnistuu() {
     kortti.lataaRahaa(500);
     assertTrue(kassa.syoMaukkaasti(kortti));
 }
 
 @Test 
 public void maksuRiittavaMaukasKortiltaVeloitetaan() {
     kortti.lataaRahaa(500);
     kassa.syoMaukkaasti(kortti);
     assertTrue(kortti.saldo()==110);
 }
 
 @Test
 public void maksuRiittavaLounasMyydaan() {
     kortti.lataaRahaa(500);
     kassa.syoMaukkaasti(kortti);
     assertTrue(kassa.maukkaitaLounaitaMyyty()==1);
 }
 
 @Test
 public void maukasOstettuKortillaKassanRahatSailyySamana() {
     kortti.lataaRahaa(500);
     kassa.syoMaukkaasti(kortti);
     assertTrue(kassa.kassassaRahaa()==100000);
 }
 
 @Test
 public void maksuRiittamatonMaukasKorttiostoEiOnnistu() {
     assertTrue(!kassa.syoMaukkaasti(kortti));
 }
 
 @Test 
 public void maksuRiittamatonMaukasKortiltaEiVeloiteta() {
     kassa.syoMaukkaasti(kortti);
     assertTrue(kortti.saldo()==10);
 }
 
 @Test
 public void maksuRiittamatonLounasEiMyyda() {
     kassa.syoMaukkaasti(kortti);
     assertTrue(kassa.maukkaitaLounaitaMyyty()==0);
 }
 
 @Test
 public void maksuRiittavaEdullinenKorttiostoOnnistuu() {
     kortti.lataaRahaa(500);
     assertTrue(kassa.syoEdullisesti(kortti));
 }
 
 @Test 
 public void maksuRiittavaEdullinenKortiltaVeloitetaan() {
     kortti.lataaRahaa(500);
     kassa.syoEdullisesti(kortti);
     assertTrue(kortti.saldo()==270);
 }
 
 @Test
 public void maksuRiittavaEdullinenLounasMyydaan() {
     kortti.lataaRahaa(500);
     kassa.syoEdullisesti(kortti);
     assertTrue(kassa.edullisiaLounaitaMyyty()==1);
 }
 
 @Test
 public void edullinenOstettuKortillaKassanRahatSailyySamana() {
     kortti.lataaRahaa(500);
     kassa.syoEdullisesti(kortti);
     assertTrue(kassa.kassassaRahaa()==100000);
 }
 
 @Test
 public void maksuRiittamatonEdullinenKorttiostoEiOnnistu() {
     assertTrue(!kassa.syoEdullisesti(kortti));
 }
 
 @Test 
 public void maksuRiittamatonEdullinenKortiltaEiVeloiteta() {
     kassa.syoEdullisesti(kortti);
     assertTrue(kortti.saldo()==10);
 }
 
 @Test
 public void maksuRiittamatonEdullinenLounasEiMyyda() {
     kassa.syoEdullisesti(kortti);
     assertTrue(kassa.edullisiaLounaitaMyyty()==0);
 }
 
 @Test
 public void korttiaLadatessaSaldoMuuttuu() {
     kassa.lataaRahaaKortille(kortti, 100);
     assertTrue(kortti.saldo()==110);
 }
 
 @Test
 public void korttiaLadatessaKassaKasvaa() {
     kassa.lataaRahaaKortille(kortti, 100);
     assertTrue(kassa.kassassaRahaa()==100100);
 }
 
 @Test
 public void korttiaNegatiivisestiLadatessaEiMuutoksia() {
     kassa.lataaRahaaKortille(kortti, -10);
     assertTrue(kassa.kassassaRahaa()+kortti.saldo()==100010);
 }
}
