package blackjack

object BlackjackController {
    fun run() {
        val game = GameLogic()
        val deck = Deck.generate()
        val dealer = Dealer(deck)
        val input = InputView()
        val output = OutputView()

        val playersNames = input.readPlayersName()
        val players = game.namesToPlayers(playersNames)
        output.printPlayersIntroToTheirCards(players)
        game.firstTurnCards(players, dealer)
        output.printFirstTurnCards(dealer)
        players.forEach { player ->
            output.printFirstTurnCards(player)
        }

        game.otherTurnCards(players, dealer)

        output.printFinalScores(dealer)
        players.forEach { player -> output.printFinalScores(player) }

        println("## Final Results ##")
        output.printFinalDealerResults(dealer,players)
        players.forEach { player ->
            output.printFinalPlayerResult(player)
        }
    }
}