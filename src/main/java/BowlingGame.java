import java.util.Arrays;

public class BowlingGame {

    static char STRIKE = 'X';
    static char SPARE = '/';
    static int FULL_SCORE = 10;
    static int FIRSTTIME = 0;
    static int SECONDTIME = 1;
    static int TENROUND = 10;
    String[] roundScores;

    public BowlingGame(String[] roundScores) throws Exception {
        if (!checkRoundScores(roundScores))
            throw new Exception("parameter error!");
        this.roundScores = roundScores;
    }

    public boolean checkRoundScores(String[] roundScores){
        if (roundScores.length > 11 || roundScores.length < 10)
            return false;
        for (String score : roundScores){
            if (score.length() < 1 || score.length() > 2||!score.matches("[0-9X/]+"))
                return false;
        }
        return true;
    }

    public int getScoreFromTime(char sign){
        if (sign == STRIKE || sign == SPARE)
            return FULL_SCORE;
        return sign - '0';
    }

    public int getScoreFromRound(String roundScore){
        if (isStrike(roundScore) || isSpare(roundScore))
            return FULL_SCORE;
        return getScoreFromTime(roundScore.charAt(FIRSTTIME)) + getScoreFromTime(roundScore.charAt(SECONDTIME));
    }

    public int getEachRoundRealSocre(int nowRound){
        String nowRoundScore = this.roundScores[nowRound - 1];
        if (isStrike(nowRoundScore) || isSpare(nowRoundScore))
            return getScoreFromRound(nowRoundScore) + getNextTimeScore(nowRound, isStrike(nowRoundScore));

        return getScoreFromRound(nowRoundScore);
    }

    public int getTotalScore(){
        int totalScore = 0;
        for (int i = 0; i < 10; i++){
            totalScore = totalScore + getEachRoundRealSocre(i+1);
        }
        return totalScore ;

    }
    public int getNextTimeScore(int nowRound, boolean twoNext){
        String nextRoundScores = this.roundScores[nowRound];
        String twoNextRoundScores = this.roundScores[nowRound + (nowRound== TENROUND ?0:1)];
        if (!twoNext)
            return getScoreFromTime(nextRoundScores.charAt(FIRSTTIME));
        if (isStrike(nextRoundScores))
            return getScoreFromRound(nextRoundScores) + getScoreFromTime(twoNextRoundScores.charAt(FIRSTTIME));
        return getScoreFromRound(nextRoundScores);
    }

    public boolean isStrike(String roundScore){
        if (roundScore.length() == 1 && roundScore.charAt(FIRSTTIME) == STRIKE)
            return true;
        return false;
    }

    public boolean isSpare(String roundScore){
        if (roundScore.length() == 2 && roundScore.charAt(SECONDTIME) == SPARE)
            return true;
        return false;
    }

}
