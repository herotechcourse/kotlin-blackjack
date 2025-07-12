package blackjack.model

class Dealer(name: String = "Dealer") : Participant( name) {
    fun mustDraw(totalValueOfCards: Int) = totalValueOfCards <= 16

    fun getFinalResultForDealer(players: List<Player>): String {
        val lose = players.count { it.isActive }
        val win = players.count { !it.isActive }
        return "Dealer: $win Win $lose Lose"
    }
}
