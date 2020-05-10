# Käyttöohje

Lataa tiedosto [kemianTestaaja-1.0-SNAPSHOT.jar](https://github.com/Deca89/ot-harjoitustyo/releases/tag/viikko6)

## Käyttövaatimukset/oletukset

Ohjelman olettaa, ettei sen käynnistyshakemistossa ole ohjelmaan kuulumattomia tietokantatiedostoja. Tai ettei niitä ainakaan yritetä avata ohjelmalla.

Ohjelman testi olettaa, ettei testejä varten tehtyjä tiedostoja käytetä muuhun.

Testienajo olettaa, että tiedosto test111.db löytyy. Mikäli tiedostoa ei löydy, epäonnistuu tiedostonpoisto testi ensimmäisellä testien ajokerralla. Toisella/seuraavilla ei väliä vaikka tiedostoa ei alunperin olisi ollut.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -kemianTestaaja-1.0-SNAPSHOT.jar
Tai avaamalla tiedosto
```

## Hallintavalikko

Ohjelma avautuu hallintavalikkoon näkymään


![hallintavalikko.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/hallintavalikko.png)

Klikkaamalla Palaa valikkoon- nappia päästään aina takaisin valikkoon.

----------------------------

Tästä voidaan luoda uusi kurssi/tietokanta klikkaamalla Luo kurssi- nappia.

![luokurssi.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/luokurssi.png)

Valikosta kirjoitetaan tekstiruutuun kurssin nimi ja klikataan Luo kurssi- nappia.

-----------------------------

Suoritettava kurssi voidaan valita klikkaamalla Valitse olemassa oleva kurssi- nappia.

![valitsekurssi.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/valitsekurssi.png)

Kurssi valitaan klikkaamalla nappia, jossa on kurssin nimi.

-------------------------------

Kurssi voidaan poistaa klikkaamalla Poista kurssi- nappia.

![poistakurssi.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/poistakurssi.png)

Kurssi poistetaan klikkaamalla nappia, jossa on poistettavan kurssin nimi.

## Kurssivalikko

Kurssin valitsemisen jälkeen avautuu uusi ikkuna/kurssivalikko.

![kurssivalikko.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kurssivalikko.png)>

Kurssivalikon valikkoon päästää jälleen aina takaisin klikkaamalla Palaa valikkoon- nappia.

------------------------------

Tietokantaan voidaan lisätä termi/selitys klikkaamalla Lisää termi/selitys- nappia 

![lisaatermiselitys.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/lisaatermiselitys.png)>

Tietokantaan lisätään termi syöttämällä ylempään tekstiruutuun termi, alempaan tekstiruutuun selitys ja sitten painamalla Lisää termi-nappia.
Napin painalluksen jälkeen napin alapuolelle tulostuu viesti, jossa kerrotaan onnistuiko termin lisäys. Huom! Sekä termin, että syötteen tulee aina olla uniikki.

-------------------------------

Tietokannasta voidaan poistaa termi/selitys klikkaamalla Poista termi/selitys- nappia

![poistatermiselitys.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/poistatermiselitys.png)>

Tietokannasta poistetaan termi painamalla termin nimen sisältämää nappia.

-------------------------------

Tietokannan termejä voidaan selata klikkaamalla Tarkastele termejä- nappia

![tarkasteletermeja.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/tarkasteletermeja.png)>

Ruutuun tulostuu lista kaikista tietokannan termeistä ja niiden selityksistä.

-------------------------------

Klikkaamalla Tee testi- nappia avataan uusi ikkuna, jossa testi toiminta.

## Testivalikko

Tee testi- napin painamisen jälkeen avautuu uusi ikkuna/testivalikko.

![tenttivalikko.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/tenttivalikko.png)>

Syöttämällä tekstiruutuun luku yhden ja max tekstiruudun oikealla puolella olevan luvun väliltä voidaan tehdä testi klikkaamalla Luo testi- nappia. Tenttivalikkoon päästään aina takaisin painamalla Palaa tentin alustukseen- nappia

-------------------------------

![tentti.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/tentti.png)>

Luoto testi suoritetaan syöttämällä kunkin rivin tekstiruutuun numero, joka vastaa selityksen termiä. Esimerkki näkymässä oikeat syötteet ylhäältä alas olisivat: 1, 3, 2.
Klikkaamalla Tarkista koe- nappia tulostuu tentti tulos.

![tulos.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/tulos.png)>


