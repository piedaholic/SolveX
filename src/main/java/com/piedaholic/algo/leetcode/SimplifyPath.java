package com.piedaholic.algo.leetcode;

/**
 * You are given an absolute path for a Unix-style file system, which always begins with a slash
 * '/'. Your task is to transform this absolute path into its simplified canonical path.
 *
 * <p>The rules of a Unix-style file system are as follows:
 *
 * <p>A single period '.' represents the current directory. A double period '..' represents the
 * previous/parent directory. Multiple consecutive slashes such as '//' and '///' are treated as a
 * single slash '/'. Any sequence of periods that does not match the rules above should be treated
 * as a valid directory or file name. For example, '...' and '....' are valid directory or file
 * names.
 *
 * <p>The simplified canonical path should follow these rules:
 *
 * <p>The path must start with a single slash '/'. Directories within the path must be separated by
 * exactly one slash '/'. The path must not end with a slash '/', unless it is the root directory.
 * The path must not have any single or double periods ('.' and '..') used to denote current or
 * parent directories.
 *
 * <p>Return the simplified canonical path. *
 */
public class SimplifyPath {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String result = solution.simplifyPath("/../..ga/.");
        System.out.println(result);
    }

    static class Solution {
        public String simplifyPath(String path) {
            StringBuilder component = new StringBuilder();
            Path current = null;

            for (int i = 1; i <= path.length(); i++) {
                char c = i < path.length() ? path.charAt(i) : (char) -1;
                if (c == '/' || c == (char) -1) {
                    if (!component.isEmpty()) {
                        if (component.toString().equals(".")) {}
                        else if (component.toString().equals("..")) {
                            current = current == null ? null : current.parent;
                        } else {
                            current = new Path(component.toString(), current);
                        }

                        component = new StringBuilder();
                    }
                } else {
                    component.append(c);
                }
            }

            return current == null ? "/" : current.build();
        }

        static class Path {
            String name;
            Path parent;

            public Path(String name, Path parent) {
                this.name = name;
                this.parent = parent;
            }

            public String build() {
                StringBuilder result = new StringBuilder(this.name);
                Path current = this.parent;

                while (true) {
                    if (current == null) {
                        result.insert(0, "/");
                        break;
                    } else {
                        result.insert(0, current.name + "/");
                        current = current.parent;
                    }
                }

                return result.toString();
            }
        }
    }
}
