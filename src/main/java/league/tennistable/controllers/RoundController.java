package league.tennistable.controllers;


import league.tennistable.domain.models.Match;
import league.tennistable.domain.models.Round;
import league.tennistable.service.MatchService;
import league.tennistable.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/rounds")
public class RoundController {


    @Autowired
    MatchService matchService;

    @Autowired
    RoundService roundService;

    @RequestMapping(value = "")
    public ResponseEntity<List<Round>> createRounds() {

        List<Match> allMatches = matchService.getAll();

        Random random = new Random();
        List<Round> league_Rounds = new ArrayList<>();

        // loop till all matches  enrolled in  round
        for (int list_count = 0; allMatches.size() > 0; list_count++) {

            HashSet<Match> temp_group_matches = new HashSet<>();

            // loop to form group of 3 = round-size (3 matches / round) Or there is no more matches to scheduled
            for (int count = 0; (temp_group_matches.size() < 3 && !allMatches.isEmpty()); count++) {

                Match match = allMatches.get(random.nextInt(allMatches.size()));
                allMatches.remove(match);
                if (match.getRound() != null) continue;
                temp_group_matches.add(match);

            }
            if (temp_group_matches.isEmpty()) continue;

            // create new round
            Round round = new Round();
            round.setMatches(temp_group_matches);
            Round createdRound = roundService.createRound(round);

            // add matches to round (update the match Entity)
            createdRound.getMatches().forEach(match -> {
                match.setRound(round);
                matchService.addToRound(match);
            });
        }
        return ResponseEntity.ok(roundService.getAllRounds());
    }

}
