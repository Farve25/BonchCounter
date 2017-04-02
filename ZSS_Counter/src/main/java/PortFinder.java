import jssc.SerialPortList;

class PortFinder extends Thread {
    private static String port = ""; // Порт к которому подключен считыватель
    private static volatile boolean status = false; //Подключен ли считыватель к порту
    private HardReader hr = null;
    private static volatile boolean stop=true;

    PortFinder(String nameOfThread){
        super(nameOfThread);
    }


    public void run() {
        while (stop) {
            String currentPort = findPort();
            if (currentPort != null) {
                status = true;
                if (port.equals(currentPort) == false){
                    closePort();
                    port = currentPort;
                    hr = new HardReader(port);
                }

            } else {
                status = false;
                System.out.println("Считыватель не найден!!! Проверьте подключение.");
            }
            try{
                sleep(5000);
            } catch (InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
        }
        closePort();
    }

    private void closePort(){
        if (hr != null) {
            hr.closePort();
        }
    }

    private String findPort() {
        String[] portNames = SerialPortList.getPortNames();
        if (portNames.length > 0){
            return portNames[0];
        } else {
            return null;
        }
        //return portNames != null ? null :portNames[0];
    }

    static boolean getStatus(){
        return status;
    }

    static void setStop(){
        stop = false;
    }


}
