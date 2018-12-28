package quests.cooksAssistant;

import quester.Main;
import quester.Node;
import org.dreambot.api.methods.quest.Quest;
import org.dreambot.api.methods.tabs.Tab;
import util.AreaProvider;
import util.ConstantsProvider;
import util.Utility;

import static org.dreambot.api.methods.MethodProvider.log;

public class EndQuestNode extends Node {
    public EndQuestNode(Main main, ConstantsProvider constantsProvider, AreaProvider areaProvider, Utility util) {
        super(main, constantsProvider, areaProvider, util);
    }
    @Override
    public boolean validate() {
        main.getTabs().openWithMouse(Tab.QUEST);
        return !main.getQuests().isStarted(Quest.COOKS_ASSISTANT);
    }
    @Override
    public int execute() {
        log("EndQuest: Start");
        util.TravelTo(areas.cookArea);
        main.getNpcs().closest(constants.NPC_COOK).interact(constants.AC_TALK);
        main.getDialogues().continueDialogue();
        main.sleep(600+util.Rand.nextInt(600));
        main.getDialogues().chooseOption(main.getDialogues().getOptionIndexContaining("What's wrong?"));
        main.sleep(600+util.Rand.nextInt(600));
        for (int i = 0; i < 3; i++) {
            main.getDialogues().continueDialogue();
            main.sleep(600+util.Rand.nextInt(600));
        }
        main.getDialogues().chooseOption(main.getDialogues().getOptionIndexContaining("I'm always happy to"));
        for (int i = 0; i < 3; i++) {
            main.getDialogues().continueDialogue();
            main.sleep(600+util.Rand.nextInt(600));
        }
        log("EndQuest: End");
        return 0;
    }

    /*


    public boolean CompleteCooksAssistant() {
        TravelTo(cookArea);
        getNpcs().closest(COOK).interact("Talk-to");
        for (int i = 0; i < 9; i++) {
            getDialogues().continueDialogue();
            sleep(600+Rand.nextInt(600));
        }
        return true;
    }


     */





}
