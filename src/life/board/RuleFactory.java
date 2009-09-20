package life.board;

import life.rules.*;

import java.util.ArrayList;
import java.util.List;

public class RuleFactory
{
    private RuleFactory() {}

    public static List<RuleHandler> allRules()
    {
        List<RuleHandler> rules = new ArrayList<RuleHandler>();
        rules.add(new DieIfLessThanTwoLiveNeighboursRule());
        rules.add(new DieIfMoreThanThreeLiveNeighboursRule());
        rules.add(new ComeToLifeIfExactlyThreeLiveNeighboursRule());
        rules.add(new StayTheSameIfTwoOrThreeNeighboursRule());
        return rules;
    }
}
