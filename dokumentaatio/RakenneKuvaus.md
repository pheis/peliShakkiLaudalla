Käyttöliittymä on yhteydessä ohjelmalogiikkaan Game-olion kautta. Käyttöliittymällä voi olla kerralla hallussaan yksi game-olio. Käyttöliittymä generoi Move-olioita, joilla se komentaa Game oliota. Move-olio sisältää tiedon siirrosta, joka halutaan tehdä.

Game olio sisältää yhden AI-olion, yhden MoveValidator-olion ja kaksi PawnPromoChooser oliota. AI olio arpoo satunnaisia laillisia peliliikkeitä. Se on yhteydessä MoveValidator olioon, joka sisältää nykyisen pelitilanteen ja pelihistorian. AI olio on yhteydessä yhteen PawnPromoChooser olioon, MoveValidator puolestaan kahteen. PawnPromoChooser-olioita käytetään valitsemaan sotilaiden korotuskohde. Ne ovat erillään muusta toteutuksesta, koska ne ovat muuttuvia olioita, joihin käyttäjä voi vaikuttaa niiden luomisen jälkeen.


MoveValidator olio sisältää pelihistorian listana pelitilanteita. Pelitilanteet on talletettu Board olioihin. MoveValitor tietää kaiken siirtojen laillisuudesta ja nykyisestä pelitilanteesta Board-olioiden avulla.

Board-oliot sisältävät kaksi PieceGrop oliota. PieceGroup oliot ovat käytännössä valkoisen pelaajan nappulat ja mustan pelaaajan nappulat. Board oliot osaavat generoida itsensä perusteella lisää laillisia shakkitilanteita.

PieceGroup oliot sisältävät tiedot nappuloiden sijainnista ja viitteet olioihin, jotka osaavat kertoa nappuloiden sijainnin perusteella mihin niitä saa liikutella. Nappulaoliot eivät sisällä mitään dataa. Kaikki data on PieceGroup olioissa. PieceGroup olioista on myös viitteet PawnPromoChooser-olioihin, joita tarvittiin sotilaiden korotukseen.

Shakin erikoisliikkeet tarvitsevat tietoa kaikkien nappuloiden sijainnista. Tämän vuoksi olen keskittänyt nappuloiden sijaintidatan PieceGroup-olioon. Samoin datan keskittäminen helpotti datan kloonausta, kun uusia pelitilanteita luodaan.

Nappulaoliot toteuttavat erilaisia rajapintoja, joiden avulla ne jakavat suuren osan käyttämästään koodista. Kaikki nappulat osaavat liikkua ja hyökätä. Hyökkäämismetodia käytetään tarkistamaan onko kuningas uhattuna.

Tämän shakkitoteutuksen rakenteen pohjana on Square-Enum. Square on tietotyyppi, joka kuvaa shakkilaudan ruutuja ja niiden välisiä operaatioita. Shakkilaudan olisi myös voinut toteuttaa kaksiulotteisena taulukkona kokonaislukuja. Enumtyyppitoteutus vähentää vähentää bugeja. 



