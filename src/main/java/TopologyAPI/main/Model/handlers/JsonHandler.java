package TopologyAPI.main.Model.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import TopologyAPI.main.Model.ITopology;
import TopologyAPI.main.Model.ITopologyComponent;
import TopologyAPI.main.Model.Topology;
import TopologyAPI.main.Model.TopologyComponent;

public class JsonHandler implements IJsonHandler {

    //constructor
    public JsonHandler() {
    }

    @Override
    public ITopology parseJson(String filePath) throws Exception {
        //parse the JSON file into a JSONObject
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject)parser.parse(new FileReader(filePath));

        //get the topology id
        ITopology topology = new Topology();
        topology.setId(obj.get("id").toString());

        //get the array of components
        ArrayList<ITopologyComponent> components = new ArrayList<ITopologyComponent>();
        JSONArray jsonComponents = obj.get("components") != null ? (JSONArray)obj.get("components") : new JSONArray();
        
        //iterate through the array of components
        for (int i = 0; i < jsonComponents.size(); i++) {
            ITopologyComponent topologyComponent = new TopologyComponent();
            JSONObject component = jsonComponents.get(i) != null ? (JSONObject)jsonComponents.get(i) : new JSONObject();
            
            //get the keys of the component object
            Iterator<String> keys = component.keySet().iterator();
            while(keys.hasNext()) {
                String key = keys.next();
                if (key.equals("id")) {
                    topologyComponent.setId(component.get(key).toString());
                } else if (key.equals("type")) {
                    topologyComponent.setType(component.get(key).toString());
                } else if (key.equals("netlist")) {
                    JSONObject netlist = component.get("netlist") != null ? (JSONObject)component.get("netlist") : new JSONObject();
                    Iterator<String> netlistKeys = netlist.keySet().iterator();
                    //add the netlist object as key-value pairs to the netlist map
                    while(netlistKeys.hasNext()) {
                        String netlistKey = netlistKeys.next();
                        topologyComponent.getNetlist().put(netlistKey, netlist.get(netlistKey).toString());
                    }
                } else { //if it gets here, it is the details object
                    //strore the name of the key in the detailsName field
                    topologyComponent.setDetailsName(key);
                    //the store the object as a map of key-value pairs
                    JSONObject details = component.get(key) != null ? (JSONObject)component.get(key) : new JSONObject();
                    Iterator<String> detailsKeys = details.keySet().iterator();
                    while(detailsKeys.hasNext()) {
                        String detailsKey = detailsKeys.next();
                        topologyComponent.getDetails().put(detailsKey, Double.parseDouble(details.get(detailsKey).toString()));
                    }
                }
            }
            components.add(topologyComponent);
        }
        topology.setComponents(components);
        return topology;
    }

    @Override
    public void writeJson(ITopology topology, String filePath) throws Exception{
        JSONObject obj = new JSONObject();
        //add the id to the JSON object
        obj.put("id", topology.getId());
        //add the array of components to the JSON object
        JSONArray jsonComponents = new JSONArray();
        for (ITopologyComponent component : topology.getComponents()) {
            JSONObject jsonComponent = new JSONObject();
            //add the id to the JSON object
            jsonComponent.put("id", component.getId());
            //add the type to the JSON object
            jsonComponent.put("type", component.getType());
            //add the netlist to the JSON object
            JSONObject jsonNetlist = new JSONObject();
            for (String key : component.getNetlist().keySet()) {
                jsonNetlist.put(key, component.getNetlist().get(key));
            }
            jsonComponent.put("netlist", jsonNetlist);
            //add the details to the JSON object
            JSONObject jsonDetails = new JSONObject();
            for (String key : component.getDetails().keySet()) {
                jsonDetails.put(key, component.getDetails().get(key));
            }
            jsonComponent.put(component.getDetailsName(), jsonDetails);
            jsonComponents.add(jsonComponent);
        }
        obj.put("components", jsonComponents);
        //write the JSON object to a file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(obj.toJSONString());
         }
        
    }
    
}
