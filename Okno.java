import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Okno extends JFrame {
    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldC;
    private JButton btn1;
    private JButton btn2;
    private JPanel mainPanel;



    JFileChooser vyberSoubor = new JFileChooser();

    public Okno() {
        setContentPane(mainPanel);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu soubor = new JMenu("Soubor");
        menuBar.add(soubor);
        JMenu akce = new JMenu("Akce");
        menuBar.add(akce);
        JMenuItem nacti = new JMenuItem("Načti...");
        soubor.add(nacti);
        JMenuItem uloz = new JMenuItem("Ulož");
        soubor.add(uloz);
        JMenuItem kopiruj = new JMenuItem("Kopíruj A do B i C");
        akce.add(kopiruj);
        JMenuItem lzeSestrojit = new JMenuItem("Lze sestrojit trojúhelník?");
        akce.add(lzeSestrojit);



        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldB.setText(textFieldA.getText());
                textFieldC.setText(textFieldA.getText());

            }
        });



        uloz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            zapis(vyberSoubor());
            }

        });

        kopiruj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldB.setText(textFieldA.getText());
                textFieldC.setText(textFieldA.getText());
            }
        });

        ActionListener lzeSestrojitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //vyskakovací okno
                //Vyskakovací okno-text- lze vyrobit trojúhelník
                //ověření
                //Integer.parseInt(textFieldA.getText());

                int a = Integer.parseInt(textFieldA.getText());
                int b = Integer.parseInt(textFieldB.getText());
                int c = Integer.parseInt(textFieldC.getText());
                boolean lzeSestrojitA = (a + b) > c;
                boolean lzeSestrojitB = (a + c) > b;
                boolean lzeSestrojitC = (b + c) > a;


                if (lzeSestrojitA && lzeSestrojitB && lzeSestrojitC){
                    JOptionPane.showMessageDialog(mainPanel, "Lze sestrojit");
                }
                else{

                    JOptionPane.showMessageDialog(mainPanel, "Nelze sestrojit");
                }

            }
        };

        lzeSestrojit.addActionListener(lzeSestrojitListener);

        nacti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            nacti(vyberSoubor());
            }
        });

        btn2.addActionListener(lzeSestrojitListener);

    }
    private File vyberSoubor(){
        int vysledek = vyberSoubor.showSaveDialog(mainPanel);
        if (vysledek == vyberSoubor.APPROVE_OPTION){
            return (vyberSoubor.getSelectedFile());


        }
        return null;

    }
    private void zapis(File soubor){
        if (soubor == null) return;
        try {
            FileWriter writer = new FileWriter(soubor);
            writer.write(textFieldA.getText() + "\n" + textFieldB.getText() + "\n"+ textFieldC.getText() );
            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private  void nacti (File soubor){
        try {
            Scanner scanner = new Scanner(soubor);
            textFieldA.setText(scanner.nextLine());
            textFieldB.setText(scanner.nextLine());
            textFieldC.setText(scanner.nextLine());



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    }






