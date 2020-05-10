# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Automatisoitujen testit on souritettu sovelluslogiikkaa testaavien, eli pakkauksen [kemiantestaaja.domain](https://github.com/Deca89/ot-harjoitustyo/tree/master/kemianTestaaja/src/main/java/fi/kemiantestaaja/domain) luokkia testaavat integraatiotestit [CourseSelectionsTest](https://github.com/Deca89/ot-harjoitustyo/blob/master/kemianTestaaja/src/test/java/CourseSelectionsTest.java), [DatabaseTest](https://github.com/Deca89/ot-harjoitustyo/blob/master/kemianTestaaja/src/test/java/DatabaseTest.java) ja [ExamTest](https://github.com/Deca89/ot-harjoitustyo/blob/master/kemianTestaaja/src/test/java/ExamTest.java) joiden testitapaukset simuloivat eri käyttöliittymien avulla suoritettuja toiminnallisuuksia.

### DAO-luokat

DAO-luokkien toiminnallisuudet on suoritettu sovelluslogiikan testien yhteydessä. Testejä varten luodaan erilliset tietokannat, joihin ei tulisi tallettaa ylimääräistä tietoa.

### Testauskattavuus

Käyttöliittymiä lukuunottamatta sovelluksen testauksen rivikattavuus on 94% ja haarautumakattavuus 100%

![jacoco.png](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/jacoco.png)>

Testaamatta jäivät erilaiset tuntemattomien virhetilanteiden tilat (SQLException, ClassNotFoundException etc).

## Järjestelmätestaus

Järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu sekä OSX- että Linux-ympäristössä [kayttoohje.md](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla.


### Toiminnallisuudet

Kaikki [Vaatimusmäärittely.md](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/Vaatimusmäärittely.md) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Toiminnallisuuksien yhteydessä on kokeiltu myös syöttää virheellisiä arvoja (kirjaimia numeroiden kohdalla, tyhjät arvot etc)

## Sovellukseen jääneet laatuongelmat

Sovellus ei anna tällä hetkellä järkeviä virheilmoituksia, seuraavissa tilanteissa
- Ohjelmalla avattava tietokantatiedosto ei ole ohjelman standardien mukainen.