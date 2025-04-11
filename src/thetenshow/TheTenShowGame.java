package thetenshow;

import ca.sheridancollege.project.Deck;
import ca.sheridancollege.project.Game;
import ca.sheridancollege.project.Suit;
import java.util.*;

public class TheTenShowGame extends Game {
    private Deck deck;
    private List<TheTenShowPlayer> players;
    private TheTenShowCard hiddenCard;
    private boolean sirRevealed = false;
    private Suit sirSuit = null;

    private Map<String, Integer> team10s = new HashMap<>();
    private Map<String, Integer> teamHands = new HashMap<>();

    public TheTenShowGame(String name) {
        super(name);
        this.deck = new Deck(52);
        this.players = new ArrayList<>();
        team10s.put("TeamA", 0);
        team10s.put("TeamB", 0);
        teamHands.put("TeamA", 0);
        teamHands.put("TeamB", 0);
    }

    public void addPlayer(TheTenShowPlayer player) {
        players.add(player);
    }

    private String getTeam(TheTenShowPlayer player) {
        int index = players.indexOf(player);
        return (index % 2 == 0) ? "TeamA" : "TeamB";
    }

    public void hideRandomCard() {
        if (!deck.getCards().isEmpty()) {
            hiddenCard = (TheTenShowCard) deck.getCards().remove(0);
            System.out.println("A card has been hidden (SIR).");
        }
    }

    public void revealSIRCard() {
        if (!sirRevealed && hiddenCard != null) {
            sirRevealed = true;
            sirSuit = hiddenCard.getSuit();
            players.get(0).addCard(hiddenCard); // Add hidden card to Player 1's hand
            System.out.println("SIR revealed! Trump suit is: " + sirSuit);
            System.out.println("The hidden card has been returned to Player 1.");
            hiddenCard = null;
        }
    }

    public void dealCards() {
        deck.shuffle();
        hideRandomCard();

        TheTenShowPlayer firstPlayer = players.get(0);
        for (int i = 0; i < 12; i++) {
            firstPlayer.addCard((TheTenShowCard) deck.getCards().remove(0));
        }

        for (int i = 1; i < players.size(); i++) {
            for (int j = 0; j < 13; j++) {
                players.get(i).addCard((TheTenShowCard) deck.getCards().remove(0));
            }
        }
    }

    private TheTenShowCard askCard(TheTenShowPlayer player, Suit leadSuit) {
        List<TheTenShowCard> hand = player.getHand();

        if (hand.isEmpty()) {
            System.out.println(player.getName() + " has no cards left to play!");
            return null;
        }

        // Try to follow lead suit
        for (TheTenShowCard c : hand) {
            if (leadSuit == null || c.getSuit() == leadSuit) {
                hand.remove(c);
                System.out.println(player.getName() + " played: " + c);
                return c;
            }
        }

        // Reveal SIR if not already revealed
        boolean causedSIR = false;
        if (!sirRevealed) {
            System.out.println(player.getName() + " has no " + leadSuit + ". Revealing SIR...");
            revealSIRCard();
            causedSIR = true;
        }

        // If player triggered SIR and has SIR suit, they MUST play it
        if (causedSIR && sirSuit != null) {
            for (TheTenShowCard c : hand) {
                if (c.getSuit() == sirSuit) {
                    hand.remove(c);
                    System.out.println(player.getName() + " played (forced SIR): " + c);
                    return c;
                }
            }
        }

        // Play any available card
        TheTenShowCard fallback = hand.remove(0);
        System.out.println(player.getName() + " played: " + fallback);
        return fallback;
    }

    @Override
    public void play() {
        dealCards();
        int starterIndex = 0;

        for (int round = 1; round <= 13; round++) {
            System.out.println("\n=== Round " + round + " ===");
            List<TheTenShowCard> roundCards = new ArrayList<>();
            Map<TheTenShowCard, TheTenShowPlayer> cardMap = new HashMap<>();
            Suit leadSuit = null;

            for (int i = 0; i < 4; i++) {
                int playerIndex = (starterIndex + i) % 4;
                TheTenShowPlayer currentPlayer = players.get(playerIndex);
                TheTenShowCard playedCard = askCard(currentPlayer, leadSuit);

                if (i == 0 && playedCard != null) leadSuit = playedCard.getSuit();
                roundCards.add(playedCard);
                cardMap.put(playedCard, currentPlayer);
            }

            // Determine round winner
            TheTenShowCard winningCard = null;
            for (TheTenShowCard card : roundCards) {
                if (winningCard == null) {
                    winningCard = card;
                    continue;
                }

                boolean trumpBeats = sirRevealed && card.getSuit() == sirSuit && winningCard.getSuit() != sirSuit;
                boolean leadHigher = card.getSuit() == winningCard.getSuit() && card.getValue() > winningCard.getValue();
                boolean trumpTieBreaker = sirRevealed && card.getSuit() == sirSuit && winningCard.getSuit() == sirSuit && card.getValue() > winningCard.getValue();

                if (trumpBeats || leadHigher || trumpTieBreaker) {
                    winningCard = card;
                }
            }

            TheTenShowPlayer roundWinner = cardMap.get(winningCard);
            String winningTeam = getTeam(roundWinner);
            teamHands.put(winningTeam, teamHands.get(winningTeam) + 1);

            for (TheTenShowCard c : roundCards) {
                if (c != null && c.getValue() == 10) {
                    team10s.put(winningTeam, team10s.get(winningTeam) + 1);
                }
            }

            System.out.println("Round " + round + " won by: " + roundWinner.getName() + " (" + winningTeam + ")");
            starterIndex = players.indexOf(roundWinner);
        }

        declareWinner();

        // Ask to replay
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDo you want to play again? (yes/no): ");
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("yes")) {
            for (TheTenShowPlayer player : players) {
                player.clearHand();
            }
            deck = new Deck(52);
            team10s.put("TeamA", 0);
            team10s.put("TeamB", 0);
            teamHands.put("TeamA", 0);
            teamHands.put("TeamB", 0);
            sirRevealed = false;
            sirSuit = null;
            play(); // restart
        } else {
            System.out.println("Thanks for playing The Ten Show!");
        }
    }

    @Override
    public void declareWinner() {
        int teamA10s = team10s.get("TeamA");
        int teamB10s = team10s.get("TeamB");
        int teamAHands = teamHands.get("TeamA");
        int teamBHands = teamHands.get("TeamB");

        System.out.println("\n=== Final Results ===");
        System.out.println("Team A - 10s: " + teamA10s + ", Hands: " + teamAHands);
        System.out.println("Team B - 10s: " + teamB10s + ", Hands: " + teamBHands);

        if (teamA10s > teamB10s) {
            System.out.println("🏆 Team A Wins by 10s!");
        } else if (teamB10s > teamA10s) {
            System.out.println("🏆 Team B Wins by 10s!");
        } else {
            if (teamAHands > teamBHands) {
                System.out.println("🏆 Team A Wins by Hands!");
            } else if (teamBHands > teamAHands) {
                System.out.println("🏆 Team B Wins by Hands!");
            } else {
                System.out.println("🤝 The Game is a Tie!");
            }
        }
    }
}
