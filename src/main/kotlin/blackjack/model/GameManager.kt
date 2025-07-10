package blackjack.model

import blackjack.view.OutputView

class GameManager(private val dealer: Player, private val players: List<Player>) {
    private val cardDeck = CardDeck()

    fun setUp() {
        players.forEach { cardDeck.hit(it, 2) }
        cardDeck.hit(dealer)
    }

    fun playGame(
        players: List<Player>,
        askForCard: () -> Boolean,
    ) {
        players.forEach {
            if (it === dealer) {
                single(it)
            } else {
                single(it, askForCard)
            }
        }
    }

    fun single(
        player: Player,
        askForCard: () -> Boolean = { true },
    ) {
        if (player === dealer) {
            playDealer(player)
        } else {
            playPlayer(player, askForCard)
        }
    }

    fun playPlayer(
        player: Player,
        askForCard: () -> Boolean,
    ) {
        while (ableToReceive(player)) {
            OutputView.printAskForCard(player)
            if (askForCard()) {
                cardDeck.hit(player)
                OutputView.printOnePlayer(player)
            } else {
                break
            }
        }
    }

    fun playDealer(player: Player) {
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
