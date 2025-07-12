package blackjack

object BlackjackController {
    fun run() {
        val game = GameLogic()
        val deckGenerator = DeckGenerator()


        val playersNames = listOf("Lili", "Jon")
        val players = game.namesToPlayers(playersNames)

        val deck = deckGenerator.generate()
        val dealer = Dealer(deck)

    }
}