# kotlin-blackjack

A simple kotlin project implementation of the classic Blackjack card game where a player competes against a dealer. 
The goal is to get as close to 21 as possible without going over.

## Features 
- Input View 
- [x] Ask user inputs for player name
- [x] Get list of names from the user input
- [x] Ask player's choice to draw card or stay 

- Card
- [x] Create Enum class - Ranks
- [x] Create Enum class - Suits
- [x] Create Class - Card
- [x] Determine the value of each card

- Deck
- [x] Prepare deck
- [x] Shuffle cards

- Participant : abstract class 
- [x] Draw card
- [x] Calculate total value of the cards
- [x] Update status of a participant

- Dealer
- [x] Use Participant abstract class
- [x] Implement additional methods to draw card

- Player
- [x] Use Participant abstract class

- Game (controller)
- [x] Initialize Game
- [x] Assign two cards to every member
- [x] Offer possibility to get a new card
- [x] Check status
- [x] Retrieve Winning Information

- Result 
- [x] Get total value of the cards
- [x] Process Winning Information

- OutputView
- [x] Display cards
- [x] Display dealer message (if 16 or less)
- [x] Display all the cards with total value
- [x] Display Winning Result
