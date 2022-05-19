package TopologyAPI.main.Model;

import java.util.ArrayList;

public class Topology implements ITopology{
    private String id;
    private ArrayList<ITopologyComponent> components;

    public Topology() {
        this.id = "";
        this.components = new ArrayList<ITopologyComponent>();
    }

    public Topology(String id, ArrayList<ITopologyComponent> components) {
        this.id = id;
        this.components = components;
    }

    //setters and getters
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public ArrayList<ITopologyComponent> getComponents() {
        return components;
    }

    @Override
    public void setComponents(ArrayList<ITopologyComponent> components) {
        this.components = components;        
    }

    @Override
    public void addComponent(ITopologyComponent component) {
        components.add(component);        
    }
}
