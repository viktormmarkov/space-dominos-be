package com.viktormmarkov.spacedominos.models;

import com.viktormmarkov.spacedominos.domain.enitities.Position;
import com.viktormmarkov.spacedominos.domain.enums.GamePhaseEnum;
import com.viktormmarkov.spacedominos.repositories.TilesRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;

@Setter
@Getter
public class GameState {
    private String gameId;
    private Player[] players;
    private String currentPlayerId;
    private int currentPlayerIndex;
    private String[] playerOrder;
    private Tile[] tiles;
    private Tile[] draftTiles;
    private Tile[] nextDraftTiles;
    private GamePhaseEnum gamePhase;
    private HashMap<String, Board> playerBoards;
    private int draftTilesCount;
    private HashMap<String, Integer> playerDraftPicks;
    private HashMap<String, Tile> playerDraftTiles;
    private int turnCounter = 0;

    public GameState(String gameId, Player[] players) {
        this.initPlayerOrder(players);
        this.initPlayerBoards(players);
        this.initDraftTilesCount(players);
        this.gameId = gameId;
        this.players = players;
        this.tiles = TilesRepository.getShuffledTiles();
        this.draftTiles = new Tile[draftTilesCount];
        this.nextDraftTiles = new Tile[draftTilesCount];
        this.gamePhase = GamePhaseEnum.CHOOSE_TILES;
        this.playerDraftPicks = new HashMap<>();
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

    public Tile[] getNewDraftTiles() {
        Tile[] newDraftItems = new Tile[draftTilesCount];
        Tile[] newTiles = new Tile[tiles.length - draftTilesCount];

        for (int i = 0; i < draftTilesCount; i++) {
            Tile topTile = tiles[tiles.length - i - 1];
             newDraftItems[i] = topTile;
        }

        for (int i = 0; i < tiles.length - draftTilesCount; i++) {
            newTiles[i] = tiles[i];
        }

        this.tiles = newTiles;
        // Sort the new draft items by GameTile number
        Arrays.sort(newDraftItems, (tile1, tile2) -> {
            if (tile1 == null || tile2 == null) {
                return 0; // Handle null tiles gracefully
            }
            return Integer.compare(tile1.getNumber(), tile2.getNumber());
        });
        this.draftTiles = newDraftItems;
        return newDraftItems;
    }

    public void pickDraftTile(int draftOptionIndex, String playerId) {
        if (draftOptionIndex < 0 || draftOptionIndex >= draftTilesCount) {
            throw new IllegalArgumentException("Invalid draft option index");
        }
        if (!playerDraftPicks.containsKey(playerId) && !playerDraftPicks.containsValue(draftOptionIndex)) {
            playerDraftPicks.put(playerId, draftOptionIndex);
        } else {
            throw new IllegalStateException("Draft option already picked");
        }
        if (playerDraftPicks.size() == players.length) {
            // all players have picked their draft options
            this.gamePhase = GamePhaseEnum.PLACE_TILES;
            // this.nextDraftTiles = this.getNewDraftTiles();
        }
        // change phase to play tile
    }

    private void resetPlayerOrder() {
    }

    public void playTile(String playerId, Position pos1, Position pos2, Tile tile) {
        if (!playerBoards.containsKey(playerId)) {
            throw new IllegalArgumentException("Player not found");
        }
        Board playerBoard = playerBoards.get(playerId);
        try {
            playerBoard.placeGameTile(pos1, pos2, tile);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Cannot place tile at the specified positions: " + e.getMessage());
        }
        if (currentPlayerIndex < playerOrder.length - 1) {
            currentPlayerIndex++;
            this.currentPlayerId = playerOrder[currentPlayerIndex];
        } else {
            String[] newPlayerOrder = new String[playerOrder.length];
            playerDraftPicks.forEach((key, value) -> {
                newPlayerOrder[value] = key;
            });

            // reset player order
            currentPlayerIndex = 0;
            this.currentPlayerId = newPlayerOrder[currentPlayerIndex];
            this.playerOrder = newPlayerOrder;
            this.gamePhase = GamePhaseEnum.CHOOSE_TILES;
            this.draftTiles = this.nextDraftTiles.clone();
        }
        // remove tile from draftTiles
        // change phase to next player
    }

    public void wipeDraftOptions() {
        playerDraftPicks.clear();
    }
}
