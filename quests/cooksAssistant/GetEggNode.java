package quests.cooksAssistant;

import quester.Main;
import quester.Node;
import util.AreaProvider;
import util.ConstantsProvider;
import util.Utility;

import static org.dreambot.api.methods.MethodProvider.log;

public class GetEggNode extends Node {
    public GetEggNode(Main main, ConstantsProvider constantsProvider, AreaProvider areaProvider, Utility util) {
        super(main, constantsProvider, areaProvider, util);
    }
    @Override
    public boolean validate() {
        return !main.getInventory().contains(constants.IT_EGG);
    }
    @Override
    public int execute() {
        log("GetEgg: Start");
        util.TravelTo(areas.eggArea);
        util.GetGroundItemIfNeeded(constants.IT_EGG);
        log("GetEgg: End");
        return 1000;
    }
}
