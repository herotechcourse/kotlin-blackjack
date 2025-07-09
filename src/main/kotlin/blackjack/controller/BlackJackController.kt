package blackjack.controller

import blackjack.model.PlayerFactory

object BlackJackController {
    fun play() {
        // show prompt
        // ask/read user input: players name
        val names = listOf("mina", "vito")

        // create players, create dealer -> store
        PlayerFactory.with(names)
        PlayerFactory.createDealer()

        // give it to manager
        // hit the cards
        // show table cards
        // asking y or n for new card
    }
}
