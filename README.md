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

## Step 2 new features

- Player bet
- [ ] Get bet from every player
- [ ] If initial hand is equal 21, player wins 1.5 bet
- [ ] If initial hand is equal 21, but dealer has also 21. Bet is returned
- [ ] If player is busted, loses their bet.

## Example output

`Enter the names of the players (comma-separated):
pobi,jason

Enter pobi’s betting amount:
10000

Enter jason’s betting amount:
20000

Dealing two cards to dealer, pobi, jason.
Dealer: 3♦  
pobi's cards: 2♥, 8♠  
jason's cards: 7♣, K♠

Would pobi like to draw another card? (y for yes, n for no)  
y  
pobi's cards: 2♥, 8♠, A♣  
Would pobi like to draw another card? (y for yes, n for no)  
n  
Would jason like to draw another card? (y for yes, n for no)  
n  
jason's cards: 7♣, K♠

Dealer draws one more card due to having 16 or less.

Dealer’s cards: 3♦, 9♣, 8♦ – Total: 20  
pobi's cards: 2♥, 8♠, A♣ – Total: 21  
jason's cards: 7♣, K♠ – Total: 17

\## Final Earnings
Dealer: 10000  
pobi: 10000  
jason: -20000`
