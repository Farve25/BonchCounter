/**
 * Created by Farvest on 31.03.2017.
 */
public class TestPro {
    public static void main(String arg[]) throws Exception {
        PortFinder pf = new PortFinder("PortFinder");
        pf.start();
        StudentDB.setConnection();
        Menu me = new Menu();
        me.menu();
        pf.join();
    }
}
