package TopologyAPI.main.Model;

import java.util.Map;

public interface ITopologyComponent {
    //setters and getters
    public String getType();
    public void setType(String type);
    public String getId();
    public void setId(String id);
    public String getDetailsName();
    public void setDetailsName(String detailsName);
    public Map<String, Double> getDetails();
    public void setDetails(Map<String, Double> details);
    public Map<String, String> getNetlist();
    public void setNetlist(Map<String, String> netlist);
}
