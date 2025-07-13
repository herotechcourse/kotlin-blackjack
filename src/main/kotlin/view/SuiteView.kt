package view

import model.Suite

enum class SuiteView(val suite: Suite, val symbol: String) {
    HEARTS(Suite.HEARTS, "\u2665"),
    CLUBS(Suite.CLUBS, "\u2663"),
    DIAMONDS(Suite.DIAMONDS, "\u2662"),
    SPADES(Suite.SPADES, "\u2664"),
    ;

    companion object {
        fun from(suite: Suite): SuiteView {
            return SuiteView.entries.first { it.suite == suite }
        }
    }
}
