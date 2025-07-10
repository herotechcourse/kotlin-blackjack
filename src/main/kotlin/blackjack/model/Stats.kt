package blackjack.model

data class Stats(val players: List<Player>, val dealer: Dealer) {
    // playerBoard["player"] => 0 -> Lose, 1 -> Win, 2 -> Tie
    private var _playerBoard = initPlayerBoard()
    val playerBoard: Map<Player, Int> get() = _playerBoard

    // [0] -> Lose, [1] -> Win, [2] -> Tie
    private var _dealerStats = mapOf("win" to 0, "lose" to 0, "tie" to 0)
    val dealerStats: Map<String, Int> get() = _dealerStats

    val dealerScore: Int get() = dealer.calculateHand()

    // create and init 'playerBoard'
    private fun initPlayerBoard(): Map<Player, Int> {
        val board = mutableMapOf<Player, Int>()
        players.forEach { recordPlayerBoard(it, board) }
        return board.toMap()
    }

    private fun recordPlayerBoard(
        player: Player,
        board: MutableMap<Player, Int>,
    ) {
        val playerScore = player.calculateHand()
        when {
            player.isBust() -> board[player] = 0
            dealer.isBust() -> board[player] = 1
            playerScore > dealerScore -> board[player] = 1
            playerScore < dealerScore -> board[player] = 0
            else -> board[player] = 2
        }
    }

    fun updateDealerStats() {
        val stats = _dealerStats.toMutableMap()
        val winCount = playerBoard.values.count { it == 0 }
        val loseCount = playerBoard.values.count { it == 1 }
        val tieCount = playerBoard.values.count { it == 2 }
        stats["win"] = winCount
        stats["lose"] = loseCount
        stats["tie"] = tieCount
        _dealerStats = stats.toMap()
    }
}
