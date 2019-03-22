/*
    Convert linux file path to a relative path.

    Input: "/home/"
    Output: "/home"
    Explanation: Note that there is no trailing slash after the last directory name.
    Example 2:

    Input: "/../"
    Output: "/"
    Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
    Example 3:

    Input: "/home//foo/"
    Output: "/home/foo"
    Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
    Example 4:

    Input: "/a/./b/../../c/"
    Output: "/c"
    Example 5:

    Input: "/a/../../b/../c//.//"
    Output: "/c"
    Example 6:

    Input: "/a//b////c/d//././/.."
    Output: "/a/b/c"
*/
class Solution {
    public String simplifyPath(String path) {
        String[] parts = path.split("/+");
        List<String> res = new ArrayList<>();

        for (String part : parts) {
            if (part.equals(".") || part.trim().equals("")) {
                continue;
            }else if (part.equals("..")) {
                if (res.size() == 0) {
                    continue;
                }
                res.remove(res.size() - 1);
            }else {
                res.add(part);
            }
        }

        StringBuilder str = new StringBuilder();
        for(String part : res){
            str.append("/");
            str.append(part);
        }
        if(str.length() == 0){
            str.append("/");
        }
        return str.toString();
    }
}