package model

class BlackJack(names: List<String>) {
    val players: List<Player> = names.map { Player(it) }
    val dealer = Dealer()
    val deck = Deck()

    init {
        require(names.size <= 6) { "Maximum player names must be 6" }
        initGame()
    }

    fun dealerTurn(
        deck: Deck,
        doAfter: (BasePlayer) -> Unit,
        decision: (BasePlayer) -> Boolean = { dealer.getScore() <= 16 },
    ) {
        dealer.turn(
            deck,
            doAfter,
            decision,
        )
    }

    fun playersTurn(
        doAfter: (BasePlayer) -> Unit,
        decision: (BasePlayer) -> Boolean = { true },
    ) {
        players.forEach { player ->
            player.turn(deck, doAfter, decision)
        }
    }

    fun calcEarning() {
        val dealerScore = dealer.getScore()
        players.forEach { player ->
            val ratio = ResultCalculator.ratio(player.getScore(), dealerScore)
            player.earning =
                (player.bet * ratio).toInt()
            dealer.earning += (player.bet * ratio * -1).toInt()
        }
    }

    fun setPlayersBet(doRequest: (BasePlayer) -> Int) {
        players.forEach { player ->
            apply {
                player.bet = doRequest(player)
            }
        }
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
}
