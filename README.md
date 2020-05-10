# KemianTestaaja

Sovelluksen avulla voidaan luoda erillisia tietokanta tiedostoja, joihin lisätään haluttua tietoa. Lisättyjä tietoja voidaan sovelluksessa tarkastella. Lisäksi sovelluksen avulla voidaan tehdä pieniä kokeita testaamaan lisätyn tiedon tuntemusta.


## Dokumentaatio

[Työaikakirjanpito](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/Työaikakirjanpito.md)

[Vaatimusmäärittely](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/Vaatimusmäärittely.md)

[Arkkitehtuurikuvaus](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttoohje](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Testausdokumentti](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

## Releaset

[Lopullinen](https://github.com/Deca89/ot-harjoitustyo/releases/tag/loppupalautus)

## Komentorivitoiminnot

Testit voidaan joko suorittaa Netbeans:in kautta, tai komennolla (mvn test)

Testikattavuusraportti voidaan suorittaa Netbeans:issa Run Maven -> tests, tai komennolla (mvn jacoco:report)

JavaDoc voidaan suorittaa NetBeans:issa Run Maven -> javadoc, tai  komennolla (mvn javadoc:javadoc)

Tiedostoon määrittelemät tarkistukset voidaan suorittaa NetBeans:issa Run Maven -> checkstyle2, tai komennolla (mvn jxr:jxr checkstyle:checkstyle)

Saadut tulokset löytyvät tiedostosta target/site/...
