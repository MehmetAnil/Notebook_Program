/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_project1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author Mehmet Anıl TAYSİ & Emel KAYACI
 */
public class MainForm extends javax.swing.JFrame {

    static String filePath = "";

    private void yanlislariBul( ArrayList<String> yanlisKelimeler) {

        textArea.getHighlighter().removeAllHighlights();
        String metin = textArea.getText();
        Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);

        for (String yanlisKelime : yanlisKelimeler) {
            metin = textArea.getText();
            while (metin.lastIndexOf(yanlisKelime) >= 0) {
                int index = metin.lastIndexOf(yanlisKelime);
                int end = index + yanlisKelime.length();
                try {
                    textArea.getHighlighter().addHighlight(index, end, painter);
                    if (metin.lastIndexOf(yanlisKelime) == 0) {
                        metin = metin.substring(0, index);
                    } else {
                        metin = metin.substring(0, index - 1);
                    }
                } catch (BadLocationException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void yanlislariDuzelt(Map<String, String> duzeltilen, ArrayList<String> yanlisKelimeler) {
        for (String yanlisKelime : yanlisKelimeler) {
            if (duzeltilen.containsKey(yanlisKelime)) {
                textArea.setText(textArea.getText().replaceAll(yanlisKelime, (String) duzeltilen.get(yanlisKelime)));
            }
        }
    }

    private void bul(boolean degistir) {
        textArea.getHighlighter().removeAllHighlights();
        final String arananKelime = JOptionPane.showInputDialog(null, "Kelimeyi Bul:", "Bul", JOptionPane.INFORMATION_MESSAGE);
        final int l1 = textArea.getText().indexOf(arananKelime);
        String metin = textArea.getText();
        Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.cyan);
        if (l1 == -1) {
            JOptionPane.showMessageDialog(null, "Aradığınız kelime bulunamadı.", "Uyarı", JOptionPane.ERROR_MESSAGE);
        } else {

            while (metin.lastIndexOf(arananKelime) >= 0) {
                int index = metin.lastIndexOf(arananKelime);
                int end = index + arananKelime.length();
                try {
                    textArea.getHighlighter().addHighlight(index, end, painter);
                    if (metin.lastIndexOf(arananKelime) == 0) {
                        metin = metin.substring(0, index);
                    } else {
                        metin = metin.substring(0, index - 1);
                    }
                } catch (BadLocationException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (degistir) {
                final String yeniKelime = JOptionPane.showInputDialog(null, "Yeni Kelime:", "Değiştir", JOptionPane.INFORMATION_MESSAGE);
                textArea.setText(textArea.getText().replaceAll(arananKelime, yeniKelime));
            }
        }

    }

    private void Kaydet() {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(filePath));
            bf.write(textArea.getText());
            bf.flush();
            bf.close();
        } catch (IOException e) {
        }
        this.setTitle(filePath + " Not Defteri");
    }

    private void FarkliKaydet() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files", "txt", "text");
        fc.setFileFilter(filter);
        int result = fc.showSaveDialog(this);

        File selectedFile;
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = new File(fc.getSelectedFile() + ".txt");
            System.out.println("File : " + selectedFile.getAbsolutePath());
            filePath = selectedFile.getPath();
        }
        Kaydet();
    }

    private void KaydetMessageBox() {
        if (textArea.getText().trim().length() != 0) {
            Object[] options = {"Kaydet",
                "Kaydetme",
                "İptal"};
            int response = JOptionPane.showOptionDialog(jPanel1,
                    "Değişiklikleri Adsız öğesine kaydetmek istiyor musunuz?",
                    "Not Defteri",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);

            switch (response) {
                case JOptionPane.YES_OPTION:
                    FarkliKaydet();
                    textArea.setText("");
                    filePath = "";
                    if (menuKapat.isEnabled()) {
                        System.exit(0);
                    }
                    break;
                case JOptionPane.NO_OPTION:
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    textArea.setText("");
                    filePath = "";
                    break;
                default:
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    break;
            }
        }
    }

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/Images/notebook.png")).getImage());
        this.setTitle("Adsız - Not Defteri");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jMenuBar2 = new javax.swing.JMenuBar();
        menubar1 = new javax.swing.JMenu();
        menuYeni = new javax.swing.JMenuItem();
        menuAc = new javax.swing.JMenuItem();
        menuKaydet = new javax.swing.JMenuItem();
        menuFarkliKaydet = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuKapat = new javax.swing.JMenuItem();
        menubar2 = new javax.swing.JMenu();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuGeriAl = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menuBul = new javax.swing.JMenuItem();
        menuDegistir = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        yanlisDuzelt = new javax.swing.JMenuItem();
        yanlisBul = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setBackground(new java.awt.Color(204, 204, 204));
        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        textArea.setForeground(new java.awt.Color(0, 0, 0));
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenuBar2.setPreferredSize(new java.awt.Dimension(47, 30));

        menubar1.setText("Dosya");
        menubar1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        menuYeni.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuYeni.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuYeni.setText("Yeni");
        menuYeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuYeniActionPerformed(evt);
            }
        });
        menubar1.add(menuYeni);

        menuAc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuAc.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuAc.setText("Aç...");
        menuAc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAcActionPerformed(evt);
            }
        });
        menubar1.add(menuAc);

        menuKaydet.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuKaydet.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuKaydet.setText("Kaydet");
        menuKaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKaydetActionPerformed(evt);
            }
        });
        menubar1.add(menuKaydet);

        menuFarkliKaydet.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuFarkliKaydet.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuFarkliKaydet.setText("Farklı Kaydet...");
        menuFarkliKaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFarkliKaydetActionPerformed(evt);
            }
        });
        menubar1.add(menuFarkliKaydet);
        menubar1.add(jSeparator1);

        menuKapat.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuKapat.setText("Kapat");
        menuKapat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKapatActionPerformed(evt);
            }
        });
        menubar1.add(menuKapat);

        jMenuBar2.add(menubar1);

        menubar2.setText("Düzen");
        menubar2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menubar2.add(jSeparator2);

        menuGeriAl.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        menuGeriAl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuGeriAl.setText("Geri Al");
        menuGeriAl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGeriAlActionPerformed(evt);
            }
        });
        menubar2.add(menuGeriAl);
        menubar2.add(jSeparator3);

        menuBul.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        menuBul.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuBul.setText("Bul...");
        menuBul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBulActionPerformed(evt);
            }
        });
        menubar2.add(menuBul);

        menuDegistir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menuDegistir.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        menuDegistir.setText("Değiştir...");
        menuDegistir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDegistirActionPerformed(evt);
            }
        });
        menubar2.add(menuDegistir);
        menubar2.add(jSeparator4);

        yanlisDuzelt.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        yanlisDuzelt.setText("Yanlış Düzelt");
        yanlisDuzelt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yanlisDuzeltActionPerformed(evt);
            }
        });
        menubar2.add(yanlisDuzelt);

        yanlisBul.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        yanlisBul.setText("Yanlış Bul");
        yanlisBul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yanlisBulActionPerformed(evt);
            }
        });
        menubar2.add(yanlisBul);

        jMenuBar2.add(menubar2);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuFarkliKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFarkliKaydetActionPerformed
        FarkliKaydet();
    }//GEN-LAST:event_menuFarkliKaydetActionPerformed

    private void menuKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKaydetActionPerformed
        if (filePath.equals("")) {
            FarkliKaydet();
        } else {
            Kaydet();
        }
    }//GEN-LAST:event_menuKaydetActionPerformed

    private void menuAcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAcActionPerformed
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files", "txt");
        fc.setFileFilter(filter);
        int result = fc.showOpenDialog(this);
        File selectedFile;
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fc.getSelectedFile();
            System.out.println("File : " + selectedFile.getAbsolutePath());
            filePath = selectedFile.getPath();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String s1 = "", s2 = "";
            while ((s1 = br.readLine()) != null) {
                s2 += s1 + "\n";
            }
            textArea.setText(s2);
            br.close();
        } catch (Exception ex) {
        }
        this.setTitle(filePath + " Not Defteri");
    }//GEN-LAST:event_menuAcActionPerformed

    private void menuYeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuYeniActionPerformed
        this.setTitle("Adsız - Not Defteri");
        KaydetMessageBox();
    }//GEN-LAST:event_menuYeniActionPerformed

    private void menuKapatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKapatActionPerformed

        if (textArea.getText().trim().length() != 0) {
            KaydetMessageBox();
            if (textArea.getText().trim().length() == 0) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_menuKapatActionPerformed

    private void menuGeriAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGeriAlActionPerformed
        try {
            if (textArea.getText().length() != 0) {
                textArea.setText(textArea.getText(0, textArea.getText().length() - 1));
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuGeriAlActionPerformed

    private void menuBulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBulActionPerformed
        bul(false);
    }//GEN-LAST:event_menuBulActionPerformed

    private void menuDegistirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDegistirActionPerformed
        bul(true);
    }//GEN-LAST:event_menuDegistirActionPerformed

    private void yanlisDuzeltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yanlisDuzeltActionPerformed

        ArrayList<String> sozluk = SingleTransposition.sozluguAktar("words.txt.txt");
        ArrayList<String> yanlisKelimeler = SingleTransposition.yanlislariBul(textArea.getText(), sozluk);
        Map<String, String> duzeltilen = SingleTransposition.yanlislariDuzelt(yanlisKelimeler, sozluk);
        yanlislariBul(yanlisKelimeler);
        yanlislariDuzelt(duzeltilen, yanlisKelimeler);
    }//GEN-LAST:event_yanlisDuzeltActionPerformed

    private void yanlisBulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yanlisBulActionPerformed
        ArrayList<String> sozluk = SingleTransposition.sozluguAktar("words.txt.txt");
        ArrayList<String> yanlisKelimeler = SingleTransposition.yanlislariBul(textArea.getText(), sozluk);
        yanlislariBul(yanlisKelimeler);
    }//GEN-LAST:event_yanlisBulActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainForm().setVisible(true);
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem menuAc;
    private javax.swing.JMenuItem menuBul;
    private javax.swing.JMenuItem menuDegistir;
    private javax.swing.JMenuItem menuFarkliKaydet;
    private javax.swing.JMenuItem menuGeriAl;
    private javax.swing.JMenuItem menuKapat;
    private javax.swing.JMenuItem menuKaydet;
    private javax.swing.JMenuItem menuYeni;
    private javax.swing.JMenu menubar1;
    private javax.swing.JMenu menubar2;
    private javax.swing.JTextArea textArea;
    private javax.swing.JMenuItem yanlisBul;
    private javax.swing.JMenuItem yanlisDuzelt;
    // End of variables declaration//GEN-END:variables
}
