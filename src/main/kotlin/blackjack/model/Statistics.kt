package blackjack.model

import blackjack.model.participant.Player

class Statistics(val dealer: Player, val players: List<Player>) {

    val totalResult
        get() = calculatePlayersWinning()
    val dealerWin
        get() = calculateDealerWinning()
    val dealerLose
        get() = players.size - dealerWin

    fun calculatePlayersWinning(): Map<Player, Int> {
        return players.associateWith { whoIsTheWinner(it) }
    }

    fun calculateDealerWinning(): Int {
        val howManyPlayersWon = totalResult.filter { it.value == 1 }.size
        return players.size - howManyPlayersWon
    }

    fun whoIsTheWinner(player: Player): Int {
        return when (playerWin(player)) {
            true -> 1
            false -> 0
        }
    }

    fun playerWin(player: Player): Boolean {
        if (player.isBust) return false
        if (dealer.isBust) return true
        return playerHasMuchPoints(player) || playerBackJack(player)
    }

    private fun playerHasMuchPoints(player: Player): Boolean {
        return dealer.calculatePoints() < player.calculatePoints()
    }

    private fun playerBackJack(player: Player): Boolean {
        return (dealer.calculatePoints() == player.calculatePoints()) &&
            (player.isBlackJack && !dealer.isBlackJack)
    }
}
