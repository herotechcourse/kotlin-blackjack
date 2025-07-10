package blackjack.model

class GameManager(private val dealer: Player, private val players: List<Player>) {
    private val cardDeck = CardDeck()

    fun setUp() {
        players.forEach { cardDeck.hit(it, 2) }
        cardDeck.hit(dealer)
    }

    fun playGame(
        players: List<Player>,
        goToLoop: () -> Boolean,
    ) {
        players.forEach {
            if (it === dealer) {
                single(it, { true })
            } else {
                single(it, goToLoop)
            }
        }
    }

    fun single(
        player: Player,
        goToLoop: () -> Boolean,
    ) {
        while (goToLoop() && ableToReceive(player)) {
            cardDeck.hit(player)
        }
    }

    private fun ableToReceive(player: Player): Boolean {
        val isDealer = player === dealer
        if (isDealer) return dealer.calculatePoints() <= ABLE_TO_RECEIVE
        return player.calculatePoints() <= BLACKJACK
    }

    companion object {
        private const val ABLE_TO_RECEIVE = 16
        private const val BLACKJACK = 21
    }
}
