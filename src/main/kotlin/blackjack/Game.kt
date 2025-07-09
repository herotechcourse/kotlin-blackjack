package blackjack

class Game {
    fun run() {
        val playerNames = InputView.askPlayerNames()
        val players = playerNames.map { Player(it) }
        val dealer = Dealer()

        OutputView.displayDealing(playerNames)

        repeat(2) {
            dealer.giveCardTo(dealer)
            players.forEach { player ->
                dealer.giveCardTo(player)
            }
        }
        OutputView.displayDealerFirstTwoCards(dealer.getHand().take(2))
        players.forEach { OutputView.displayPlayerHand(it) }

        println()

        players.forEach { dealer.askPlayerDraw(it) }
        println()
        players.forEach { OutputView.displayPlayerHand(it) }

        dealer.playDealerTurn()

        println()
        OutputView.displayDealerStatus(dealer)
        players.forEach { OutputView.displayPlayerStatus(it, dealer) }

    }
}