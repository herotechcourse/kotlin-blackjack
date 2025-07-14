package blackjack.model

class Player(name: String, val bettingAmount: Int) : Participant(name) {
    var finalEarnings: Double = 0.0
}
