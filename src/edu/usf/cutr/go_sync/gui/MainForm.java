/**
Copyright 2010 University of South Florida

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 **/

/*
 * MainForm.java
 *
 * Created on Jul 20, 2010, 9:15:56 PM
 */
package edu.usf.cutr.go_sync.gui;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import edu.usf.cutr.go_sync.object.OperatorInfo;
import edu.usf.cutr.go_sync.task.CompareData;
import edu.usf.cutr.go_sync.task.OsmTask;
import edu.usf.cutr.go_sync.task.RevertChangeset;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import edu.usf.cutr.go_sync.io.DefaultOperatorReader;
import edu.usf.cutr.go_sync.object.DefaultOperator;
import java.awt.event.ItemEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Khoa Tran and Marcy Gordon
 */
public class MainForm extends javax.swing.JFrame implements PropertyChangeListener {

    private String _operatorName;
    private String _operatorNameAbbreviate;
    private String _operatorAlias;
    private String _operatorNtdId = "";
    private int _gtfsIdDigit;
    private String _revertChangesetId;
    private String _fileDir;
    private CompareData compareTask;
    private RevertChangeset revertTask;
    private ProgressMonitor progressMonitor;
    private OsmTask task;
    private List<DefaultOperator> ops;

    /** Creates new form MainForm */
    public MainForm() {
        initComponents();

        DefaultOperatorReader reader = new DefaultOperatorReader(); //create a new reader
        ops = reader.readOperators(new File("operators.csv").getAbsolutePath()); //read a file with operator info for autocompletion

        //TODO Fix textfield with ops is null (operators.csv doesn't exist)
        
        List l = new ArrayList(); //create a new list to store operator names
        l.add(""); //first entry in the list will be blank
        if (ops != null) {
            for (DefaultOperator op : ops) { //for each operator
                l.add(op.getOperatorName()); //add their name to the list for autocompletion
            }
        }

        //create a new textfield with autocomplete for operator names
        operatorNameField = new edu.usf.cutr.go_sync.gui.object.AutoCompleteTextField(l);

        //add the textfield to the panel
        jPanel1.add(operatorNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 20, 240, -1));
        
        if (ops != null) {
            KeyListener listener = new KeyListener() { //create key listener for autocomple text field

                public void keyTyped(KeyEvent e) {
                    ///System.out.println("Typed!");
                }

                public void keyPressed(KeyEvent e) {
                    //System.out.println("Pressed!");
                }

                public void keyReleased(KeyEvent e) {
                    //System.out.println("Released!");
                    boolean isFound = false;
                    for (DefaultOperator op : ops) { //look through all known operators
                        if (op.getOperatorName().equalsIgnoreCase(operatorNameField.getText())) { //if we have info on the operator
                            operatorNameAbbField.setText(op.getOperatorAbbreviation()); //automatically fill out the abbreviation
                            operatorNTDIDField.setText(String.valueOf(op.getNtdID())); //fill in the NTD ID
                            if (op.getStopDigits() > 0) { //if we know the length of the stop IDs
                                gtfsIdDigitField.setText(String.valueOf(op.getStopDigits())); //fill in the GTFS stop ID length
                            }
                            if (op.getGtfsURL() != null && !op.getGtfsURL().isEmpty()) { //if we know the GTFS URL
                                rbURL.setSelected(true); //select the URL radio button
                                fileDirTextField.setText(op.getGtfsURL()); //fill in the GTFS URL
                            }

                            isFound = true;

                            break;
                        }
                    }
                    // clear all fields
                    if(!isFound){
                        operatorNameAbbField.setText("");
                        operatorNTDIDField.setText("");
                        gtfsIdDigitField.setText("");
                        rbURL.setSelected(true);
                        fileDirTextField.setText("");
                    }
                }
            };
            operatorNameField.addKeyListener(listener); //add the listener to the textfield so that once an operator is recognized, other known values will populate
        }

        operatorNameField.requestFocusInWindow(); //set the cursor in the operator name autocomplete text field
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        exitButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        operatorNameLabel = new javax.swing.JLabel();
        OperatorAbbLabel = new javax.swing.JLabel();
        operatorNameAbbField = new javax.swing.JTextField();
        compareButton = new javax.swing.JButton();
        usernameLabel2 = new javax.swing.JLabel();
        operatorNTDIDField = new javax.swing.JTextField();
        usernameLabel3 = new javax.swing.JLabel();
        gtfsIdDigitField = new javax.swing.JTextField();
        operatorNameLabel1 = new javax.swing.JLabel();
        operatorAliasField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        rbURL = new javax.swing.JRadioButton();
        rbFileFolder = new javax.swing.JRadioButton();
        fileNameLabel = new javax.swing.JLabel();
        fileDirTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        changesetLabel = new javax.swing.JLabel();
        revertChangesetField = new javax.swing.JTextField();
        revertButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taskOutput = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GO-Sync");
        setName("mainForm"); // NOI18N
        setResizable(false);

        exitButton.setText("Exit");
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        operatorNameLabel.setText("Operator Full Name (*)");
        jPanel1.add(operatorNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 126, -1));

        OperatorAbbLabel.setText("Operator Abbreviation (*)");
        jPanel1.add(OperatorAbbLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(391, 23, -1, -1));

        operatorNameAbbField.setName("usernameField"); // NOI18N
        jPanel1.add(operatorNameAbbField, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 70, -1));
        operatorNameAbbField.getAccessibleContext().setAccessibleName("operatorNameAbbField");

        compareButton.setText("Run");
        compareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compareButtonActionPerformed(evt);
            }
        });
        jPanel1.add(compareButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 209, 74, -1));

        usernameLabel2.setText("Operator NTD ID");
        jPanel1.add(usernameLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 111, -1));

        operatorNTDIDField.setName("usernameField"); // NOI18N
        jPanel1.add(operatorNTDIDField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 50, -1));
        operatorNTDIDField.getAccessibleContext().setAccessibleName("OperatorNTDIDField");

        usernameLabel3.setText("Length of GTFS Stop IDs");
        jPanel1.add(usernameLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 120, -1));

        gtfsIdDigitField.setName("usernameField"); // NOI18N
        jPanel1.add(gtfsIdDigitField, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 20, -1));

        operatorNameLabel1.setText("Operator Alias");
        jPanel1.add(operatorNameLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 111, -1));

        operatorAliasField.setName("usernameField"); // NOI18N
        jPanel1.add(operatorAliasField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 180, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("GTFS Data"));
        jPanel3.setName("pnlGTFSData"); // NOI18N

        buttonGroup1.add(rbURL);
        rbURL.setText("URL");
        rbURL.setName("rbURL"); // NOI18N
        rbURL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbURLItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rbFileFolder);
        rbFileFolder.setSelected(true);
        rbFileFolder.setText("Folder or Zip File");
        rbFileFolder.setName("rbFolderFile"); // NOI18N
        rbFileFolder.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbFileFolderItemStateChanged(evt);
            }
        });

        fileNameLabel.setText("Folder or Zip File (*)");

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fileNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fileDirTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(rbFileFolder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbURL)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rbFileFolder, rbURL});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbURL)
                    .addComponent(rbFileFolder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileNameLabel)
                    .addComponent(fileDirTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addContainerGap())
        );

        fileNameLabel.getAccessibleContext().setAccessibleParent(jPanel3);
        fileDirTextField.getAccessibleContext().setAccessibleParent(jPanel3);
        browseButton.getAccessibleContext().setAccessibleParent(jPanel3);

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 98, 600, -1));

        jLabel1.setText("Fields marked with an asterisk(*) are required");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, 30));

        jTabbedPane1.addTab("Compare Data", jPanel1);

        jPanel2.setName(""); // NOI18N

        changesetLabel.setText("Changeset ID");

        revertChangesetField.setName("usernameField"); // NOI18N

        revertButton.setText("Run");
        revertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revertButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(changesetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(revertChangesetField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(revertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(240, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(revertChangesetField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changesetLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(revertButton)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Revert Changeset", jPanel2);

        taskOutput.setColumns(20);
        taskOutput.setRows(5);
        jScrollPane1.setViewportView(taskOutput);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                        .addGap(280, 280, 280)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        exitButton.getAccessibleContext().setAccessibleName("exitButton");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        System.exit(0);
}//GEN-LAST:event_exitButtonMouseClicked

    private void compareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compareButtonActionPerformed
        //optional field
        /*
        _operatorNtdId = operatorNTDIDField.getText();
        if(gtfsIdDigitField.getText()!=null && !gtfsIdDigitField.getText().equals("")) {
            try {
                _gtfsIdDigit = Integer.parseInt(gtfsIdDigitField.getText());
            } catch (Exception e){
                JOptionPane.showMessageDialog(this, "Message: "+e.getMessage());
            }
        }
        _operatorAlias = operatorAliasField.getText();

        //can't left blank
        try {
            _operatorName = operatorNameField.getText();
            _operatorNameAbbreviate = operatorNameAbbField.getText();
            _username = usernameField.getText();
            _password = new String(passwordField.getPassword());
            _changesetComment = sessionCommentField.getText();
            _fileDir = fileDirTextField.getText();
hill
            if(!_operatorName.isEmpty() && !_operatorNameAbbreviate.isEmpty() && !_fileDir.isEmpty() &&
                    !_username.isEmpty() && !_password.isEmpty() && !_changesetComment.isEmpty()) {
                new OperatorInfo(_operatorName, _operatorNameAbbreviate, _operatorAlias, _operatorNtdId, _gtfsIdDigit, _fileDir);
                new Session(_username, _password, _changesetComment);
                
                progressMonitor = new ProgressMonitor(MainForm.this, "Comparing GTFS and OSM data","", 0, 100);
                progressMonitor.setProgress(0);
                compareButton.setEnabled(false);
                compareTask = new CompareData(progressMonitor, taskOutput);
                task = compareTask;
                task.addPropertyChangeListener(this);
                compareTask.invokeee();
            }
            else {
                JOptionPane.showMessageDialog(this, "Please fill in all the required fields!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Message: "+e.getMessage());
        }
        
        */


        // clear the text area output
        taskOutput.setText("");
        taskOutput.setLineWrap(true);

        // get data from user input

        if (rbURL.isSelected()) { //if user selected a URL
            try {
                if (!UnzipGTFS(null, new URL(fileDirTextField.getText()))) { //try to unzip from the URL
                    JOptionPane.showMessageDialog(this, "Unable to unzip from URL. Please try again with another URL.");
                    return;
                }
            } catch (MalformedURLException ex) {
                JOptionPane.showMessageDialog(this, "Invalid URL. Please try again with another URL.");
                System.err.println("Error: " + ex.getLocalizedMessage());
                return;
            }
            _fileDir = new File("GTFS_Temp").getAbsolutePath() + System.getProperty("file.separator");//"\\"; //set the actual location to the GTFS_Temp folder
        } else if (rbFileFolder.isSelected()) { //else user selected a local file/folder
            if (fileDirTextField.getText().toLowerCase().contains(".zip")) { //if a zip file was selected
                if (!UnzipGTFS(chooser.getSelectedFile(), null)) { //unzip it to a temporary folder
                    JOptionPane.showMessageDialog(this, "Unable to unzip from file. Please try again with another file.");
                    return;
                }
                _fileDir = new File("GTFS_Temp").getAbsolutePath() + System.getProperty("file.separator");//"\\"; //set the actual location to the GTFS_Temp folder
            } else {
                _fileDir = fileDirTextField.getText(); //else use the folder selected with GTFS files in it
                //TODO - validate that a folder was selected and that it does have GTFS files
            }
        }

        //optional field
        _operatorNtdId = operatorNTDIDField.getText();
        if (gtfsIdDigitField.getText() != null && !gtfsIdDigitField.getText().equals("")) {
            try {
                _gtfsIdDigit = Integer.parseInt(gtfsIdDigitField.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Message: " + e.getMessage());
                return;
            }
        }

        //can't leave blank
        try {
            _operatorAlias = operatorAliasField.getText();
            _operatorName = operatorNameField.getText();
            _operatorNameAbbreviate = operatorNameAbbField.getText();

            if (!_operatorName.isEmpty() && !_operatorNameAbbreviate.isEmpty() && !_fileDir.isEmpty()) {
                new OperatorInfo(_operatorName, _operatorNameAbbreviate, _operatorAlias, _operatorNtdId, _gtfsIdDigit, _fileDir);

                progressMonitor = new ProgressMonitor(MainForm.this, "Comparing GTFS and OSM data", "", 0, 100);
                progressMonitor.setProgress(0);
                compareButton.setEnabled(false);
                compareTask = new CompareData(progressMonitor, taskOutput);
                task = compareTask;
                task.addPropertyChangeListener(this);
                compareTask.invokeee();
//                try{
//                    compareTask.execute();
//                } catch(Exception e){
//                    System.out.println("MainForm: "+e);
//                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all the required fields!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Message: " + e.getMessage());
        }
}//GEN-LAST:event_compareButtonActionPerformed

    private void revertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revertButtonActionPerformed
        try {
            _revertChangesetId = revertChangesetField.getText();

            OSMSessionForm osmLogin = new OSMSessionForm();
            if (!osmLogin.showDialog()) //if user hit cancel and didn't enter OSM credentials
            {
                JOptionPane.showMessageDialog(this, "To revert an OSM changeset, you must log in to OSM.");
                return;
            }
            progressMonitor = new ProgressMonitor(MainForm.this, "Reverting Openstreetmap changeset", "", 0, 100);
            progressMonitor.setProgress(0);
            revertTask = new RevertChangeset(_revertChangesetId, progressMonitor, taskOutput);
            task = revertTask;
            task.addPropertyChangeListener(this);
            revertTask.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Message: " + e.getMessage());
        }
}//GEN-LAST:event_revertButtonActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Browse for GTFS file...");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setMultiSelectionEnabled(true);
        chooser.showOpenDialog(this);
        if (chooser.getSelectedFile() != null) {
            fileDirTextField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    /*
     * Unzips a GTFS zip file to a temporary directory called GTFS_Temp, which is created in the current directory
     * Pass either a file or URL and NULL for the other - do not send both
     * Returns true if it successfully unzipped the file
     */
    private boolean UnzipGTFS(File zipFile, URL zipURL) {
        //TODO display a progress bar to user so they know a file is being unzipped

        File unzipFolder = new File("GTFS_Temp");
        String unzipLocation = unzipFolder.getAbsolutePath() + System.getProperty("file.separator"); //"\\"; //temporary folder to store unzipped files
        try {
            unzipFolder.mkdir(); //create the directory if not already created
        } catch (SecurityException ex) {
            System.err.println("Unable to create temporary directory to unzip the GTFS data to. \n" + ex.getLocalizedMessage());
            return false;
        }
        if (unzipFolder.listFiles().length > 0) { //if the folder has old files in it
            for (File f : unzipFolder.listFiles()) {
                f.delete(); //delete all the old files
            }
        }

        try { //try to unzip the file and write the files into the temporary directory
            OutputStream out = null;
            ZipInputStream zip;

            //TODO test that URL and file are actually ZIP files

            if (zipFile == null) {
                System.out.println("Unzipping " + zipURL.toString() + " to " + unzipLocation);
                zip = new ZipInputStream(zipURL.openStream());
            } else {
                System.out.println("Unzipping " + zipFile.getAbsolutePath() + " to " + unzipLocation);
                zip = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
            }

            ZipEntry next_file;

            while ((next_file = zip.getNextEntry()) != null) {

                System.out.println("Reading the file: " + next_file.getName() + " Size: " + next_file.getSize());

                out = new FileOutputStream(unzipLocation + next_file.getName());
                byte[] buf = new byte[1024];
                int len;

                while ((len = zip.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                out.close();
                //out.flush();
                //do any processing here if you’d like
                zip.closeEntry();
            }
            zip.close();
            System.out.println("Files have been written");
        } catch (Exception e) {
            System.err.println("Error writing a file: " + e.getLocalizedMessage());
            return false;
        }
        return true;
    }

    private void rbFileFolderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbFileFolderItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED && rbFileFolder.isSelected()) {
            fileNameLabel.setText("Folder or Zip File (*)");
            browseButton.setVisible(true);
            if (chooser != null) {
                fileDirTextField.setText(chooser.getSelectedFile().getAbsolutePath());
            } else {
                fileDirTextField.setText("");
            }
        }
    }//GEN-LAST:event_rbFileFolderItemStateChanged

    private void rbURLItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbURLItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED && rbURL.isSelected()) {
            fileNameLabel.setText("URL of Zip File (*)");
            browseButton.setVisible(false);
            fileDirTextField.setText("");
        }
    }//GEN-LAST:event_rbURLItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            // Set system native Java L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Error setting LookAndFeel: " + e.getLocalizedMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error setting LookAndFeel: " + e.getLocalizedMessage());
        } catch (InstantiationException e) {
            System.err.println("Error setting LookAndFeel: " + e.getLocalizedMessage());
        } catch (IllegalAccessException e) {
            System.err.println("Error setting LookAndFeel: " + e.getLocalizedMessage());
        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            progressMonitor.setProgress(progress);
            String message = task.getMessage() + "   " + progress + "% \n";
            taskOutput.append(message);
            if (progressMonitor.isCanceled() || task.isDone()) {
                progressMonitor.close();
                Toolkit.getDefaultToolkit().beep();
                if (progressMonitor.isCanceled() || progress<100 ) {
                    task.cancel(true);
                    taskOutput.append("Task canceled.\n");
                    compareButton.setEnabled(true);
                } else {
                    taskOutput.append("Task completed.\n");
//                    if (task==compareTask) compareTask.generateReport();
                    this.dispose();
                }
            }
        }
        taskOutput.setCaretPosition(taskOutput.getText().length());
    }
    private edu.usf.cutr.go_sync.gui.object.AutoCompleteTextField operatorNameField;
    private javax.swing.JFileChooser chooser;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel OperatorAbbLabel;
    private javax.swing.JButton browseButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel changesetLabel;
    private javax.swing.JButton compareButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JTextField fileDirTextField;
    private javax.swing.JLabel fileNameLabel;
    private javax.swing.JTextField gtfsIdDigitField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField operatorAliasField;
    private javax.swing.JTextField operatorNTDIDField;
    private javax.swing.JTextField operatorNameAbbField;
    private javax.swing.JLabel operatorNameLabel;
    private javax.swing.JLabel operatorNameLabel1;
    private javax.swing.JRadioButton rbFileFolder;
    private javax.swing.JRadioButton rbURL;
    private javax.swing.JButton revertButton;
    private javax.swing.JTextField revertChangesetField;
    private javax.swing.JTextArea taskOutput;
    private javax.swing.JLabel usernameLabel2;
    private javax.swing.JLabel usernameLabel3;
    // End of variables declaration//GEN-END:variables
}