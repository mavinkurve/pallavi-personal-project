import java.util.HashMap;

public class VersionControl {

    private HashMap<Integer,Boolean> versions;

    boolean isBadVersion(int n) {
        return versions.get(n);
    }

    public VersionControl() {
        this.versions = new HashMap<Integer, Boolean>();
    }

    public VersionControl setVersions(HashMap<Integer,Boolean> versions) {
        this.versions = versions;
        return this;
    }
}
