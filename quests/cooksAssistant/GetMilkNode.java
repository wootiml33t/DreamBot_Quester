package quests.cooksAssistant;

import quester.Main;
import quester.Node;
import org.dreambot.api.methods.Calculations;
import util.AreaProvider;
import util.ConstantsProvider;
import util.Utility;

import static org.dreambot.api.methods.MethodProvider.log;

public class GetMilkNode extends Node {
    public GetMilkNode(Main main, ConstantsProvider constantsProvider, AreaProvider areaProvider, Utility util) {
        super(main, constantsProvider, areaProvider, util);
    }
    @Override
    public boolean validate() {
        return !main.getInventory().contains(constants.IT_MILK)
                && main.getInventory().contains(constants.IT_BUCKET);
    }
    @Override
    public int execute() {
        log("GetMilk: Start");
        util.TravelTo(areas.dairyCowArea);
        main.getGameObjects().closest(constants.GO_DAIRYCOW).interact("Milk");
        main.sleepWhile( () -> !main.getInventory().contains(constants.IT_MILK), Calculations.random(35000, 40000));
        log("GetMilk: End");
        return 1000;
    }
}
