public class SystemInfo {
    //strings for system information
    private String systemStatus;
    private String currentVersion;

    SystemInfo(String systemStatus, String currentVersion) {//constructor
        this.systemStatus = systemStatus;
        this.currentVersion = currentVersion;
    }

    //getters
    public String getSystemStatus() {
        return systemStatus;
    }
    public String getCurrentVersion() {
        return currentVersion;
    }

    public static void Menu() {//menu for system info

        SystemInfo sysInfo = new SystemInfo("Good", "1.2.0");

        System.out.println("\n--------------------------------");
        System.out.println("       System Information       ");
        System.out.println("--------------------------------\n");

        System.out.println("System Status: " + sysInfo.systemStatus);
        System.out.println("Software Version: v" + sysInfo.currentVersion);
    }
}
