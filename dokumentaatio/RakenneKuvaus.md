AI luokka arpoo satunnaisia sallittuja liikkeita. Tiedot sallistuista liikkeista se saa MoveValidator luokalta.

MoveValidator luokka on säiliö Board olioista. 

Board luokan oliot ovat pelitilanteita shakissa. Jokaisen shakki siirron jälkeen luodaan uusi Board olio. Board oli sisältää sekä valkoiset, että mustat nappulat. Board olio sisältää kaksi PieceGroup oliota. Toisessa PieceGroup oliossa on mustat ja toisessa valkoiset nappulat.

PieceGroup olio sisältää EnumMapin Ruuduista Nappula Enumiin ja Toisen EnumMapin Nappula Enumeista nappula olioihin. Nappula oliot tietvät miten ne saavat liikkua. Ne eivät tiedä missä ne sijaitsevat. Piece groupissa oleva EnumMap tietää nappuloiden sijainnit.

PawnPromoChooser luokkaa käytetään valitsemaan se upseeri, joksi sotilas halutaan korottaa.
