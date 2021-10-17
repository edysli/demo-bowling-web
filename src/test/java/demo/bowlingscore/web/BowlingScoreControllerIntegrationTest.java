package demo.bowlingscore.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

class BowlingScoreControllerIntegrationTest {
  WebTestClient client = WebTestClient.bindToController(new BowlingScoreController()).configureClient()
    .baseUrl("/score").build();

  @Test
  void gutterGame() {
    var requestMono = Mono
      .just(new ScoringRequest(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }));
    client.post().contentType(MediaType.APPLICATION_JSON).body(requestMono, ScoringRequest.class).exchange()
      .expectStatus().isOk().expectBody().json("{\"score\":0}");
  }

  @Test
  void requestContainsAtLeastTwelvePins() {
    var requestMono = Mono.just(new ScoringRequest(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1 }));
    client.post().contentType(MediaType.APPLICATION_JSON).body(requestMono, ScoringRequest.class).exchange()
      .expectStatus().isBadRequest();
  }

  @Test
  void requestContainsAtMostTwentyOnePins() {
    var requestMono = Mono
      .just(new ScoringRequest(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 10, 1 }));
    client.post().contentType(MediaType.APPLICATION_JSON).body(requestMono, ScoringRequest.class).exchange()
      .expectStatus().isBadRequest();
  }
}
