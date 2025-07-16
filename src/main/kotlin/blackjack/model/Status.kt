package blackjack.model

data class Status(
    var isBlackjack: Boolean = false,
    var isBusted: Boolean = false,
    var isNeitherBlackjackNorBusted: Boolean = true,
)
