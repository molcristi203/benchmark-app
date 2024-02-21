package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JPanel contentPane;
    private JTextField cpuNameTextField;
    private JTextField familyIdTextField;
    private JTextField extendedFamilyTextField;
    private JTextField modelTextField;
    private JTextField extendedModelTextField;
    private JTextField steppingTextField;
    private JTextField coresTextField;
    private JTextField threadsTextField;
    private JTextField singleCoreTextField;
    private JTextField multiCoreTextField;
    private JButton benchCPUButton;
    private JTextField ramSizeTextField;
    private JTextField ramBenchScoreTextField;
    private JButton benchRAMButton;
    private JTextField memoryTextField;
    private JTextField diskBenchScoreTextField;
    private JButton benchDiskButton;
    private JTextArea diskTextArea;
    private JTextField ioScoreField;
    private JTextArea ioTextArea;
    private JButton benchIOButton;
    private JTextField testsNoTextField;
    private JButton cpuButton;
    private JButton ramButton;
    private JButton memoryButton;
    private JButton ioButton;

    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 360);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        setContentPane(tabbedPane);

        JPanel tab1 = new JPanel();
        tabbedPane.add(tab1);
        tabbedPane.setTitleAt(0, "CPU Info");
        GridBagLayout gbl_tab1 = new GridBagLayout();
        gbl_tab1.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_tab1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_tab1.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_tab1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        tab1.setLayout(gbl_tab1);

        JLabel cpuNameLabel = new JLabel("CPU Name:");
        GridBagConstraints gbc_cpuNameLabel = new GridBagConstraints();
        gbc_cpuNameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_cpuNameLabel.anchor = GridBagConstraints.EAST;
        gbc_cpuNameLabel.gridx = 0;
        gbc_cpuNameLabel.gridy = 0;
        tab1.add(cpuNameLabel, gbc_cpuNameLabel);

        cpuNameTextField = new JTextField();
        cpuNameTextField.setEditable(false);
        GridBagConstraints gbc_cpuNameTextField = new GridBagConstraints();
        gbc_cpuNameTextField.gridwidth = 3;
        gbc_cpuNameTextField.insets = new Insets(0, 0, 5, 5);
        gbc_cpuNameTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_cpuNameTextField.gridx = 1;
        gbc_cpuNameTextField.gridy = 0;
        tab1.add(cpuNameTextField, gbc_cpuNameTextField);
        cpuNameTextField.setColumns(10);

        JLabel familyLabel = new JLabel("Family:");
        GridBagConstraints gbc_familyLabel = new GridBagConstraints();
        gbc_familyLabel.anchor = GridBagConstraints.EAST;
        gbc_familyLabel.insets = new Insets(0, 0, 5, 5);
        gbc_familyLabel.gridx = 0;
        gbc_familyLabel.gridy = 1;
        tab1.add(familyLabel, gbc_familyLabel);

        familyIdTextField = new JTextField();
        familyIdTextField.setEditable(false);
        GridBagConstraints gbc_familyIdTextField = new GridBagConstraints();
        gbc_familyIdTextField.insets = new Insets(0, 0, 5, 5);
        gbc_familyIdTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_familyIdTextField.gridx = 1;
        gbc_familyIdTextField.gridy = 1;
        tab1.add(familyIdTextField, gbc_familyIdTextField);
        familyIdTextField.setColumns(10);

        JLabel extendedFamilyLabel = new JLabel("Extended Family:");
        GridBagConstraints gbc_extendedFamilyLabel = new GridBagConstraints();
        gbc_extendedFamilyLabel.anchor = GridBagConstraints.EAST;
        gbc_extendedFamilyLabel.insets = new Insets(0, 0, 5, 5);
        gbc_extendedFamilyLabel.gridx = 2;
        gbc_extendedFamilyLabel.gridy = 1;
        tab1.add(extendedFamilyLabel, gbc_extendedFamilyLabel);

        extendedFamilyTextField = new JTextField();
        extendedFamilyTextField.setEditable(false);
        GridBagConstraints gbc_extendedFamilyTextField = new GridBagConstraints();
        gbc_extendedFamilyTextField.insets = new Insets(0, 0, 5, 0);
        gbc_extendedFamilyTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_extendedFamilyTextField.gridx = 3;
        gbc_extendedFamilyTextField.gridy = 1;
        tab1.add(extendedFamilyTextField, gbc_extendedFamilyTextField);
        extendedFamilyTextField.setColumns(10);

        JLabel modelLabel = new JLabel("Model:");
        GridBagConstraints gbc_modelLabel = new GridBagConstraints();
        gbc_modelLabel.anchor = GridBagConstraints.EAST;
        gbc_modelLabel.insets = new Insets(0, 0, 5, 5);
        gbc_modelLabel.gridx = 0;
        gbc_modelLabel.gridy = 2;
        tab1.add(modelLabel, gbc_modelLabel);

        modelTextField = new JTextField();
        modelTextField.setEditable(false);
        GridBagConstraints gbc_modelTextField = new GridBagConstraints();
        gbc_modelTextField.insets = new Insets(0, 0, 5, 5);
        gbc_modelTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_modelTextField.gridx = 1;
        gbc_modelTextField.gridy = 2;
        tab1.add(modelTextField, gbc_modelTextField);
        modelTextField.setColumns(10);

        JLabel extendedModelLabel = new JLabel("Extended Model:");
        GridBagConstraints gbc_extendedModelLabel = new GridBagConstraints();
        gbc_extendedModelLabel.anchor = GridBagConstraints.EAST;
        gbc_extendedModelLabel.insets = new Insets(0, 0, 5, 5);
        gbc_extendedModelLabel.gridx = 2;
        gbc_extendedModelLabel.gridy = 2;
        tab1.add(extendedModelLabel, gbc_extendedModelLabel);

        extendedModelTextField = new JTextField();
        extendedModelTextField.setEditable(false);
        GridBagConstraints gbc_txtExtendedModel = new GridBagConstraints();
        gbc_txtExtendedModel.insets = new Insets(0, 0, 5, 0);
        gbc_txtExtendedModel.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtExtendedModel.gridx = 3;
        gbc_txtExtendedModel.gridy = 2;
        tab1.add(extendedModelTextField, gbc_txtExtendedModel);
        extendedModelTextField.setColumns(10);

        JLabel steppingLabel = new JLabel("Stepping:");
        GridBagConstraints gbc_steppingLabel = new GridBagConstraints();
        gbc_steppingLabel.anchor = GridBagConstraints.EAST;
        gbc_steppingLabel.insets = new Insets(0, 0, 0, 5);
        gbc_steppingLabel.gridx = 0;
        gbc_steppingLabel.gridy = 3;
        tab1.add(steppingLabel, gbc_steppingLabel);

        steppingTextField = new JTextField();
        steppingTextField.setEditable(false);
        GridBagConstraints gbc_steppingTextField = new GridBagConstraints();
        gbc_steppingTextField.insets = new Insets(0, 0, 0, 5);
        gbc_steppingTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_steppingTextField.gridx = 1;
        gbc_steppingTextField.gridy = 3;
        tab1.add(steppingTextField, gbc_steppingTextField);
        steppingTextField.setColumns(10);

        JLabel coresLabel = new JLabel("Cores:");
        GridBagConstraints gbc_coresLabel = new GridBagConstraints();
        gbc_coresLabel.insets = new Insets(0, 0, 0, 5);
        gbc_coresLabel.anchor = GridBagConstraints.EAST;
        gbc_coresLabel.gridx = 0;
        gbc_coresLabel.gridy = 4;
        tab1.add(coresLabel, gbc_coresLabel);

        coresTextField = new JTextField();
        coresTextField.setEditable(false);
        GridBagConstraints gbc_coresTextField = new GridBagConstraints();
        gbc_coresTextField.insets = new Insets(0, 0, 0, 5);
        gbc_coresTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_coresTextField.gridx = 1;
        gbc_coresTextField.gridy = 4;
        tab1.add(coresTextField, gbc_coresTextField);
        coresTextField.setColumns(10);

        JLabel threadsLabel = new JLabel("Threads:");
        GridBagConstraints gbc_threadsLabel = new GridBagConstraints();
        gbc_threadsLabel.insets = new Insets(0, 0, 0, 5);
        gbc_threadsLabel.anchor = GridBagConstraints.EAST;
        gbc_threadsLabel.gridx = 2;
        gbc_threadsLabel.gridy = 4;
        tab1.add(threadsLabel, gbc_threadsLabel);

        threadsTextField = new JTextField();
        threadsTextField.setEditable(false);
        GridBagConstraints gbc_threadsTextField = new GridBagConstraints();
        gbc_threadsTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_threadsTextField.gridx = 3;
        gbc_threadsTextField.gridy = 4;
        tab1.add(threadsTextField, gbc_threadsTextField);
        threadsTextField.setColumns(10);

        benchCPUButton = new JButton("Bench");
        GridBagConstraints gbc_benchCPUButton = new GridBagConstraints();
        gbc_benchCPUButton.insets = new Insets(0, 0, 5, 5);
        gbc_benchCPUButton.gridx = 0;
        gbc_benchCPUButton.gridy = 5;
        tab1.add(benchCPUButton, gbc_benchCPUButton);

        JLabel singleCoreLabel = new JLabel("Single Core:");
        GridBagConstraints gbc_singleCoreLabel = new GridBagConstraints();
        gbc_singleCoreLabel.anchor = GridBagConstraints.EAST;
        gbc_singleCoreLabel.insets = new Insets(0, 0, 5, 5);
        gbc_singleCoreLabel.gridx = 0;
        gbc_singleCoreLabel.gridy = 6;
        tab1.add(singleCoreLabel, gbc_singleCoreLabel);

        singleCoreTextField = new JTextField();
        singleCoreTextField.setEditable(false);
        GridBagConstraints gbc_singleCoreTextField = new GridBagConstraints();
        gbc_singleCoreTextField.insets = new Insets(0, 0, 5, 5);
        gbc_singleCoreTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_singleCoreTextField.gridx = 1;
        gbc_singleCoreTextField.gridy = 6;
        tab1.add(singleCoreTextField, gbc_singleCoreTextField);
        singleCoreTextField.setColumns(10);

        JLabel multiCoreLabel = new JLabel("Multi Core:");
        GridBagConstraints gbc_multiCoreLabel = new GridBagConstraints();
        gbc_multiCoreLabel.anchor = GridBagConstraints.EAST;
        gbc_multiCoreLabel.insets = new Insets(0, 0, 5, 5);
        gbc_multiCoreLabel.gridx = 0;
        gbc_multiCoreLabel.gridy = 7;
        tab1.add(multiCoreLabel, gbc_multiCoreLabel);

        multiCoreTextField = new JTextField();
        multiCoreTextField.setEditable(false);
        GridBagConstraints gbc_multiCoreTextField = new GridBagConstraints();
        gbc_multiCoreTextField.insets = new Insets(0, 0, 5, 5);
        gbc_multiCoreTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_multiCoreTextField.gridx = 1;
        gbc_multiCoreTextField.gridy = 7;
        tab1.add(multiCoreTextField, gbc_multiCoreTextField);
        multiCoreTextField.setColumns(10);

        JPanel tab2 = new JPanel();
        tabbedPane.addTab("RAM Info", null, tab2, null);
        GridBagLayout gbl_tab2 = new GridBagLayout();
        gbl_tab2.columnWidths = new int[]{0, 0, 0};
        gbl_tab2.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_tab2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_tab2.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        tab2.setLayout(gbl_tab2);

        JLabel ramSizeLabel = new JLabel("RAM Size:");
        GridBagConstraints gbc_ramSizeLabel = new GridBagConstraints();
        gbc_ramSizeLabel.insets = new Insets(0, 0, 5, 5);
        gbc_ramSizeLabel.anchor = GridBagConstraints.EAST;
        gbc_ramSizeLabel.gridx = 0;
        gbc_ramSizeLabel.gridy = 0;
        tab2.add(ramSizeLabel, gbc_ramSizeLabel);

        ramSizeTextField = new JTextField();
        ramSizeTextField.setEditable(false);
        GridBagConstraints gbc_ramSizeTextField = new GridBagConstraints();
        gbc_ramSizeTextField.insets = new Insets(0, 0, 5, 0);
        gbc_ramSizeTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_ramSizeTextField.gridx = 1;
        gbc_ramSizeTextField.gridy = 0;
        tab2.add(ramSizeTextField, gbc_ramSizeTextField);
        ramSizeTextField.setColumns(10);

        benchRAMButton = new JButton("Bench");
        GridBagConstraints gbc_benchRAMButton = new GridBagConstraints();
        gbc_benchRAMButton.insets = new Insets(0, 0, 5, 5);
        gbc_benchRAMButton.gridx = 0;
        gbc_benchRAMButton.gridy = 1;
        tab2.add(benchRAMButton, gbc_benchRAMButton);

        JLabel ramBenchScoreLabel = new JLabel("Score:");
        GridBagConstraints gbc_ramBenchScoreLabel = new GridBagConstraints();
        gbc_ramBenchScoreLabel.anchor = GridBagConstraints.EAST;
        gbc_ramBenchScoreLabel.insets = new Insets(0, 0, 5, 5);
        gbc_ramBenchScoreLabel.gridx = 0;
        gbc_ramBenchScoreLabel.gridy = 2;
        tab2.add(ramBenchScoreLabel, gbc_ramBenchScoreLabel);

        ramBenchScoreTextField = new JTextField();
        ramBenchScoreTextField.setEditable(false);
        GridBagConstraints gbc_ramBenchScoreTextField = new GridBagConstraints();
        gbc_ramBenchScoreTextField.insets = new Insets(0, 0, 5, 0);
        gbc_ramBenchScoreTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_ramBenchScoreTextField.gridx = 1;
        gbc_ramBenchScoreTextField.gridy = 2;
        tab2.add(ramBenchScoreTextField, gbc_ramBenchScoreTextField);
        ramBenchScoreTextField.setColumns(10);

        JPanel tab3 = new JPanel();
        tabbedPane.addTab("Memory Info", null, tab3, null);
        GridBagLayout gbl_tab3 = new GridBagLayout();
        gbl_tab3.columnWidths = new int[]{0, 0, 0};
        gbl_tab3.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_tab3.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_tab3.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        tab3.setLayout(gbl_tab3);

        JLabel diskSpaceLabel = new JLabel("Disk Size:");
        GridBagConstraints gbc_diskSpaceLabel = new GridBagConstraints();
        gbc_diskSpaceLabel.anchor = GridBagConstraints.EAST;
        gbc_diskSpaceLabel.insets = new Insets(0, 0, 5, 5);
        gbc_diskSpaceLabel.gridx = 0;
        gbc_diskSpaceLabel.gridy = 0;
        tab3.add(diskSpaceLabel, gbc_diskSpaceLabel);

        diskTextArea = new JTextArea();
        diskTextArea.setEditable(false);
        diskTextArea.setRows(2);
        GridBagConstraints gbc_diskTextArea = new GridBagConstraints();
        gbc_diskTextArea.insets = new Insets(0, 0, 5, 0);
        gbc_diskTextArea.fill = GridBagConstraints.BOTH;
        gbc_diskTextArea.gridx = 1;
        gbc_diskTextArea.gridy = 0;
        tab3.add(diskTextArea, gbc_diskTextArea);

        benchDiskButton = new JButton("Bench");
        GridBagConstraints gbc_benchDiskButton = new GridBagConstraints();
        gbc_benchDiskButton.insets = new Insets(0, 0, 5, 5);
        gbc_benchDiskButton.gridx = 0;
        gbc_benchDiskButton.gridy = 1;
        tab3.add(benchDiskButton, gbc_benchDiskButton);

        JLabel diskBenchScoreLabel = new JLabel("Score:");
        GridBagConstraints gbc_diskBenchScoreLabel = new GridBagConstraints();
        gbc_diskBenchScoreLabel.anchor = GridBagConstraints.EAST;
        gbc_diskBenchScoreLabel.insets = new Insets(0, 0, 5, 5);
        gbc_diskBenchScoreLabel.gridx = 0;
        gbc_diskBenchScoreLabel.gridy = 2;
        tab3.add(diskBenchScoreLabel, gbc_diskBenchScoreLabel);

        diskBenchScoreTextField = new JTextField();
        diskBenchScoreTextField.setEditable(false);
        diskBenchScoreTextField.setColumns(10);
        GridBagConstraints gbc_diskBenchScoreTextField = new GridBagConstraints();
        gbc_diskBenchScoreTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_diskBenchScoreTextField.insets = new Insets(0, 0, 5, 0);
        gbc_diskBenchScoreTextField.gridx = 1;
        gbc_diskBenchScoreTextField.gridy = 2;
        tab3.add(diskBenchScoreTextField, gbc_diskBenchScoreTextField);

        JPanel tab4 = new JPanel();
        tabbedPane.addTab("IO Info", null, tab4, null);
        GridBagLayout gbl_tab4 = new GridBagLayout();
        gbl_tab4.columnWidths = new int[]{0, 0, 0};
        gbl_tab4.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_tab4.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_tab4.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        tab4.setLayout(gbl_tab4);

        JLabel ioSpaceLabel = new JLabel("IO Size:");
        GridBagConstraints gbc_ioSpaceLabel = new GridBagConstraints();
        gbc_ioSpaceLabel.anchor = GridBagConstraints.EAST;
        gbc_ioSpaceLabel.insets = new Insets(0, 0, 5, 5);
        gbc_ioSpaceLabel.gridx = 0;
        gbc_ioSpaceLabel.gridy = 0;
        tab4.add(ioSpaceLabel, gbc_ioSpaceLabel);

        ioTextArea = new JTextArea();
        ioTextArea.setEditable(false);
        ioTextArea.setRows(2);
        GridBagConstraints gbc_ioTextArea = new GridBagConstraints();
        gbc_ioTextArea.fill = GridBagConstraints.BOTH;
        gbc_ioTextArea.insets = new Insets(0, 0, 5, 0);
        gbc_ioTextArea.gridx = 1;
        gbc_ioTextArea.gridy = 0;
        tab4.add(ioTextArea, gbc_ioTextArea);

        benchIOButton = new JButton("Bench");
        GridBagConstraints gbc_benchIOButton = new GridBagConstraints();
        gbc_benchIOButton.insets = new Insets(0, 0, 5, 5);
        gbc_benchIOButton.gridx = 0;
        gbc_benchIOButton.gridy = 1;
        tab4.add(benchIOButton, gbc_benchIOButton);

        JLabel ioBenchScoreLabel = new JLabel("Score:");
        GridBagConstraints gbc_ioBenchScoreLabel = new GridBagConstraints();
        gbc_ioBenchScoreLabel.anchor = GridBagConstraints.EAST;
        gbc_ioBenchScoreLabel.insets = new Insets(0, 0, 5, 5);
        gbc_ioBenchScoreLabel.gridx = 0;
        gbc_ioBenchScoreLabel.gridy = 2;
        tab4.add(ioBenchScoreLabel, gbc_ioBenchScoreLabel);

        ioScoreField = new JTextField();
        ioScoreField.setEditable(false);
        ioScoreField.setColumns(10);
        GridBagConstraints gbc_ioScoreField = new GridBagConstraints();
        gbc_ioScoreField.fill = GridBagConstraints.HORIZONTAL;
        gbc_ioScoreField.insets = new Insets(0, 0, 5, 0);
        gbc_ioScoreField.gridx = 1;
        gbc_ioScoreField.gridy = 2;
        tab4.add(ioScoreField, gbc_ioScoreField);

        JPanel tab5 = new JPanel();
        tabbedPane.addTab("Statistics", null, tab5, null);
        GridBagLayout gbl_tab5 = new GridBagLayout();
        gbl_tab5.columnWidths = new int[]{0, 0, 0};
        gbl_tab5.rowHeights = new int[]{0, 0, 0, 0};
        gbl_tab5.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_tab5.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        tab5.setLayout(gbl_tab5);

        JLabel testsNoLabel = new JLabel("Tests No: ");
        GridBagConstraints gbc_testsNoLabel = new GridBagConstraints();
        gbc_testsNoLabel.insets = new Insets(0, 0, 5, 5);
        gbc_testsNoLabel.anchor = GridBagConstraints.EAST;
        gbc_testsNoLabel.gridx = 0;
        gbc_testsNoLabel.gridy = 0;
        tab5.add(testsNoLabel, gbc_testsNoLabel);

        testsNoTextField = new JTextField();
        GridBagConstraints gbc_testsNoTextField = new GridBagConstraints();
        gbc_testsNoTextField.insets = new Insets(0, 0, 5, 0);
        gbc_testsNoTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_testsNoTextField.gridx = 1;
        gbc_testsNoTextField.gridy = 0;
        tab5.add(testsNoTextField, gbc_testsNoTextField);
        testsNoTextField.setColumns(10);

        cpuButton = new JButton("CPU");
        GridBagConstraints gbc_cpuButton = new GridBagConstraints();
        gbc_cpuButton.insets = new Insets(0, 0, 5, 5);
        gbc_cpuButton.gridx = 0;
        gbc_cpuButton.gridy = 1;
        tab5.add(cpuButton, gbc_cpuButton);

        ramButton = new JButton("RAM");
        GridBagConstraints gbc_ramButton = new GridBagConstraints();
        gbc_ramButton.insets = new Insets(0, 0, 5, 0);
        gbc_ramButton.anchor = GridBagConstraints.WEST;
        gbc_ramButton.gridx = 1;
        gbc_ramButton.gridy = 1;
        tab5.add(ramButton, gbc_ramButton);

        memoryButton = new JButton("Memory");
        GridBagConstraints gbc_memoryButton = new GridBagConstraints();
        gbc_memoryButton.insets = new Insets(0, 0, 0, 5);
        gbc_memoryButton.gridx = 0;
        gbc_memoryButton.gridy = 2;
        tab5.add(memoryButton, gbc_memoryButton);

        ioButton = new JButton("IO");
        GridBagConstraints gbc_ioButton = new GridBagConstraints();
        gbc_ioButton.anchor = GridBagConstraints.WEST;
        gbc_ioButton.gridx = 1;
        gbc_ioButton.gridy = 2;
        tab5.add(ioButton, gbc_ioButton);

        setVisible(true);
    }

    public String getCpuNameTextField() {
        return cpuNameTextField.getText();
    }

    public void setCpuNameTextField(String cpuNameTextField) {
        this.cpuNameTextField.setText(cpuNameTextField);
    }

    public String getFamilyIdTextField() {
        return familyIdTextField.getText();
    }

    public void setFamilyIdTextField(String familyIdTextField) {
        this.familyIdTextField.setText(familyIdTextField);
    }

    public String getExtendedFamilyTextField() {
        return extendedFamilyTextField.getText();
    }

    public void setExtendedFamilyTextField(String extendedFamilyTextField) {
        this.extendedFamilyTextField.setText(extendedFamilyTextField);
    }

    public String getModelTextField() {
        return modelTextField.getText();
    }

    public void setModelTextField(String modelTextField) {
        this.modelTextField.setText(modelTextField);
    }

    public String getExtendedModelTextField() {
        return extendedModelTextField.getText();
    }

    public void setExtendedModelTextField(String extendedModelTextField) {
        this.extendedModelTextField.setText(extendedModelTextField);
    }

    public String getSteppingTextField() {
        return steppingTextField.getText();
    }

    public void setSteppingTextField(String steppingTextField) {
        this.steppingTextField.setText(steppingTextField);
    }

    public String getCoresTextField() {
        return coresTextField.getText();
    }

    public void setCoresTextField(String coresTextField) {
        this.coresTextField.setText(coresTextField);
    }

    public String getThreadsTextField() {
        return threadsTextField.getText();
    }

    public void setThreadsTextField(String threadsTextField) {
        this.threadsTextField.setText(threadsTextField);
    }

    public JButton getBenchCPUButton() {
        return benchCPUButton;
    }

    public void setBenchCPUButton(JButton benchCPUButton) {
        this.benchCPUButton = benchCPUButton;
    }

    public double getSingleCoreTextField() {
        return Double.parseDouble(singleCoreTextField.getText());
    }

    public void setSingleCoreTextField(double singleCoreTextField) {
        this.singleCoreTextField.setText(String.valueOf(singleCoreTextField));
    }

    public double getMultiCoreTextField() {
        return Double.parseDouble(multiCoreTextField.getText());
    }

    public void setMultiCoreTextField(double multiCoreTextField) {
        this.multiCoreTextField.setText(String.valueOf(multiCoreTextField));
    }

    public String getRamSizeTextField() {
        return ramSizeTextField.getText();
    }

    public void setRamSizeTextField(String ramSizeTextField) {
        this.ramSizeTextField.setText(ramSizeTextField);
    }

    public double getRAMBenchScoreTextField() {
        return Double.parseDouble(ramBenchScoreTextField.getText());
    }

    public void setRAMBenchScoreTextField(double scoreTextField) {
        this.ramBenchScoreTextField.setText(String.valueOf(scoreTextField));
    }

    public JButton getBenchRAMButton() {
        return benchRAMButton;
    }

    public void setBenchRAMButton(JButton benchRAMButton) {
        this.benchRAMButton = benchRAMButton;
    }

    public String getMemoryTextField() {
        return memoryTextField.getText();
    }

    public void setMemoryTextField(String memoryTextField) {
        this.memoryTextField.setText(memoryTextField);
    }

    public double getDiskBenchScoreTextField() {
        return Double.parseDouble(diskBenchScoreTextField.getText());
    }

    public void setDiskBenchScoreTextField(double diskBenchScoreTextField) {
        this.diskBenchScoreTextField.setText(String.valueOf(diskBenchScoreTextField));
    }

    public String getDiskTextArea() {
        return diskTextArea.getText();
    }

    public void setDiskTextArea(String diskTextArea) {
        this.diskTextArea.setText(diskTextArea);
    }

    public String getIoScoreField() {
        return ioScoreField.getText();
    }

    public void setIoScoreField(Double ioScoreField) {
        this.ioScoreField.setText(String.valueOf(ioScoreField));
    }

    public String getIoTextArea() {
        return ioTextArea.getText();
    }

    public void setIoTextArea(String ioTextArea) {
        this.ioTextArea.setText(ioTextArea);
    }

    public int getTestsNoTextField() {
        return Integer.parseInt(testsNoTextField.getText());
    }

    public void setTestsNoTextField(int testsNoTextField) {
        this.testsNoTextField.setText(String.valueOf(testsNoTextField));
    }

    public void addBenchCPUButtonListener(ActionListener actionListener) {
        benchCPUButton.addActionListener(actionListener);
    }

    public void addBenchRAMButtonListener(ActionListener actionListener) {
        benchRAMButton.addActionListener(actionListener);
    }

    public void addBenchDiskButtonListener(ActionListener actionListener) {
        benchDiskButton.addActionListener(actionListener);
    }

    public void addBenchIOButtonListener(ActionListener actionListener) {
        benchIOButton.addActionListener(actionListener);
    }

    public void addCPUButtonListener(ActionListener actionListener) {
        cpuButton.addActionListener(actionListener);
    }

    public void addRAMButtonListener(ActionListener actionListener) {
        ramButton.addActionListener(actionListener);
    }

    public void addMemoryButtonListener(ActionListener actionListener) {
        memoryButton.addActionListener(actionListener);
    }

    public void addIOButtonListener(ActionListener actionListener) {
        ioButton.addActionListener(actionListener);
    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.PLAIN_MESSAGE);
    }
}
