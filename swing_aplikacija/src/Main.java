
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.MaskFormatter;

public class Main {
    public static JPanel panel;
    public static JFrame f;
    static ButtonGroup group;
    static JRadioButton radiob;
    static JRadioButton radiob2;
    static JButton btn;
    static JButton b1;
    static JComboBox combo;
    public static Timer t;
    public static Date d;
    public static JFormattedTextField jtb;
    public static JFormattedTextField jtb2;
    static String uneto;
    static String unetBroj2;
    static MaskFormatter mf;
    static DefaultFormatter format;
    static  MaskFormatter mf2;
    static JFrame treciFrame;
    static Color c;
    static JPanel jt4;
    static JLabel izabranaBoja;
    static JColorChooser jc;
    
    public static void clickOnsettings() {
        f = new JFrame();
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                f.dispose();
            }
        });
        panel = new JPanel();

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        panel.setSize(400, 400);
        group = new ButtonGroup();
        radiob = new JRadioButton("On time");
        group.add(radiob);

        radiob.addActionListener((ActionEvent e) -> {
            jtb.setEnabled(true);
            jtb2.setEnabled(false);
        });
        
        d = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        uneto = formatter.format(d);
       
        try {
            mf = new MaskFormatter("##:##:##");
            jtb = new JFormattedTextField(mf);
            jtb.setText(uneto);
            jtb.setEnabled(false);
            
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        radiob2 = new JRadioButton("Coundown(sec)");
        radiob2.setSelected(true);
        group.add(radiob2);

        try {
            jtb2= new JFormattedTextField(new MaskFormatter("#"));
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
       

      
        radiob2.addActionListener((ActionEvent e) -> {
            jtb.setEnabled(false);
            jtb2.setEnabled(true);
        });
        
        jt4 = new JPanel();
        izabranaBoja = new JLabel();
        jt4.add(izabranaBoja);
        btn = new JButton("Choose color");
        btn.addActionListener((ActionEvent e) -> {
            chooseColor();
        });

        JLabel jcombo = new JLabel("Speed");
        combo =new JComboBox();
        combo.setMaximumSize(new Dimension(50, 10));
        combo.addItem("1");
        combo.addItem("2");
        combo.addItem("3");
        combo.addItem("4");
        combo.addItem("5");

        b1 = new JButton("START");
        b1.addActionListener((ActionEvent e) -> {
            clickOnStart();
        });

        JButton b2 = new JButton("STOP");
        b2.addActionListener((ActionEvent e) -> {
            clickOnStop();
        });

        layout.setHorizontalGroup(
                layout.createSequentialGroup().addGap(20)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(radiob)
                                .addComponent(radiob2)
                                .addComponent(btn)
                                .addComponent(jcombo)
                                .addComponent(combo)
                                .addComponent(b1)
                        ).addGap(30)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(jtb)
                                .addComponent(jtb2)
                                .addComponent(jt4)
                                .addComponent(b2)
                        )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(radiob)
                                        .addComponent(jtb)
                        ).addGap(10)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(radiob2)
                                        .addComponent(jtb2)
                        ).addGap(30)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(btn)
                                        .addComponent(jt4)
                        ).addGap(10)
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(jcombo)
                        ).addGap(10)
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(combo)
                        ).addGap(30)
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(b1)
                                        .addComponent(b2)
                        ).addGap(30)
        );
        panel.setBounds(0, 0, 300, 300);
        f.setSize(400, 400);
        f.add(panel);
        f.requestFocus();
        f.setVisible(true);
    }
    
    public static void chooseColor() {
        jc = new JColorChooser();

        JDialog dialog = JColorChooser.createDialog(btn, "Choose color", true, jc, (ActionEvent e) -> {
            c = jc.getColor();
            jt4.setBackground(c);
            izabranaBoja.setText("Izabrana boja");
        }, null);
        dialog.setVisible(true);
    }
    
    public static void clickOnStart() {
        radiob.setEnabled(false);
        radiob2.setEnabled(false);
        jtb.setEnabled(false);
        jtb2.setEnabled(false);
        jt4.setEnabled(false);
        btn.setEnabled(false);
        combo.setEnabled(false);
        b1.setEnabled(false);

        if (c == null) {
            JOptionPane.showMessageDialog(panel, "Izaberite boju.","",JOptionPane.ERROR_MESSAGE);
            clickOnStop();
            return;
        }

        if (radiob.isSelected()) {
            firstRadioSelected();
        } else {
            secondRadioSelected();
        }
    }
    
    public static void firstRadioSelected() {
        uneto = jtb.getText();
        String[] unetText = uneto.split(":");
        if (unetText.length != 3 ) {
            JOptionPane.showMessageDialog(panel, "Pogresan format.","12:00:00",JOptionPane.ERROR_MESSAGE);
            clickOnStop();
            return;
        }

        for (String unetText1 : unetText) {
            String[] entry = unetText1.split(" ");
            if (entry.length != 1 || entry[0].length() != 2) {
                JOptionPane.showMessageDialog(panel, "Pogresan format.","12:00:00",JOptionPane.ERROR_MESSAGE);
                clickOnStop();
                return;
            }
        }
        int satBr = Integer.parseInt(unetText[0]);
        if(satBr>11){
            JOptionPane.showMessageDialog(panel, "Pogresan unos sata.","12:00:00",JOptionPane.ERROR_MESSAGE);
            clickOnStop();
            return;
        }
        String min = unetText[1];
        int minBr = Integer.parseInt(min);
        if(minBr>=60){
            JOptionPane.showMessageDialog(panel, "Pogresan unos minuta.","",JOptionPane.ERROR_MESSAGE);
            clickOnStop();
            return;
        }
        String sekund = unetText[2];
        int sekundBr = Integer.parseInt(sekund);
        if(sekundBr>=60){
            JOptionPane.showMessageDialog(panel, "Pogresan unos sekunda.","",JOptionPane.ERROR_MESSAGE);
            clickOnStop();
            return;
        }
        int userTime = (satBr * 3600) + (minBr *60) + sekundBr;
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        int currentTime = (hour*3600) + (minute*60) + second;
        int delay = userTime - currentTime;

        t = new Timer(delay * 500, (ActionEvent e) -> {
            clickOnStart();
            createThirdFrame();
            t.stop();
        });

            t.setRepeats(false);
            t.start();
    }
    
    public static void secondRadioSelected() {
        unetBroj2 =jtb2.getText();
        if (unetBroj2.isEmpty() || unetBroj2.equals(" ")) {
            JOptionPane.showMessageDialog(panel, "Unesite vrednost.","",JOptionPane.ERROR_MESSAGE);
            clickOnStop();
            return;
        }
        int unetBroj = Integer.parseInt(unetBroj2);
        int elapsed=unetBroj*1000;
       
        t = new Timer(elapsed , (ActionEvent e) -> {
            createThirdFrame();
            t.stop();
        });
        t.setRepeats(false);
        t.start();
    }
    
    public static void clickOnStop() {
        radiob.setEnabled(true);
        radiob2.setEnabled(true);
        if (radiob.isSelected()) {
            jtb.setEnabled(true);
        } else {
            jtb2.setEnabled(true);
        }

        btn.setEnabled(true);
        jt4.setEnabled(true);
        combo.setEnabled(true);
        b1.setEnabled(true);
        if (t != null) {
            t.stop();
        }

        if(treciFrame!=null) {
            treciFrame.dispose();
        }       
    }
    
    public static void createThirdFrame() {
        treciFrame= new JFrame();
        treciFrame.setSize(400, 400);
        treciFrame.setLocation(401, 0);
        JPanel treciJPanell = new JPanel();
        treciFrame.add(treciJPanell);

        treciFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clickOnStop();
            }
        });

        int brzina = combo.getSelectedIndex() + 1;
        treciJPanell.setBackground(Color.WHITE);
        
        Timer timer = new Timer(brzina*1000 , (ActionEvent e) -> {
            if (treciJPanell.getBackground().getRGB() == c.getRGB()) {
                treciJPanell.setBackground(Color.WHITE);
            } else {
                treciJPanell.setBackground(c);
            }
            treciFrame.repaint();
        });
        timer.setRepeats(true);
        timer.start();
        treciFrame.getContentPane().setBackground(c);
        treciFrame.setVisible(true);
    }

    public static void main(String[] args) {
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.pink);
        UIManager.put("Button.background", Color.LIGHT_GRAY);

        int odluka = JOptionPane.showOptionDialog(null, "Choose option", "Option dialog", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Settings", "Cancel"}, null);

        if (odluka == JOptionPane.OK_OPTION) {
            clickOnsettings();
        }
    }
}
