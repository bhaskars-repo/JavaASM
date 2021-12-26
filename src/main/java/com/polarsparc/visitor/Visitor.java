/*
 * Description: Interface that indicates the various operations
 * Author:      Bhaskar S
 * Date:        12/25/2021
 * Blog:        https://www.polarsparc.com
 */

package com.polarsparc.visitor;

public interface Visitor {
    void visit(FieldNode node);
    void visit(MethodNode node);
}
