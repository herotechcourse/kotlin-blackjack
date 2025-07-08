# kotlin-blackjack

## Features

### Model
- [ ] **Create Player Class**
  - [ ] Should contain a name, score, hand: MutableList<Card>
  - [ ] Should Add a card to the hand.
  - [ ] Should track the score
  - [ ] The name should be not empty.
  - [ ] Should check if the score is Blackjack or Busts.
- [ ] Create Dealer Class
  - [ ] Should Inherit maybe from the Player class.
  - [ ] Should contain a Deck and manage it.
  - [ ] Should be able to automatically add cards to the hand.
  - [ ] Should give a card to the player from the deck.
  - [ ] Should check if the score is less or equal to 16. 
- [ ] create Card Class
  - [ ] It contains Suit and Rank Properties
  - [ ] Suit is an enum class containing the values: Diamonds, hearts, clubs, spades
  - [ ] Rank is an enum class containing the symbols and values from the cards
  - [ ] Card could contain toString() to simplify when a card is printed
- [ ] create Deck value Class
  - [ ] Should contain a list<Card>
  - [ ] Should generate a new instance with the static method.
  - [ ] The list is created from 52
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
