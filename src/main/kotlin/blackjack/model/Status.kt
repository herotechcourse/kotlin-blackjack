package blackjack.model

// TODO: 'State Pattern' -> sealed class? abstract class? what to use
data class Status(
    var isBlackjack: Boolean = false,
    var isBusted: Boolean = false,
    var isNeitherBlackjackNorBusted: Boolean = true,
)
