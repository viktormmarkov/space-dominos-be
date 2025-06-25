package com.viktormmarkov.spacedominos.models.game;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.viktormmarkov.spacedominos.models.game.board.Position;
import com.viktormmarkov.spacedominos.domain.enums.GamePhaseEnum;
import com.viktormmarkov.spacedominos.models.game.board.Board;
import com.viktormmarkov.spacedominos.models.game.board.Tile;
import com.viktormmarkov.spacedominos.repositories.TilesRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;

@Setter
@Getter
public class GameState {
    private String gameId;
    private GamePhaseEnum gamePhase;
    private Player[] players;
    private Tile[] tilesDeck;
    private HashMap<String, Player> playerMap;
    private String currentPlayerId;
    private int currentPlayerIndex;
    private String[] playerOrder;
    private Tile[] draftTiles;
    private Tile[] nextDraftTiles;
    private HashMap<String, Board> playerBoards;
    private int draftTilesCount;
    private HashMap<String, Tile> playerDraftTiles;
    private int turnCounter = 0;

    public GameState(String gameId, Player[] players) {
        if (players == null || players.length == 0) {
            throw new IllegalArgumentException("Players cannot be null or empty");
        }
        // add check for two players or fix logic as each player will play twice, currently is not working
        this.initPlayerOrder(players);
        this.initPlayerBoards(players);
        this.initDraftTilesCount(players);
        this.gameId = gameId;
        this.players = players;
        this.tilesDeck = TilesRepository.getShuffledTiles();
        this.draftTiles = new Tile[draftTilesCount];
        this.nextDraftTiles = new Tile[draftTilesCount];
        this.gamePhase = GamePhaseEnum.CHOOSE_TILES;
    }

    private void initDraftTilesCount(Player[] players) {
        int numberOfPlayers = players.length;
        if (numberOfPlayers == 2) {
            this.draftTilesCount = 4;
        } else {
            this.draftTilesCount = numberOfPlayers;
        }
    }

    private void initPlayerOrder(Player[] players) {
        if(players == null || players.length == 0) {
            return;
        }
        this.playerMap = new HashMap<>();
        for (Player player : players) {
            this.playerMap.put(player.getId(), player);
        }
        Player[] playersCopy = players.clone();
        java.util.Collections.shuffle(Arrays.asList(playersCopy));
        this.playerOrder = Arrays.stream(playersCopy).toList().stream()
                .map(Player::getId)
                .toArray(String[]::new);
        if (playerOrder.length == 2) {
            // for two players each player will go twice
            this.playerOrder = new String[]{playerOrder[0], playerOrder[1], playerOrder[0], playerOrder[1]};
        }
        this.currentPlayerIndex = 0;
        this.currentPlayerId = playerOrder[currentPlayerIndex];
    }

    private void initPlayerBoards(Player[] players) {
        if (players == null || players.length == 0) {
            throw new IllegalArgumentException("Players cannot be null or empty");
        }
        this.playerBoards = new HashMap<>();
        for (Player player : players) {
            Board board = new Board(5, 5);
            playerBoards.put(player.getId(), board);
        }
    }

    public void drawNewDraftTiles() {
        Tile[] newDraftItems = new Tile[draftTilesCount];
        Tile[] newTiles = new Tile[tilesDeck.length - draftTilesCount];

        for (int i = 0; i < draftTilesCount; i++) {
            Tile topTile = tilesDeck[tilesDeck.length - i - 1];
             newDraftItems[i] = topTile;
        }

        System.arraycopy(tilesDeck, 0, newTiles, 0, tilesDeck.length - draftTilesCount);

        this.tilesDeck = newTiles;
        // Sort the new draft items by GameTile number
        Arrays.sort(newDraftItems, (tile1, tile2) -> {
            if (tile1 == null || tile2 == null) {
                return 0; // Handle null tiles gracefully
            }
            return Integer.compare(tile1.getNumber(), tile2.getNumber());
        });
        this.draftTiles = newDraftItems;
    }

    public void createNewPlayerOrder() {
        String[] newPlayerOrder = new String[playerOrder.length];
        for (String id: playerMap.keySet()) {
            Player player = playerMap.get(id);
            PlayerTileChoice playerTileChoice = player.getNextTileChoice();
            if (playerTileChoice == null) {
                throw new IllegalStateException("Player has not picked a draft tile yet");
            }
            if (playerTileChoice.draftIndex >= 0 && playerTileChoice.draftIndex < newPlayerOrder.length) {
                newPlayerOrder[playerTileChoice.draftIndex] = id;
            }
        }
        this.playerOrder = newPlayerOrder;
        this.currentPlayerIndex = 0;
        this.currentPlayerId = playerOrder[currentPlayerIndex];
    }

    public void nextPlayer() {
        if (currentPlayerIndex < playerOrder.length - 1) {
            currentPlayerIndex++;
            this.currentPlayerId = playerOrder[currentPlayerIndex];
        }
    }

    public void startNewRound() {
        this.turnCounter++;
        this.gamePhase = GamePhaseEnum.CHOOSE_TILES;
        this.drawNewDraftTiles();
        this.createNewPlayerOrder();
        for (Player player: playerMap.values()) {
            player.updateCurrentTileChoice();
        }
    }

    public void nextPhase() {
        boolean isLastPlayer = currentPlayerIndex == playerOrder.length - 1;
        boolean isFirstTurn = this.turnCounter == 0;

        boolean shouldStartNewRound = (isLastPlayer && isFirstTurn && this.gamePhase == GamePhaseEnum.CHOOSE_TILES) ||
                (isLastPlayer && this.gamePhase == GamePhaseEnum.PLACE_TILES);

        if (shouldStartNewRound) {
            this.createNewPlayerOrder();
            this.startNewRound();
        } else if (this.gamePhase == GamePhaseEnum.CHOOSE_TILES) {
            if (isFirstTurn) {
                this.nextPlayer();
            } else {
                this.gamePhase = GamePhaseEnum.PLACE_TILES;
            }
        } else if (this.gamePhase == GamePhaseEnum.PLACE_TILES) {
            this.nextPlayer();
            this.gamePhase = GamePhaseEnum.CHOOSE_TILES;
        }
    }

    public void chooseTile(String playerId, int draftOptionIndex) {
        if (draftOptionIndex < 0 || draftOptionIndex >= draftTilesCount) {
            throw new IllegalArgumentException("Invalid draft option index");
        }

        if (playerBoards.get(playerId) == null) {
            throw new IllegalArgumentException("Player not found");
        }

        if (playerMap.get(playerId) == null) {
            throw new IllegalArgumentException("Player not found in player map");
        }

        Player player = playerMap.get(playerId);

        if (!player.canPickNextTile()) {
            throw new IllegalStateException("Player has already picked a draft tile");
        }

        for (String id : playerMap.keySet()) {
            PlayerTileChoice choice = playerMap.get(id).getNextTileChoice();
            if (!id.equals(playerId) && choice != null && choice.draftIndex == draftOptionIndex) {
                throw new IllegalStateException("Draft option already picked by another player");
            }
        }

        player.setNextTileChoice(draftTiles[draftOptionIndex], draftOptionIndex);
        this.nextPhase();
    }

    public void placeTile(String playerId, Position pos1, Position pos2, Tile tile) {
        if (!playerBoards.containsKey(playerId)) {
            throw new IllegalArgumentException("Player not found");
        }
        Board playerBoard = playerBoards.get(playerId);
        try {
            playerBoard.placeGameTile(pos1, pos2, tile);
            this.nextPhase();
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Cannot place tile at the specified positions: " + e.getMessage());
        }
    }

    public Player getPlayerById(String playerId) {
        if (playerMap.containsKey(playerId)) {
            return playerMap.get(playerId);
        } else {
            throw new IllegalArgumentException("Player not found with ID: " + playerId);
        }
    }
}
