package blackjack.model

import blackjack.utils.Constants

data class Stats(val players: List<Player>, val dealer: Dealer) {
    private var _playerBoard = initPlayerBoard()
    val playerBoard: Map<Player, Int> get() = _playerBoard

    private var _dealerStats = mapOf("win" to 0, "lose" to 0, "tie" to 0)
    val dealerStats: Map<String, Int> get() = _dealerStats

    val dealerScore: Int get() = dealer.calculateHand()

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
            player.isBust() -> board[player] = Constants.LOSE
            dealer.isBust() -> board[player] = Constants.WIN
            playerScore > dealerScore -> board[player] = Constants.WIN
            playerScore < dealerScore -> board[player] = Constants.LOSE
            else -> board[player] = Constants.TIE
        }
    }

    fun updateDealerStats() {
        val stats = _dealerStats.toMutableMap()
        val winCount = playerBoard.values.count { it == Constants.LOSE }
        val loseCount = playerBoard.values.count { it == Constants.WIN }
        val tieCount = playerBoard.values.count { it == Constants.TIE }
        stats["win"] = winCount
        stats["lose"] = loseCount
        stats["tie"] = tieCount
        _dealerStats = stats.toMap()
    }
}
