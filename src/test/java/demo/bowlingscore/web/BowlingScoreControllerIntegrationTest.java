package demo.bowlingscore.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

class BowlingScoreControllerIntegrationTest {
  WebTestClient client = WebTestClient.bindToController(new BowlingScoreController()).build();

  @Test
  void gutterGame() {
    var requestMono = Mono
      .just(new ScoringRequest(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }));
    client.post().uri("/score").contentType(MediaType.APPLICATION_JSON).body(requestMono, ScoringRequest.class)
      .exchange().expectStatus().isOk().expectBody().json("{\"score\":0}");
  }
}
