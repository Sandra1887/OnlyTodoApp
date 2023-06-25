# Documentation 
Sandra Jeppsson Kristiansson - *2023-06-25*

### Projektbeskrivning:

- Skapa en fungerande TODO applikation med CRUD-funktioner *(Create, Read, Update, Delete)*.
- Testa applikationen med JUnit och Mockito
- Vem som helst skall kunna använda och testa applikationen

### Vad jag har gjort:
- Jag har enligt instruktionerna ovan skapat ett program där användaren startar programmet och blir ombedd att fylla i 
databasens namn. Därefter får användaren en meny med fem olika val där fyra står för varsin bokstav i "CRUD". Vilken man
än väljer så börjar dessa med att be användaren om "Table Name". Detta för att ha möjligheten att skapa olika tabeller
med olika sorters TODO - till exempel *Midsommar, Packa, Städa* etc. 
- __Create__: När man anger *Table Name* så kollar denna metoden först om tabellen redan finns - gör den det så går den 
vidare till att be om "assignment", "assignee" och "done" och en ny TODO skapas. Finns inte tabellen så skapar den 
istället en ny sådan och man blir sedan ombedd att fylla i all TODO-information.
- __Read__: Efter att man angett *Table Name* frågar programmet användaren om man vill läsa en TODO - där man isåfall fyller
i "id" för TODO:n och TODO med matchande ID visas - eller om man vill läsa alla TODOs.Vid sist nämnda får man en lista 
med alla TODOs i tabellen med information om ID, Assignment, Assignee och Done.
- __Update__: Efter att man angett *Table Name* frågar programmet om man vill uppdatera assignment eller uppdatera done. 
Båda startar med att visa en lista på de TODOs som finns i tabellen och man får sedan välja ID för TODO:n som man vill 
uppdatera och därefter fylla i antingen "assignment" eller "done".
- __Delete__: Efter att man angett *Table Name* visar metoden alla TODOs som finns i tabellen. Därefter blir man ombedd 
att skriva in ID för att sedan ta bort matchande TODO.
- __Exit__: När man är känner sig färdig med programmet väljer man denna.

- __iCrud__: Ett gränssnitt(*interface*) med CRUD-funktionalitet. Där Create() och Delete() har boolean som returntyper
  (som i detta fall är anpassade efter testerna) och Read() och Update() är satta till void.

### Planering 
#### Lösningsförslag:
- Planering
  - Lösningsförslag innan uppgiften påbörjades, t.ex:
    - Skisser
    - Hur du tänker lösa uppgiften
    - Pseudokod
    - Diagram
  - Jira/trello och projekthantering enligt Scrum/KanBan

### Arbetet och dess genomförande:
#### Vad som varit svårt:
- Något som varit riktigt svårt är att få Mockito att fungera korrekt och trots över två timmar med Marcus på Code With 
Me så kunde vi inte hitta en lösning. Lämnad vind för våg så valde jag att börja om helt med kod och testning och i viss 
mån ta hjälp av chatGPT.
- Jag har testat mig fram med lite olika gränssnitt då planen från början var att försöka hinna göra ett fullt program 
med User och UserFacade med eftersom att jag fick börja om så fanns inte tiden till detta. I Facaden(/Handler) hade jag 
tänkt implementera iCrud och även "iTable" som innehåller metoderna för att skapa och söka efter en tabell. Utöver det
tänkte jag även skapa en *Database Super Class* med metoderna "getConnection" och "setConnection" som hade gjort att jag 
i både DBHandler och UserHandler kunnat "extenda" denna och dess anslutning till databasen.
- I min första kodversion så mockade(@Mock) jag alla återanvändbara variabler i testerna, t.ex. "tableName", 
"mockAssignment", "mockAssignee" etc. men Mockito gillade inte detta och jag valde därför, efter chatGPTs 
rekommendation, att hårdkoda testerna.

  - Vad som varit svårt
  - Beskriv lite olika lösningar du gjort
  - Beskriv något som var svårt att få till
  - Beskriv om du fått byta lösning och isf varför

### Reflektioner och slutsatser
##### Vad har gått bra:
- Jag tycker ändå att jag haft många bra idéer längs vägen(se ovan) som jag gärna hade haft tid att implementera i 
applikationen. 
##### Vad gick dåligt:
- Mockito. För det första att i första versionen hitta alla kryphål och ha 100% mockning av beroenden - där inte ens
läraren kunde komma med en lösning på problemet. 
- Att behöva göra om och begränsa sig efter deadline.
##### Vad jag har lärt mig och vad jag hade gjort annorlunda:
- Att använda GitHub actions och vad en YML-fil är
- Att ta ett test och en metod i taget och successivt bygga på testerna.
##### Möjligheter med kurskunskaperna:
- Att veta hur man testar med JUnit och Mockito
- Veta vad det är och hur man skriver/tyder en testrapport

- Reflektioner och slutsatser
    - Vad gick bra
    - Vad gick dåligt
    - Vad har du lärt dig
    - Vad har du gjort annorlunda om du gjort om projektet
    - Vilka möjligheter ser du av kunskapen du fått under kursen
    - 