package blackjack.view

import blackjack.model.Dealer
import blackjack.model.EarningsResult
import blackjack.model.Player

data class StatsView(val players: List<Player>, val dealer: Dealer) {
    private var _playerBoard = initPlayerBoard()
    val playerBoard: Map<Player, EarningsResult> get() = _playerBoard

    private var _dealerStats = mapOf("win" to 0, "lose" to 0, "tie" to 0)
    val dealerStats: Map<String, Int> get() = _dealerStats

//    private var _earnings: Map<Playable, Int> = emptyMap()
//    val earnings: Map<Playable, Int> get() = _earnings

    init {
        updateDealerStats()
//        updateEarnings()
    }

    private fun initPlayerBoard(): Map<Player, EarningsResult> {
        val board = mutableMapOf<Player, EarningsResult>()
        players.forEach { recordPlayerBoard(it, board) }
        return board.toMap()
    }

    private fun recordPlayerBoard(
        player: Player,
        board: MutableMap<Player, EarningsResult>,
    ) {
        val playerScore = player.calculateHand()
        val dealerScore = dealer.calculateHand()
        val playerHasBlackJack = player.hasBlackJack()
        val dealerHasBlackJack = dealer.hasBlackJack()
        when {
            playerHasBlackJack && !dealerHasBlackJack -> board[player] = EarningsResult.WIN_BLACK_JACK_BET
            dealerHasBlackJack && !playerHasBlackJack -> board[player] = EarningsResult.LOSE_BET
            player.isBust() -> board[player] = EarningsResult.LOSE_BET
            dealer.isBust() -> board[player] = EarningsResult.WIN_BET
            playerScore == dealerScore -> board[player] = EarningsResult.TIE_BET
            playerScore > dealerScore -> board[player] = EarningsResult.WIN_BET
            else -> board[player] = EarningsResult.LOSE_BET
        }
    }

    fun updateDealerStats() {
        val stats = _dealerStats.toMutableMap()
        val winCount = playerBoard.values.count { it == EarningsResult.LOSE_BET }
        val loseCount =
            playerBoard.values.count { it == EarningsResult.WIN_BET || it == EarningsResult.WIN_BLACK_JACK_BET }
        val tieCount = playerBoard.values.count { it == EarningsResult.TIE_BET }
        stats["win"] = winCount
        stats["lose"] = loseCount
        stats["tie"] = tieCount
        _dealerStats = stats.toMap()
    }

//    fun updateEarnings() {
//        val result = mutableMapOf<Playable, Int>()
//        var dealerTotal = 0
//
//        for ((player, resultCode) in playerBoard) {
//            val earning = player.earnings(resultCode)
//            result[player] = earning
//            dealerTotal -= earning
//        }
//
//        result[dealer] = dealerTotal
//        _earnings = result.toMap()
//    }
}
