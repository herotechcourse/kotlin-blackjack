package blackjack

object BlackjackController {
    fun run() {
        val game = GameLogic()
        val deck = Deck.generate()
        val dealer = Dealer(deck)

        val playersNames = listOf("Lili", "Jon")
        val players = game.namesToPlayers(playersNames)
        game.firstTurnCards(players, dealer)

    }
}