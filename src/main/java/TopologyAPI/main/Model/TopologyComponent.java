package TopologyAPI.main.Model;

import java.util.HashMap;
import java.util.Map;

public class TopologyComponent  implements ITopologyComponent{
    private String type, id, detailsName;
    private Map<String, Double> details;
    private Map<String,String> netlist;

    public TopologyComponent(String type, String id, String detailsName, Map<String, Double> details, Map<String, String> netlist) {
        this.type = type;
        this.id = id;
        this.detailsName = detailsName;
        this.details = details;
        this.netlist = netlist;
    }

    public TopologyComponent() {
        this.type = "";
        this.id = "";
        this.detailsName = "";
        this.details = new HashMap<String, Double>();
        this.netlist = new HashMap<String, String>();
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getDetailsName() {
        return detailsName;
    }

    @Override
    public void setDetailsName(String detailsName) {
        this.detailsName = detailsName;
    }

    @Override
    public Map<String, Double> getDetails() {
        return details;
    }

    @Override
    public void setDetails(Map<String, Double> details) {
        this.details = details;
    }
    
    @Override
    public Map<String, String> getNetlist() {
        return netlist;
    }

    @Override
    public void setNetlist(Map<String, String> netlist) {
        this.netlist = netlist;
    }
}
