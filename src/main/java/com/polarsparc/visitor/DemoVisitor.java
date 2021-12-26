/*
 * Description: Demo of the Visitor Pattern
 * Author:      Bhaskar S
 * Date:        12/25/2021
 * Blog:        https://www.polarsparc.com
 */

package com.polarsparc.visitor;

public class DemoVisitor {
    public static void main(String[] args) {
        FieldNode fn = new FieldNode("name", "string");
        MethodNode mn = new MethodNode("greet");
        Visitor visitor = new NodeVisitor();
        fn.accept(visitor);
        mn.accept(visitor);
    }
}
