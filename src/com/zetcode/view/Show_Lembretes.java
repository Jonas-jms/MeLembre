package com.zetcode.view;

import com.zetcode.controller.LembreteController;
import com.zetcode.model.Lembrete;
import java.awt.Color;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Show_Lembretes extends javax.swing.JFrame
{
    List<Lembrete> lembretes = new LembreteController().findAll();
    
    public Show_Lembretes()
    {
        initComponents();
        this.getContentPane().setBackground(Color.decode("#00AD7C"));
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); 
        popula_tabela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_lembretes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Estes são seus lembretes criados!");

        tbl_lembretes.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        tbl_lembretes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_lembretes.setGridColor(new java.awt.Color(0, 173, 124));
        tbl_lembretes.setOpaque(false);
        tbl_lembretes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_lembretesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_lembretes);

        jLabel2.setText("Selecione na tabela com um cliques caso queira editar ou apagar um lembrete");
        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 373, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_lembretesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_lembretesMouseClicked
       seleciona_lembrete(evt);
    }//GEN-LAST:event_tbl_lembretesMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
      
    }//GEN-LAST:event_formWindowClosed

    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(() -> {
            new Show_Lembretes().setVisible(true);
        });
    }

    
    private void popula_tabela()
    {       
        DefaultTableModel modeloTabela = new DefaultTableModel();
        
        tbl_lembretes.setModel(modeloTabela);
        
        modeloTabela.addColumn("Lembrete Nº");
        modeloTabela.addColumn("Status Lembrete");
        modeloTabela.addColumn("Título");
        modeloTabela.addColumn("Descrição");
        modeloTabela.addColumn("Horário");
        modeloTabela.addColumn("Tipo de Lembrete");
        modeloTabela.addColumn("Enviar para");
        
        for (Lembrete lembrete: lembretes)
        {
            modeloTabela.addRow
            (
               new Object[]
               {
                   lembrete.getId(),
                   lembrete.getAtivo(),
                   lembrete.getTitulo(),
                   lembrete.getDescricao(),
                   lembrete.getHorario(),
                   lembrete.getTipo_lembrete(),
                   lembrete.getTelefone()
               }
            );
        }
    }
    
    public void seleciona_lembrete(java.awt.event.MouseEvent evt)
    {
        JTable source = (JTable) evt.getSource();
        int linha = source.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 1)
        {
            long id = (long) tbl_lembretes.getValueAt(linha, 0);
           
            for (Lembrete lembrete: lembretes)
            {
                if(lembrete.getId()==id)
                {
                    new Visualizar_Lembrete(lembrete).setVisible(true);
                    break;
                }
            }
            this.dispose();
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_lembretes;
    // End of variables declaration//GEN-END:variables
}
