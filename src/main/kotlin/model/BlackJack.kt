package model

class BlackJack(names: List<String>) {
    val players = Players(names.map { Player(it) })
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
        players.turn(deck, doAfter, decision)
    }

    fun calcEarning() {
        val dealerScore = dealer.getScore()
        players.value.forEach { player ->
            val ratio = ResultCalculator.ratio(player.getScore(), dealerScore)
            player.earning = player.earning.calc(player.bet, ratio)
            dealer.earning += Earning(0).calc(player.bet, (ratio * -1))
        }
    }

    fun setPlayersBet(doRequest: (BasePlayer) -> Int) {
        players.setBet(doRequest)
    }

    private fun initGame() {
        players.init(deck)
        dealer.init(deck)
    }
}
