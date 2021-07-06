package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LicenseActions {
    private static void backupOldData(String fileName) {
        File baseFile = new File(fileName);
        if (!baseFile.renameTo(new File(fileName + ".tmp"))) {
            System.out.println("Error while backupping old file!");
            return;
        }
    }

    private static void removeGarbage(String fileName) {
        File tempFile = new File(fileName);
        tempFile.delete();
    }

    public static void insertLicenseInfo(String fileName, String licenseFileName) throws IOException {
        backupOldData(fileName);

        try {
            FileReader baseFileReader = new FileReader(fileName + ".tmp");
            Scanner baseFileScanner = new Scanner(baseFileReader);

            FileReader licenseFileReader = new FileReader(licenseFileName);
            Scanner licenseFileScanner = new Scanner(licenseFileReader);

            FileWriter newFileWriter = new FileWriter(fileName, true);

            while (licenseFileScanner.hasNextLine()) {
                newFileWriter.append(licenseFileScanner.nextLine() + "\n");
            }
            while (baseFileScanner.hasNextLine()) {
                newFileWriter.append(baseFileScanner.nextLine() + "\n");
            }

            newFileWriter.close();
            licenseFileReader.close();
            licenseFileScanner.close();
            baseFileReader.close();
            baseFileScanner.close();

            removeGarbage(fileName + ".tmp");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void replaceLicenseInfo(String fileName, String oldLicenseFileName, String newLicenseFileName) throws IOException {
        deleteLicenseInfo(fileName, oldLicenseFileName);
        insertLicenseInfo(fileName, newLicenseFileName);
    }

    public static void deleteLicenseInfo(String fileName, String licenseFileName) throws IOException {
        backupOldData(fileName);

        try {
            FileWriter newFileWriter = new FileWriter(fileName, true);

            FileReader licenseFileReader = new FileReader(licenseFileName);
            Scanner licenseFileScanner = new Scanner(licenseFileReader);

            FileReader oldFileReader = new FileReader(fileName + ".tmp");
            Scanner oldFileScanner = new Scanner(oldFileReader);

            String line = "";
            while (oldFileScanner.hasNextLine()) {
                line = oldFileScanner.nextLine();
                if (licenseFileScanner.hasNextLine()) {
                    if (!line.equals(licenseFileScanner.nextLine())) {
                        newFileWriter.append(line + "\n");
                    }
                } else {
                    newFileWriter.append(line + "\n");
                }
            }

            newFileWriter.close();
            licenseFileReader.close();
            licenseFileScanner.close();
            oldFileReader.close();
            oldFileScanner.close();

            removeGarbage(fileName + ".tmp");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
