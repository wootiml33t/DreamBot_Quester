package quests.cooksAssistant;

import quester.Main;
import quester.Node;
import org.dreambot.api.methods.Calculations;
import util.AreaProvider;
import util.ConstantsProvider;
import util.Utility;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleep;

public class GetBucketNode extends Node {
    public GetBucketNode(Main main, ConstantsProvider constantsProvider, AreaProvider areaProvider, Utility util) {
        super(main, constantsProvider, areaProvider, util);
    }
    @Override
    public boolean validate() {
        return !main.getInventory().contains(constants.IT_BUCKET)
                && !main.getInventory().contains(constants.IT_MILK);
    }
    @Override
    public int execute() {
        log("GetBucket: Start");
        util.TravelTo(areas.cookArea);
        util.ClosestSpecifiedGameObjectInteract(constants.GO_TRAPDOOR,constants.AC_CLIMB);
        sleep(Calculations.random(4000,5000));
        util.TravelTo(areas.bucketArea);
        util.GetGroundItemIfNeeded(constants.IT_BUCKET);
        util.TravelTo(areas.cookBasementArea);
        sleep(Calculations.random(2000,3000));
        util.ClimbClosest(1,true,false);
        log("GetBucket: End");
        return 1000;
    }
}
