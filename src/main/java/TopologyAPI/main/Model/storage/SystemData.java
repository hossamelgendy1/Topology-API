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

    @Override
    public ITopology getTopology(String topologyID) {
        for (ITopology topology : topologies) {
            if (topology.getId().equals(topologyID)) {
                return topology;
            }
        }
        return null;
    }

    @Override
    public ArrayList<ITopology> getTopologies() {
        return topologies;
    }

    @Override
    public void addTopology(ITopology topology) {
        topologies.add(topology);
    }

    @Override
    public boolean removeTopology(String topologyID) {
        for (ITopology topology : topologies) {
            if (topology.getId().equals(topologyID)) {
                topologies.remove(topology);
                return true;
            }
        }
        return false;
    }
}
