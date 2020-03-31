/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kemiantestaaja;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Juuri
 */
public class Kayttoliittyma {
    private Tietokanta tietokantanen;
    
    public Kayttoliittyma() throws SQLException {
        this.tietokantanen = new Tietokanta();
    }
    
    public void kaynnista() throws SQLException {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Tervetuloa harjoitustyön maailmaan\n");
        tulostaValikko();
        
        while (true) {

        
        System.out.println("Valitse toiminto (1-2, valikko, lopeta)");
        String syote = lukija.nextLine();
        
        if (syote.equals("1")) {
            this.tietokantanen.luoTietokanta();
        } else if (syote.equals("2") && this.tietokantanen.isTietokantaOnOlemassa()) {
            System.out.print("Termi: ");
            String termi = lukija.nextLine();
            System.out.print("Selitys: ");
            String selitys = lukija.nextLine();
            this.tietokantanen.lisaaTermi(termi, selitys);
        }else if (syote.equals("valikko")) {
            tulostaValikko();
        } else if (syote.equals("lopeta")) {
            return;
        }
        
        }
    }
    
    public void tulostaValikko() {
        System.out.println("Valikko:");
        System.out.println("1: Luo tietokanta");
        System.out.println("2: Lisää termi/selitys tietokantaan.");
        System.out.println("valikko: Tulosta valikko");
    }
    
    
}

