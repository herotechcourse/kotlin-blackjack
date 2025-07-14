package blackjack.model

data class Player(override val name: String, val bet: Bet) : Playable()
