# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla on tarkoitus antaa mahdollisuus harjoitella kemian taitoja (tai yleisemmin minkä tahansa asian, jonka sisällön haluaa syöttää sovellukseen). Sovellus antaa mahdollisuuden lisätä termistö/selitys pareja, suorittaa testejä lisätyistä asioita ja lukea siihen lisättyä materiaalia.


## Käyttäjät

Koska sovellus toimii erikseen koneelle ladattavalla versiolla, ei sovellukseen ole luotu erillisiä käyttäjiä. Sovelluksen latauksen jälkeen, luo sovellus ladatulle koneelle tietokantoja. Tietokannat sisältävät lisätyt kurssimateriaalit.
Kurssimateriaalien jako onnistuu tietokantatiedostojen jakamisella.

## Käyttöliittymäluonnos

Sovellus koostuu kolmesta avautuvasta ikkunasta.
Ohjelman käynnistyksen myötä avautuu UIDatabaseChooserGraphic:

![UIDatabaseChooserGraphic.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/UIDatabaseChooserGraphic.png)

Ikkunassa vaihtuu nappien myötä keskellä ikkunaa olevan ScrollPane:in sisältö. Aluksi siinä näkyy valikko. Palaa valikko- nappi palauttaa näkymän aina valikkoon.

Luo kurssi- nappi vaihtaa sisällöksi ruudun, jossa syöttämällä Tekstiruutu1:een jotakin tekstiä, voidaan luoda uusi tiedosto/kurssi painamalla Luo kurssi- nappia.

Valitse olemassa oleva kurssi listaa nappeina kaikki olemassa olevat kurssit. Kurssia painamalla avataan UserInterfaceGraphic ikkuna, joka on liitetty valittuun kurssiin.

Poista kurssi- nappia painamalla siirrytään näkymään, jossa on kaikki kurssit listattuna nappeina. Nappeja painamalla poistetaan kyseinen tiedosto/kurssi.

UserInterfaceGraphic:

![UserInterfaceGraphic.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/UserInterfaceGraphic.png)

Ikkunan rakenne sama: nappien myötä keskellä ikkunaa olevan ScrollPane:in sisältö muuttuu. Aluksi siinä näkyy valikko. Palaa valikko- nappi palauttaa näkymän aina valikkoon.

Lisää termi/selitys- nappi vaihtaa sisällöksi ruudun, jossa syöttämällä termin Tekstikenttä1:een ja selityksen Tekstikenttä2:een voidaan lisätä tietokantaan uusi termi painamalla Lisää termi- nappia.

Poista termi/selitys- nappi vaihtaa sisällöksi ruudun, jossa on listattuna kaikki tietokannan termit. Tietokannasta voidaan poistaa termi painamalla termin nimen sisältämää nappia.

Tarkastele termejä- nappi vaihtaa sisällöksi ruudun, jossa on listattuna kaikki tietokannan termit ja selitykset.

Tee testi- nappia painamalla avataan uuteen ikkunaan UITakeExam, joka on liitetty käytävissä olevaan kurssiin.

![UITakeExam.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/UITakeExam.png)

Ikkunan rakenne on jälleen sama: nappien myötä keskellä ikkunaa olevan ScrollPane:in sisältö muuttuu. Aluksi siinä näkyy testin alustus. Palaa tentin alustukseen- nappi palauttaa näkymän aina alustukseen.

Ensimmäisessä ikkunassa tulee ensin Tekstiruutu1:een syöttää haluttujen kysymysten/termien määrä. Mikäli syöte ei ole välillä 1-termien määrä tietokannassa tulostuu ruudulle ongelmaviesti asiaan liittyen. Mikäli syöte on sopiva vaihtuu Luo testi- nappia painamalla sisällöksi ruutu, jossa on tehtävä testi.

Testi: ruudulle tulostuu näkymä, jossa on aina horisontaalisesti termi, tyhjä tekstiruutu, selitys.
Koe tehdään syöttämällä tyhjään tekstiruutuun numero, joka vastaa vieressä olevan selityksen termiä.
Painamalla Tarkista koe- nappia tulostuu ruudulle tehdyn kokeen tulos.

## Toiminnallisuus

- käyttäjä voi luoda/poistaa eri kursseja.

- käyttäjä voi lisätä termi/selitys pareja tehtyihin kursseihin/tietokantoihin

- käyttäjä voi lukea lisättyä materiaalia. 

- käyttäjä voi suorittaa lisätystä materiaalista luotuja testejä.

## Jatkokehitysideoita

- lisätä kuvien lisäysmahdollisuus
- lisättyjen termien selitysten muokkausvaihtoehto
- tulosten talletusmahdollisuus
- saatujen tulosten tarkastelumahdollisuus