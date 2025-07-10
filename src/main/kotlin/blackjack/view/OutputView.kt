package blackjack.view

import blackjack.model.Player

object OutputView {
    fun printFinalResult(players: List<Player>) {
        players.map {
            println("\"${it.name}'s cards: \" + \"${it.cards} - Total: ${it.calculatePoints()}")
        }
    }

    fun printSetUp(players: List<Player>) {
        players.forEach { println("${it.name}'s cards: " + "${it.cards}") }
    }
}
