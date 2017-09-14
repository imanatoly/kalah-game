# Kalah Game Server

Simple Rest based server for [Kalah](https://en.wikipedia.org/wiki/Kalah) game.

Tests has  net.kalah.simulation package has simulation for two random agents playing game.
`KalahGameAccessor` in same package summarizes API endpoints:

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



## TODO

* Tests
* Better Error handling
* GUI client, possibly mobile game with libGDX
* Keeping games in key-value store

