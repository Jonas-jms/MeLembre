package com.zetcode.view;
import com.zetcode.controller.LembreteController;
import com.zetcode.model.Lembrete;
import javax.swing.JOptionPane;

public class Visualizar_Lembrete extends javax.swing.JFrame
{      
    public Visualizar_Lembrete(Lembrete lembrete)
    { 
        initComponents();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); 
        
        show_lembrete_unico(false);
        show_lembrete_recorrente(false);
        bt_save_lembrete.setVisible(false);
        bt_deletar_lembrete.setVisible(false);
        box_dia_semanal.setVisible(false);
        show_semanal_personalizado(false);
        lbl_informe_dia.setVisible(false);
        txt_dia_mes.setVisible(false);
        lbl_whatsapp.setVisible(false);
        txt_whatsapp.setVisible(false);
        lbl_quando_lembrar.setVisible(false);
        lbl_informe_dia.setVisible(false);
        txt_dia_mes.setVisible(false);
        
        recupera_lembrete(lembrete);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calendarPanel1 = new com.github.lgooddatepicker.components.CalendarPanel();
        jLabel1 = new javax.swing.JLabel();
        bt_lembreteUnico = new javax.swing.JButton();
        bt_lembreteRecorrente = new javax.swing.JButton();
        lbl_titulo = new javax.swing.JLabel();
        txt_titulo = new javax.swing.JTextField();
        lbl_descricao = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_descricao = new javax.swing.JTextArea();
        picker_horario = new com.github.lgooddatepicker.components.TimePicker();
        picker_data = new com.github.lgooddatepicker.components.DatePicker();
        lbl_dataHorario = new javax.swing.JLabel();
        lbl_tipoLembrete = new javax.swing.JLabel();
        box_tipo_lembrete = new javax.swing.JComboBox<>();
        lbl_quando_lembrar = new javax.swing.JLabel();
        check_seg = new javax.swing.JCheckBox();
        check_ter = new javax.swing.JCheckBox();
        check_qua = new javax.swing.JCheckBox();
        check_qui = new javax.swing.JCheckBox();
        check_dom = new javax.swing.JCheckBox();
        box_dia_semanal = new javax.swing.JComboBox<>();
        lbl_informe_dia = new javax.swing.JLabel();
        txt_dia_mes = new javax.swing.JTextField();
        check_sex = new javax.swing.JCheckBox();
        check_sab = new javax.swing.JCheckBox();
        lbl_whatsapp = new javax.swing.JLabel();
        txt_whatsapp = new javax.swing.JTextField();
        bt_save_lembrete = new javax.swing.JButton();
        bt_deletar_lembrete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Me Lembre!");
        jLabel1.setFont(new java.awt.Font("Helvetica", 0, 48)); // NOI18N

        bt_lembreteUnico.setText("Alterar para lembrete único");
        bt_lembreteUnico.setFocusPainted(false);
        bt_lembreteUnico.setFont(new java.awt.Font("Helvetica", 0, 24)); // NOI18N
        bt_lembreteUnico.setMaximumSize(new java.awt.Dimension(310, 33));
        bt_lembreteUnico.setMinimumSize(new java.awt.Dimension(310, 33));
        bt_lembreteUnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_lembreteUnicoActionPerformed(evt);
            }
        });

        bt_lembreteRecorrente.setText("Alterar para lembrete recorrente");
        bt_lembreteRecorrente.setFocusPainted(false);
        bt_lembreteRecorrente.setFont(new java.awt.Font("Helvetica", 0, 24)); // NOI18N
        bt_lembreteRecorrente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_lembreteRecorrenteActionPerformed(evt);
            }
        });

        lbl_titulo.setText("Título:");
        lbl_titulo.setFont(new java.awt.Font("Helvetica", 0, 22)); // NOI18N

        txt_titulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tituloActionPerformed(evt);
            }
        });

        lbl_descricao.setText("Descrição:");
        lbl_descricao.setFont(new java.awt.Font("Helvetica", 0, 22)); // NOI18N

        txt_descricao.setColumns(20);
        txt_descricao.setRows(5);
        jScrollPane1.setViewportView(txt_descricao);

        lbl_dataHorario.setText("Horário/Data");
        lbl_dataHorario.setFont(new java.awt.Font("Helvetica", 0, 22)); // NOI18N

        lbl_tipoLembrete.setText("Tipo de lembrete:");
        lbl_tipoLembrete.setFont(new java.awt.Font("Helvetica", 0, 22)); // NOI18N

        box_tipo_lembrete.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o tipo de lembrete:", "Todo dia", "Semanal", "Mensal", "Personalizado" }));
        box_tipo_lembrete.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                box_tipo_lembreteItemStateChanged(evt);
            }
        });

        lbl_quando_lembrar.setText("Quando você deseja ser lembrado?");
        lbl_quando_lembrar.setFont(new java.awt.Font("Helvetica", 0, 22)); // NOI18N

        check_seg.setText("Segunda");

        check_ter.setText("Terça");

        check_qua.setText("Quarta");

        check_qui.setText("Quinta");

        check_dom.setText("Domingo");

        box_dia_semanal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo" }));

        lbl_informe_dia.setText("Informe o dia do mês que deseja ser lembrado:");
        lbl_informe_dia.setFont(new java.awt.Font("Helvetica", 0, 22)); // NOI18N

        txt_dia_mes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dia_mesActionPerformed(evt);
            }
        });

        check_sex.setText("Sexta");

        check_sab.setText("Sábado");

        lbl_whatsapp.setText("Informe o número do Whatsapp que irá receber o lembrete:");
        lbl_whatsapp.setFont(new java.awt.Font("Helvetica", 0, 22)); // NOI18N

        txt_whatsapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_whatsappActionPerformed(evt);
            }
        });

        bt_save_lembrete.setText("Alterar lembrete");
        bt_save_lembrete.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        bt_save_lembrete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_save_lembreteActionPerformed(evt);
            }
        });

        bt_deletar_lembrete.setText("Deletar lembrete");
        bt_deletar_lembrete.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_descricao)
                            .addComponent(lbl_dataHorario)
                            .addComponent(lbl_titulo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(picker_horario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(picker_data, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1)
                                .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_tipoLembrete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(box_tipo_lembrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_quando_lembrar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(box_dia_semanal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(check_seg)
                        .addGap(0, 0, 0)
                        .addComponent(check_ter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_qua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_qui)
                        .addGap(0, 0, 0)
                        .addComponent(check_sex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_sab)
                        .addGap(0, 0, 0)
                        .addComponent(check_dom))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_informe_dia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dia_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_whatsapp)
                    .addComponent(txt_whatsapp, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_save_lembrete, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_deletar_lembrete, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_lembreteUnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_lembreteRecorrente))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel1)))
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_lembreteUnico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_lembreteRecorrente))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_titulo)
                    .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_descricao)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_dataHorario)
                    .addComponent(picker_horario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(picker_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_tipoLembrete)
                    .addComponent(box_tipo_lembrete, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_quando_lembrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(check_seg)
                            .addComponent(check_ter)
                            .addComponent(check_qua)
                            .addComponent(check_qui)
                            .addComponent(check_dom)
                            .addComponent(box_dia_semanal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(check_sex)
                            .addComponent(check_sab))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_informe_dia))
                    .addComponent(txt_dia_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(lbl_whatsapp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_whatsapp, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_save_lembrete, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(bt_deletar_lembrete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_lembreteUnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_lembreteUnicoActionPerformed
        bt_save_lembrete.setVisible(true);
        show_lembrete_recorrente(false);
        show_lembrete_unico(true);
        lbl_dataHorario.setText("Horário/Data");
    }//GEN-LAST:event_bt_lembreteUnicoActionPerformed

    private void bt_lembreteRecorrenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_lembreteRecorrenteActionPerformed
        bt_save_lembrete.setVisible(true);
        show_lembrete_unico(false);
        show_lembrete_recorrente(true);
        lbl_dataHorario.setText("Horário");
    }//GEN-LAST:event_bt_lembreteRecorrenteActionPerformed

    private void txt_tituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tituloActionPerformed
    }//GEN-LAST:event_txt_tituloActionPerformed

    private void txt_dia_mesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dia_mesActionPerformed
    }//GEN-LAST:event_txt_dia_mesActionPerformed

    private void box_tipo_lembreteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_box_tipo_lembreteItemStateChanged
        
        switch (box_tipo_lembrete.getSelectedIndex())
        {
            case 1:
                    lbl_quando_lembrar.setVisible(false);
                    box_dia_semanal.setVisible(false);
                    lbl_informe_dia.setVisible(false);
                    txt_dia_mes.setVisible(false);
                    show_semanal_personalizado(false);
                    lbl_quando_lembrar.setVisible(false);
                    break;
            case 2:
                    lbl_quando_lembrar.setVisible(true);
                    box_dia_semanal.setVisible(true);
                    lbl_informe_dia.setVisible(false);
                    txt_dia_mes.setVisible(false);
                    show_semanal_personalizado(false);
                    lbl_quando_lembrar.setVisible(true);
                    break;
            case 3:
                    box_dia_semanal.setVisible(false);
                    lbl_informe_dia.setVisible(true);
                    txt_dia_mes.setVisible(true);
                    show_semanal_personalizado(false);
                    lbl_quando_lembrar.setVisible(false);
                    break;
            case 4:
                    show_semanal_personalizado(true);
                    box_dia_semanal.setVisible(false);
                    lbl_informe_dia.setVisible(false);
                    txt_dia_mes.setVisible(false);
                    lbl_quando_lembrar.setVisible(true);
                    break;
        }
    }//GEN-LAST:event_box_tipo_lembreteItemStateChanged

    private void txt_whatsappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_whatsappActionPerformed
    }//GEN-LAST:event_txt_whatsappActionPerformed

    private void bt_save_lembreteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_save_lembreteActionPerformed
       
        criar_lembrete();    }//GEN-LAST:event_bt_save_lembreteActionPerformed

    public static void main(String args[])
    {     
        java.awt.EventQueue.invokeLater(() -> {
            new Visualizar_Lembrete(null).setVisible(true);
        });
    }
    
    private void recupera_lembrete(Lembrete lembrete)
    {
       
    }
    
    private void criar_lembrete()
    {
       Lembrete novo_lembrete = new Lembrete();

       novo_lembrete.setTelefone(txt_whatsapp.getText());
       novo_lembrete.setTitulo(txt_titulo.getText());
       novo_lembrete.setDescricao(txt_descricao.getText());
       novo_lembrete.setHorario(picker_horario.getTime());
       novo_lembrete.setData(picker_data.getDate());  
       
       if(box_tipo_lembrete.getSelectedIndex()==0)
       novo_lembrete.setTipo_lembrete("Único");
       else
       novo_lembrete.setTipo_lembrete(box_tipo_lembrete.getSelectedItem().toString());
       
       if(box_tipo_lembrete.getSelectedIndex()==1)
       novo_lembrete.setDiario(true);
       else if(box_tipo_lembrete.getSelectedIndex()==2)
       novo_lembrete.setSemanal(box_dia_semanal.getSelectedIndex()+1);
       else if(box_tipo_lembrete.getSelectedIndex()==3)
       novo_lembrete.setMensal(Integer.parseInt(txt_dia_mes.getText()));
       else
       {
           String dias = "";
           
           if(check_seg.isSelected())
           dias+="1,";
           if(check_ter.isSelected())
           dias+="2,";
           if(check_qua.isSelected())
           dias+="3,";
           if(check_qui.isSelected())
           dias+="4,";
           if(check_sex.isSelected())
           dias+="5,";
           if(check_sab.isSelected())
           dias+="6,";
           if(check_sab.isSelected())
           dias+="7,";
           
           novo_lembrete.setSemana_personalizado(dias);
       }
       
       if(new LembreteController().inserir(novo_lembrete)!=null)
       {
           JOptionPane.showMessageDialog(null, "Lembrete cadastrado com sucesso!");
           limpar_campos();
           show_semanal_personalizado(false);
           show_lembrete_recorrente(false);
           bt_save_lembrete.setVisible(false);
           picker_data.setVisible(false);
           picker_data.setDate(null);
           picker_horario.setTime(null);
       }
       else
       JOptionPane.showMessageDialog(null, "Houve um erro ao cadastrar o lembrete!");
    }
    
    private void show_semanal_personalizado(boolean hide)
    {
        check_seg.setVisible(hide);
        check_ter.setVisible(hide);
        check_qua.setVisible(hide);
        check_qui.setVisible(hide);
        check_sex.setVisible(hide);
        check_sab.setVisible(hide);
        check_dom.setVisible(hide);
    }
            
     private void show_lembrete_unico(boolean hide)
     {
        lbl_titulo.setVisible(hide);
        txt_titulo.setVisible(hide);
        lbl_descricao.setVisible(hide);
        txt_descricao.setVisible(hide);
        lbl_dataHorario.setVisible(hide);
        picker_horario.setVisible(hide);
        picker_data.setVisible(hide);
        lbl_whatsapp.setVisible(hide);
        txt_whatsapp.setVisible(hide);
     }
     
     private void show_lembrete_recorrente(boolean hide)
     {
        lbl_titulo.setVisible(hide);
        txt_titulo.setVisible(hide);
        lbl_descricao.setVisible(hide);
        txt_descricao.setVisible(hide);
        lbl_dataHorario.setVisible(hide);
        picker_horario.setVisible(hide);
        lbl_tipoLembrete.setVisible(hide);
        box_tipo_lembrete.setVisible(hide);
        lbl_whatsapp.setVisible(hide);
        txt_whatsapp.setVisible(hide);
       
     }
    
     private void limpar_campos()
     {
         txt_titulo.setText("");
         txt_descricao.setText("");
         txt_whatsapp.setText("");
         txt_dia_mes.setText("");
         box_tipo_lembrete.setSelectedIndex(0);
         box_dia_semanal.setSelectedIndex(0);
         check_seg.setSelected(false);
         check_ter.setSelected(false);
         check_qua.setSelected(false);
         check_qui.setSelected(false);
         check_sex.setSelected(false);
         check_sab.setSelected(false);
         check_dom.setSelected(false);
     }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> box_dia_semanal;
    private javax.swing.JComboBox<String> box_tipo_lembrete;
    private javax.swing.JButton bt_deletar_lembrete;
    private javax.swing.JButton bt_lembreteRecorrente;
    private javax.swing.JButton bt_lembreteUnico;
    private javax.swing.JButton bt_save_lembrete;
    private com.github.lgooddatepicker.components.CalendarPanel calendarPanel1;
    private javax.swing.JCheckBox check_dom;
    private javax.swing.JCheckBox check_qua;
    private javax.swing.JCheckBox check_qui;
    private javax.swing.JCheckBox check_sab;
    private javax.swing.JCheckBox check_seg;
    private javax.swing.JCheckBox check_sex;
    private javax.swing.JCheckBox check_ter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_dataHorario;
    private javax.swing.JLabel lbl_descricao;
    private javax.swing.JLabel lbl_informe_dia;
    private javax.swing.JLabel lbl_quando_lembrar;
    private javax.swing.JLabel lbl_tipoLembrete;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JLabel lbl_whatsapp;
    private com.github.lgooddatepicker.components.DatePicker picker_data;
    private com.github.lgooddatepicker.components.TimePicker picker_horario;
    private javax.swing.JTextArea txt_descricao;
    private javax.swing.JTextField txt_dia_mes;
    private javax.swing.JTextField txt_titulo;
    private javax.swing.JTextField txt_whatsapp;
    // End of variables declaration//GEN-END:variables
}
