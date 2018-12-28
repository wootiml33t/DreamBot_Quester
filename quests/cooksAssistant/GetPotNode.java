package quests.cooksAssistant;

import quester.Main;
import quester.Node;
import util.AreaProvider;
import util.ConstantsProvider;
import util.Utility;

import static org.dreambot.api.methods.MethodProvider.log;

public class GetPotNode extends Node {
    public GetPotNode(Main main, ConstantsProvider constantsProvider, AreaProvider areaProvider, Utility util) {
        super(main, constantsProvider, areaProvider, util);
    }
    @Override
    public boolean validate() {
        return !main.getInventory().contains(constants.IT_POT)
                && !main.getInventory().contains(constants.IT_FLOUR);
    }
    @Override
    public int execute() {
        log("GetPot: Start");
        util.TravelTo(areas.cookArea);
        util.GetGroundItemIfNeeded(constants.IT_POT);
        log("GetPot: End");
        return 1000;
    }
}
