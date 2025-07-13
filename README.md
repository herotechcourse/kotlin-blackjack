# kotlin-blackjack

## Features

### Model
- [x] **Create Participant Class**
  - [x] Should contain a name, hand
  - [x] Should Add a card to the hand.
  - [x] Should check for blackjack and busts
  - [x] The name should be not empty.
  - [x] Update the hasBlackJack method to return true only if score is 21 and Hand size 2
- [x] **Create Player Class inherit from a participant**
- [x] **Create Dealer Class inherit from a participant**
  - [x] Should contain a Deck and manage it.
  - [x] Should be able to automatically add cards to the hand.
  - [x] Should give a card to the player from the deck.
  - [x] Should check if the score is less or equal to 16. 
- [x] **Create Card Class**
  - [x] It contains Suit and Rank Properties
  - [x] Suit is an enum class containing the values: Diamonds, hearts, clubs, spades
  - [x] Rank is an enum class containing the symbols and values from the cards
  - [x] Card could contain toString() to simplify when a card is printed
- [x] **Create Deck value Class**
  - [x] Should contain an ImmutableList<Card>
  - [x] The list is created from 52
- [x] **Create a Hand Class**
  - [x] contain an Immutable list of dealtCards
  - [x] calculate the current score
  - [x] check for blackjack and busts
  - [x] toString() return a nice formatted string
- [x] **Create a Result enum Class**
  - [x] Should contain the values: WIN, LOSE, DRAW
- [x] **Create a ResultTracker Interface**
  - [x] Should contain an abstract method to save the result depending if is a player or a dealer.
- [x] **Create a PlayerResultTracker and DealerResultTracker Class**
  - [x] Should implement the ResultTracker interface
  - [x] Should save the result of the player in a state of Result and dealer in a Map<Result, Int> to track the results
    of the game.
  - [x] Should return the results at the end of the game to print them.
- [x] **Create GameJudge Object**
  - [x] Should contain a method to calculate the final hand of each player and dealer.
  - [x] Should set the result of the game for each player and dealer.
### Controller
- [x] Create Game Controller
  - [x] Should call the InputView to get the player names
  - [x] Create the necessary Players and add to a List<Player>
  - [x] Should create Dealer("Dealer")
  - [x] Initialize the game by having the Dealer deal two cards to each player and two to themselves, one card at a time.
  - [x] The controller initializes the game, giving cards to each player as longer the player wants, or until the player isBusts()
  - [x] If all the players finish, then the Dealer drawCards automatically for themselves.
  - [x] Print a final Hand from each participant
  - [x] Call the dealer to calculate the final hand for each player and set the results
  - [x] Print final results
  - [x] Add a try catch with a retry template function for the validation of the user input.
### View
- [x] InputView
  - [x] Should accept the names of the players
    - [x] Should be comma separated names
    - [x] Should contain a minimum of one player.
    - [x] The names should be unique.
- [x] OutputView
  - [x] Should print the list of all participants and their hands.
  - [x] Should print the final hands and score.
  - [x] Should print the Final Result.
  - [x] Add empty lines between each step on the game.
- [x] Add constants to Input and Output Prompts
- [x] Print cards with color (black and red)
- [x] Add AnsiColor for the cards and error messages