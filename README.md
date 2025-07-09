# kotlin-blackjack

## Features

### Model
- [x] **Create Participant Class**
  - [x] Should contain a name, hand
  - [x] Should Add a card to the hand.
  - [x] Should check for blackjack and busts
  - [x] The name should be not empty.
- [ ] **Create Player Class inherit from a participant**
- [ ] **Create Dealer Class inherit from a participant**
  - [ ] Should Inherit maybe from the Player class.
  - [ ] Should contain a Deck and manage it.
  - [ ] Should be able to automatically add cards to the hand.
  - [ ] Should give a card to the player from the deck.
  - [ ] Should check if the score is less or equal to 16. 
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
### Controller
- [ ] Create Game Controller
  - [ ] Should call the InputView to get the player names
  - [ ] Create the necessary Players and add to a List<Player>
  - [ ] Should create Dealer("Dealer")
  - [ ] Initialize the game by having the Dealer deal two cards to each player and two to themselves, one card at a time.
  - [ ] The controller initializes the game, giving cards to each player as longer the player wants, or until the player isBusts()
  - [ ] If all the players finish, then the Dealer drawCards automatically for themselves.
### View
- [ ] InputView
  - [ ] Should accept the names of the players
    - [ ] Should be comma separated names
    - [ ] Should contain a minimum of one player.
    - [ ] The names should be unique.
- [ ] OutputView
  - [ ] Should print the list of all participants and their hands.
  - [ ] Should print the final hands and score.
  - [ ] Should print the Final Result.
