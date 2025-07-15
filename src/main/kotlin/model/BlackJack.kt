package model

class BlackJack(names: List<String>) {
    val players: List<Player> = names.map { Player(it) }
    val dealer = Dealer()
    val deck = Deck()

    init {
        require(names.size <= 6) { "Maximum player names must be 6" }
        initGame()
    }

    private fun initGame() {
        players.forEach { player ->
            repeat(2) {
                player.drawCard(deck.pop())
            }
        }
        repeat(2) {
            dealer.drawCard(deck.pop())
        }
    }

    fun dealerTurn(
        deck: Deck,
        doAfter: (String) -> Unit,
        decision: () -> Boolean = { dealer.getScore() <= 16 },
    ) {
        dealer.turn(
            deck,
            doAfter,
            decision,
        )
    }

    fun playerTurn(
        player: Player,
        doAfter: (String) -> Unit,
        decision: () -> Boolean = { true },
    ) {
        player.turn(
            deck,
            doAfter,
            decision,
        )
    }

    fun result(): List<ResultStatus> {
        return ResultCalculator.getResult(
            players.map { it.getScore() },
            dealer.getScore(),
        )
    }
}
