/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package face;
import face.ControlPerson;
import face.ModelPerson;
import com.digitalpersona.onetouch.DPFPCaptureFeedback;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import util.ConectaBanco;
import util.TrainLBPH;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.bytedeco.javacpp.BytePointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;


/**
 *
 * @author Way4ward
 */
public class RegisterFace extends javax.swing.JFrame {
    Variables var = new Variables();
    private DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
    int y = 4;
    private RegisterFace.DaemonThread myThread = null;

    //JavaCV
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("C://photos//haarcascade_frontalface_alt.xml");

    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();

    int numSamples = 10, sample = 1, idPerson;

    //Connection
    ConectaBanco conecta = new ConectaBanco();

    /**
     * Creates new form RegisterFace
     */
    public RegisterFace() {
         super("Register Student | Face Recognition Attendance System");
        initComponents();
        getIdUser();
        startCamera();
        var.setFing(0); 
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
       stopCamera();
    }
});
    
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
    
      private int getIdUser() {
        int id = 0;
        conecta.conexao();
        conecta.executaSQL("SELECT * FROM person ORDER BY id DESC LIMIT 1");
        try {
            conecta.rs.first();
            txt_id_label.setText(String.valueOf(conecta.rs.getInt("id")));
            id = Integer.parseInt(txt_id_label.getText());
            id++;
            txt_id_label.setText(String.valueOf(id));
        } catch (NumberFormatException | SQLException e) {
          //  e.printStackTrace();
            System.out.println("Empty Database");
        }
        return id;
    }
    



    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel9 = new keeptoo.KGradientPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        txt_first_name = new javax.swing.JTextField();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        txt_last_name = new javax.swing.JTextField();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        txt_phone_number = new javax.swing.JFormattedTextField();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        txt_office = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        txt_facebook = new javax.swing.JTextField();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        txt_instagram = new javax.swing.JTextField();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        txt_linkedin = new javax.swing.JTextField();
        kGradientPanel8 = new keeptoo.KGradientPanel();
        txt_github = new javax.swing.JTextField();
        counterLabel = new javax.swing.JLabel();
        saveButton = new keeptoo.KButton();
        label_photo = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_id_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel9.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel9.setkFillBackground(false);
        kGradientPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(170, 170, 170));
        jLabel2.setText("Villa Address");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));

        jLabel3.setForeground(new java.awt.Color(170, 170, 170));
        jLabel3.setText("First Name");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel4.setForeground(new java.awt.Color(170, 170, 170));
        jLabel4.setText("Last Name");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        jLabel5.setForeground(new java.awt.Color(170, 170, 170));
        jLabel5.setText("Phone Number");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        kGradientPanel1.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkEndColor(new java.awt.Color(57, 114, 227));
        kGradientPanel1.setkFillBackground(false);
        kGradientPanel1.setkStartColor(new java.awt.Color(122, 227, 192));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_first_name.setBorder(null);
        txt_first_name.setOpaque(false);
        kGradientPanel1.add(txt_first_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 20));

        jPanel3.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 180, 20));

        kGradientPanel2.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel2.setkEndColor(new java.awt.Color(57, 114, 227));
        kGradientPanel2.setkFillBackground(false);
        kGradientPanel2.setkStartColor(new java.awt.Color(122, 227, 192));
        kGradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_last_name.setBorder(null);
        txt_last_name.setOpaque(false);
        kGradientPanel2.add(txt_last_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 20));

        jPanel3.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 180, 20));

        kGradientPanel3.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setkEndColor(new java.awt.Color(57, 114, 227));
        kGradientPanel3.setkFillBackground(false);
        kGradientPanel3.setkStartColor(new java.awt.Color(122, 227, 192));
        kGradientPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_phone_number.setBorder(null);
        try {
            txt_phone_number.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)-##########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_phone_number.setOpaque(false);
        txt_phone_number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_phone_numberActionPerformed(evt);
            }
        });
        kGradientPanel3.add(txt_phone_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 20));

        jPanel3.add(kGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 180, 20));

        kGradientPanel4.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel4.setkEndColor(new java.awt.Color(57, 114, 227));
        kGradientPanel4.setkFillBackground(false);
        kGradientPanel4.setkStartColor(new java.awt.Color(122, 227, 192));
        kGradientPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_office.setBorder(null);
        txt_office.setOpaque(false);
        kGradientPanel4.add(txt_office, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 20));

        jPanel3.add(kGradientPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 180, 20));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(200, 204, 208));
        jLabel13.setText("Personal Information");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        kGradientPanel9.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 420, 140));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("After registration, capture 10 photos.");
        kGradientPanel9.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 440, 370, 30));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setForeground(new java.awt.Color(170, 170, 170));
        jLabel6.setText("Lecturer Level Rep.");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        jLabel7.setForeground(new java.awt.Color(170, 170, 170));
        jLabel7.setText("Faculty");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel8.setForeground(new java.awt.Color(170, 170, 170));
        jLabel8.setText("Department");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        jLabel9.setForeground(new java.awt.Color(170, 170, 170));
        jLabel9.setText("Matric Number");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(200, 204, 208));
        jLabel11.setText("School Info");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, -1));

        kGradientPanel5.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel5.setkEndColor(new java.awt.Color(57, 114, 227));
        kGradientPanel5.setkFillBackground(false);
        kGradientPanel5.setkStartColor(new java.awt.Color(122, 227, 192));
        kGradientPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_facebook.setBorder(null);
        txt_facebook.setOpaque(false);
        kGradientPanel5.add(txt_facebook, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 20));

        jPanel4.add(kGradientPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 180, 20));

        kGradientPanel6.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel6.setkEndColor(new java.awt.Color(57, 114, 227));
        kGradientPanel6.setkFillBackground(false);
        kGradientPanel6.setkStartColor(new java.awt.Color(122, 227, 192));
        kGradientPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_instagram.setBorder(null);
        txt_instagram.setOpaque(false);
        kGradientPanel6.add(txt_instagram, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 20));

        jPanel4.add(kGradientPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 180, 20));

        kGradientPanel7.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel7.setkEndColor(new java.awt.Color(57, 114, 227));
        kGradientPanel7.setkFillBackground(false);
        kGradientPanel7.setkStartColor(new java.awt.Color(122, 227, 192));
        kGradientPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_linkedin.setBorder(null);
        txt_linkedin.setOpaque(false);
        kGradientPanel7.add(txt_linkedin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 20));

        jPanel4.add(kGradientPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 180, 20));

        kGradientPanel8.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel8.setkEndColor(new java.awt.Color(57, 114, 227));
        kGradientPanel8.setkFillBackground(false);
        kGradientPanel8.setkStartColor(new java.awt.Color(122, 227, 192));
        kGradientPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_github.setBorder(null);
        txt_github.setOpaque(false);
        kGradientPanel8.add(txt_github, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 20));

        jPanel4.add(kGradientPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 180, 20));

        kGradientPanel9.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 420, 150));

        counterLabel.setBackground(new java.awt.Color(255, 255, 255));
        counterLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        counterLabel.setForeground(new java.awt.Color(32, 78, 199));
        counterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        counterLabel.setText("00/10");
        counterLabel.setOpaque(true);
        kGradientPanel9.add(counterLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 480, 370, 20));

        saveButton.setText("Finish");
        saveButton.setkAllowTab(false);
        saveButton.setkEndColor(new java.awt.Color(118, 195, 118));
        saveButton.setkHoverEndColor(new java.awt.Color(22, 92, 22));
        saveButton.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        saveButton.setkHoverStartColor(new java.awt.Color(80, 142, 80));
        saveButton.setkPressedColor(new java.awt.Color(28, 72, 28));
        saveButton.setkStartColor(new java.awt.Color(87, 167, 87));
        saveButton.setOpaque(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        kGradientPanel9.add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, 370, 30));
        saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        label_photo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        kGradientPanel9.add(label_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 360, 390));

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(100, 100, 100));
        jLabel12.setText("Register Student");
        kGradientPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.setBackground(new java.awt.Color(90, 68, 193));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setBackground(new java.awt.Color(90, 68, 193));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("ID Student");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 40));

        txt_id_label.setBackground(new java.awt.Color(90, 68, 193));
        txt_id_label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_id_label.setForeground(new java.awt.Color(255, 255, 255));
        txt_id_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_id_label.setText("1");
        jPanel1.add(txt_id_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 40, 40));

        kGradientPanel9.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 410, 40));

        getContentPane().add(kGradientPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 510));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_phone_numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_phone_numberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_phone_numberActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed

    }//GEN-LAST:event_saveButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RegisterFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterFace().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel counterLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    private keeptoo.KGradientPanel kGradientPanel8;
    private keeptoo.KGradientPanel kGradientPanel9;
    private javax.swing.JLabel label_photo;
    private keeptoo.KButton saveButton;
    private javax.swing.JTextField txt_facebook;
    private javax.swing.JTextField txt_first_name;
    private javax.swing.JTextField txt_github;
    private javax.swing.JLabel txt_id_label;
    private javax.swing.JTextField txt_instagram;
    private javax.swing.JTextField txt_last_name;
    private javax.swing.JTextField txt_linkedin;
    private javax.swing.JTextField txt_office;
    private javax.swing.JFormattedTextField txt_phone_number;
    // End of variables declaration//GEN-END:variables

   class DaemonThread implements Runnable{

       protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = label_photo.getGraphics(); 

                            Mat imageColor = new Mat(); 
                            imageColor = cameraImage;

                            Mat imageGray = new Mat();
                            cvtColor(imageColor, imageGray, COLOR_BGRA2GRAY);
//                            flip(cameraImage, cameraImage, +1);

                            RectVector detectedFaces = new RectVector(); 
                            cascade.detectMultiScale(imageColor, detectedFaces, 1.1, 1, 1, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFaces.size(); i++) { 
                                Rect dadosFace = detectedFaces.get(0);

                                rectangle(imageColor, dadosFace, new Scalar(255, 255, 0, 2), 3, 0, 0);

                                Mat face = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(face, face, new Size(160, 160));

                                if (saveButton.getModel().isPressed()) { 
                                    if (txt_first_name.getText().equals("") || txt_first_name.getText().equals(" ")) {
                                        JOptionPane.showMessageDialog(null, "One or more field are empty");
                                    } else if (txt_first_name.getText().equals("") || txt_first_name.getText().equals(" ")) {
                                        JOptionPane.showMessageDialog(null, "One or more field are empty");
                                    } else if (txt_last_name.getText().equals("") || txt_last_name.getText().equals(" ")) {
                                        JOptionPane.showMessageDialog(null, "One or more field are empty");
                                    } else if (txt_office.getText().equals("") || txt_office.getText().equals(" ")) {
                                        JOptionPane.showMessageDialog(null, "One or more field are empty");
                                    } else {
                                        if (sample <= numSamples) {
//       
                                            String cropped = "C:\\photos\\person." + txt_id_label.getText() + "." + sample + ".jpg";
                                            imwrite(cropped, face);

                                            counterLabel.setText(String.valueOf(sample) + "/10");
                                            sample++;
                                        }
                                        if (sample > 10) {
                                            new TrainLBPH().trainPhotos();
                                            insertDatabase(); 

                                            System.out.println("File Generated");
                                            stopCamera();
                                            dispose();
                                        }

                                    }
                                }
                            }

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;
                            try {
                                if (g.drawImage(buff, 0, 0, 360, 390, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                    if (runnable == false) {
                                        System.out.println("Salve a Foto");
                                        this.wait();
                                    }
                                }
                            } catch (Exception e) {
                            }
                        }

                    } catch (Exception ex) {
                       // ex.printStackTrace();
                    }
                }
            }
        }

        private void insertDatabase() {
            System.out.println("Enter Insert");
              // String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
         String date = new SimpleDateFormat("dd/MM/yyy").format(new Date(System.currentTimeMillis()));
         try {
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement("INSERT INTO person (id, first_name, last_name, phone_number, office, profile_facebook, profile_instagram, profile_linkedin, profile_github, register_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setInt(1, Integer.parseInt(txt_id_label.getText()));
            pst.setString(2, txt_first_name.getText());
            pst.setString(3, txt_last_name.getText());
            pst.setString(4, txt_phone_number.getText());
            pst.setString(5, txt_office.getText());
            pst.setString(6, txt_facebook.getText());
            pst.setString(7, txt_instagram.getText());
            pst.setString(8, txt_linkedin.getText());
            pst.setString(9, txt_github.getText());
            pst.setString(10, date);
           
            pst.executeUpdate();
            conecta.desconecta();
            
                        JOptionPane.showMessageDialog(null, "Data Saved");
            stopCamera();
            dispose();
            System.out.println("Exit Insert");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }    
        
        }
        
    }
   
   
   
   
   
   private void stopCamera() {
        myThread.runnable = false;
        webSource.release();
        dispose(); }
        
      public void startCamera() {
        new Thread() {
            @Override
            public void run() {
                webSource = new VideoCapture(0);

                myThread = new RegisterFace.DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();
            }
        }.start();
    }
}
