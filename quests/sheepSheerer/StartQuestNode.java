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

public class StartQuestNode extends Node {
    public StartQuestNode(Main main, ConstantsProvider constantsProvider, AreaProvider areaProvider, Utility util) {
        super(main, constantsProvider, areaProvider, util);
    }
    @Override
    public boolean validate() {
        main.getTabs().openWithMouse(Tab.QUEST);
        //return !main.getQuests().isStarted(Quest.SHEEP_SHEARER);
        return false;
    }
    @Override
    public int execute() {
        log("StartQuest: Start");
        util.TravelTo(areas.quest.sheepShearer.farmerFredArea);
        main.getNpcs().closest(constants.quest.sheepSheerer.NPC_FRED).interact(constants.AC_TALK);
        main.sleep(600+Rand.nextInt(6000));
        main.getDialogues().continueDialogue();
        main.sleep(600+Rand.nextInt(6000));
        main.getDialogues().chooseOption(main.getDialogues().getOptionIndexContaining("I'm looking for a quest."));
        main.sleep(600+Rand.nextInt(6000));
        while (main.getDialogues().canContinue()) {
            main.getDialogues().clickContinue();
            main.sleep(600+Rand.nextInt(3000));
        }
        main.getDialogues().chooseOption(main.getDialogues().getOptions()[0]);
        while (main.getDialogues().canContinue()) {
            main.getDialogues().clickContinue();
            main.sleep(600+Rand.nextInt(3000));
        }
        log("StartQuest: End");
        return 1000;
    }
}
