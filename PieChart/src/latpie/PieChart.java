/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latpie;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;
import javax.swing.*;

/**
 *
 * @author user
 */
public class PieChart extends JPanel {
    //Definisi field untuk menyimpan data
    String [] nilaiUjian = {"A", "B", "C", "D", "E"};
    int [] jumlahMahasiswa = {3, 10, 23, 5, 2};
    Color [] warna = {Color.BLUE, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.RED};
            
    //Constructor
    public PieChart() {
        this.setPreferredSize(new Dimension(300, 300));
        this.setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g){
        // paintComponent dengan default method
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    
    //Gunakan antialis, agar hasil bagus
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    
    //Frame background
    Shape bg = new Rectangle2D.Float(10, 10, 250, 200);
    g2.setColor(Color.LIGHT_GRAY);
    g2.fill(bg);
    g2.setColor(Color.GRAY);
    g2.draw(bg);
    
    //Hitung total
    float total = 0.0f;
    for(int k = 0; k < jumlahMahasiswa.length; k++)
        total += jumlahMahasiswa[k];
    
    // Menghitung sudut/prosentase tiap-tiap sektor
    float sudut, awal = 90;
    float lx = 220, ly = 70, lw = 10, lh = 10;
    for (int k = 0; k < jumlahMahasiswa.length; k++) {
        //Hitung besarnya sudut tiap-tiap sektor
        sudut = 360.0f * jumlahMahasiswa[k] / total;
        Shape sektor = new Arc2D.Float(30, 30, 150, 150, awal, sudut, Arc2D.PIE);
        
        //Tampilkan (Render) PIE
        g2.setColor(warna[k]);
        g2.fill(sektor);
        awal += sudut;
        
        //Tampilkan LEGEND di sebelah kanan
        g2.fill(new Rectangle2D.Float(lx, ly, lw, lh));
        g2.setColor(java.awt.Color.BLACK);
        g2.drawString(nilaiUjian[k], lx+lw+5, ly+lh);
                ly+=(lh+5);
                
        //Tambahkan signature (pilihan)
        g2.setColor(java.awt.Color.GRAY);
        g2.drawString("Created by : Angga Ramansyah Putra", 15,205);
        g2.drawString("Universitas Pamulang", 40,27);
    }
    }
            

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("PIE Chart");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {System.exit(0);
            }
        });
        
        //tambahkan objek dari PieChart
        PieChart canvas = new PieChart();
        frame.getContentPane().add(canvas);
        
        frame.pack();
        frame.setVisible(true);
            }
        }