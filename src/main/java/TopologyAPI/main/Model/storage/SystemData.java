package TopologyAPI.main.Model.storage;

import java.util.ArrayList;

import TopologyAPI.main.Model.ITopology;

public class SystemData implements ISystemData{
    private ArrayList<ITopology> topologies;

    public SystemData() {
        this.topologies = new ArrayList<ITopology>();
    }
    
}
