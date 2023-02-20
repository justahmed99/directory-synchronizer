# Directory Synchronizer

## Background
This program is a directory synchronization mechanism that will minimize the overall copy
operation from the source directory to the target directory.

## Technologies
- Java 17 (Compatible with Java 11)
- Gradle

## How It Works
The following are a set of example cases using the above source directories: <br>

Given the following source directories:
```
├── source_folder_a
│   ├── file_a
│   ├── file_b
│   ├── file_c
├── source_folder_b
│   ├── file_a
│   ├── file_c
│   ├── file_d
├── source_folder_c
│   ├── dir_a_a
│   │   ├── file_a_a
│   ├── file_a
│   ├── file_d
│   ├── file_e
```


### Case 1
**Target directory:** empty <br>
**Command given:** `java DirectorySynchronizer sync -s source_folder_a -d target_folder` <br>
**Expected result:** `target_folder` will have the exact same contents with `source_folder_a`

### Case 2
**Target directory:** already synced with `source_folder_a` <br>
**Command given:** `java DirectorySynchronizer sync -s source_folder_b -d target_folder` <br>
**Expected result:** `target_folder` will have the exact same contents with `source_folder_b`, meaning
`file_b` is deleted and `file_d` is copied.

### Case 3
**Target directory:** already synced with `source_folder_b` <br>
**Command given:** `java DirectorySynchronizer sync -s source_folder_c -d target_folder` <br>
**Expected result:** `target_folder` will have the exact same contents with `source_folder_c`, meaning
`file_c` is deleted, `file_e` is copied into root and `file_a_a` is copied into `dir_a`.

### Case 4
**Target directory:** already synced with `source_folder_c` <br>
**Command given:** `java DirectorySynchronizer sync -s source_folder_a -d target_folder` <br>
**Expected result:** target_folder will have the exact same contents with `source_folder_a`, meaning
`file_d`, `file_e` and `file_a_a` are deleted and `file_b` and `file_c` are copied.


## How To Use
This program has several command interfaces :

| Command | Arguments | Description                                                               |
|---------|-----------|---------------------------------------------------------------------------|
| sync    | -d        | Destination directory                                                     |
|         | -s        | Source, for this test the source is simply a directory in the file system |

- You can run with `java` command :
  - ` java DirectorySynchronizer sync -s [source_dir] -d [destination_dir]`
- On sync command, The CLI application will start synchronizing the destination folder with the source folder,
  and the source folder should be regarded as the source of truth.

### Build The Program
To build the program, you can use `gradlew` with following steps :
- `./gradlew clean build`
- If you are using Linux OS, you must set the execution permission for `gradlew` file with this command :
  - `chmod +x gradlew`
- If the program successfully build, you will find the .jar file inside `build/libs` directory.
- You can run the .jar file with this command :
  - `java -jar DirectorySynchronizer.jar sync -s [source_folder] -d [destination_folder]`
- **(OPTIONAL)** If you want to convert the JAR file to .exe file, you can use `Launch4j` program.
