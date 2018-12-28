package quests.cooksAssistant;

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
        return !main.getQuests().isStarted(Quest.COOKS_ASSISTANT);
    }
    @Override
    public int execute() {
        log("StartQuest: Start");
        util.TravelTo(areas.cookArea);
        main.getNpcs().closest(constants.NPC_COOK).interact(constants.AC_TALK);
        main.getDialogues().continueDialogue();
        main.sleep(600+Rand.nextInt(600));
        main.getDialogues().chooseOption(main.getDialogues().getOptionIndexContaining("What's wrong?"));
        main.sleep(600+Rand.nextInt(600));
        for (int i = 0; i < 3; i++) {
            main.getDialogues().continueDialogue();
            main.sleep(600+Rand.nextInt(600));
        }
        main.getDialogues().chooseOption(main.getDialogues().getOptionIndexContaining("I'm always happy to"));
        for (int i = 0; i < 3; i++) {
            main.getDialogues().continueDialogue();
            main.sleep(600+Rand.nextInt(600));
        }

        log("StartQuest: End");
        return 1000;
    }
}
