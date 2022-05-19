package TopologyAPI.main.Model.handlers;

import TopologyAPI.main.Model.ITopology;

public interface IJsonHandler {
    public ITopology parseJson(String filePath) throws Exception;
    public void writeJson(ITopology topology, String filePath) throws Exception;
}
