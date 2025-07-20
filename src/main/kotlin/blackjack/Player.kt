package blackjack

class Player(playerName: String): Participant(playerName) {
    override fun shouldHit(): Boolean {
        val scoreHand = this.sumCards()
        return scoreHand < 21
    }
}
