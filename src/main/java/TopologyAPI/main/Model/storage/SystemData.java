package TopologyAPI.main.Model.storage;

import java.util.ArrayList;

import TopologyAPI.main.Model.ITopology;

public class SystemData implements ISystemData{
    private static SystemData instance;//to make the class singleton
    private ArrayList<ITopology> topologies;

    private SystemData() {
        this.topologies = new ArrayList<ITopology>();
    }
    
    public static SystemData getInstance() {
        if (instance == null) {
            instance = new SystemData();
        }
        return instance;
    }
}
