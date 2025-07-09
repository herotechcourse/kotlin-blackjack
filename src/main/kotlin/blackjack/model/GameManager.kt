package blackjack.model

class GameManager(private val dealer: Player, private val players: List<Player>) {
    private val cardDeck = CardDeck()

    init {
        setUp()
    }

    fun setUp() {
        players.forEach { cardDeck.hit(it, 2) }
        cardDeck.hit(dealer)
    }

    fun finalResult(
        players: List<Player>,
        goToLoop: () -> Boolean,
    ): List<PlayerResult> {
        return players.map {
            if (it === dealer) {
                singlePlayerResult(it, { true })
            } else {
                singlePlayerResult(it, goToLoop)
            }
        }.toList()
    }

    fun singlePlayerResult(
        player: Player,
        goToLoop: () -> Boolean,
    ): PlayerResult {
        while (goToLoop() && ableToReceive(player)) {
            cardDeck.hit(player)
//            val currentResult = PlayerResult(player)
//            println("${currentResult.name} ${ currentResult.cards } - ${currentResult.points}")
            // OutputView.printPlayerResult(PlayerResult(player))
        }
        return PlayerResult(player)
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
