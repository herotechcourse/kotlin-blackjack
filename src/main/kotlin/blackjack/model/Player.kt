package blackjack.model

class Player(name: String) : Participant(name) {
    var bettingAmount: Int = 0
    var finalEarnings: Double = 0.0
}
