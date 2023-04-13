package org.bomsage;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GitOIDTest extends TestCase {

    public static Test suite() {
        return new TestSuite(GitOIDTest.class);
    }

    public void superBasicTestSha1() {
        byte[] sha1Hash = GitOID.computeGitOID(GitOID.HashType.SHA1, GitOID.ObjectType.Blob, "hello world".getBytes());
        assertEquals(sha1Hash.length, 20);
        assertEquals(sha1Hash[0] + 256, 0x95);
    }

    public void testSha256FromBuffer() {
        GitOID result = new GitOID(GitOID.HashType.SHA256, GitOID.ObjectType.Blob, "hello world".getBytes());
        assertEquals(
                result.gitOidAsHex(),
                "fee53a18d32820613c0527aa79be5cb30173c823a9b448fa4817767cc84c6f03");

        assertEquals(
                result.toString(),
                "sha256:fee53a18d32820613c0527aa79be5cb30173c823a9b448fa4817767cc84c6f03");

        assertEquals(
                result.url(),
                "gitoid:blob:sha256:fee53a18d32820613c0527aa79be5cb30173c823a9b448fa4817767cc84c6f03");
    }

    public void testSha1FromBuffer() {

        GitOID result = new GitOID(GitOID.HashType.SHA1, GitOID.ObjectType.Blob, "hello world".getBytes());
        assertEquals(
                result.gitOidAsHex(),
                "95d09f2b10159347eece71399a7e2e907ea3df4f");

        assertEquals(
                result.toString(),
                "sha1:95d09f2b10159347eece71399a7e2e907ea3df4f");

    }
}
