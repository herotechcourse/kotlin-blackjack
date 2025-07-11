package blackjack.model

class Stats(val players: List<Player>, val dealer: Dealer) {
    val playerBoard: Map<Player, Result> get() = calculatePlayerBoard()

    private var _dealerStats = emptyMap<Result, Int>()
    val dealerStats: Map<Result, Int> get() = _dealerStats

    val dealerScore: Int get() = dealer.calculateHand()

    init {
        _dealerStats = mapOf(Result.WIN to 0, Result.LOSE to 0, Result.TIE to 0)
    }

    private fun calculatePlayerBoard(): Map<Player, Result> {
        val board = mutableMapOf<Player, Result>()
        players.forEach { recordPlayerBoard(it, board) }
        return board.toMap()
    }

    private fun recordPlayerBoard(
        player: Player,
        board: MutableMap<Player, Result>,
    ) {
        val playerScore = player.calculateHand()
        when {
            player.isBust() -> board[player] = Result.LOSE
            dealer.isBust() -> board[player] = Result.WIN
            playerScore > dealerScore -> board[player] = Result.WIN
            playerScore < dealerScore -> board[player] = Result.LOSE
            else -> board[player] = Result.TIE
        }
    }

    fun updateDealerStats() {
        val stats = _dealerStats.toMutableMap()
        val winCount = playerBoard.values.count { it == Result.LOSE }
        val loseCount = playerBoard.values.count { it == Result.WIN }
        val tieCount = playerBoard.values.count { it == Result.TIE }
        stats[Result.WIN] = winCount
        stats[Result.LOSE] = loseCount
        stats[Result.TIE] = tieCount
        _dealerStats = stats.toMap()
    }
}
