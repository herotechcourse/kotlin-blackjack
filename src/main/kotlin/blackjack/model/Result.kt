package blackjack.model

enum class Result() {
    LOSE,
    WIN,
    TIE,
    BLACKJACK,
    NONE,
    ;

    companion object {
        const val BLACKJACK_BONUS = 1.5
    }
}
