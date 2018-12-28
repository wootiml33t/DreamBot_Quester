package quests.sheepSheerer;

import quester.Main;
import quester.Node;
import org.dreambot.api.methods.quest.Quest;
import org.dreambot.api.methods.tabs.Tab;
import util.AreaProvider;
import util.ConstantsProvider;
import util.Utility;

import static org.dreambot.api.methods.MethodProvider.log;
import static util.Utility.Rand;

public class EndQuestNode extends Node {
    public EndQuestNode(Main main, ConstantsProvider constantsProvider, AreaProvider areaProvider, Utility util) {
        super(main, constantsProvider, areaProvider, util);
    }
    @Override
    public boolean validate() {
        return main.getInventory().contains(constants.quest.sheepSheerer.IT_BALL_OF_WOOL);
    }
    @Override
    public int execute() {
        log("EndQuest: Start");
        util.TravelTo(areas.quest.sheepShearer.farmerFredArea);
        main.getNpcs().closest(constants.quest.sheepSheerer.NPC_FRED).interact(constants.AC_TALK);
        main.sleep(600+Rand.nextInt(6000));
        main.getDialogues().continueDialogue();
        main.sleep(600+Rand.nextInt(6000));
        main.getDialogues().chooseOption(main.getDialogues().getOptions()[0]);
        main.sleep(600+Rand.nextInt(6000));
        while (main.getDialogues().canContinue()) {
            main.getDialogues().clickContinue();
            main.sleep(600+Rand.nextInt(3000));
        }
        log("EndQuest: End");
        return 0;
    }
}
