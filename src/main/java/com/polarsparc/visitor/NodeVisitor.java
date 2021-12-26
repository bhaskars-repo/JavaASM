/*
 * Description: Concrete class that implements Visitor to perform print operation
 * Author:      Bhaskar S
 * Date:        12/25/2021
 * Blog:        https://www.polarsparc.com
 */

package com.polarsparc.visitor;

public class NodeVisitor implements Visitor {
    @Override
    public void visit(FieldNode node) {
        System.out.printf("FIELD: Name: %s, Type: %s\n", node.getName(), node.getType());
    }

    @Override
    public void visit(MethodNode node) {
        System.out.printf("METHOD: Name: %s\n", node.getName());
    }
}
