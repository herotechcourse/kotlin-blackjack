package model

class Dealer(name: String = "Dealer") : BasePlayer(name) {
    override fun makeDecision(): Boolean {
        return getScore() <= 16
    }
}
