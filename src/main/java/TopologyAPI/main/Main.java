package TopologyAPI.main;
import TopologyAPI.main.Model.ITopology;
import TopologyAPI.main.Model.handlers.IJsonHandler;
import TopologyAPI.main.Model.handlers.JsonHandler;

public class Main {
    public static void main(String[] args) {
        IJsonHandler jsonHandler = new JsonHandler();
        try {
            ITopology topology = jsonHandler.parseJson("E:/Master Micro internship/topology.json");
            jsonHandler.writeJson(topology, "E:/Master Micro internship/topology2.json");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
