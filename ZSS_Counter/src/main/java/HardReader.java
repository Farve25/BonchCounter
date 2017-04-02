import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class HardReader{

    private static SerialPort serialPort;

    HardReader(String port) {
        //this.parent = parent;
        serialPort = new SerialPort(port);
        //Открываем порт
        try {
            serialPort.openPort();
            //Выставляем параметры
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Включаем аппаратное управление потоком
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
        } catch (SerialPortException ex) {
            ex.getMessage();
        }
    }

    public static void startReading() {
        try {
            //Устанавливаем ивент лисенер и маску
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            //serialPort.
            //Отправляем запрос устройству
            serialPort.writeString("Get data");
        } catch (SerialPortException ex) {
            ex.getMessage();
        }
    }

    public static void closePort(){
        try {
            serialPort.closePort();
        } catch (SerialPortException e) {
            System.out.println(e.getMessage());
        }
    }

    private static class PortReader implements SerialPortEventListener {

        synchronized public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    String data = serialPort.readString(event.getEventValue());
                    System.out.println(data);
                    serialPort.removeEventListener();
                    if (data.length() == 8) {


                        QueryProcessor.process(data);
                    } else {
                        System.out.println("Карта считана неправильно, попробуйте еще раз");
                    }

                } catch (SerialPortException ex) {
                    ex.getMessage();
                } finally {
                    try {
                        Menu.menu();
                    } catch (Exception e) {

                    }
                }
            }
        }

    }
}