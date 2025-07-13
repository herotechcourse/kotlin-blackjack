package blackjack

class Player(playerName: String): Participant(playerName) {
    override fun shouldHit(): Boolean {
        val scoreHand = this.sumCards()
        if (scoreHand < 21) {
            println("Would ${this.name} like to draw another card? (y for yes, n for no)")
            val wantHit = readln().trim()
            if (wantHit == "y") {
                return true
            }
        }
        return false
    }
}