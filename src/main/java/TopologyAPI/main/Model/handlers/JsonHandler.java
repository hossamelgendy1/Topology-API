package TopologyAPI.main.Model.handlers;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import TopologyAPI.main.Model.ITopology;
import TopologyAPI.main.Model.ITopologyComponent;
import TopologyAPI.main.Model.Topology;
import TopologyAPI.main.Model.TopologyComponent;

public class JsonHandler implements IJsonHandler {

    private static JsonHandler instance;//to make the class singleton

    private JsonHandler() {
    }

    public static JsonHandler getInstance() {
        if (instance == null) {
            instance = new JsonHandler();
        }
        return instance;
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
            Iterator<?> keys = component.keySet().iterator();
            while(keys.hasNext()) {
                String key = (String)keys.next();
                if (key.equals("id")) {
                    topologyComponent.setId(component.get(key).toString());
                } else if (key.equals("type")) {
                    topologyComponent.setType(component.get(key).toString());
                } else if (key.equals("netlist")) {
                    JSONObject netlist = component.get("netlist") != null ? (JSONObject)component.get("netlist") : new JSONObject();
                    Iterator<?> netlistKeys = netlist.keySet().iterator();
                    //add the netlist object as key-value pairs to the netlist map
                    while(netlistKeys.hasNext()) {
                        String netlistKey = (String)netlistKeys.next();
                        topologyComponent.getNetlist().put(netlistKey, netlist.get(netlistKey).toString());
                    }
                } else { //if it gets here, it is the details object
                    //strore the name of the key in the detailsName field
                    topologyComponent.setDetailsName(key);
                    //the store the object as a map of key-value pairs
                    JSONObject details = component.get(key) != null ? (JSONObject)component.get(key) : new JSONObject();
                    Iterator<?> detailsKeys = details.keySet().iterator();
                    while(detailsKeys.hasNext()) {
                        String detailsKey = (String)detailsKeys.next();
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
        HashMap<String, Object> objectMap = new HashMap<String, Object>();
        //add the id to the JSON object
        objectMap.put("id", topology.getId());
        //add the array of components to the JSON object
        JSONArray jsonComponents = new JSONArray();
        for (ITopologyComponent component : topology.getComponents()) {
            HashMap<String, Object> componentMap = new HashMap<String, Object>();
            //add the id to the JSON object
            componentMap.put("id", component.getId());
            //add the type to the JSON object
            componentMap.put("type", component.getType());
            //add the netlist to the JSON object
            HashMap<String, Object> netlistMap = new HashMap<String, Object>();
            for (String key : component.getNetlist().keySet()) {
                netlistMap.put(key, component.getNetlist().get(key));
            }
            JSONObject jsonNetlist = new JSONObject(netlistMap);
            componentMap.put("netlist", jsonNetlist);
            //add the details to the JSON object
            HashMap<String, Object> detailsMap = new HashMap<String, Object>();
            for (String key : component.getDetails().keySet()) {
                detailsMap.put(key, component.getDetails().get(key));
            }
            JSONObject jsonDetails = new JSONObject(detailsMap);
            componentMap.put(component.getDetailsName(), jsonDetails);
            //add the component to the array of components
            JSONObject jsonComponent = new JSONObject(componentMap);
            jsonComponents.add(jsonComponent);
        }
        objectMap.put("components", jsonComponents);
        JSONObject jsonObject = new JSONObject(objectMap);
        //write the JSON object to a file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonObject.toJSONString());
         }
        
    }
    
}
