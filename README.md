# kotlin-blackjack

A simple kotlin project implementation of the classic Blackjack card game where a player competes against a dealer. 
The goal is to get as close to 21 as possible without going over.

## Features 
- Input View 
- [x] ask user inputs for player name
- [x] get list of names from the user input

- Card 
- [x] Visibility of the card implemented by flipCard() 
- [x] Determine the value of each card
- [x] Create Enum class - Ranks
- [x] Create Class - Card
- [x] Create Enum class - Suits

- Deck
- [x] prepare deck
- [x] shuffle cards

- Participant interface 
- [x] drawCard()
- [x] update status

- Dealer
- [x] use Participant interface
- [x] implement additional methods to draw card

- Player
- [x] use Participant interface

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
