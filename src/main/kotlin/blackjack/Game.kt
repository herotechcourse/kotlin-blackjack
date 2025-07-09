package blackjack

class Game {

    fun run() {
        val playerNames = InputView.askPlayerNames()
        val players = playerNames.map { Player(it) }
        val deck = Deck()
        val dealer = Dealer(deck)

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

        val dealerScore = dealer.calculateScore(dealer)
        val dealerBusted = dealer.isBusted(dealer)

        var dealerWins = 0
        var dealerLosses = 0

        OutputView.displayFinalResultsHeader()

        players.forEach { player ->
            val playerScore = dealer.calculateScore(player)
            val playerBusted = dealer.isBusted(player)

            val result = when {
                playerBusted -> {
                    dealerWins++
                    "Lose"
                }
                dealerBusted -> {
                    dealerLosses++
                    "Win"
                }
                playerScore > dealerScore -> {
                    dealerLosses++
                    "Win"
                }
                playerScore < dealerScore -> {
                    dealerWins++
                    "Lose"
                }
                else -> {
                    dealerWins++
                    "Lose"
                }
            }

            OutputView.displayPlayerResult(player.name, result)
        }

        OutputView.displayDealerResult(dealerWins, dealerLosses)
    }
}