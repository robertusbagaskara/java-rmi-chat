/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import server.IServer;

/**
 *
 * @author user
 */
public class MainForm extends javax.swing.JFrame {
    
    public static String user;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
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
        txtUserList = new javax.swing.JTextArea();
        txtChatInput = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtChatHistory = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<String>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtUserList.setColumns(20);
        txtUserList.setRows(5);
        jScrollPane1.setViewportView(txtUserList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
        );

        txtChatInput.setText("Hello");
        txtChatInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChatInputActionPerformed(evt);
            }
        });
        txtChatInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtChatInputKeyPressed(evt);
            }
        });

        txtChatHistory.setColumns(20);
        txtChatHistory.setRows(5);
        jScrollPane2.setViewportView(txtChatHistory);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("User Online");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All User" }));

        jLabel2.setText("Select User");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtChatInput, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addComponent(txtChatInput, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtChatInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChatInputKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !txtChatInput.getText().equals("")) {
            try {
//                iserver.MsgToServer(jTextField1.getText(), nickname, jComboBox1.getSelectedItem().toString());
                iserver.MsgToServer(txtChatInput.getText(), user, jComboBox1.getSelectedItem().toString());
                txtChatInput.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtChatInputKeyPressed

    private void txtChatInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChatInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChatInputActionPerformed

    
    private void formWindowsClosing(java.awt.event.WindowEvent evt) {
        try {
            if(iserver != null) {
//                iserver.LogoutToServer(clientObj, nickname);
                iserver.LogoutToServer(clientObj, user);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        user = args[0];
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mf = new MainForm();
                    
                    Point p = new Point((Toolkit.getDefaultToolkit().getScreenSize().width-mf.getSize().width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-mf.getSize().height)/2);
                    mf.setLocation(p);
                    mf.setVisible(true);
                    
                    clientObj = new ClientObj();
                    host = JOptionPane.showInputDialog("Masukan Server Chat", "localhost");
                    iserver = (IServer)Naming.lookup("//" + host + "/DpkServer");
                    
//                    boolean flag = true;
//                    while(flag) {
//                        nickname = JOptionPane.showInputDialog("Masukan Nickname Anda");
//                        if(nickname==null) {
//                            JOptionPane.showConfirmDialog(mf, "Maaf Masukan NickName anda", "Nick Name", JOptionPane.CLOSED_OPTION);
//                        } else if(!nickname.equalsIgnoreCase("")) {
//                            if(iserver.RegisterToServer(clientObj, nickname)) {
//                                mf.setTitle("User : " + nickname);
//                                flag = false;
//                            } else {
//                                JOptionPane.showConfirmDialog(mf, "NickName yang anda gunakan sudah ada", "Nick Name Sudah Ada", JOptionPane.CLOSED_OPTION);
//                            }
//                        }
//                    }
                    if(iserver.RegisterToServer(clientObj, user)) {
                        mf.setTitle("User : " + user);
                    } else {
                        JOptionPane.showConfirmDialog(mf, "Maaf Terjadi Kesalahan", "Terjadi Kesalahan pada Username", JOptionPane.CLOSED_OPTION);
                    }
                    
                } catch (NotBoundException ex) {
                    JOptionPane.showConfirmDialog(mf, "Server ", "Server Connection", JOptionPane.CLOSED_OPTION);
                } catch (MalformedURLException ex) {
                    JOptionPane.showConfirmDialog(mf, "Sever might not be responding", "Server Connection", JOptionPane.CLOSED_OPTION);
                } catch (RemoteException ex) {
                    JOptionPane.showConfirmDialog(mf, "Server might not be responding", "Server Connection", JOptionPane.CLOSED_OPTION);
                }
            }
        });
    }
    
    public static void _MsgArrived(String msg, String FromUser) {
        mf.NewMsg(msg, FromUser);
    }
    public static void _UpdateUserList(List<String> ClientsName) {
        mf.NewUser(ClientsName);
    }
    public void NewUser(List<String> ClientsName) {
        txtUserList.setText("");
        String selectedUser = jComboBox1.getSelectedItem().toString();
        jComboBox1.removeAllItems();
        jComboBox1.addItem("All Users");
        
        for(String UserName : ClientsName) {
            if(UserName.length() > 15) {
                UserName = UserName.substring(0,15) + "..";
            }
            
            txtUserList.append(" " + UserName + "\n");
            jComboBox1.addItem(UserName);
        }
        
        jComboBox1.setSelectedItem(selectedUser);
    }
    
    public void NewMsg(String msg, String FromUser) {
        txtChatHistory.append(FromUser + " : " + msg + "\n");
    }
    
    /*variable*/
    static MainForm mf;
    static ClientObj clientObj;
    static IServer iserver;
    static String host;
//    static String nickname;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtChatHistory;
    private javax.swing.JTextField txtChatInput;
    private javax.swing.JTextArea txtUserList;
    // End of variables declaration//GEN-END:variables
}
