package blackjack.model

class Dealer(
    override val name: String = "Dealer",
    override var isActive: Boolean = true,
    override val cardsInHand: MutableList<Card> = mutableListOf(),
) : Participant {
    fun mustDraw(totalValueOfCards: Int) = totalValueOfCards <= 16

    fun getFinalResultForDealer(players: List<Player>): String {
        val lose = players.count { it.isActive }
        val win = players.count { !it.isActive }
        return "Dealer: $win Win $lose Lose"
    }
}
