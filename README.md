# kotlin-blackjack
Implementation of the Blackjack card game in Kotlin.
## Standard Blackjack rules:
- Number cards are counted by their face value.
- Face cards (King, Queen, Jack) are each worth 10.
- Aces can be worth either 1 or 11.
- Each player starts with two cards.
- Players may draw additional cards as long as their total remains 21 or less.
- The dealer must draw a card if their total is 16 or less, and must stand on 17 or more.
- If the dealer busts (goes over 21), all remaining players automatically win.
- After the game ends, display the result (win/loss) for each player.

## Sample Output
```
Enter the names of the players (comma-separated):
pobi,jason

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

Dealer's cards: 3♦, 9♣, 8♦ – Total: 20
pobi's cards: 2♥, 8♠, A♣ – Total: 21
jason's cards: 7♣, K♠ – Total: 17

Final Results
Dealer: 1 Win 1 Lose
pobi: Win
jason: Lose
```

## Features
### Players
- [x] get user input for player names
  - [x] MIN (1), MAX(7) excluding dealer
  - [x] names can contain only letters
  - [x] MAX..MIN length
  - [x] comma separated
- [x] display names

### Bets
- [x] get user input for player's bets

### Cards
- [x] create card deck
- [x] Card values follow standard Blackjack rules:
  - [x] Number cards are counted by their face value.
  - [x] Face cards (King, Queen, Jack) are each worth 10.
  - [x] Aces can be worth either 1 or 11.

### Deck
- [x] suit
- [x] rank
- [x] value

- [x] deal initial cards to player and dealer (participants)
  - [x] 2 per player
- [x] print cards
  - [x] for the dealer only 1
  - [x] for players all
  - [x] color and value

- [x] prompt each player for drawing cards
  - [x] until "n"
  - [x] until he is busted
- [x] draw cards
- [x] calculate sum
- [x] display cards of player

- [x] draw cards for dealer until sum is <= 16
- [x] display message

- [x] display cards
- [x] display sum

### Results
- [x] calculate earnings (= difference of before and after round ~ profit)
  - [x] player busts: lose their bet
  - [x] player blackjack (21 with first two cards): receive 1.5x their bet
  - [x] player and dealer blackjack: bet returned
  - [x] dealer busts: remaining players receive 1.0x their bet
- [x] display earnings

## Considerations
Q1: How should we name players inside the Players class? </br>
Q2: Should we use multiple decks? </br>
Q3: State in Controller (even if private) is bad practice?</br>
