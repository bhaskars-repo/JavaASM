/*
 * Description: Simple class that represents a Field
 * Author:      Bhaskar S
 * Date:        12/25/2021
 * Blog:        https://www.polarsparc.com
 */

package com.polarsparc.visitor;

public class FieldNode {
    private final String name;
    private final String type;

    public FieldNode(String n, String t) {
        this.name = n;
        this.type = t;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
