package org.bomsage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
// import java.util.HexFormat;
import java.math.BigInteger;
/**
 * A set of utilities that computes gitoids
 */
public class GitOID {
    /**
     * The object type
     */
    public enum ObjectType {
        Blob, Tree, Commit, Tag;

        /**
         * Get the canonical name for the Object Type
         * 
         * @return the canonical name for the object type
         */
        public String gitoidName() {
            switch (this) {
                case Blob:
                    return "blob";
                case Tree:
                    return "tree";
                case Commit:
                    return "commit";
                case Tag:
                    return "tag";
                default:
                    throw new RuntimeException("Cannot find name for " + this);
            }
        }
    };

    /**
     * The hash type
     */
    public enum HashType {
        SHA1, SHA256;

        /**
         * Based on the hash type, get the MessageDigest
         * 
         * @return the MessageDigest for the type
         */
        public MessageDigest getDigest() {
            try {
                switch (this) {
                    case SHA1:
                        return MessageDigest.getInstance("SHA-1");
                    case SHA256:
                        return MessageDigest.getInstance("SHA-256");
                    default:
                        throw new RuntimeException("Unable to create digest for unknown type " + this);
                }
            } catch (NoSuchAlgorithmException nse) {
                throw new RuntimeException("Could not find algorithm for " + this, nse);
            }
        }

        /**
         * Get the canonical name for the hash type
         * 
         * @return the canonical name
         */
        public String hashTypeName() {
            switch (this) {
                case SHA1:
                    return "sha1";
                case SHA256:
                    return "sha256";
                default:
                    throw new RuntimeException("Unable to get name for " + this);
            }
        }
    }

    /**
     * Given an object type, a pile of bytes, and a hash type, compute the GitOID
     * 
     * @param hashType the hash type
     * @param type     the type of object
     * @param bytes    the bytes to compute the gitoid for
     * @return the gitoid
     */
    public static byte[] computeGitOID(HashType hashType, ObjectType type, byte[] bytes) {
        // get the prefix bytes in local encoding... which should be okay given that
        // the string should be ASCII
        byte[] prefix = String.format("%s %d\0", type.gitoidName(), bytes.length).getBytes();
        MessageDigest md = hashType.getDigest();
        md.update(prefix);

        return md.digest(bytes);
    }

    final ObjectType type;
    final HashType hashType;
    final byte[] bytes;

    public static String toHex(byte[] bytes) {
	   BigInteger bi = new BigInteger(1, bytes);
	  return bi.toString(16); 
    }

    /**
     * Construct a new GitOID instance
     * 
     * @param hashType the hash type
     * @param type     the object type
     * @param bytes    the bytes the represent the object
     */
    public GitOID(HashType hashType, ObjectType type, byte[] bytes) {
        this.type = type;
        this.hashType = hashType;
        this.bytes = bytes;
    }

    /**
     * Compute the `gitoid`
     * 
     * @return the gitoid
     */
    public byte[] gitOid() {
        return computeGitOID(hashType, type, bytes);
    }

    /**
     * Get the `gitoid` as a hexidecimal string
     * 
     * @return the gitoid
     */
    public String gitOidAsHex() {
        return toHex(gitOid()); // HexFormat.of().formatHex(gitOid());
    }

    /**
     * Compute the hash for the object given the hashing algorithm. Why is this
     * different
     * from the `gitoid`? The bytes hashed for a `gitoid` include the object type
     * and the
     * length of bytes being hashed. This is just a hash of the raw bytes.
     * 
     * @return the hash of the bytes
     */
    public byte[] hash() {
        return hashType.getDigest().digest(bytes);
    }

    /**
     * The value returned by `hash()` formatted as a hex string
     * 
     * @return The hex representing the hash of the bytes
     */
    public String hashAsHex() {
        return toHex(hash()); // HexFormat.of().formatHex(hash());
    }

    /**
     * The String representing the hash
     */
    @Override
    public String toString() {
        return String.format("%s:%s", this.hashType.hashTypeName(), gitOidAsHex());
    }

    /**
     * A `gitoid` URL. See https://www.iana.org/assignments/uri-schemes/prov/gitoid
     * 
     * @return the `gitoid` URL 
     */
    public String url() {
        return String.format("gitoid:%s:%s:%s", this.type.gitoidName(), this.hashType.hashTypeName(), gitOidAsHex());
    }

}
