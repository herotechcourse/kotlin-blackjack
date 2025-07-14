# kotlin-blackjack

A simple Kotlin implementation of the classic Blackjack game, where players compete against a dealer. 
The goal is to get as close to 21 as possible without exceeding it with a betting system.

## Features 

### Input View 
- [x] Ask user inputs for player name
- [x] Get a list of names from the user input
- [x] Ask each of the player's betting amounts
- [x] Ask player's choice to draw card or stay 

### Card
- [x] Create Enum class - Ranks
- [x] Create Enum class - Suits
- [x] Create Class - Card
- [x] Determine the value of each card

### Deck
- [x] Prepare deck
- [x] Shuffle cards
- [x] Validation on drawing more than available cards

### Participant : abstract class 
- [x] Draw card
- [x] Calculate the total value of the cards
- [x] Common logics for players and dealer (card handling, score calculation, blackjack/bust check)

### Dealer
- [x] Use Participant abstract class
- [x] Implement additional methods to draw card following Blackjack rules (draws up to 16, stands on 17+)
- [x] Calculate the final earnings for a dealer based on all players' outcomes

### Player
- [x] Use Participant abstract class
- [x] Places a bet
- [x] Draw cards and update earnings based on game outcome
- [x] Calculate the final earnings for players (Blackjack = 1.5*bet, tie = 0, loss = -bet)

### Players
- [x] Wraps list of Player instances
- [x] Provides utility methods like `forEach` and `toList`

### Participants
- [x] Combine Dealer and Players into a single unit
- [x] Deal initial cards to all participants
- [x] Run dealer’s turn logic
- [x] Evaluate results for all players

### Game (Controller)
- [x] Initialize Game
- [x] Manage game flow (deal, draw, dealer turn)
- [x] Offer possibility to get a new card
- [x] Coordinate user input and output
- [x] Retrieve Winning Information

### OutputView
- [x] Display initial cards (with dealer showing only one)
- [x] Show each player’s hand as they draw
- [x] Show dealer’s draw message if needed
- [x] Display final hands with total values
- [x] Display the final earnings result for dealer and players

## Blackjack Rules Implemented
- Aces can count as 1 or 11
- Blackjack on initial two cards gives 1.5x the bet
- Player loses the entire bet if busted
- Dealer stands at 17 or more
- If both dealer and player have blackjack, bet is returned
- Dealer bust results in automatic player wins

