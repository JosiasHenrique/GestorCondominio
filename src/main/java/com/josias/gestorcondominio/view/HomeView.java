package com.josias.gestorcondominio.view;

import com.josias.gestorcondominio.controller.ResidenciaController;
import com.josias.gestorcondominio.observer.Observer;
import com.josias.gestorcondominio.model.Residencia;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HomeView extends javax.swing.JFrame implements Observer {

    private final ResidenciaController rc = ResidenciaController.getInstancia();

    public HomeView() {
        initComponents();
        rc.registrarObservador(this);
        carregarMoradores();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        btnVerDetalhes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnNovaResidencia = new javax.swing.JButton();
        btnNovoProprietario = new javax.swing.JButton();
        btnExcluirResidencia = new javax.swing.JButton();
        btnEditarResidencia = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cod", "Rua", "Nº", "Cep"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable);

        btnVerDetalhes.setText("Ver Detalhes");
        btnVerDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalhesActionPerformed(evt);
            }
        });

        jLabel1.setText("*Selecione uma residencia na tabela");

        jLabel2.setText("Gestor de Condomínio");

        btnNovaResidencia.setText("Nova Residencia");
        btnNovaResidencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaResidenciaActionPerformed(evt);
            }
        });

        btnNovoProprietario.setText("Novo Proprietário");
        btnNovoProprietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoProprietarioActionPerformed(evt);
            }
        });

        btnExcluirResidencia.setText("Excluir Residencia");
        btnExcluirResidencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirResidenciaActionPerformed(evt);
            }
        });

        btnEditarResidencia.setText("Editar Residencia");
        btnEditarResidencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarResidenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnVerDetalhes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExcluirResidencia))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNovaResidencia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditarResidencia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNovoProprietario)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovaResidencia)
                    .addComponent(btnNovoProprietario)
                    .addComponent(btnEditarResidencia))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerDetalhes)
                    .addComponent(jLabel1)
                    .addComponent(btnExcluirResidencia))
                .addGap(57, 57, 57))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalhesActionPerformed
        // TODO add your handling code here:
        int linhaSelecionada = jTable.getSelectedRow();
        if (linhaSelecionada >= 0) {
            try {
                int residenciaId = (int) jTable.getValueAt(linhaSelecionada, 0);
                ResidenciaDetalhesView detalheView = new ResidenciaDetalhesView(residenciaId);
                detalheView.setVisible(true);
            } catch (ClassCastException e) {
                JOptionPane.showMessageDialog(this, "Erro ao obter o ID da residência. Verifique os dados na tabela.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Mensagem para o usuário caso nenhuma linha esteja selecionada
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma residência na tabela para ver os detalhes.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnVerDetalhesActionPerformed

    private void btnNovaResidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaResidenciaActionPerformed
        // TODO add your handling code here:
        ResidenciaFormView form = new ResidenciaFormView();
        form.setVisible(true);
    }//GEN-LAST:event_btnNovaResidenciaActionPerformed

    private void btnEditarResidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarResidenciaActionPerformed
        // TODO add your handling code here:
        int linhaSelecionada = jTable.getSelectedRow();
        if (linhaSelecionada >= 0) {
            // Obtém os dados da linha selecionada
            int id = (int) jTable.getValueAt(linhaSelecionada, 0);
            String rua = (String) jTable.getValueAt(linhaSelecionada, 1);
            int numero = (int) jTable.getValueAt(linhaSelecionada, 2);
            String cep = (String) jTable.getValueAt(linhaSelecionada, 3);

            // Passa os dados para o formulário
            ResidenciaFormView form = new ResidenciaFormView(id, rua, numero, cep);
            form.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma residência na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarResidenciaActionPerformed

    private void btnExcluirResidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirResidenciaActionPerformed
        // TODO add your handling code here:
        int linhaSelecionada = jTable.getSelectedRow();
        if (linhaSelecionada >= 0) {
            // Obtém os dados da linha selecionada
            int id = (int) jTable.getValueAt(linhaSelecionada, 0);

            // Confirmação antes de excluir
            int confirmacao = JOptionPane.showConfirmDialog(
                    this,
                    "Tem certeza que deseja excluir esta residência? Isso também removerá todas as dívidas e moradores associadas a ela.",
                    "Confirmação de Exclusão",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacao == JOptionPane.YES_OPTION) {
                if (rc.excluirResidencia(id)) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Residência excluída com sucesso.",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Erro ao excluir a residência. Tente novamente.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, selecione uma residência para excluir.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_btnExcluirResidenciaActionPerformed

    private void btnNovoProprietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoProprietarioActionPerformed
        // TODO add your handling code here:
        new ProprietarioFormView().setVisible(true);
    }//GEN-LAST:event_btnNovoProprietarioActionPerformed

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
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeView().setVisible(true);
            }
        });
    }

    public void carregarMoradores() {
        List<Residencia> residencias = rc.listarResidencias();

        DefaultTableModel modelo = (DefaultTableModel) jTable.getModel();
        modelo.setRowCount(0);

        for (Residencia r : residencias) {
            Object[] linha = {r.getId(), r.getRua(), r.getNumero(), r.getCep()};
            modelo.addRow(linha);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarResidencia;
    private javax.swing.JButton btnExcluirResidencia;
    private javax.swing.JButton btnNovaResidencia;
    private javax.swing.JButton btnNovoProprietario;
    private javax.swing.JButton btnVerDetalhes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void atualizar() {
        carregarMoradores();
    }

}
