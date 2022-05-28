package com.meLembre;
import com.meLembre.controller.LembreteController;
import com.meLembre.model.Lembrete;
import com.meLembre.util.AuthorizationService;
import com.meLembre.view.Show_Lembretes;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.MetalLookAndFeel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application extends javax.swing.JFrame
{   
    private String dias = "";
    private static LembreteController controller;
    
    public Application()
    { 
        try
        { UIManager.setLookAndFeel( new MetalLookAndFeel()); }
        catch( Exception ex )
        { JOptionPane.showMessageDialog(null,"Não foi possível inicializar a Interface Gráfica Tatto" ); }
        
        initComponents();
        show_lembrete_recorrente(false);
        show_lembrete_unico(true);
        bt_save_lembrete.setVisible(false);
        box_dia_semanal.setVisible(false);
        show_semanal_personalizado(false);
        lbl_whatsapp.setVisible(false);
        txt_whatsapp.setVisible(false);
        lbl_quando_lembrar.setVisible(false);
        box_tipo_envio.setVisible(false);
        ativa_minimizar_aplicacao();
        this.getContentPane().setBackground(Color.decode("#00AD7C"));
        this.setVisible(true);
    }
            
    private void criar_lembrete()
    {
       boolean personalizado_valido = true;
       
       Lembrete novo_lembrete = new Lembrete();

       if(box_tipo_envio.getSelectedIndex()==2)
       novo_lembrete.setTelefone("+55"+txt_whatsapp.getText());
       else
       novo_lembrete.setTelefone(txt_whatsapp.getText());   
       
       novo_lembrete.setTitulo(txt_titulo.getText());
       novo_lembrete.setDescricao(txt_descricao.getText());
       novo_lembrete.setHorario(picker_horario.getTime());
       novo_lembrete.setData(picker_data.getDate());  
       
       if(box_tipo_lembrete.getSelectedIndex()==1)
       novo_lembrete.setTipo_lembrete("Único");
       else
       novo_lembrete.setTipo_lembrete(box_tipo_lembrete.getSelectedItem().toString());
       
       if(box_tipo_lembrete.getSelectedIndex()==2)
       novo_lembrete.setDiario(true);
       else if(box_tipo_lembrete.getSelectedIndex()==3)
       novo_lembrete.setSemanal(box_dia_semanal.getSelectedIndex()+1);
       else if(box_tipo_lembrete.getSelectedIndex()==4)
       {           
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
           if(check_dom.isSelected())
           dias+="7,";
           
           if(dias.equals(""))
           personalizado_valido=false;
           
           novo_lembrete.setSemana_personalizado(dias);
       }
       
       if(personalizado_valido)
       {
            novo_lembrete.setAtivo("Ativo");

            if(controller.save(novo_lembrete)!=false)
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
       else
       JOptionPane.showMessageDialog(null, "Favor marcar os dias da semana para o Lembrete personalizado");
       
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
        box_tipo_envio.setVisible(hide);
        lbl_titulo.setVisible(hide);
        txt_titulo.setVisible(hide);
        lbl_descricao.setVisible(hide);
        txt_descricao.setVisible(hide);
        lbl_dataHorario.setVisible(hide);
        picker_horario.setVisible(hide);
        lbl_whatsapp.setVisible(hide);
        txt_whatsapp.setVisible(hide);
     }
     
    private void show_lembrete_recorrente(boolean hide)
    {
        box_tipo_envio.setVisible(hide);
        lbl_titulo.setVisible(hide);
        txt_titulo.setVisible(hide);
        lbl_descricao.setVisible(hide);
        txt_descricao.setVisible(hide);
        lbl_dataHorario.setVisible(hide);
        picker_horario.setVisible(hide);
        lbl_whatsapp.setVisible(hide);
        txt_whatsapp.setVisible(hide);
        lbl_quando_lembrar.setVisible(hide);
        box_dia_semanal.setVisible(hide);
        picker_data.setVisible(hide);
     }
    
    private void limpar_campos()
    {
         txt_titulo.setText("");
         txt_descricao.setText("");
         txt_whatsapp.setText("");
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
    
    private void ativa_minimizar_aplicacao()
    {  
        if(SystemTray.isSupported()==true)
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        
        SystemTray systemTray = SystemTray.getSystemTray();
        TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("src/com/meLembre/util/icone.png"));
        PopupMenu popMenu = new PopupMenu();
        
        MenuItem show = new MenuItem("Mostrar o MeLembre!");
        show.addActionListener
        (
          new ActionListener()
          {
             @Override
             public void actionPerformed(ActionEvent e)
             { desminimiza_aplicacao(); }
          }
        );
        
        MenuItem encerrar = new MenuItem("Encerrar o MeLembre!");
        encerrar.addActionListener
        (
          new ActionListener()
          {
             @Override
             public void actionPerformed(ActionEvent e)
             { 
                 controller.quitWebDriver();
                 systemTray.remove(trayIcon);
                 System.exit(0);
             }
          }
        );
        
        popMenu.add(show);
        popMenu.add(encerrar);
        
        trayIcon.setPopupMenu(popMenu);
        
        try
        { systemTray.add(trayIcon); }
        catch (AWTException ex)
        { JOptionPane.showMessageDialog(null, ex); }
    }
     
    private void desminimiza_aplicacao()
    { 
        if(this.isVisible()==false)
        this.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calendarPanel1 = new com.github.lgooddatepicker.components.CalendarPanel();
        jLabel1 = new javax.swing.JLabel();
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
        check_sex = new javax.swing.JCheckBox();
        check_sab = new javax.swing.JCheckBox();
        lbl_whatsapp = new javax.swing.JLabel();
        txt_whatsapp = new javax.swing.JTextField();
        bt_save_lembrete = new javax.swing.JButton();
        box_tipo_envio = new javax.swing.JComboBox<>();
        bt_showLembretes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Me Lembre!");
        jLabel1.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        lbl_titulo.setText("Título:");
        lbl_titulo.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lbl_titulo.setForeground(new java.awt.Color(255, 255, 255));

        txt_titulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tituloActionPerformed(evt);
            }
        });

        lbl_descricao.setText("Mensagem:");
        lbl_descricao.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lbl_descricao.setForeground(new java.awt.Color(255, 255, 255));

        txt_descricao.setColumns(20);
        txt_descricao.setRows(5);
        jScrollPane1.setViewportView(txt_descricao);

        picker_horario.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N

        picker_data.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        picker_data.setMinimumSize(new java.awt.Dimension(161, 23));
        picker_data.setPreferredSize(new java.awt.Dimension(161, 23));

        lbl_dataHorario.setText("Horário:");
        lbl_dataHorario.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lbl_dataHorario.setForeground(new java.awt.Color(255, 255, 255));

        lbl_tipoLembrete.setText("Tipo de lembrete:");
        lbl_tipoLembrete.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lbl_tipoLembrete.setForeground(new java.awt.Color(255, 255, 255));

        box_tipo_lembrete.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o tipo de lembrete:", "Único", "Todo dia", "Semanal", "Personalizado" }));
        box_tipo_lembrete.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        box_tipo_lembrete.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                box_tipo_lembreteItemStateChanged(evt);
            }
        });

        lbl_quando_lembrar.setText("Quando você deseja ser lembrado?");
        lbl_quando_lembrar.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lbl_quando_lembrar.setForeground(new java.awt.Color(255, 255, 255));

        check_seg.setText("Segunda");
        check_seg.setContentAreaFilled(false);
        check_seg.setFocusPainted(false);
        check_seg.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        check_seg.setForeground(new java.awt.Color(255, 255, 255));

        check_ter.setText("Terça");
        check_ter.setContentAreaFilled(false);
        check_ter.setFocusPainted(false);
        check_ter.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        check_ter.setForeground(new java.awt.Color(255, 255, 255));

        check_qua.setText("Quarta");
        check_qua.setContentAreaFilled(false);
        check_qua.setFocusPainted(false);
        check_qua.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        check_qua.setForeground(new java.awt.Color(255, 255, 255));

        check_qui.setText("Quinta");
        check_qui.setContentAreaFilled(false);
        check_qui.setFocusPainted(false);
        check_qui.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        check_qui.setForeground(new java.awt.Color(255, 255, 255));

        check_dom.setText("Domingo");
        check_dom.setContentAreaFilled(false);
        check_dom.setFocusPainted(false);
        check_dom.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        check_dom.setForeground(new java.awt.Color(255, 255, 255));
        check_dom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_domActionPerformed(evt);
            }
        });

        box_dia_semanal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo" }));
        box_dia_semanal.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        box_dia_semanal.setForeground(new java.awt.Color(0, 51, 51));
        box_dia_semanal.setInheritsPopupMenu(true);
        box_dia_semanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box_dia_semanalActionPerformed(evt);
            }
        });

        check_sex.setText("Sexta");
        check_sex.setContentAreaFilled(false);
        check_sex.setFocusPainted(false);
        check_sex.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        check_sex.setForeground(new java.awt.Color(255, 255, 255));

        check_sab.setText("Sábado");
        check_sab.setContentAreaFilled(false);
        check_sab.setFocusPainted(false);
        check_sab.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        check_sab.setForeground(new java.awt.Color(255, 255, 255));

        lbl_whatsapp.setText("Informe como será enviado o Lembrete:");
        lbl_whatsapp.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        lbl_whatsapp.setForeground(new java.awt.Color(255, 255, 255));

        txt_whatsapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_whatsappActionPerformed(evt);
            }
        });

        bt_save_lembrete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/meLembre/util/icon_CriarLembrete.png"))); // NOI18N
        bt_save_lembrete.setBorderPainted(false);
        bt_save_lembrete.setContentAreaFilled(false);
        bt_save_lembrete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_save_lembrete.setFocusPainted(false);
        bt_save_lembrete.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        bt_save_lembrete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_save_lembreteActionPerformed(evt);
            }
        });

        box_tipo_envio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Para um contato salvo (informe o nome do contato):", "Para um número de telefone (informe o número):" }));
        box_tipo_envio.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        box_tipo_envio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                box_tipo_envioItemStateChanged(evt);
            }
        });

        bt_showLembretes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/meLembre/util/icon_VisualizarLembrete.png"))); // NOI18N
        bt_showLembretes.setBorderPainted(false);
        bt_showLembretes.setContentAreaFilled(false);
        bt_showLembretes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_showLembretes.setFocusPainted(false);
        bt_showLembretes.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        bt_showLembretes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_showLembretesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(327, 327, 327))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bt_save_lembrete, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_showLembretes, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbl_descricao)
                                .addComponent(lbl_dataHorario)
                                .addComponent(lbl_titulo))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(picker_horario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(picker_data, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbl_quando_lembrar)
                            .addGap(5, 5, 5)
                            .addComponent(box_dia_semanal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lbl_whatsapp))
                    .addGroup(layout.createSequentialGroup()
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
                        .addComponent(box_tipo_envio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_whatsapp, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_tipoLembrete)
                        .addGap(5, 5, 5)
                        .addComponent(box_tipo_lembrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_tipoLembrete)
                    .addComponent(box_tipo_lembrete, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_titulo)
                    .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_descricao)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picker_data, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_dataHorario)
                        .addComponent(picker_horario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_quando_lembrar)
                    .addComponent(box_dia_semanal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_seg)
                    .addComponent(check_ter)
                    .addComponent(check_qua)
                    .addComponent(check_qui)
                    .addComponent(check_dom)
                    .addComponent(check_sex)
                    .addComponent(check_sab))
                .addGap(7, 7, 7)
                .addComponent(lbl_whatsapp)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(box_tipo_envio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_whatsapp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_save_lembrete, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_showLembretes, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_tituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tituloActionPerformed
    }//GEN-LAST:event_txt_tituloActionPerformed

    private void box_tipo_lembreteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_box_tipo_lembreteItemStateChanged
        
        if(box_tipo_lembrete.getSelectedIndex()>0)
        {
            bt_save_lembrete.setVisible(true);
            lbl_whatsapp.setVisible(true);
            box_tipo_envio.setVisible(true);
            txt_whatsapp.setVisible(true);
            
            if(box_tipo_lembrete.getSelectedIndex()==1)
            {
                lbl_dataHorario.setText("Horário/Data:");
                picker_data.setVisible(true);
            }
            else
            {
                lbl_dataHorario.setText("Horário:");
                picker_data.setVisible(false);
            }
                        
            if(box_tipo_lembrete.getSelectedIndex()==3 || box_tipo_lembrete.getSelectedIndex()==4)
            {
                lbl_quando_lembrar.setVisible(true);
                
                if(box_tipo_lembrete.getSelectedIndex()==3)
                box_dia_semanal.setVisible(true);
                else
                box_dia_semanal.setVisible(false);
                
                if(box_tipo_lembrete.getSelectedIndex()==4)
                show_semanal_personalizado(true);
                else
                show_semanal_personalizado(false);      
            }
            else
            {
                lbl_quando_lembrar.setVisible(false);
                box_dia_semanal.setVisible(false);
                show_semanal_personalizado(false);      
            }
        }
        else
        {
            show_lembrete_recorrente(false);
            show_lembrete_unico(true);
            bt_save_lembrete.setVisible(false);
            lbl_dataHorario.setText("Horário:");
            show_semanal_personalizado(false);      
            lbl_whatsapp.setVisible(false);
            box_tipo_envio.setVisible(false);
            txt_whatsapp.setVisible(false);
            show_semanal_personalizado(false);     
        }
    }//GEN-LAST:event_box_tipo_lembreteItemStateChanged

    private void txt_whatsappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_whatsappActionPerformed
    }//GEN-LAST:event_txt_whatsappActionPerformed

    private void bt_save_lembreteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_save_lembreteActionPerformed
  
        criar_lembrete();    }//GEN-LAST:event_bt_save_lembreteActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    }//GEN-LAST:event_formWindowOpened

    private void box_tipo_envioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_box_tipo_envioItemStateChanged

    }//GEN-LAST:event_box_tipo_envioItemStateChanged

    private void check_domActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_domActionPerformed
       
    }//GEN-LAST:event_check_domActionPerformed

    private void box_dia_semanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box_dia_semanalActionPerformed
        
    }//GEN-LAST:event_box_dia_semanalActionPerformed

    private void bt_showLembretesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_showLembretesActionPerformed
       new Show_Lembretes().setVisible(true);
    }//GEN-LAST:event_bt_showLembretesActionPerformed

    public static void main(String args[])
    { 
       boolean start = false;
               
       AuthorizationService authorization = new AuthorizationService();

       int found = authorization.findUser(System.getProperty("user.name"));
       
       if(found==-1)
       {
          if(authorization.createUser(System.getProperty("user.name")))
          start = true;
       }
       else if(found==1)
       start = true;
       
       authorization.closeConnection();
       
       if(start)
       {
           SpringApplication.run(Application.class, args);
           controller = new LembreteController();
           controller.getProgramacaoAtiva();
           controller.startWebDriver();
       }
       else
       {
           JOptionPane.showMessageDialog(null, "Infelizmente o período de testes do MeLembre foi encerrado. Contate o criador Jonas Magalhães para readquirir o acesso do programa.\nContato: 71983391878\nE-mail: jonasmagalhaes.8@gmail.com");
           System.exit(0);
       }
    }     
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> box_dia_semanal;
    private javax.swing.JComboBox<String> box_tipo_envio;
    private javax.swing.JComboBox<String> box_tipo_lembrete;
    private javax.swing.JButton bt_save_lembrete;
    private javax.swing.JButton bt_showLembretes;
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
    private javax.swing.JLabel lbl_quando_lembrar;
    private javax.swing.JLabel lbl_tipoLembrete;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JLabel lbl_whatsapp;
    private com.github.lgooddatepicker.components.DatePicker picker_data;
    private com.github.lgooddatepicker.components.TimePicker picker_horario;
    private javax.swing.JTextArea txt_descricao;
    private javax.swing.JTextField txt_titulo;
    private javax.swing.JTextField txt_whatsapp;
    // End of variables declaration//GEN-END:variables
}
