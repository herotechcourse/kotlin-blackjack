package blackjack

object BlackjackController {
    fun run() {
        val game = GameLogic()
        val deck = Deck.generate()
        val dealer = Dealer(deck)

        val playersNames = listOf("Lili", "Jon")
        val players = game.namesToPlayers(playersNames)
        game.firstTurnCards(players, dealer)
        // display first hand of each participant

        // check hand status

        game.otherTurnCards(players, dealer)
        // display second hand of each participant

        // check hand status


//        if (gameBlackjackCheck == true) {
//            // print results
//
//        } else {
//            game.shouldHit(players, dealer)
//            // remove busted players
//            // ask for hit or stay
//        }

    }
}