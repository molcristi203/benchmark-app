package controller;

import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;

public class Controller {
    private View view;

    private String ioLocation = "";

    public Controller(View view) {
        this.view = view;
        populateCPUInfo();
        populateRAMInfo();
        populateExternalMemoryInfo();
        view.addBenchCPUButtonListener(new BenchCPU(500000, 1500, 10));
        view.addBenchRAMButtonListener(new BenchRAM(20, 1024 * 1024 * 1024));
        view.addBenchDiskButtonListener(new BenchMemory(10, "", 512, 1024 * 1024, false));
        view.addBenchIOButtonListener(new BenchMemory(10, ioLocation, 10, 1024 * 1024, true));
        view.addCPUButtonListener(new TestCPU(500000, 1500, 10));
        view.addRAMButtonListener(new TestRAM(20, 1024 * 1024 * 1024));
        view.addMemoryButtonListener(new TestMemory(10, "", 512, 1024 * 1024, false));
        view.addIOButtonListener(new TestMemory(10, ioLocation, 10, 1024 * 1024, true));
    }

    public List<String> runProcess(String... args) throws Exception {
        List<String> command = new ArrayList<>(Arrays.asList(args));
        List<String> list = new ArrayList<>();
        Process process = new ProcessBuilder(command).start();
        InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            list.add(line);
        }

        System.out.println();

        return list;
    }

    public int computeSingleCPUScore(int iterations, int runs) throws Exception {
        double milliseconds = 0.0f;
        long cycles = 0L;
        int score;

        List<String> lines1 = runProcess("SingleThreadPIBenchmark.exe", String.valueOf(iterations), String.valueOf(runs));
        for (String line : lines1) {
            String[] terms = line.split(":");
            switch (terms[0]) {
                case "milliseconds":
                    milliseconds = Float.parseFloat(terms[1]);
                    break;
                case "cycles":
                    cycles = Long.parseLong(terms[1]);
                    break;
                default:
                    System.out.println("Error for term " + terms[0]);
            }
        }
        score = (int) (10000000000000.0 / ((milliseconds + cycles) / 2.0));

        return score;
    }

    public int computeMultiCPUScore(int matrixSize, int runs) throws Exception {
        double milliseconds = 0.0f;
        long cycles = 0L;
        int score;

        List<String> lines2 = runProcess("MultiThreadMatrixBenchmark.exe", String.valueOf(matrixSize), String.valueOf(runs));
        for (String line : lines2) {
            String[] terms = line.split(":");
            switch (terms[0]) {
                case "milliseconds":
                    milliseconds = Float.parseFloat(terms[1]);
                    break;
                case "cycles":
                    cycles = Long.parseLong(terms[1]);
                    break;
                default:
                    System.out.println("Error for term " + terms[0]);
            }
        }
        score = (int) (10000000000000.0 / ((milliseconds + cycles) / 2.0));
        return score;
    }

    class TestCPU implements ActionListener {
        int iterations;
        int matrixSize;
        int runs;

        public TestCPU(int iterations, int matrixSize, int runs) {
            this.iterations = iterations;
            this.matrixSize = matrixSize;
            this.runs = runs;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                File file = new File("SingleCPUResults.txt");
                file.createNewFile();
                FileWriter myWriter = new FileWriter(file);

                int tests = view.getTestsNoTextField();

                for (int i = 0; i < tests; i++) {
                    int score = computeSingleCPUScore(iterations, runs);
                    myWriter.write(score + "\n");
                }

                myWriter.close();

                file = new File("MultiCPUResults.txt");
                file.createNewFile();

                myWriter = new FileWriter(file);

                for (int i = 0; i < tests; i++) {
                    int score = computeMultiCPUScore(matrixSize, runs);
                    myWriter.write(score + "\n");
                }

                myWriter.close();

                view.showMessage("Test finished");
            } catch (Exception exception) {
                exception.printStackTrace();
                view.showError(exception.getMessage());
            }
        }
    }

    class BenchCPU implements ActionListener {
        int iterations;
        int matrixSize;
        int runs;

        public BenchCPU(int iterations, int matrixSize, int runs) {
            this.iterations = iterations;
            this.matrixSize = matrixSize;
            this.runs = runs;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int score;
                score = computeSingleCPUScore(iterations, runs);
                view.setSingleCoreTextField(score);
                score = computeMultiCPUScore(matrixSize, runs);
                view.setMultiCoreTextField(score);
                view.showMessage("Test finished");
            } catch (Exception exception) {
                exception.printStackTrace();
                view.showError(exception.getMessage());
            }
        }
    }

    class TestRAM implements ActionListener {
        int runs;
        int blockSize;

        public TestRAM(int runs, int blockSize) {
            this.blockSize = blockSize;
            this.runs = runs;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                File file = new File("RAMResults.txt");
                file.createNewFile();
                FileWriter myWriter = new FileWriter(file);

                int tests = view.getTestsNoTextField();

                for (int i = 0; i < tests; i++) {
                    int score = computeRAMScore(runs, blockSize);
                    myWriter.write(score + "\n");
                }

                myWriter.close();

                view.showMessage("Test finished");
            } catch (Exception exception) {
                exception.printStackTrace();
                view.showError(exception.getMessage());
            }
        }
    }

    class BenchRAM implements ActionListener {
        int runs;
        int blockSize;

        public BenchRAM(int runs, int blockSize) {
            this.runs = runs;
            this.blockSize = blockSize;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int score = computeRAMScore(runs, blockSize);
                view.setRAMBenchScoreTextField(score);
                view.showMessage("Test finished");
            } catch (Exception ex) {
                ex.printStackTrace();
                view.showError(ex.getMessage());
            }
        }
    }

    public int computeRAMScore(int runs, int blockSize) throws Exception {
        double score1 = 0;
        List<String> lines1 = runProcess("MemoryCopyRAMBenchmark.exe", String.valueOf(runs), String.valueOf(blockSize));
        for (String line : lines1) {
            score1 = Double.parseDouble(line);
        }

        double score2 = 0;
        List<String> lines2 = runProcess("MemoryReadWriteRAMBenchmark.exe", String.valueOf(runs), String.valueOf(blockSize));
        for (String line : lines2) {
            score2 = Double.parseDouble(line);
        }

        double score3 = 0;
        List<String> lines3 = runProcess("MemoryFillRAMBenchmark.exe", String.valueOf(runs), String.valueOf(blockSize));
        for (String line : lines3)
        {
            score3 = Double.parseDouble(line);
        }

        int score = (int) ((score1 + score2 + score3) / 3);
        return score;
    }

    class TestMemory implements ActionListener {
        private int runs;
        private String location;
        private int blockCount;
        private int blockSize;
        private boolean io;

        public TestMemory(int runs, String location, int blockCount, int blockSize, boolean io) {
            this.runs = runs;
            this.location = location;
            this.blockCount = blockCount;
            this.blockSize = blockSize;
            this.io = io;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String fileName = "";

                if (io) {
                    fileName = "IOResults.txt";
                }
                else {
                    fileName = "MemoryResults.txt";
                }

                File file = new File(fileName);
                file.createNewFile();
                FileWriter myWriter = new FileWriter(file);

                int tests = view.getTestsNoTextField();

                for (int i = 0; i < tests; i++) {
                    int score = computeMemoryScore(runs, location, blockCount, blockSize);
                    myWriter.write(score + "\n");
                }

                myWriter.close();

                view.showMessage("Test finished");
            } catch (Exception exception) {
                exception.printStackTrace();
                view.showError(exception.getMessage());
            }
        }
    }

    class BenchMemory implements ActionListener {
        private int runs;
        private String location;
        private int blockCount;
        private int blockSize;
        private boolean io;

        public BenchMemory(int runs, String location, int blockCount, int blockSize, boolean io) {
            this.runs = runs;
            this.location = location;
            this.blockCount = blockCount;
            this.blockSize = blockSize;
            this.io = io;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double score = computeMemoryScore(runs, location, blockCount, blockSize);
                if (io) {
                    view.setIoScoreField(score);
                } else {
                    view.setDiskBenchScoreTextField(score);
                }
                view.showMessage("Test finished");
            } catch (Exception exception) {
                exception.printStackTrace();
                view.showError(exception.getMessage());
            }
        }
    }

    public int computeMemoryScore(int runs, String location, int blockCount, int blockSize) throws Exception {
        String fileName = System.currentTimeMillis() + ".dat";
        double score1 = 0;
        List<String> lines1 = runProcess("CreateFileMemoryBenchmark.exe", String.valueOf(runs), location + fileName, String.valueOf(blockCount), String.valueOf(blockSize));
        for (String line : lines1) {
            score1 = Double.parseDouble(line);
        }

        double score2 = 0;
        List<String> lines2 = runProcess("ReadFileMemoryBenchmark.exe", String.valueOf(runs), location + fileName, String.valueOf(blockCount), String.valueOf(blockSize));
        for (String line : lines2) {
            score2 = Double.parseDouble(line);
        }

        int score = (int) ((score1 + score2) / 2);
        return score;
    }

    public void populateCPUInfo() {
        try {
            List<String> lines = runProcess("CPUInfo");
            for (String line : lines) {
                String[] terms = line.split(":");
                switch (terms[0]) {
                    case "family":
                        view.setFamilyIdTextField(terms[1]);
                        break;
                    case "model":
                        view.setModelTextField(terms[1]);
                        break;
                    case "extendedfamily":
                        view.setExtendedFamilyTextField(terms[1]);
                        break;
                    case "extendedmodel":
                        view.setExtendedModelTextField(terms[1]);
                        break;
                    case "name":
                        view.setCpuNameTextField(terms[1]);
                        break;
                    case "stepping":
                        view.setSteppingTextField(terms[1]);
                        break;
                    case "logical":
                        view.setThreadsTextField(terms[1]);
                        break;
                    case "physical":
                        view.setCoresTextField(terms[1]);
                        break;
                    default:
                        System.out.println("No input for the term: " + terms[0]);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void populateRAMInfo() {
        try {
            List<String> lines = runProcess("RAMInfo");
            for (String line : lines) {
                view.setRamSizeTextField(line);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void populateExternalMemoryInfo() {
        try {
            List<String> lines = runProcess("ExternalMemoryInfo");
            for (String line : lines) {
                String[] terms = line.split("-");
                switch (terms[0]) {
                    case "HDD/SSD":
                        String[] terms2 = terms[1].split(" ");
                        String text = "Disk " + terms2[0] + "   " + terms2[1] + " GB Free out of " + terms2[2] + " GB\n";
                        view.setDiskTextArea(view.getDiskTextArea() + text);
                        break;
                    case "IO":
                        String[] terms3 = terms[1].split(" ");
                        String text2 = "Drive " + terms3[0] + "   " + terms3[1] + " GB Free out of " + terms3[2] + " GB\n";
                        view.setIoTextArea(view.getIoTextArea() + text2);
                        if (ioLocation.isEmpty()) {
                            ioLocation = terms3[0];
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            view.showError(exception.getMessage());
        }
    }
}
