package demo.bowlingscore.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.bowlingscore.domain.BowlingGame;

@RestController
@RequestMapping(path = "/score")
public class BowlingScoreController {

  @PostMapping
  public ScoringResponse compute(@RequestBody ScoringRequest request) {
    var g = new BowlingGame();
    for (int p : request.getPins()) {
      g.roll(p);
    }
    return new ScoringResponse(g.score());
  }
}
