package com.example.random;

import java.util.Random;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class RandomController {

    int num = -1;   // important
    int count = 0;

    @GetMapping("/guess")
    public String guess(@RequestParam int user) {

        // generate random ONLY ONCE
        if (num == -1) {
            Random rand = new Random();
            num = rand.nextInt(15) + 1;
            count = 0;
            System.out.println("Generated number: " + num);
        }

        if (user == num) {
            num = -1; // reset game
            return "WIN";
        }

        count++;

        if (count >= 5) {
            num = -1;          // reset game
            return "LOSE";
        }

        if (count % 3 == 1) {
            return "WRONG NUMBER";
        } else if (count % 3 == 2) {
            return "TRY AGAIN";
        } else {
            return "NOT MATCHED";
        }
    }
}
