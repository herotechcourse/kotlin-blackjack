package blackjack.model

import blackjack.view.OutputView

class GameManager(private val dealer: Player, private val players: List<Player>) {
    private val cardDeck = CardDeck()

    init {
        players.forEach { cardDeck.hit(it, 2) }
        cardDeck.hit(dealer)
    }

    fun playGame(
        dealer: Player,
        players: List<Player>,
        askForCard: (Player) -> Boolean,
        printPlayerResult: (Player) -> Unit,
    ) {
        players.forEach { round(it, askForCard, printPlayerResult) }
        round(dealer, askForCard, printPlayerResult)
    }

    internal fun round(
        player: Player,
        askForCard: (Player) -> Boolean = { true },
        printPlayerResult: (Player) -> Unit,
    ) {
        when (player) {
            dealer -> roundForDealer(player)
            else -> roundForPlayers(player, askForCard, printPlayerResult)
        }
    }

    private fun roundForPlayers(
        player: Player,
        askForCard: (Player) -> Boolean,
        printPlayerResult: (Player) -> Unit,
    ) {
        while (ableToReceive(player)) {
            if (askForCard(player)) {
                cardDeck.hit(player)
                printPlayerResult(player)
            } else {
                break
            }
        }
    }

    private fun roundForDealer(player: Player) {
        while (ableToReceive(player)) {
            cardDeck.hit(player)
        }
        OutputView.printDealerDrawsCards(player)
    }

    private fun ableToReceive(player: Player): Boolean {
        val isDealer = player === dealer
        if (isDealer) return dealer.calculatePoints() <= ABLE_TO_RECEIVE
        return player.calculatePoints() < BLACKJACK
    }

    companion object {
        private const val ABLE_TO_RECEIVE = 16
        private const val BLACKJACK = 21
    }
}
