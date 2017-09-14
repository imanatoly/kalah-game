# Kalah Game Server

Simple Rest based server for [Kalah](https://en.wikipedia.org/wiki/Kalah) game with Spring Boot.

Tests has `net.kalah.simulation` package with simulation for two random agents playing game.
`net.kalah.game.feignclient.KalahGameAccessor` in test folder summarizes API endpoints:

```java
@RequestLine("GET /api/kalah/join")
PlayerInfo joinGame();

@RequestLine("GET /api/kalah/game/{id}/status")
GameStatusDto getStatus(@Param("id") String id);

@RequestLine("GET /api/kalah/game/{id}")
GameDto getGame(@Param("id") String id);

@RequestLine("PUT /api/kalah/game/{id}/slot/{slotIndex}")
GameDto play(@Param("id") String id, @Param("slotIndex") int slotIndex);
```

Basically server pairs each subsequent players to a game. When you join a game you obtain a (session/player) id
and using this id query game or make a move.

## Usage

To run game server simply

```
$ gradle bootRun
```

## Swagger

When you run server, on http://localhost:8080/swagger-ui.html you will see Swagger UI. There you can inspect API.

![Swagger UI](/../<master/assets/swagger.png?raw=true "Swagger UI")

## TODO

* Better Error handling
* Expire games in cache
* Keeping games in key-value store instead of memory
* Websockets
* GUI client, possibly mobile game with libGDX
