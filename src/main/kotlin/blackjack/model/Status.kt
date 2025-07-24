package blackjack.model

sealed class Status {
    object Normal : Status()
    object Blackjack : Status()
    object Busted : Status()
}
