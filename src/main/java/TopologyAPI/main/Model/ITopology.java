package TopologyAPI.main.Model;

import java.util.ArrayList;

public interface ITopology {
    //setters and getters
    public String getId();
    public void setId(String id);
    public ArrayList<ITopologyComponent> getComponents();
    public void setComponents(ArrayList<ITopologyComponent> components);
    public void addComponent(ITopologyComponent component);

}
