# IntegrationTestingSPE

#### Morten Schultz Laursen

Jeg har lavet et simple projekt der bruger en database til at CRUD på en customer. Jeg har tilpasset projektet til et testbart design, ved bla. at bruge DI samt ikke have nogle static og final variable til min database connection.

Jeg har lavet nogle simple integrations test som tester flere moduler i projektet og til det benytter jeg mig af en test database i stedet for en produktions databasen. En af problematikkerne ved at teste noget der benytter en database er at sikre at datasættet er ens og testene derfor har det samme grundlag før og efter hver test bliver kørt. 
For at løse denne problematik har jeg benyttet mig af DBUnit, jeg har en Sampler klasse, der inderholder en main til at generer et XML datasæt. Dette generet datasæt kan så bruger i mine test, da de er en ”kopi” af min database med data bare i XML form.
Så jeg starte med en test fixture (@BeforeEach) i junit for at sætte min database op. DB Unit har en metode hvorpå man kan sætte et frisk datasæt ind i samme tilstand, fra den generet XML fil, der blev lavet tidligere. Metode DatabaseOperation.CLEAN_INSERT.execute() med XML filen som input parametre sikre at dette sker og problematikken med test rækkefølge og kan derved undgås.
