package TopologyAPI.main.Controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import TopologyAPI.main.Model.ITopology;
import TopologyAPI.main.Model.ITopologyComponent;
import TopologyAPI.main.Model.handlers.JsonHandler;
import TopologyAPI.main.Model.storage.SystemData;

@RestController
public class UserController {

    @PostMapping("/user/readJSON/{filePath}")
    boolean readJSON(@PathVariable String filePath) throws Exception{
        ITopology topology = JsonHandler.getInstance().parseJson(filePath);
        SystemData.getInstance().addTopology(topology);
        return true; // if it is successful, otherwise it throws an exception
    };

    @PostMapping("/user/writeJSON/{topologyID}/{filePath}")
    boolean writeJSON(@PathVariable String topologyID, @PathVariable String filePath) throws Exception{
        ITopology topology = SystemData.getInstance().getTopology(topologyID);
        if (topology == null) {
            throw new Exception("Topology not found");
        }
        JsonHandler.getInstance().writeJson(topology, filePath);
        return true; // if it is successful, otherwise it throws an exception
    };

    @GetMapping("/user/queryTopologies")
    ArrayList<ITopology> queryTopologies() {
        return SystemData.getInstance().getTopologies();
    };

    @DeleteMapping("/user/deleteTopology/{topologyID}")
    boolean deleteTopology(@PathVariable String topologyID) throws Exception{
        if(!SystemData.getInstance().removeTopology(topologyID)) {
            throw new Exception("Topology not found");
        }
        return true;// if it is successful, otherwise it throws an exception
    };

    @GetMapping("/user/queryDevices/{topologyID}")
    ArrayList<ITopologyComponent> queryDevices(@PathVariable String topologyID) throws Exception{
        ITopology topology = SystemData.getInstance().getTopology(topologyID);
        if (topology == null) {
            throw new Exception("Topology not found");
        }
        return topology.getComponents();
    };

    @GetMapping("/user/queryDevicesWithNetlistNode/{topologyID}/{netlistNodeID}")
    ArrayList<ITopologyComponent> queryDevicesWithNetlistNode(@PathVariable String topologyID, @PathVariable String netlistNodeID) throws Exception{
        ITopology topology = SystemData.getInstance().getTopology(topologyID);
        if (topology == null) {
            throw new Exception("Topology not found");
        }
        return topology.getComponentsWithNetlist(netlistNodeID);
    };
}
