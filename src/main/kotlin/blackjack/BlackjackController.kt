package blackjack

class BlackjackController {
    val game = GameLogic()
    val deck = Deck.generate()
    val dealer = Dealer(deck)
    val input = InputView()
    val output = OutputView()

    fun run() {
        output.printNameQuestion()
        val playersNames = input.readPlayersName()

        val players = game.namesToPlayers(playersNames)

        output.printPlayersIntroToTheirCards(players)
        game.firstTurnCards(players, dealer)
        output.printDealerFirstTurnCards(dealer)
        output.printPlayersFirstTurnCards(players)

        players.forEach { player -> playerTurn(player) }
        dealerTurn()

        output.printDealerFinalScore(dealer)
        output.printPlayersFinalScore(players)

        output.printFinalDealerResults(dealer, players)
        output.printFinalPlayerResult(players)
    }

    fun playerTurn(player: Player) {
        while (game.playerShouldHit(player)) {
            if (input.askPlayerIfShouldHit(player)) {
                game.drawCardForParticipant(player, dealer)
                output.printCurrentCardsOfOnePlayer(player)
            } else {
                break
            }
        }
    }

    fun dealerTurn() {
        while (game.dealerShouldHit(dealer)) {
            output.printDealerDrawsOneMoreCardMessage()
            game.drawCardForParticipant(dealer, dealer)
            output.printCurrentDealerCards(dealer)
        }
    }
}
