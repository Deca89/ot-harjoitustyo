# Arkkitehtuurikuvaus
-------------------------------------------
## Rakenne
-------------------------------------------

Ohjelman rakenne noudattaa kolmitasoista kerrosarkkitehtuuria. Koodin pakkausrakenne on seuraavanlainen:

![arkkitehtuuri1.jpg](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuuri1.jpg)

Pakkaus 
* fi.kemiantestaaja.ui sisältää JavaFX:llä toteutetun graafisen käyttöliittymän.
* fi.kemiantestaaja.domain sisältää sovelluslogiikan
* fi.kemiantestaaja.dao sisältää pysyväistallennuksesta vastaavan koodin

## Käyttöliittymä
-------------------------------------------

Käyttöliittymä sisältää kolme pääikkunaa, joilla molemmilla on oma Scene-luokka:
kurssien hallintavalikon, kurssivalikon ja tenttivalikon.

Käyttöliittymä on suurilta osin eriytetty sovelluslogiikasta. Virheviestit sisältyy osittain käyttöliittymän tekemiin syötteen tarkastuksiin.

### Hallintavalikko 

- sisältää neljä eri näkymää, joista jokanen on muodostettu omaan VBox pakettiin.
    - Aloitusruutu/valikko: johtaa muihin näkymiin.
    - Luo kurssi: kohdasta voidaan luoda uusi kurssi/tietokanta.
    - Valitse olemassa oleva kurssi: kohdasta avataan kurssin kurssivalikko
    - Poista kurssi: kohdasta voidaan poistaa kursseja/tietokantoja
- Center stage:n lisäksi hallintavalikossa alareunassa "Palaa valikkoon" painike käyttäjäystävällisyyden ja turhien nappien koodaamisen välttelyn vuoksi.


### Kurssivalikko

Muotoiltu Hallintavalikon mukaisesti. Eron muodostavat ainoastaan VBox paketit.

- sisältää neljä eri VBox pakettia.
    - Aloitusruutu/valikko: johtaa muihin näkymiin.
    - Lisää termi/selitys: näkymästä mahdollisuus lisätä termi/selitys
    - Poista termi/selitys: näkymästä mahdollisuus poistaa termi/selitys
    - Tarkastele termejä: näkymästä nähdään kaikki tietokannan termi/selitys parit.
- Center stage:n lisäksi hallintavalikossa alareunassa "Palaa valikkoon" painike käyttäjäystävällisyyden ja turhien nappien koodaamisen välttelyn vuoksi.

Lisäksi aloitusruudussa Tee testi- nappi joka avaa tenttivalikon


### Tenttivalikko

Muotoiltu Hallintavalikon mukaisesti. Eron muodostavat ainoastaan VBox paketit.

- sisältää kaksi eri VBox pakettia
    - Aloitusruutu: syöttämällä haluttujen kysymysten/termien määrä ja painamalla Luo testi- nappia siirrytään tentti näkymään
    - Tenttinäkymä: Sisältää luodun tentin.



## Sovelluslogiikka
--------------------------------

Sovelluksen pakkauskuvana voidaan pitää 

![pakkauskaavio.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskaavio.png)

Yleisesti ottaen jokaiselle eri ui luokalle on olemassa erillinen sovelluslogiikka. Tallennuksesta vastaavia luokkia on kaksi, joihin päästään ui:sta käsiksi sovelluslogiikan kautta.

## Tietojen pysyväistallennus
---------------------------------

Pakkauksen fi.kemiantestaaja.dao luokat FileDAO ja DatabaseDAO huolehtivat tietojen tallettamisesta ja tallennettujen tietojen käsittelystä.

FileDAO vastaa tiedostojen käsittelystä ja DatabaseDAO vastaa tietokannan käsittelystä tiedostossa.

Sovelluslogiikasta päästään käsiksi tallennettuihin tietoihin ainoastaan dao paketin luokkien kautta.

## Tiedostot 

Sovellus tallettaa tiedostot/tietokannat FileDAO:n avulla sovelluksen juurikansioon.

Tiedostoissa tiedot tallennetaan tietokantaan, joka sisältää yhden taulukon.

Taulukko koostuu
    - identifioivasta id:stä (INTEGER PRIMARY KEY)
    - termin nimestä name (TEXT UNIQUE NOT NULL)
    - selityksen tekstistä explanation (TEXT UNIQUE NOT NULL)

## Päätoiminnallisuudet

Kuvataan muutama sovelluksen toimintalogiikka päätoiminnallisuuden osalta sekvenssikaaviona.

### Kurssin lisäys

Kun UIDatabaseChooserGraphic ikkunassa on esillä Luo kurssi- VBox, syötekenttään on kirjoitettuna kurssinimi jota ei ole vielä käytössä ja klikataan Luo kurssi- nappia etenee sovelluksen kontrolli seuraavasti:

![lisaaKurssi.jpg](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/lisaaKurssi.jpg)

Napin painamiseen reagoiva [tapahtumankäsittelijä](https://github.com/Deca89/ot-harjoitustyo/blob/master/kemianTestaaja/src/main/java/fi/kemiantestaaja/ui/UIDatabaseChooserGraphic.java#L62) kutsuu sovelluslogiikan _CourseSelections_ metodia [addCourse](https://github.com/Deca89/ot-harjoitustyo/blob/master/kemianTestaaja/src/main/java/fi/kemiantestaaja/domain/CourseSelections.java#L24) antaen parametriksi annetun syötteen.
Sovelluslogiikka lähettää käskyn eteenpäin FileDAO:lle, joka selvittää onko kurssinimi jo olemassa. Tässä tapauksessa ei ole, joten DAO saa tiedon false ja luo tiedoston. Tämän jälkeen DAO lähettää numeron 2 tiedoksi sovelluslogiikalle (tiedoston luonti onnistui), joka lähettää tiedon eteenpäin UI:lle. UI vaihtaa Label:in viestiksi "Kurssi lisätty".

### Kurssin poisto

Kun UIDatabaseChooserGraphic ikkunassa on esillä Poista kurssi- VBox ja klikataan poistettavan kurssin nappia etenee sovelluksen kontrolli seuraavasti:

![poistaKurssi.jpg](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/poistaKurssi.jpg)

Napin painamiseen reagoiva [tapahtumankäsittelijä](https://github.com/Deca89/ot-harjoitustyo/blob/master/kemianTestaaja/src/main/java/fi/kemiantestaaja/ui/UIDatabaseChooserGraphic.java#L112) kutsuu sovelluslogiikan _CourseSelections_ metodia [deleteCourse](https://github.com/Deca89/ot-harjoitustyo/blob/master/kemianTestaaja/src/main/java/fi/kemiantestaaja/domain/CourseSelections.java#L52) antaen parametriksi painetun napin tekstin.
Sovelluslogiikka lähettää käskyn eteenpäin FileDAO:lle, joka selvittää onko kurssinimi olemassa. Tässä tapauksessa on, joten DAO saa tiedon true ja poistaa tiedoston. Tämän jälkeen DAO lähettää true tiedoksi sovelluslogiikalle (tiedoston poisto onnistui), joka lähettää tiedon eteenpäin UI:lle. UI vaihtaa Label:in viestiksi "Kurssi poistettu".

### Termien ja selitysten listaus

Kun UserInterfaceGraphic ikkunassa on esillä alkuvalikko- VBox ja klikataan Tarkastele termejä- nappia etenee sovelluksen kontrolli seuraavasti:

![listaaTermitJaSelitykset.jpg](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/listaaTermitJaSelitykset.jpg)

Napin painamiseen reagoiva [tapahtumankäsittelijä](https://github.com/Deca89/ot-harjoitustyo/blob/master/kemianTestaaja/src/main/java/fi/kemiantestaaja/ui/UserInterfaceGraphic.java#L117) kutsuu sovelluslogiikan _Database_ metodia [getTermsAndExplanations](https://github.com/Deca89/ot-harjoitustyo/blob/master/kemianTestaaja/src/main/java/fi/kemiantestaaja/domain/Database.java#L7).
Sovelluslogiikka lähettää käskyn eteenpäin DatabaseDAO:lle. Tämän jälkeen DAO lähettää luodun listan termeistä ja selityksistä sovelluslogiikalle, joka lähettää tiedon eteenpäin UI:lle. UI lisää listasta syöte syötteeltä kaikki uusiksi Lable:leiksi esillä olevaan VBox:iin.

### Muut toiminnallisuudet

Yllä selitettyjen tapausten periaate toistuu muissakin toiminnalisuuksissa. Käytössä oleva käyttäjäliittymä kutsuu sovelluslogiikan metodia. Sovelluslogiikka hakee halutut tiedot DAO:sta, tai luo uudet tarvittavat (ei tallennetut/tallennettavat) tiedot.
Kontrollin palaessa käyttäjäliittymällä päivitetään aktiivinen näkymä.

## Ohjelman rakenteen tunnetut heikkoudet
-------------------------------------

### Käyttöliittymä

Jokaisessa ui:ssa koko kyseisen ikkunan struktuuri toteutettu metodissa Start. Tämä olisi voitu jakaa pienempiin osiin, mutta johtuen hieman pyörehköistä toteutuksista jakaminen osoittautui haastavaksi.

Osa mahdollisista virhelähteistä toteutettu suoraan käyttöliittymässä syötteen tarkastuksena, sen sijaan että syöte olisi käsitelty sovelluslogiikassa/dao:ssa ja sieltä tulleen palautteen myötä toimittu kokonaan. Tämä voitaisiin eriyttää kokonaan ui:sta.


