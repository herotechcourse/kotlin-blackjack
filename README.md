# kotlin-blackjack

## Features 
- Input View 
- [ ] ask user inputs for player name
- [ ] validate number of players (max 7 players)
- [ ] validate name
- [ ] get list of names from the user input

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

- Game 
- [ ] Initialize Game
- [ ] Assign two cards to every member
- [ ] Offer possibility to get a new card
- [ ] Check status
- [ ] Retrieve Winning Information

- Result 
- [ ] Get total value of the cards
- [ ] Process Winning Information

- OutputView
- [ ] Display cards
- [ ] Display dealer message (if 16 or less)
- [ ] Display all the cards with total value
- [ ] Display Winning Result
