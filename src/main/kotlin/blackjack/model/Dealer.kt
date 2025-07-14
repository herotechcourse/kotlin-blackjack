package blackjack.model

class Dealer : Participant("Dealer") {
    fun mustDraw(): Boolean = total() <= DEALER_DRAW_LIMIT

    fun returnWinningMoneyForDealer(players: List<Player>): Int {
        return players.sumOf { -it.returnWinningMoneyForPlayer() }
    }

    companion object {
        const val DEALER_DRAW_LIMIT = 16
    }
}
