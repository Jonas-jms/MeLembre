package com.zetcode;
import com.zetcode.controller.LembreteController;
import com.zetcode.model.Lembrete;
import com.zetcode.view.Show_Lembretes;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends javax.swing.JFrame
{   
    String dias = "";
    WebDriver webDriver;
    
    public Application()
    { 
        initComponents();
        show_lembrete_unico(false);
        show_lembrete_recorrente(false);
        bt_save_lembrete.setVisible(false);
        box_dia_semanal.setVisible(false);
        show_semanal_personalizado(false);
        lbl_whatsapp.setVisible(false);
        txt_whatsapp.setVisible(false);
        lbl_quando_lembrar.setVisible(false);
        box_tipo_envio.setVisible(false);
        ativa_minimizar_aplicacao();
    }
        
    @Bean
    public WebDriver webDriver()
    {
        ChromeOptions options = new ChromeOptions();
        
        File f = new File("C:\\MeLembreCache");
        if (f.isDirectory())
        {
            //options.setHeadless(true);
            options.addArguments("--no-sandbox");
            options.addArguments("--hide-scrollbars");
            options.addArguments("--disable-gpu");
            options.addArguments("--log-level=3");
            options.addArguments("--mute-audio");
        }
        else
        options.setHeadless(false);
        
        options.addArguments("user-data-dir=C:\\MeLembreCache");
        options.addArguments("user-agent=User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36");
        webDriver = new ChromeDriver(options);
        webDriver.get("https://web.whatsapp.com/");
        return webDriver;
    }
    
    private void criar_lembrete()
    {
       boolean personalizado_valido = true;
       
       Lembrete novo_lembrete = new Lembrete();

       if(box_tipo_envio.getSelectedIndex()==1)
       novo_lembrete.setTelefone("+55"+txt_whatsapp.getText());
       else
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
            
            LembreteController controller = new LembreteController();

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
        picker_data.setVisible(hide);
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
        lbl_tipoLembrete.setVisible(hide);
        box_tipo_lembrete.setVisible(hide);
        lbl_whatsapp.setVisible(hide);
        txt_whatsapp.setVisible(hide);
        lbl_quando_lembrar.setVisible(hide);
        box_dia_semanal.setVisible(hide);
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
        TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("icone.png"));
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
                 webDriver.quit();
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
    { this.setVisible(true); }
    
    private void getProgramacaoAtiva()
    { new LembreteController().getProgramacaoAtiva(); }
    
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
        bt_showLembretes = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        jLabel1.setFont(new java.awt.Font("Helvetica", 0, 48)); // NOI18N

        bt_lembreteUnico.setText("Criar lembrete único");
        bt_lembreteUnico.setFocusPainted(false);
        bt_lembreteUnico.setFont(new java.awt.Font("Helvetica", 0, 24)); // NOI18N
        bt_lembreteUnico.setMaximumSize(new java.awt.Dimension(310, 33));
        bt_lembreteUnico.setMinimumSize(new java.awt.Dimension(310, 33));
        bt_lembreteUnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_lembreteUnicoActionPerformed(evt);
            }
        });

        bt_lembreteRecorrente.setText("Criar lembrete recorrente");
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

        lbl_descricao.setText("Mensagem:");
        lbl_descricao.setFont(new java.awt.Font("Helvetica", 0, 22)); // NOI18N

        txt_descricao.setColumns(20);
        txt_descricao.setRows(5);
        jScrollPane1.setViewportView(txt_descricao);

        bt_showLembretes.setText("Visualizar meus lembretes");
        bt_showLembretes.setFocusPainted(false);
        bt_showLembretes.setFont(new java.awt.Font("Helvetica", 0, 24)); // NOI18N
        bt_showLembretes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_showLembretesActionPerformed(evt);
            }
        });

        lbl_dataHorario.setText("Horário/Data");
        lbl_dataHorario.setFont(new java.awt.Font("Helvetica", 0, 22)); // NOI18N

        lbl_tipoLembrete.setText("Tipo de lembrete:");
        lbl_tipoLembrete.setFont(new java.awt.Font("Helvetica", 0, 22)); // NOI18N

        box_tipo_lembrete.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o tipo de lembrete:", "Todo dia", "Semanal", "Personalizado" }));
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

        check_sex.setText("Sexta");

        check_sab.setText("Sábado");

        lbl_whatsapp.setText("Informe o número do Whatsapp que irá receber o lembrete:");
        lbl_whatsapp.setFont(new java.awt.Font("Helvetica", 0, 22)); // NOI18N

        txt_whatsapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_whatsappActionPerformed(evt);
            }
        });

        bt_save_lembrete.setText("Criar lembrete");
        bt_save_lembrete.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        bt_save_lembrete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_save_lembreteActionPerformed(evt);
            }
        });

        box_tipo_envio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Para um contato salvo (informe o nome do contato):", "Para um número de telefone (informe o número):" }));
        box_tipo_envio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                box_tipo_envioItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(lbl_whatsapp)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(box_tipo_envio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_whatsapp, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(bt_save_lembrete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_lembreteUnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_lembreteRecorrente))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(bt_showLembretes)
                                .addGap(141, 141, 141)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_lembreteUnico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_lembreteRecorrente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addComponent(bt_showLembretes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_seg)
                    .addComponent(check_ter)
                    .addComponent(check_qua)
                    .addComponent(check_qui)
                    .addComponent(check_dom)
                    .addComponent(box_dia_semanal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(check_sex)
                    .addComponent(check_sab))
                .addGap(6, 6, 6)
                .addComponent(lbl_whatsapp)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(bt_save_lembrete, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(box_tipo_envio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_whatsapp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
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

    private void box_tipo_lembreteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_box_tipo_lembreteItemStateChanged
        
        switch (box_tipo_lembrete.getSelectedIndex())
        {
            case 1:
                    lbl_quando_lembrar.setVisible(false);
                    box_dia_semanal.setVisible(false);
                    show_semanal_personalizado(false);
                    lbl_quando_lembrar.setVisible(false);
                    break;
            case 2:
                    lbl_quando_lembrar.setVisible(true);
                    box_dia_semanal.setVisible(true);
                    show_semanal_personalizado(false);
                    lbl_quando_lembrar.setVisible(true);
                    break;
            case 3:
                    show_semanal_personalizado(true);
                    box_dia_semanal.setVisible(false);
                    lbl_quando_lembrar.setVisible(true);
                    break;
        }
    }//GEN-LAST:event_box_tipo_lembreteItemStateChanged

    private void txt_whatsappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_whatsappActionPerformed
    }//GEN-LAST:event_txt_whatsappActionPerformed

    private void bt_save_lembreteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_save_lembreteActionPerformed
  
        criar_lembrete();    }//GEN-LAST:event_bt_save_lembreteActionPerformed

    private void bt_showLembretesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_showLembretesActionPerformed
        new Show_Lembretes().setVisible(true);
    }//GEN-LAST:event_bt_showLembretesActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        getProgramacaoAtiva();
    }//GEN-LAST:event_formWindowOpened

    private void box_tipo_envioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_box_tipo_envioItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_box_tipo_envioItemStateChanged

    public static void main(String args[])
    {
        SpringApplication.run(Application.class, args);
     
        java.awt.EventQueue.invokeLater(() -> {
            new Application().setVisible(true);
        });
    }     
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> box_dia_semanal;
    private javax.swing.JComboBox<String> box_tipo_envio;
    private javax.swing.JComboBox<String> box_tipo_lembrete;
    private javax.swing.JButton bt_lembreteRecorrente;
    private javax.swing.JButton bt_lembreteUnico;
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
