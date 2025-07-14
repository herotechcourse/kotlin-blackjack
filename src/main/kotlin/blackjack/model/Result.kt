package blackjack.model

enum class Result(val multiplier: Double) {
    LOOSE(-1.0),
    WIN(1.0),
    TIE(0.0),
    BLACKJACK(1.5),
}
