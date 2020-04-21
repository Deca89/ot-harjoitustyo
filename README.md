# KemianTestaaja

Vielä keskeneräistä mitä kaikkia testausvälineitä halutaan käyttöön - johtuen osittain mielekkyydestä ja osittain teknillisistä syistä (kuinka monta if- lauseketta halutaan lisätä projektiin). Mahdollisia ratkaisuja olisi 
1. antaa ainoastaan ns pääkäyttäjän lisätä dataa. 
2. määritellä täsmällinen muoto missä jokaisen pitää lisätä dataa ja valvoa, että lisätty data on kyseisessä muodossa

Molemmissa tapauksissa testaus olisi hieman eri muodossa.

-> Päädytty yleistämään rakennetta kokonaisuudessaan. Mahdollistetaan useampien kokonaisuuksien (kurssiem tms) lisäyksen erillisinä tiedostoina projektiin. Alussa valitaan minkä kurssin parissa suoritetaan toimenpiteitä.

Selvitetty miten yhdistää sqlite javan Maveniin - muutaman mutkan kautta.
Tällä hetkellä projekti sisältää toiminnallisuudet termien/selitysten lisäykseen&poistamiseen, sekä niiden tarkasteluun. Lisäksi olemassa yksinkertainen testi termi/selitysten yhdistykseen. Ennen graaffisen käyttöliittymän käyttöönottoa ovat nämä hieman hankalan oloisia.

Tärkeimpänä asiana nyt tehdä graafinen käyttöliittymä: mahdollistaisi helpomman kuvien lisäyksen ja mielekkyyttä materiaalin läpikäymiseen ja testien tekoon.

Tämän lisäksi vielä lisättävää: 
graafisen tietokannan lisääminen
kirjautumisfunktio
testien tulosten talletus ja niiden tarkastelu

## Dokumentaatio

[Työaikakirjanpito.md](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/Työaikakirjanpito.md)

[Vaatimusmäärittely.md](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/Vaatimusmäärittely.md)

[arkkitehtuuri.md](https://github.com/Deca89/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)
