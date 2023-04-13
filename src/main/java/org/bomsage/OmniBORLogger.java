package org.bomsage;

import java.lang.instrument.ClassFileTransformer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that logs the SHA256 and the `gitoid` of all classes loaded into the
 * JVM
 */
class OmniBORLogger implements ClassFileTransformer {

  private Logger logger = Logger.getGlobal();

  /**
   * Called by the JVM. Does no transformation of the class, but logs the SHA256
   * and `gitoid` of the loaded class
   */
  public byte[] transform(java.lang.ClassLoader loader, java.lang.String className,
      java.lang.Class<?> classBeingRedefined, java.security.ProtectionDomain protectionDomain, byte[] classfileBuffer)
      throws java.lang.instrument.IllegalClassFormatException {
    GitOID oid = new GitOID(GitOID.HashType.SHA256, GitOID.ObjectType.Blob, classfileBuffer);
    logger.log(Level.INFO,
        "Loaded class `" + className + "` with SHA256: `" + oid.hashAsHex() + "` and gitoid: `" + oid.url() + "`");
    return null;
  }

  /**
   * Called by the JVM. Does no transformation of the class, but logs the SHA256
   * and `gitoid` of the loaded class
   */
  public byte[] transform(java.lang.Module module, java.lang.ClassLoader loader, java.lang.String className,
      java.lang.Class<?> classBeingRedefined, java.security.ProtectionDomain protectionDomain, byte[] classfileBuffer)
      throws java.lang.instrument.IllegalClassFormatException {
    GitOID oid = new GitOID(GitOID.HashType.SHA256, GitOID.ObjectType.Blob, classfileBuffer);
    logger.log(Level.INFO,
        "Loaded class `" + className + "` with SHA256: `" + oid.hashAsHex() + "` and gitoid: `" + oid.url() + "`");

    return null;
  }
}
