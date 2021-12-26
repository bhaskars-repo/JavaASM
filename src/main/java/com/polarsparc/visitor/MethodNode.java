/*
 * Description: Simple class that represents a Method
 * Author:      Bhaskar S
 * Date:        12/25/2021
 * Blog:        https://www.polarsparc.com
 */

package com.polarsparc.visitor;

public class MethodNode {
    private final String name;

    public MethodNode(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
