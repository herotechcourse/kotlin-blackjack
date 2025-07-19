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
        output.printDealerFirstTurnCards(dealer)
        output.printPlayersFirstTurnCards(players)

        game.otherTurnCards(players, dealer)
        output.printDealerFinalScore(dealer)
        output.printPlayersFinalScore(players)

        output.printFinalDealerResults(dealer,players)
        output.printFinalPlayerResult(players)

    }
}
