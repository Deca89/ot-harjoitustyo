# Arkkitehtuurikuvaus
-------------------------------------------
## Rakenne
-------------------------------------------

Ohjelman rakenne noudattaa kaksitasoista kerrosarkkitehtuuria. Koodin pakkausrakenne on seuraavanlainen:

![arkkitehtuuri1.jpg](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuuri1.jpg)

Pakkaus 
* fi.kemiantestaaja.ui sisältää (osittain keskeneräisen) graafisen käyttöliittymän.
* fi.kemiantestaaja.logics sisältää sovelluslogiikan

## Käyttöliittymä
-------------------------------------------

Käyttöliittymä sisältää tällä hetkellä kaksi pääikkunaa, joilla molemmilla on oma Scene-luokka:
kurssien hallintavalikon ja kurssivalikon.

### Hallintavalikko 

- sisältää neljä eri näkymää, joista jokanen on muodostettu omaan center_stage pakettiin.
    - Luo kurssi: kohdasta voidaan luoda uusi kurssi/tietokanta.
    - Valitse olemassa oleva kurssi: kohdasta avataan kurssin kurssivalikko
    - Poista kurssi: kohdasta voidaan poistaa kursseja/tietokantoja
    - Toisesta ja kolmannesta siirrytään väliruutuun, joka palauttaa käyttäjän takaisin päävalikkoon.
- Center stage:n lisäksi hallintavalikossa alareunassa "Palaa valikkoon" painike käyttäjäystävällisyyden ja turhien nappien koodaamisen välttelyn vuoksi.


### Kurssivalikko

Muotoiltu Hallintavalikon mukaisesti. Eron muodostavat ainoastaan center_stage paketit.

- sisältää neljä eri center stage pakettia.
    - Lisää termi/selitys: näkymästä mahdollisuus lisätä termi/selitys
    - Poista termi/selitys: näkymästä mahdollisuus poistaa termi/selitys
    - Tarkastele termejä: näkymästä nähdään kaikki tietokannan termi/selitys parit.
    - Tee testi: ei vielä lisätty kokonaisuus.

## Sovelluslogiikka
--------------------------------

Tähän lisää ensi viikolla. 

Yleisesti ottaen tallennus ja metodit ovat kytkettynä suoraan toisiinsa, johtuen ohjelman loogisesta rakenteesta.

Tietokannat, joihin tallennetaan tiedot sijaitsevat ohjelman juurikansiossa.


Sekvenssikuva kurssin lisäykseen (joka myönnettäköön oli aika nopeasti tehty viime viikolla)

![Sekvenssi_kl.jpg](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Sekvenssi_kl.jpg)

## Ohjelman rakenteen tunnetut heikkoudet
-------------------------------------

Ohjelma sisältää vielä ei-graafisen käyttöliittymän luokat, johtuen viikon aikana tehtävistä muutoksista. Poistetaan nämä ensi viikkoon mennessä.

Käyttöliittymä

    Hallintavalikko

    - listat kursseista (sekä poisto, että valinta näkymässä) eivät vielä päivity. Ratkaisu tähän jo tiedossa, korjataan ensi viikoksi.
    - välinäkymä jäi hieman turhaksi johtuen päivän aikana tehdyistä muutoksista. Tämä muutetaan.

    Kurssivalikko

    - testi ominaisuutta ei vielä lisätty. Tämä suoritetaan ensi viikkoon mennessä.

Testit

    - Graafisen käyttöliittymän tulon myötä nämä alkaneet kiukuttelemaan suuresti. Asiaa selvitellään.




