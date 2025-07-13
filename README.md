# Kotlin-Blackjack

## Description:
A simplified terminal-based Blackjack game written in Kotlin.
The player or dealer with a total closest to 21—without going over—wins.
Includes card dealing, drawing logic, and win/loss/draw result handling.

## Features: 
### Blackjack as Controller:
- [x] Implement a Blackjack class as a controller

### Participants:
- [x] Create Participant abstract class and extend it as Player and Dealer classes
- [x] Implement Card, Dealer and Deck
- [x] Check its own scores and results

### Dealer
- [x] Draw card to any participant
- [x] Dealer auto-draws when score ≤ 16
- [x] Ask players if they want to draw more cards

### Hand
- [x] Calculate participant score with Ace logic
- [x] Calculate participant score with J, Q and K logic
- [ ] provide information for game results announce

### Game
- [ ] Accept player names
- [x] Deal two initial cards to all participants
- [x] Handle player turns
- [x] Handle dealer turn
- [ ] Check if a participant is busted
- [ ] Display final hands and scores
- [ ] Determine and print winners using GameLogic.getGameResult()