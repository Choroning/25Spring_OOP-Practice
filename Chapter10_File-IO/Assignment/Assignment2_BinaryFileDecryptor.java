/**
 * @file Assignment2_BinaryFileDecryptor.java
 * @brief Assignment 2, Q1: Reads a binary data file according to a codebook
 *        that specifies the data type of each entry, then writes the
 *        decoded values to separate output files by type.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Assignment2_BinaryFileDecryptor {

    /**
     * Main entry point. Reads a codebook and binary data file,
     * categorizes values by type, and writes results to text files.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // 1. Load codebook
            List<String> codebook = loadCodebook("Q1_CodeBook.data");
            if (codebook.isEmpty()) {
                System.err.println("Codebook is empty.");
                return;
            }

            // 2. Prepare typed lists
            List<Integer> intList = new ArrayList<>();
            List<Boolean> boolList = new ArrayList<>();
            List<Double> doubleList = new ArrayList<>();
            List<Character> charList = new ArrayList<>();
            List<Long> longList = new ArrayList<>();

            // 3. Read binary data according to codebook
            try (DataInputStream dis = new DataInputStream(
                    new BufferedInputStream(new FileInputStream("Q1.data")))) {
                for (String type : codebook) {
                    try {
                        switch (type.toLowerCase()) {
                            case "int": case "integer":
                                intList.add(dis.readInt()); break;
                            case "boolean":
                                boolList.add(dis.readBoolean()); break;
                            case "double":
                                doubleList.add(dis.readDouble()); break;
                            case "char":
                                charList.add(dis.readChar()); break;
                            case "long":
                                longList.add(dis.readLong()); break;
                            default:
                                System.err.println("Unknown type: " + type);
                        }
                    } catch (EOFException eof) {
                        System.err.println("Reached end of file unexpectedly.");
                        break;
                    }
                }
            }

            // 4. Compute sum of doubles
            double sumDouble = doubleList.stream().mapToDouble(Double::doubleValue).sum();

            // 5. Print summary
            System.out.println("Sum of all double values: " + sumDouble);
            System.out.println("=================================");
            System.out.println("Integer count:  " + intList.size());
            System.out.println("Boolean count:  " + boolList.size());
            System.out.println("Double count:   " + doubleList.size());
            System.out.println("Char count:     " + charList.size());
            System.out.println("Long count:     " + longList.size());

            // 6. Write results to separate files
            writeListToFile("Q1_Int.txt", intList);
            writeListToFile("Q1_Boolean.txt", boolList);
            writeListToFile("Q1_Double.txt", doubleList);
            writeListToFile("Q1_Char.txt", charList);
            writeListToFile("Q1_Long.txt", longList);

        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }

    /**
     * Loads the codebook from a text file (one type name per line).
     *
     * @param filename the codebook file path
     * @return list of type names
     * @throws IOException if the file cannot be read
     */
    private static List<String> loadCodebook(String filename) throws IOException {
        List<String> codebook = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    codebook.add(line);
                }
            }
        }
        return codebook;
    }

    /**
     * Writes each element of a list to a text file, one per line.
     *
     * @param filename the output file path
     * @param list     the data to write
     * @param <T>      the element type
     */
    private static <T> void writeListToFile(String filename, List<T> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (T item : list) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing " + filename + ": " + e.getMessage());
        }
    }
}
