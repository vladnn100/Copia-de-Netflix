package Vista;

import java.awt.Color;

/**
 *
 * @author Julian Torres
 */
public class FrmReproductor extends javax.swing.JFrame {

    public FrmReproductor() {
        initComponents();
        Color color = new Color(000017);
        jContenedor.setBackground(color);
        jBoxButtons.setBackground(color);
        btnPause.setBackground(color);
        btnStop.setBackground(color);
        btnVolver.setBackground(color);
        sldVolumen.setBackground(color);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jContenedor = new javax.swing.JPanel();
        jBoxButtons = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        sldVolumen = new javax.swing.JSlider();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        lblSee = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jContenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jContenedor.setForeground(new java.awt.Color(60, 63, 65));

        javax.swing.GroupLayout jContenedorLayout = new javax.swing.GroupLayout(jContenedor);
        jContenedor.setLayout(jContenedorLayout);
        jContenedorLayout.setHorizontalGroup(
            jContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jContenedorLayout.setVerticalGroup(
            jContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        jBoxButtons.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBoxButtons.setForeground(new java.awt.Color(60, 63, 65));

        btnVolver.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Player/back.png"))); // NOI18N
        btnVolver.setBorderPainted(false);
        btnVolver.setContentAreaFilled(false);
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnPause.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        btnPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Player/pause.png"))); // NOI18N
        btnPause.setBorderPainted(false);
        btnPause.setContentAreaFilled(false);

        btnStop.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Player/stop.png"))); // NOI18N
        btnStop.setBorderPainted(false);
        btnStop.setContentAreaFilled(false);

        lblTitulo.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTitulo.setText("Titulo");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VOLUMEN");

        lblSee.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 24)); // NOI18N
        lblSee.setForeground(new java.awt.Color(255, 255, 255));
        lblSee.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSee.setText("Estás viendo:");

        javax.swing.GroupLayout jBoxButtonsLayout = new javax.swing.GroupLayout(jBoxButtons);
        jBoxButtons.setLayout(jBoxButtonsLayout);
        jBoxButtonsLayout.setHorizontalGroup(
            jBoxButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBoxButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVolver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(btnStop)
                .addGroup(jBoxButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jBoxButtonsLayout.createSequentialGroup()
                        .addGroup(jBoxButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jBoxButtonsLayout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addComponent(jLabel1))
                            .addGroup(jBoxButtonsLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(sldVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBoxButtonsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSee, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                        .addGap(40, 40, 40))))
        );
        jBoxButtonsLayout.setVerticalGroup(
            jBoxButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBoxButtonsLayout.createSequentialGroup()
                .addGroup(jBoxButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jBoxButtonsLayout.createSequentialGroup()
                        .addComponent(lblSee)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jBoxButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jBoxButtonsLayout.createSequentialGroup()
                                .addComponent(sldVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addComponent(lblTitulo)))
                    .addGroup(jBoxButtonsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jBoxButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jBoxButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnStop, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator1)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBoxButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBoxButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnPause;
    public javax.swing.JButton btnStop;
    public javax.swing.JButton btnVolver;
    public javax.swing.JPanel jBoxButtons;
    public javax.swing.JPanel jContenedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JLabel lblSee;
    public javax.swing.JLabel lblTitulo;
    public javax.swing.JSlider sldVolumen;
    // End of variables declaration//GEN-END:variables
}
