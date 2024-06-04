package test;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

class Test {

    public static void main(String[] args) {
        Path fileForWrite = Path.of("C:", "Users", "UserPC", "Desktop", "io", "fileWrite.txt");
        Path fileForRead = Path.of("C:", "Users", "UserPC", "Desktop", "io", "fileRead.txt");

        ///////////////
        // io
       ///////////////
//        fileWriter(fileForWrite);
//        fileReader(fileForRead);
//        bufferReader(fileForRead);
//        bufferWriter(fileForWrite);
//        inputOutputStream(fileForWrite, fileForRead);
//        writeObject(fileForWrite);
//        readObject(fileForRead);
//        randomAccessFile(fileForWrite, fileForRead);

        ///////////////
        // nio
        ///////////////
//        FilesRead(fileForRead);
//        FilesWrite(fileForWrite);



    }

    private static void FilesWrite(Path fileForWrite) {
        try {
            Files.writeString(fileForWrite, "\nAnother string", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void FilesRead(Path fileForRead) {
        try {
            List<String> list = Files.readAllLines(fileForRead);
            String result = list.stream().collect(Collectors.joining("\n"));
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void randomAccessFile(File fileForWrite, File fileForRead) {
        try (RandomAccessFile reader = new RandomAccessFile(fileForRead, "r");
             RandomAccessFile writer = new RandomAccessFile(fileForWrite, "rw")) {

            reader.seek(20);
            System.out.println(reader.getFilePointer());
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
//                builder.append(line).append("\n");
                writer.writeBytes(line);
                writer.writeBytes("\n");
            }
//            System.out.println(builder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeObject(File fileForWrite) {
        Student student = Student.builder()
                .id(1)
                .name("Ivan")
                .age(22)
                .avgGrade(8.3)
                .build();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileForWrite))) {
            oos.writeObject(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readObject(File fileForRead) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileForRead))) {
            Student student = (Student) ois.readObject();
            System.out.println(student);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private static void bufferWriter(File fileForWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileForWrite, true))) {
            writer.write("New line");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void bufferReader(File fileForRead) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileForRead))) {
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null)
                builder.append(line).append("\n");
            System.out.println(builder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void inputOutputStream(File fileForWrite, File fileForRead) {
        try (FileInputStream inputStream = new FileInputStream(fileForRead);
             FileOutputStream outputStream = new FileOutputStream(fileForWrite)) {
            byte[] bytes = inputStream.readAllBytes();
            outputStream.write(bytes);
//            int readByte;
//            while ((readByte = inputStream.read())  != -1)
//                outputStream.write(readByte);
            byte[] readBytes = inputStream.readNBytes(1024);
            while (readBytes.length != 0) {
                outputStream.write(readBytes);
                readBytes = inputStream.readNBytes(1024);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fileReader(File fileForRead) {
        try (FileReader reader = new FileReader(fileForRead)) {
            StringBuilder builder = new StringBuilder();
            int readByte;
            while ((readByte = reader.read()) != -1)
                builder.append((char) readByte);
            System.out.println(builder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fileWriter(File file) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("Some string ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

