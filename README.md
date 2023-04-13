# jbor

A Java/JVM Agent that logs OmniBOR identifiers for every loaded class.

This project provides [OmniBOR](https://omnibor.dev/) information for
classes that are loaded into the Java Virtual Machine at runtime.

## Build

To build, instance Java 17+ and [Maven](https://maven.apache.org/install.html).

`mvn package` builds and packages the code.

## To Run

`java -javaagent:target/jbor-0.1-SNAPSHOT.jar -jar target/jbor-0.1-SNAPSHOT.jar`

Adding `-javaagent:target/jbor-0.1-SNAPSHOT.jar` to starting the JVM will load the
agent and log lines:

```shell
Apr 13, 2023 3:29:17 PM org.bomsage.OmniBORLogger transform
INFO: Loaded class `sun/launcher/LauncherHelper` with SHA256: `ed1039441b84ec72a8d66d3e458f32c1e535719fd88d508959fc1dc2a3466515` and gitoid: `gitoid:blob:sha256:53189152803134a6b17a834cb3c0ba49d4766f99ddc78550b10127bdfabefa5a`
Apr 13, 2023 3:29:17 PM org.bomsage.OmniBORLogger transform
INFO: Loaded class `java/util/jar/JarVerifier` with SHA256: `0fae5e6016446bdb06affce8abc4423fac463d3bc3643ada9897b21e6b9da3ed` and gitoid: `gitoid:blob:sha256:421074df5eb45eb2db15cb566547915c79e5daec8bbe378d03e42062e7ed499e`
Apr 13, 2023 3:29:17 PM org.bomsage.OmniBORLogger transform
INFO: Loaded class `java/security/CodeSigner` with SHA256: `58f12966c49244b6cee72020f202e86d4d798eea06e2f9ac31b7c64ffc645628` and gitoid: `gitoid:blob:sha256:8d2334e7b9a3746fa7af449fcd7380a15e4725d1290e90d422c9d77cd3468677`
Apr 13, 2023 3:29:17 PM org.bomsage.OmniBORLogger transform
INFO: Loaded class `java/io/RandomAccessFile$1` with SHA256: `14b1a85e018a1eaa90ea5e87d8dbe7b78f5d0ca65802ce1a2646d75bd8f947c0` and gitoid: `gitoid:blob:sha256:62ee0822681115822842f62311ad6bd7161cd4e49032edd3dee11dbe9d1e8215`
Apr 13, 2023 3:29:17 PM org.bomsage.OmniBORLogger transform
INFO: Loaded class `org/bomsage/App` with SHA256: `a2e494110841c4f92d8013948fe3f4f3d97697720debf33ff3c48d914c87e593` and gitoid: `gitoid:blob:sha256:a27bb054ca4288ad794db18287dca7d71ae0a50c77da1a582fd69ab34c8aaf52`
```

