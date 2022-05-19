package TopologyAPI.main.Model.storage;

import java.util.ArrayList;

import TopologyAPI.main.Model.ITopology;

public interface ISystemData {
    public void addTopology(ITopology topology);
    public ITopology getTopology(String topologyID);
    public boolean removeTopology(String topologyID);
    public ArrayList<ITopology> getTopologies();
}
