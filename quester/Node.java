package quester;

import util.AreaProvider;
import util.ConstantsProvider;
import util.Utility;

public abstract class Node {
    protected final Main main;
    protected final ConstantsProvider constants;
    protected final AreaProvider areas;
    protected final Utility util;
    protected Node(Main main, ConstantsProvider constantsProvider, AreaProvider areaProvider, Utility util) {
        this.main = main;
        this.constants = constantsProvider;
        this.areas = areaProvider;
        this.util = util;
    }
    public abstract boolean validate();
    public abstract int execute();
    //I want all children to augment the execute method (which here should simply set the visited flag to true) but i dont want to have to
    //call the parent method in each child node... hence my usage of the word augment. Can I do this wiht a virtual class rather than an abstract class?
}
