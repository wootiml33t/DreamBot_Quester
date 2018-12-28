package quester;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import quests.cooksAssistant.*;
import util.AreaProvider;
import util.ConstantsProvider;
import util.Utility;

@ScriptManifest(
        author = "Hitchhiker",
        description = "Completes Cook's Assistant. The script will gather all the items needed if you don't already have them in your inventory." +
                "ie. Bucket, Pot. Script will complete quicker if these items area already had so for maximum efficiency this " +
                "should be ran after the completion of Tutorial Island. Script can be started from anywhere in the world at ground level.",
        category = Category.QUEST,
        version = 0.03,
        name = "Cook's Assistant Quester (Free Edition)"
)
public class Main extends AbstractScript {
    private ConstantsProvider constantsProvider = ConstantsProvider.getInstance();
    private AreaProvider areaProvider = AreaProvider.getInstance();
    private Utility utility = Utility.getInstance(this);
    private Node nodes[];
    private Node sheepSheererNodes[];
    @Override
    public void onStart()
    {
        nodes = new Node[]{
            new StartQuestNode(this, constantsProvider, areaProvider, utility),
            new GetPotNode(this, constantsProvider, areaProvider, utility),
            new GetBucketNode(this, constantsProvider, areaProvider, utility),
            new GetEggNode(this, constantsProvider, areaProvider, utility),
            new GetMilkNode(this, constantsProvider, areaProvider, utility),
            new GetGrainNode(this, constantsProvider, areaProvider, utility),
            new GetFlourNode(this, constantsProvider, areaProvider, utility),
            new EndQuestNode(this, constantsProvider, areaProvider, utility)
        };

        sheepSheererNodes = new Node[]{
            new quests.sheepSheerer.StartQuestNode(this, constantsProvider, areaProvider, utility),
            new quests.sheepSheerer.GetGoldNode(this, constantsProvider, areaProvider, utility),
            new quests.sheepSheerer.ShearSheepNode(this, constantsProvider, areaProvider, utility),
            new quests.sheepSheerer.SpinWoolNode(this, constantsProvider, areaProvider, utility),
            new quests.sheepSheerer.EndQuestNode(this, constantsProvider, areaProvider, utility)
        };
    }
    @Override
    public int onLoop() {
        /*
        for (Node node : cooksAssistantNodes)
            if (node.validate())
                return node.execute();
                */
        for (Node node : sheepSheererNodes)
            if (node.validate())
                return node.execute();
        log("NO VALID NODE");
        return 0;
    }
}
