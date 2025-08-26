package blackjack.model

class ParticipantWallet {
    var bet: Double = 0.0
    var earnings: Double = 0.0

    fun addToBet(value: Double) {
        bet += value
    }

    fun total(): Double {
        return bet + earnings
    }

    fun updateWithEarningsRate(earningsRate: Double) {
        earnings += bet * earningsRate
    }

    fun addToEarnings(newEarnings: Double) {
        earnings += newEarnings
    }
}
