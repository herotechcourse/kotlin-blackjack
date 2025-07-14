package blackjack.view

import blackjack.model.ErrorMessage

object InputView {
    fun readNames(): List<String> {
        println("\nEnter the names of the players (comma-separated):")
        val input = readln()
        require (input.isNotEmpty()) { (ErrorMessage.EMPTY_INPUT.toString()) }
        return input.split(",").map { it.trim() }
    }

//    fun promptForDraw(player: Player): Boolean {
//        println("\nWould ${player.name} like to draw another card? (y for yes, n for no)")
//        val input = readln().trim()
//        require (input.isNotEmpty()) { (ErrorMessage.EMPTY_INPUT.toString()) }
//        return when (input) {
//            "y" -> true
//            "n" -> false
//            else -> throw IllegalArgumentException(ErrorMessage.EMPTY_INPUT.toString())
//        }
//    }
}
