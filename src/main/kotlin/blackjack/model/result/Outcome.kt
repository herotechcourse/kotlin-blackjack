package blackjack.model.result

enum class Outcome(val payoutMultiplier: Double) {
    WIN(2.0),
    LOSE(-1.0),
    DRAW(1.0),
    BLACKJACK(2.5),
}
