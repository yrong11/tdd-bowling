import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BowlingGameTest {

    String[] onlyStrike = {"X","X","X","X","X","X","X","X","X","X","XX"};
    String[] scores2 = {"9/","13","X","X","X","X","X","X","X","X","13"};
    String[] withoutStrikeSpare = {"12","53","43","61","81","23","17","45","11","12"};
    String[] errorScores = {"9/","13","..","X","X","X","X","X","X","X","13"};
    String[] errorScores1 = {"dfa", ""};
    BowlingGame bowlingGame = new BowlingGame(scores2);
    BowlingGame onlyStrikeBowlingGame = new BowlingGame(onlyStrike);
    BowlingGame withoutStrikeSparebowlingGame = new BowlingGame(withoutStrikeSpare);

    public BowlingGameTest() throws Exception {
    }

    @Test()
    void throw_error_when_roundScores_parameters_error() throws Exception {
        assertThrows(
                Exception.class,
                () -> new BowlingGame(errorScores)
        );

        assertThrows(Exception.class,
                () -> new BowlingGame(errorScores1));
    }

    @Test
    void test_bowlingGame_get_total_score() throws Exception {
        assertEquals(230, bowlingGame.getTotalScore());
    }

    @Test
    void return_300_when_only_strike() throws Exception {

        assertEquals(300, onlyStrikeBowlingGame.getTotalScore());
    }

    @Test
    void get_total_score_when_without_strike_and_spare() throws Exception {
        assertEquals(61, withoutStrikeSparebowlingGame.getTotalScore());
    }

    @Test
    void test_get_each_round_real_socre_when_socre_has_strike() throws Exception {
        assertEquals(30, onlyStrikeBowlingGame.getEachRoundRealSocre(2));
        assertEquals(14, bowlingGame.getEachRoundRealSocre(10));
    }

    @Test
    void test_get_each_round_real_socre_when_socre_has_spare() throws Exception {
        assertEquals(11, bowlingGame.getEachRoundRealSocre(1));
    }

    @Test
    void test_get_round_real_score_when_score_without_strike_spare() throws Exception {
        assertEquals(7, withoutStrikeSparebowlingGame.getEachRoundRealSocre(3));
    }

    @Test
    void return_false_when_not_strike(){
        assertEquals(false,bowlingGame.isStrike(bowlingGame.roundScores[1]));
    }

    @Test
    void return_true_when_strike(){
        assertEquals(true, bowlingGame.isStrike(bowlingGame.roundScores[3]));
    }

    @Test
    void return_false_when_two_strike(){
        assertEquals(false, onlyStrikeBowlingGame.isStrike(onlyStrikeBowlingGame.roundScores[10]));
    }

    @Test
    void return_false_when_without_spare(){
        assertEquals(false, bowlingGame.isSpare(bowlingGame.roundScores[2]));
    }

    @Test
    void return_true_when_spare(){
        assertEquals(true, bowlingGame.isSpare(bowlingGame.roundScores[0]));
    }

    @Test
    void test_get_next_time_score_when_twonext_is_false(){
        assertEquals(10, bowlingGame.getNextTimeScore(4, false));
    }

    @Test
    void should_return_two_next_time_score_when_two_next_is_true(){
        assertEquals(20, bowlingGame.getNextTimeScore(4, true));
        assertEquals(7,withoutStrikeSparebowlingGame.getNextTimeScore(3,true));
    }

    @Test
    void should_return_10_when_strike(){
        assertEquals(10, bowlingGame.getScoreFromRound(bowlingGame.roundScores[5]));
    }

    @Test
    void should_return_10_when_spare(){
        assertEquals(10, bowlingGame.getScoreFromRound(bowlingGame.roundScores[0]));
    }

    @Test
    void should_return_two_time_score_sum_when_without_spare_strike(){
        assertEquals(4, bowlingGame.getScoreFromRound(bowlingGame.roundScores[1]));
    }

    @Test
    void should_return_10_when_sign_is_X(){
        assertEquals(10, bowlingGame.getScoreFromTime('X'));
    }

    @Test
    void should_return_10_when_sign_is_SPARE(){
        assertEquals(10, bowlingGame.getScoreFromTime('/'));
    }





}
