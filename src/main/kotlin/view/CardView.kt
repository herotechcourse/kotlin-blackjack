package view

import model.Card

object CardView {
    private fun render(card: Card): String {
        return if (card.faceUp) {
            card.rank.value + SuiteView.from(card.suite).symbol
        } else ""
    }

    fun renderAll(cards: Collection<Card>, showAll: Boolean): String {
        return if (showAll) {
            cards.joinToString(", ") { render(it) }
        } else {
            render(cards.first())
        }
    }
}
