public class BadVersions extends VersionControl {

    public int firstBadVersion (int n) {
        return searchBadVersion(1,n);
    }

    private int searchBadVersion(int begin, int end) {

        if (end < begin)
            return -1;
        if (begin == end) {
            if (isBadVersion(begin))
                return begin;
            return -1;
        }
        int mid = begin + (end - begin)/2;
        final boolean midIsBad = isBadVersion(mid);
        final boolean midPlusIsBad = isBadVersion(mid + 1);
        if (!midIsBad && midPlusIsBad)
            return mid + 1;
        if (midIsBad && midPlusIsBad)
            return searchBadVersion(begin,mid);
        if (!midIsBad && !midPlusIsBad)
            return searchBadVersion(mid+1,end);
        return -1;
    }

}
