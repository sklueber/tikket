

public class tikketClient {
    private String tikketServerIP;
    private int tikketServerPort;
    private int SrvVa_ID;
    private String SrvVa_name;

    public tikketClient(String ServerIP, int ServerPort) {
        tikketServerIP = ServerIP;
        tikketServerPort = ServerPort;
        //Hier muss auch ein grundlegender Sync stattfinden
    }
}
