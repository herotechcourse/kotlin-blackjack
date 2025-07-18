package blackjack.model

interface PlayerState {
    fun canHit(): Boolean
}

object Playing : PlayerState {
    override fun canHit() = true
}

object Busted : PlayerState {
    override fun canHit() = false
}

object Blackjack : PlayerState {
    override fun canHit() = false
}
