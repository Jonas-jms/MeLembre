package com.zetcode;
import com.zetcode.controller.CityController;
import com.zetcode.model.City;
import javax.swing.JOptionPane;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application extends javax.swing.JFrame
{   
    public Application()
    { initComponents();}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_cidade = new javax.swing.JLabel();
        txt_cidade = new javax.swing.JTextField();
        lbl_populacao = new javax.swing.JLabel();
        txt_populacao = new javax.swing.JTextField();
        bt_cadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_cidade.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_cidade.setText("Cidade:");

        txt_cidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cidadeActionPerformed(evt);
            }
        });

        lbl_populacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_populacao.setText("População:");

        txt_populacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_populacaoActionPerformed(evt);
            }
        });

        bt_cadastrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bt_cadastrar.setText("Cadastrar");
        bt_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_populacao)
                                .addGap(18, 18, 18)
                                .addComponent(txt_populacao, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_cidade)
                                .addGap(44, 44, 44)
                                .addComponent(txt_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(bt_cadastrar)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_cidade)
                    .addComponent(txt_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_populacao)
                    .addComponent(txt_populacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bt_cadastrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_populacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_populacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_populacaoActionPerformed

    private void txt_cidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cidadeActionPerformed

    private void bt_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cadastrarActionPerformed
        cadastrar();
    }//GEN-LAST:event_bt_cadastrarActionPerformed

    public static void main(String args[])
    {
        SpringApplication.run(Application.class, args);
     
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            { new Application().setVisible(true); }
        });
    }
    
    private void cadastrar()
    {
       City model = new City();
       model.setName(txt_cidade.getText());
       model.setPopulation(Integer.valueOf(txt_populacao.getText()));
       
       if(new CityController().inserir(model)!=null)
       JOptionPane.showMessageDialog(null, "Cidade cadastrada com sucesso");
       else
       JOptionPane.showMessageDialog(null, "Cidade não foi cadastrada");  
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cadastrar;
    private javax.swing.JLabel lbl_cidade;
    private javax.swing.JLabel lbl_populacao;
    private javax.swing.JTextField txt_cidade;
    private javax.swing.JTextField txt_populacao;
    // End of variables declaration//GEN-END:variables
}
